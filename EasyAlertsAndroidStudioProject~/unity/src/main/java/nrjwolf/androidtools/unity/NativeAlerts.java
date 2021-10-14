package nrjwolf.androidtools.unity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;

public class NativeAlerts {
    private static final NativeAlerts ourInstance = new NativeAlerts();
    public static NativeAlerts getInstance() {
        return ourInstance;
    }
    private static final String PLUGIN_TAG = "EasyNativeAlerts";
    public static Activity getUnityActivity() {
        return UnityPlayer.currentActivity;
    }
    public NativeAlerts() {
        Log("plugin started");
    }
    private void Log(String message) {
        Log.i(PLUGIN_TAG, message);
    }

    public boolean isCanceledOnTouchOutside = true;

    public void createAlert(String title, String message, String[] buttonTitles, int[] buttonStyles) {
        AlertDialog alertDialog = new AlertDialog.Builder(getUnityActivity()).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);

        // Add buttons
        for (int i = 0; i < buttonTitles.length; i++) {
            final String id = buttonTitles[i] + i; // unique id
            final int buttonStyle = buttonStyles[i]; // style

            alertDialog.setButton(buttonStyle, buttonTitles[i],
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            UnityBridge.sendMessageToUnity(id); // send id to unity
                        }
                    });
        }
        alertDialog.show();
    }

    public void showToast (String message, boolean isLong ) {
        Toast.makeText(getUnityActivity(), message, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}
