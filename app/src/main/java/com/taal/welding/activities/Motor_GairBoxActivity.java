package com.taal.welding.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.assistent.AndroidSharedPreferences;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.DeviceClass;
import com.taal.welding.model.GearBoxClass;
import com.taal.welding.model.NewDevice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Motor_GairBoxActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private List<DeviceClass> newList;
    private String device;
    private TextView ipText;
    private Button set;
    private EditText bId;
    private EditText bOd;
    private EditText bMd;
    private EditText pId;
    private EditText pMd;
    private EditText pOd;
    private EditText gbrText;
    private EditText bracketText;
    private EditText gearText;
    private TextView rpmVal;
    private Button confirm;
    private String []x;
    private TextView connectText;
    private TextView conn;
    private DatabaseHelper databaseHelper;
    private List<GearBoxClass> mList;
    private RelativeLayout rel;
    private String ip;// = "192.168.0.22";
    private int port;// = 20108;
    private final String TCP_CLIENT_PORT = "tcp_client_port";
    private final String TCP_CLIENT_IP = "tcp_client_ip";
    private String mDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor__gair_box);
        rel = (RelativeLayout) findViewById(R.id.relative);
        bId = (EditText) findViewById(R.id.bId);
        bOd = (EditText) findViewById(R.id.bOd);
        bMd = (EditText) findViewById(R.id.bMd);
        pId = (EditText) findViewById(R.id.pId);
        pOd = (EditText) findViewById(R.id.pOd);
        pMd = (EditText) findViewById(R.id.pMd);
        gbrText = (EditText) findViewById(R.id.gbrText);
        bracketText = (EditText) findViewById(R.id.bracketText);
        gearText = (EditText) findViewById(R.id.gearText);
        //rpmVal = (TextView) findViewById(R.id.rpmtext);
        set = (Button) findViewById(R.id.setBut);
        //confirm = (Button) findViewById(R.id.confirmBut);
        connectText = (TextView) findViewById(R.id.connection);
        //conn = (TextView) findViewById(R.id.closeCon);
        ipText = (TextView) findViewById(R.id.idTitle);
        databaseHelper = new DatabaseHelper(this);
        mList = databaseHelper.getAllGearBoxes();
        try {
            ip = AndroidSharedPreferences.getString(TCP_CLIENT_IP, "");
            String portPre = AndroidSharedPreferences.getString(TCP_CLIENT_PORT,"-1");
            port = Integer.parseInt(portPre);
        }
        catch (Exception e) {

        }
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

        db = new DatabaseHelper(getApplicationContext());
        newList = new ArrayList<>();
        newList = db.getAllNewDevices();


        for (int i = 0; i < newList.size(); i++) {
            if(newList.get(i).getIp().trim().equals(ip) || newList.get(i).getIp().trim().equals("192.168.0.22")) {
                device = newList.get(i).getIp() + "::" +newList.get(i).getDevice();
                mDevice = newList.get(i).getDevice();
                break;
            }
        }

        if(device != null){
            ipText.setText(device);
            rel.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(getApplicationContext(), "No device in Database", Toast.LENGTH_LONG).show();
        }
        //confirm.setEnabled(false);

        try{
            if(!mList.isEmpty()) {
                for(int i = 0; i < mList.size(); i++) {
                    if(mList.get(i).getDeviceNmae().equals(mDevice)) {
                        bId.setText(mList.get(i).getBandId());
                        bOd.setText(mList.get(i).getBandOd());
                        bMd.setText(mList.get(i).getBandMd());
                        pId.setText(mList.get(i).getPipeId());
                        pOd.setText(mList.get(i).getPipeOd());
                        pMd.setText(mList.get(i).getPipeMd());
                        gbrText.setText(mList.get(i).getGbr());
                        bracketText.setText(mList.get(i).getBandDia());
                        gearText.setText(mList.get(i).getGearwheelDia());
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send1("{,4,48,1," + gbrText.getText().toString().trim() + "," + gearText.getText().toString().trim() + "," + bracketText.getText().toString().trim() + "," +"323,}");
                if(!(ipText.getText().toString().trim().isEmpty()) && !(bId.getText().toString().trim().isEmpty()) && !(bOd.getText().toString().trim().isEmpty() && !(bMd.getText().toString().trim().isEmpty())
                && !(pId.getText().toString().trim().isEmpty()) && !(pOd.getText().toString().trim().isEmpty()) && !(pMd.getText().toString().trim().isEmpty()) && !(gbrText.getText().toString().trim().isEmpty())
                && !(bracketText.getText().toString().trim().isEmpty()) && !(gearText.getText().toString().isEmpty()))) {
                    db.createGearBox(new GearBoxClass(mDevice, bId.getText().toString().trim(), bOd.getText().toString().trim(), bMd.getText().toString().trim(), pId.getText().toString().trim(),pOd.getText().toString().trim(), pMd.getText().toString().trim(), gbrText.getText().toString().trim(), bracketText.getText().toString().trim(), gearText.getText().toString().trim()));
                    Toast.makeText(getApplicationContext(), "Successfully saved in database", Toast.LENGTH_SHORT).show();
                    //finish();
                }

                bId.setEnabled(false);
                bMd.setEnabled(false);
                bOd.setEnabled(false);
                pId.setEnabled(false);
                pMd.setEnabled(false);
                pOd.setEnabled(false);
                gbrText.setEnabled(false);
                bracketText.setEnabled(false);
                gearText.setEnabled(false);
                set.setEnabled(false);

                finish();
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
}
