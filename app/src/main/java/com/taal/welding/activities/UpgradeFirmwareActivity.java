package com.taal.welding.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.DeviceClass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpgradeFirmwareActivity extends AppCompatActivity {

    @Bind(R.id.upgradeBut)
    protected Button upgrade;
    @Bind(R.id.firm1Button)
    protected Button m1browse;
    @Bind(R.id.firm2Button)
    protected Button m2browse;
    @Bind(R.id.firm3Button)
    protected Button m3browse;

    @Bind(R.id.firm1Path)
    protected TextView m1Path;
    @Bind(R.id.firm2Path)
    protected TextView m2Path;
    @Bind(R.id.firm3Path)
    protected TextView m3Path;

    @Bind(R.id.selectCheck)
    protected CheckBox checkAll;
    @Bind(R.id.firm1)
    protected CheckBox mfirm1;
    @Bind(R.id.firm2)
    protected CheckBox mFirm2;
    @Bind(R.id.firm3)
    protected CheckBox mFirm3;

    @Bind(R.id.progress)
    protected ProgressBar progressBar;
    private static final int REQUEST_PICK_FILE = 1;
    private File selectedFile;
    private String status = null;
    private List<DeviceClass> mList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_firmware);
        ButterKnife.bind(this);

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
            try{
                getSupportActionBar().setTitle("BLDC PoC");

            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        databaseHelper = new DatabaseHelper(this);

        mList =  new ArrayList<DeviceClass>();
        mList = databaseHelper.getAllNewDevices();
        System.out.println("mList size is " + mList.size());


        try {
            if(!mList.isEmpty()) {
                checkAll.setVisibility(View.VISIBLE);
                if(!mList.get(0).getIp().isEmpty()){
                    mfirm1.setVisibility(View.VISIBLE);
                    mfirm1.setText(mList.get(0).getDevice());
                    m1browse.setVisibility(View.VISIBLE);
                }
                if(!mList.get(1).getIp().isEmpty()){
                    mFirm2.setVisibility(View.VISIBLE);
                    mFirm2.setText(mList.get(1).getDevice());
                    m2browse.setVisibility(View.VISIBLE);
                }
                if(!mList.get(2).getIp().isEmpty()) {
                    mFirm3.setVisibility(View.VISIBLE);
                    mFirm3.setText(mList.get(2).getDevice());
                    m3browse.setVisibility(View.VISIBLE);
                }

                //newDeviceList.addAll(mList);
            }
            if(mList.isEmpty()){
                Toast.makeText(getApplicationContext(), "No device in Database", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            System.out.println("jhjh " + e.toString());
        }
        m1browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.firm1Button:
                        status = "firm1";
                        Intent intent = new Intent(getApplicationContext(), FilePicker.class);
                        startActivityForResult(intent, REQUEST_PICK_FILE);
                        break;
                }
            }
        });
        m2browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.firm2Button:
                        status = "firm2";
                        Intent intent = new Intent(getApplicationContext(), FilePicker.class);
                        startActivityForResult(intent, REQUEST_PICK_FILE);
                        break;
                }
            }
        });
        m3browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.firm3Button:
                        status = "firm3";
                        Intent intent = new Intent(getApplicationContext(), FilePicker.class);
                        startActivityForResult(intent, REQUEST_PICK_FILE);
                        break;
                }
            }
        });

        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.postDelayed(new Runnable() {
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        finish();
                    }
                }, 3000);

            }
        });

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox chk = (CheckBox) v;
                if(chk.isChecked()) {
                    mfirm1.setChecked(true);
                    mFirm2.setChecked(true);
                    mFirm3.setChecked(true);
                } else {
                    mfirm1.setChecked(false);
                    mFirm2.setChecked(false);
                    mFirm3.setChecked(false);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            case R.id.action_name:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            switch(requestCode) {
                case REQUEST_PICK_FILE:
                    if(data.hasExtra(FilePicker.EXTRA_FILE_PATH)) {
                        selectedFile = new File(data.getStringExtra(FilePicker.EXTRA_FILE_PATH));
                        if(status == "firm1") {
                            m1Path.setText(selectedFile.getPath());
                        } else if(status == "firm2"){
                            m2Path.setText(selectedFile.getPath());
                        } else if(status == "firm3"){
                            m3Path.setText(selectedFile.getPath());
                        }

                    }
                break;
            }
        }
    }
}
