package com.taal.welding.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.taal.welding.R;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.DeviceClass;
import com.taal.welding.model.NewDevice;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class CalibrationActivity extends TabActivity {

    private TabHost TabHostWindow;
    private DatabaseHelper db;
    private List<DeviceClass> newList;
    private String device;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);
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
        //Assign id to Tabhost.
        TabHostWindow = (TabHost)findViewById(android.R.id.tabhost);

        //Creating tab menu.
        TabSpec TabMenu1 = TabHostWindow.newTabSpec("GearBox");
        TabSpec TabMenu2 = TabHostWindow.newTabSpec("Sensor");

        //Setting up tab 1 name.
        TabMenu1.setIndicator("GearBox");
        //Set tab 1 activity to tab 1 menu.
        TabMenu1.setContent(new Intent(this, Motor_GairBoxActivity.class));

        //Setting up tab 2 name.
        TabMenu2.setIndicator("Sensor");
        //Set tab 3 activity to tab 1 menu.
        TabMenu2.setContent(new Intent(this, SensorActivity.class));


        //Adding tab1, tab2, tab3 to tabhost view.

        TabHostWindow.addTab(TabMenu1);
        TabHostWindow.addTab(TabMenu2);

    }
}
