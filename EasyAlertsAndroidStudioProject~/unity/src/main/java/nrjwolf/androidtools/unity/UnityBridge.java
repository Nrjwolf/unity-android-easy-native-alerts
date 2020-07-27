package nrjwolf.androidtools.unity;
import android.os.Handler;

public class UnityBridge {
    private static JavaMessageHandler javaMessageHandler;
    private static Handler unityMainThreadHandler;

    public static void registerMessageHandler(JavaMessageHandler handler) {
        javaMessageHandler = handler;
        if (unityMainThreadHandler == null) {
            unityMainThreadHandler = new Handler();
        }
    }

    public static void runOnUnityThread(Runnable runnable) {
        if(unityMainThreadHandler != null && runnable != null) {
            unityMainThreadHandler.post(runnable);
        }
    }

    public static void sendMessageToUnity(final String buttonId) {
        runOnUnityThread(new Runnable() {

            @Override
            public void run() {
                if (javaMessageHandler != null) {
                    javaMessageHandler.onButtonClicked(buttonId);
                }
            }
        });
    }
}
