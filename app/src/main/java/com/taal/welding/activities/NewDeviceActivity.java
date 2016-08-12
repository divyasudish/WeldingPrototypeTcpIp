package com.taal.welding.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.adapter.DeviceInfoAdapter;
import com.taal.welding.adapter.MachineAdapter;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.DeviceClass;
import com.taal.welding.model.NewDevice;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewDeviceActivity extends AppCompatActivity {
    @Bind(R.id.sub)
    protected Button submit;
    @Bind(R.id.checkbox1)
    protected CheckBox checkBox1;
//    @Bind(R.id.checkbox2)
//    protected CheckBox checkBox2;
//    @Bind(R.id.checkbox3)
//    protected CheckBox checkBox3;
    @Bind(R.id.ipValue1)
    protected TextView ip1;
    @Bind(R.id.devicenameValue1)
    protected TextView device1;
    @Bind(R.id.spinner1)
    protected Spinner sp1;
//    @Bind(R.id.ipValue2)
//    protected TextView ip2;
//    @Bind(R.id.devicenameValue2)
//    protected TextView device2;
//    @Bind(R.id.spinner2)
//    protected Spinner sp2;
//    @Bind(R.id.ipValue3)
//    protected TextView ip3;
//    @Bind(R.id.devicenameValue3)
//    protected TextView device3;
//    @Bind(R.id.spinner3)
//    protected Spinner sp3;

    private ArrayAdapter<String> adapter;
    private List<String> list;
    private List<String> list1;

    private DatabaseHelper db;
    private List<NewDevice> newList;
    private Button createEdit;
    Boolean flag = false;
    //private ListView listView;
    private ArrayList<DeviceClass> newDeviceList;
    private List<DeviceClass> mList;
    private DeviceInfoAdapter deviceInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);
        ButterKnife.bind(this);
        db = new DatabaseHelper(getApplicationContext());
        newDeviceList = new ArrayList<>();

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

        ip1.setText("192.168.0.22");
        device1.setText("Device 1");
//        ip2.setText("192.168.0.102");
//        device2.setText("Device 2");
//        ip3.setText("192.168.0.103");
//        device3.setText("Device3");
        list = new ArrayList<String>();
        list.add("BLDC Motor");
        list.add("IMU");
        list.add("Relative encoder");
        list.add("Absolute encoder");
        list.add("Digital Input");
        list.add("Proximity Input");
        list.add("Solenoid");
        list.add("All");
        list.add("None");
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text, list);
        sp1.setAdapter(ad);
//        sp2.setAdapter(ad);
//        sp3.setAdapter(ad);
        list1 = new ArrayList<>();
        try {
            mList = db.getAllNewDevices();
            if(mList.isEmpty()) {
                ip1.setText("192.168.0.22");
                device1.setText("Crc_Evans");
//                ip2.setText("192.168.0.102");
//                device2.setText("Device 2");
//                ip3.setText("192.168.0.103");
//                device3.setText("Device3");
//                newDeviceList.add(new DeviceClass("192.168.0.101", "Device1 ", "Submit"));
//                newDeviceList.add(new DeviceClass("192.168.0.102", "Device2 ", "Submit"));
//                newDeviceList.add(new DeviceClass("192.168.0.103", "Device3 ", "Submit"));
            }
            else if(!mList.isEmpty()) {
                if(mList.get(0).getIp().isEmpty()){
                    checkBox1.setChecked(false);
                    ip1.setText("192.168.0.22");
                    device1.setText("Crc_Evans");
                }
                else if(!mList.get(0).getIp().isEmpty()) {
                    checkBox1.setChecked(true);
                    ip1.setText(mList.get(0).getIp());
                    device1.setText(mList.get(0).getDevice());
                }
//                if(mList.get(1).getIp().isEmpty()){
//                    checkBox2.setChecked(false);
//                    ip1.setText("192.168.0.102");
//                    device1.setText("Device 2");
//                }
//                else if(!mList.get(1).getIp().isEmpty()){
//                    checkBox2.setChecked(true);
//                    ip2.setText(mList.get(1).getIp());
//                    device2.setText(mList.get(1).getDevice());
//                }
//                if(mList.get(2).getIp().isEmpty()){
//                    checkBox3.setChecked(false);
//                    ip1.setText("192.168.0.103");
//                    device1.setText("Device 3");
//                }
//                else if(!mList.get(2).getIp().isEmpty()){
//                    checkBox3.setChecked(true);
//                    ip3.setText(mList.get(2).getIp());
//                    device3.setText(mList.get(2).getDevice());
//                }
                newDeviceList.addAll(mList);
            }
        }
        catch (Exception e) {
            System.out.println("jhjh " + e.toString());
        }
        deviceInfoAdapter = new DeviceInfoAdapter(NewDeviceActivity.this, newDeviceList);

    }
    @OnClick(R.id.sub)
    protected void submit(){
        try {
            if(checkBox1.isChecked()) {
                if(!(ip1.getText().toString().trim().isEmpty()) && !(device1.getText().toString().trim().isEmpty()) && !(sp1.getSelectedItem().toString().trim().isEmpty())) {
                    db.createNewDevice(new DeviceClass(ip1.getText().toString().trim(), device1.getText().toString().trim(), sp1.getSelectedItem().toString().trim()));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter device name", Toast.LENGTH_LONG).show();
                }
            }
//                    if(checkBox2.isChecked()) {
//                        if(!(ip2.getText().toString().trim().isEmpty()) && !(device2.getText().toString().trim().isEmpty()) && !(sp2.getSelectedItem().toString().trim().isEmpty())) {
//                            db.createNewDevice(new DeviceClass(ip2.getText().toString().trim(), device2.getText().toString().trim(), sp2.getSelectedItem().toString().trim()));
//                        }
//                        else {
//                            Toast.makeText(getApplicationContext(), "Please enter device name", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                    if(checkBox3.isChecked()) {
//                        if(!(ip3.getText().toString().trim().isEmpty()) && !(device3.getText().toString().trim().isEmpty()) && !(sp3.getSelectedItem().toString().trim().isEmpty())) {
//                            db.createNewDevice(new DeviceClass(ip3.getText().toString().trim(), device3.getText().toString().trim(), sp3.getSelectedItem().toString().trim()));
//                        }
//                        else {
//                            Toast.makeText(getApplicationContext(), "Please enter device name", Toast.LENGTH_LONG).show();
//                        }
//                    }
            if((checkBox1.isChecked() && !(device1.getText().toString().trim().isEmpty()))){
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
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
}
