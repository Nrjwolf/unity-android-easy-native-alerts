using System;
using System.Linq;
using UnityEngine;

namespace Nrjwolf.Tools.AndroidEasyAlerts
{
    public enum ButtonStyle
    {
        POSITIVE = -1,
        NEGATIVE = -2,
        NEUTRAL = -3,
    }

    public class AlertButton
    {
        public Nrjwolf.Tools.AndroidEasyAlerts.ButtonStyle Style;
        public string Title;
        public Action Callback;
        internal string m_Id;

        public AlertButton(string title, Action callback, ButtonStyle style = ButtonStyle.NEUTRAL)
        {
            Title = title;
            Callback = callback;
            Style = style;
        }
    }

#if UNITY_ANDROID && !UNITY_EDITOR
    public class JavaMessageHandler : AndroidJavaProxy
    {
        private JavaMessageHandler() : base("nrjwolf.androidtools.unity.JavaMessageHandler") { }
        public void onButtonClicked(string buttonId)
        {
            var buttons = AndroidEasyAlerts.m_CurrentAlertButtons;
            if (buttons == null || buttons.Length == 0) return;

            Debug.Log($"Clicked {buttonId}");
            foreach (var alertButton in buttons)
            {
                if (alertButton.m_Id == buttonId)
                {
                    alertButton.Callback?.Invoke();
                    break;
                }
            }
        }

        [RuntimeInitializeOnLoadMethod]
        private static void Initialize()
        {
            new AndroidJavaClass("nrjwolf.androidtools.unity.UnityBridge").CallStatic("registerMessageHandler", new JavaMessageHandler());
        }
    }
#endif

    public class AndroidEasyAlerts
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        private const string k_PluginNativeAlerts = "nrjwolf.androidtools.unity.NativeAlerts";
        private static AndroidJavaClass m_PluginClass;
        private static AndroidJavaObject m_PluginInstance;

        private static AndroidJavaClass PluginClass { get { return m_PluginClass != null ? m_PluginClass : m_PluginClass = new AndroidJavaClass(k_PluginNativeAlerts); } }
        private static AndroidJavaObject PluginInstance { get { return m_PluginInstance != null ? m_PluginInstance : m_PluginInstance = PluginClass.CallStatic<AndroidJavaObject>("getInstance"); } }

        internal static AlertButton[] m_CurrentAlertButtons;

        public static void ShowAlert(string title, string message, params AlertButton[] buttons)
        {
            var buttonTitles = buttons.Select(x => x.Title).ToArray();
            var buttonStyles = buttons.Select(x => (int)x.Style).ToArray();
            PluginInstance.Call("createAlert", title, message, buttonTitles, buttonStyles);

            // create unique button id (same method as in .aar)
            for (int i = 0; i < buttons.Length; i++)
            {
                var item = buttons[i];
                item.m_Id = item.Title + i;
            }

            // cache current buttons
            m_CurrentAlertButtons = buttons;
        }

        public static void ShowToast(string text, bool isLongDuration = false) => PluginInstance.Call("showToast", text, isLongDuration);
#else
        public static void ShowAlert(string title, string message, params AlertButton[] buttons) { }
        public static void ShowToast(string text, bool isLongDuration = false) { }
#endif
    }
}
