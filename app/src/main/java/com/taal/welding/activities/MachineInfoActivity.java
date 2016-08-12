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
    @Bind(R.id.checkbox1)
    protected CheckBox checkBox1;
    @Bind(R.id.checkbox2)
    protected CheckBox checkBox2;
    @Bind(R.id.checkbox3)
    protected CheckBox checkBox3;
    @Bind(R.id.selectCheck)
    protected CheckBox checkAll;
    @Bind(R.id.ipValue1)
    protected TextView ip1;
    @Bind(R.id.devicenameValue1)
    protected TextView device1;
    @Bind(R.id.spinner1)
    protected TextView sp1;
    @Bind(R.id.ipValue2)
    protected TextView ip2;
    @Bind(R.id.devicenameValue2)
    protected TextView device2;
    @Bind(R.id.spinner2)
    protected TextView sp2;
    @Bind(R.id.ipValue3)
    protected TextView ip3;
    @Bind(R.id.devicenameValue3)
    protected TextView device3;
    @Bind(R.id.spinner3)
    protected TextView sp3;
    @Bind(R.id.ipAddress)
    protected TextView ipAddress;
    @Bind(R.id.devicename)
    protected TextView devicename;
    @Bind(R.id.submit)
    protected TextView oprn;
    @Bind(R.id.connection)
    protected TextView connectionText;

    private MachineInfoList mMachineList;
    public MachineInfoTemp1Adapter machineInfoAdapter;
    private DatabaseHelper databaseHelper;
    private List<DeviceClass> mList;
    public static StringBuilder s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_info);
        ButterKnife.bind(this);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
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
