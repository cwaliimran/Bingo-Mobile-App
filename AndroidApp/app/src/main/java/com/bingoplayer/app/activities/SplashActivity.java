package com.bingoplayer.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.bingoplayer.app.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(getResources().getColor(R.color.transparent));
        setContentView(R.layout.activity_splash);
        context = this;
        timer = new Timer();
        if (!isNetworkAvailable()) {
            new AlertDialog.Builder(SplashActivity.this)
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Alert!")
                    .setMessage("No Internet Connection,check your settings")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .show();
        } else {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    gotoActivity(HomeActivity.class);
                    finishAffinity();
                }
            }, 2000);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}