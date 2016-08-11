package com.taal.welding.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taal.welding.R;

public class SplashScreenActivity extends Activity {
    //Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(Color.BLACK);
        if ((Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT))
        {
            //statusbar.setVisibility(View.VISIBLE);
        }
        else
        {
            window.setStatusBarColor(Color.BLACK);
        }
        ImageView img_animation = (ImageView) findViewById(R.id.crc_logo);
        //TextView img_logo2 = (TextView) findViewById(R.id.logo_1);

//        TranslateAnimation animation = new TranslateAnimation(0.0f, 400.0f,
//                0.0f, 0.0f);
//        animation.setDuration(5000);
//        animation.setRepeatCount(5);
//        animation.setRepeatMode(2);
//        animation.setFillBefore(true);
//        img_animation.startAnimation(animation);
//        StartAnimations();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_translate);
        img_animation.startAnimation(animation);

//        Animation animation_bottom = AnimationUtils.loadAnimation(this, R.anim.rotate_translate_bottom);
//        img_logo2.startAnimation(animation_bottom);
        startSplashTimer();
    }

//    private void StartAnimations() {
//        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
//        anim.reset();
//        ImageView iv = (ImageView) findViewById(R.id.logo);
//        iv.clearAnimation();
//        iv.startAnimation(anim);
//    }
    private void startSplashTimer() {
        new AsyncTask<Void,Void,Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try{
                    Thread.sleep(1700);
                } catch (Exception e){

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }.execute();
    }
}
