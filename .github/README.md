# Unity Android Easy Alerts

### Installation

Add this as a package to your project by adding the below as an entry to the dependencies in the `/Packages/manifest.json` file:

```json
"nrjwolf.games.easyandroidalerts": "https://github.com/Nrjwolf/unity-android-easy-native-alerts.git"
```
For more information on adding git repositories as a package see the [Git support on Package Manager](https://docs.unity3d.com/Manual/upm-git.html) in the Unity Documentation.

### Example
``` c#
using System.Collections;
using System.Collections.Generic;
using Nrjwolf.Tools.AndroidEasyAlerts;
using UnityEngine;

public class EasyNativeAlertsExample : MonoBehaviour
{
    void OnGUI()
    {
        GUI.matrix = Matrix4x4.Scale(new Vector3(3.5f, 3.5f, 3.5f));
        GUILayout.Label($"Easy native alerts");

        if (GUILayout.Button("Call Alert"))
        {
            AndroidEasyAlerts.ShowAlert("Title", "Your message",
            new AlertButton("IDK", () => AndroidEasyAlerts.ShowToast("Clicked IDK", false), ButtonStyle.NEUTRAL),
            new AlertButton("Cancel", () => AndroidEasyAlerts.ShowToast("Clicked Cancel", false), ButtonStyle.NEGATIVE),
            new AlertButton("Ok", () => AndroidEasyAlerts.ShowToast("Clicked Ok", false), ButtonStyle.POSITIVE)
            );
        }
        else if (GUILayout.Button("Show long toast"))
        {
            AndroidEasyAlerts.ShowToast("long toast", true);
        }
        else if (GUILayout.Button("Show short toast"))
        {
            AndroidEasyAlerts.ShowToast("short toast", false);
        }
    }
}
```

![](https://github.com/Nrjwolf/unity-android-easy-native-alerts/blob/master/.github/images/screenshot.png "Example") </br>

For IOS check another my plugin https://github.com/Nrjwolf/unity-ios-easy-native-alert <br>
Do not forgot to ⭐️ it.

---

>Telegram : https://t.me/nrjwolf_live <br>
>Discord : https://discord.gg/jwPVsat <br>
>Reddit : https://www.reddit.com/r/Nrjwolf/ <br>
>Twitter : https://twitter.com/nrjwolf <br>
