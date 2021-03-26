package com.bingoplayer.app.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public Context context;
    Intent intent;
    //random id
    public String randomId;
    //intent value
    String comeFrom;
    //check order for payment
    boolean isCustomOrder = false;
    //order now
    String chat_id, order_type, service_id, status, user_id, title, description, orderNum;
    String created_at;
    //check if user is from order chat then load chat against order otherwiswe load general chats (which was created by clicking on chat icon on home services
    boolean isOrderChat = false;
    String order_id, message, orderDetails, created_at_time, created_at_date;
    int price;


    //

    protected void gotoActivity(Class activity) {
        intent = new Intent(context, activity);
        startActivity(intent);
    }

    protected void fullScreen(int colorRes) {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(colorRes);
        }
    }
}
