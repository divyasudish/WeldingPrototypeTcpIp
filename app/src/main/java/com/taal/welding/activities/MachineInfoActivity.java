package com.taal.welding.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.adapter.MachineInfoTemp1Adapter;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.DeviceClass;
import com.taal.welding.model.MachineInfoClass;
import com.taal.welding.model.MachineInfoList;
import com.taal.welding.model.NewDevice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MachineInfoActivity extends AppCompatActivity {
    @Bind(R.id.save)
    protected Button mSave;
    private MachineInfoList mMachineList;
    private ArrayList<DeviceClass> mArrayList;
    private Intent mIntent;
    private String mTitle;
    private String mProgressData;
    public MachineInfoTemp1Adapter machineInfoAdapter;
    private DatabaseHelper databaseHelper;
    private List<DeviceClass> mList;
    private CheckBox checkBox1;
    private TextView ip1;
    private TextView device1;
    private TextView sp1;

    private CheckBox checkBox2;
    private TextView ip2;
    private TextView device2;
    private TextView sp2;

    private CheckBox checkBox3;
    private TextView ip3;
    private TextView device3;
    private TextView sp3;

    private TextView ipAddress;
    private TextView devicename;
    private TextView oprn;

    private CheckBox checkAll;
    private TextView connectionText;
    public static StringBuilder s;
    public static String s_append;
    private String []x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_info);
        connectionText = (TextView) findViewById(R.id.connection);

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
        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);
        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        ip1 = (TextView) findViewById(R.id.ipValue1);
        device1 = (TextView) findViewById(R.id.devicenameValue1);
        sp1 = (TextView) findViewById(R.id.spinner1);

        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        ip2 = (TextView) findViewById(R.id.ipValue2);
        device2 = (TextView) findViewById(R.id.devicenameValue2);
        sp2 = (TextView) findViewById(R.id.spinner2);

        checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        ip3 = (TextView) findViewById(R.id.ipValue3);
        device3 = (TextView) findViewById(R.id.devicenameValue3);
        sp3 = (TextView) findViewById(R.id.spinner3);

        checkAll = (CheckBox) findViewById(R.id.selectCheck);
        ipAddress = (TextView) findViewById(R.id.ipAddress);
        devicename = (TextView) findViewById(R.id.devicename);
        oprn = (TextView) findViewById(R.id.submit);


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
        mMachineList = new MachineInfoList();
        mList =  new ArrayList<DeviceClass>();
        mList = databaseHelper.getAllNewDevices();
        System.out.println("mList size is " + mList.size());


        try {
            if(!mList.isEmpty()) {
                checkAll.setVisibility(View.VISIBLE);
                ipAddress.setVisibility(View.VISIBLE);
                devicename.setVisibility(View.VISIBLE);
                oprn.setVisibility(View.VISIBLE);
                mSave.setVisibility(View.VISIBLE);
                if(!mList.get(0).getIp().isEmpty()){
                    checkBox1.setVisibility(View.VISIBLE);
                    checkBox1.setChecked(true);
                    ip1.setText(mList.get(0).getIp());
                    device1.setText(mList.get(0).getDevice());
                    sp1.setText(mList.get(0).getOperation());
                }
                if(!mList.get(1).getIp().isEmpty()){
                    checkBox2.setChecked(true);
                    checkBox2.setVisibility(View.VISIBLE);
                    ip2.setText(mList.get(1).getIp());
                    device2.setText(mList.get(1).getDevice());
                    sp2.setText(mList.get(1).getOperation());
                }
                if(!mList.get(2).getIp().isEmpty()){
                    checkBox3.setVisibility(View.VISIBLE);
                    checkBox3.setChecked(true);
                    ip3.setText(mList.get(2).getIp());
                    device3.setText(mList.get(2).getDevice());
                    sp3.setText(mList.get(2).getOperation());
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

        mMachineList.setMachineInfoClassList(mList);
        machineInfoAdapter = new MachineInfoTemp1Adapter(MachineInfoActivity.this, mList);

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox chk = (CheckBox) v;
                if(chk.isChecked()) {
                    checkBox1.setChecked(true);
//                    checkBox2.setChecked(true);
//                    checkBox3.setChecked(true);
                } else {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
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
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @OnClick(R.id.save)
    protected void Save() {
        try {

            if(checkBox1.isChecked()){
                databaseHelper.create_M_NewDevice(new DeviceClass(ip1.getText().toString().trim(), device1.getText().toString().trim(), sp1.getText().toString().trim()));
            }
            if(checkBox2.isChecked()){
                databaseHelper.createNewDevice(new DeviceClass(ip2.getText().toString().trim(), device2.getText().toString().trim(), sp2.getText().toString().trim()));
            }
            if(checkBox3.isChecked()){
                databaseHelper.createNewDevice(new DeviceClass(ip3.getText().toString().trim(), device3.getText().toString().trim(), sp3.getText().toString().trim()));
            }
            if(checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked()){
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
