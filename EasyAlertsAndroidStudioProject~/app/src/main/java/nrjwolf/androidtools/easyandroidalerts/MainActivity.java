package nrjwolf.androidtools.easyandroidalerts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import nrjwolf.androidtools.unity.NativeAlerts;

public class MainActivity extends AppCompatActivity {

    NativeAlerts NativeAlerts;

    Button alertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create NativeAlerts
        NativeAlerts = new NativeAlerts();

        // get buttons
        alertButton = (Button) findViewById(R.id.btnalert);

        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("App", NativeAlerts.getMessage());

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Alert message to be shown");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }
}
