package com.chriscel.splashscreenloadingbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    /* Initialize String TAG for Log */
    private static final String TAG = "SplashScreen";

    /* Progress Bar Initialize */
    ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /* Hide Action Bar when Splash Screen Running */
        getSupportActionBar().hide();

        /* Casting Progress Bar from activity_splash.xml */
        progressBarLoading = (ProgressBar) findViewById(R.id.progressbar_loading);

        /* Set Maximum Values for Progress Bar to 100 Percents */
        progressBarLoading.setMax(100);

        /* Set Default Value for Progress Bar to 0 Percents */
        progressBarLoading.setProgress(0);

        /* Create a new Thread for Count the Splash Screen's time(s) */
        Thread timerThread = new Thread(){
            public void run(){
                try {
                    /* Create For loop to increase the Progress Bar value by 1 */
                    for(int i = 0; i < 100; i++) {
                        /* Set the Progress Bar value +1 */
                        progressBarLoading.setProgress(i);

                        /* Set the time(s) to 30 miliseconds
                        *  It means the Progress Bar will increase from 0 to 100 in 3 seconds
                        */
                        sleep(30); //3 seconds
                    }
                } catch(InterruptedException e){
                    /* Create a Log if the Try section is failed
                    *  It will create a Error Log and Display the message to Logcat
                    *  (You can see the Error Log by open the Logcat from Bottom-Left menu and change "Verbose" to "Error")
                    * */
                    Log.e(TAG, "Splash Screen Failed because : " + e);
                } finally{
                    /* Start the Main Activity
                    *  If the codes from Try section is success
                    * */
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                    /* Finish the Splash Activity
                      * This code use to close the Splash Activity
                      * So when you press Back button from Main Activity, the Splash Activity will never show again
                    */
                    finish();
                }
            }
        };

        /* Start the Thread */
        timerThread.start();
    }
}
