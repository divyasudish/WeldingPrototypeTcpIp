package com.taal.welding.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.assistent.CommunicationBaseFragment;
import com.taal.welding.assistent.MainActivity;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.DeviceClass;
import com.taal.welding.model.GearBoxClass;
import com.taal.welding.model.NewDevice;
import com.taal.welding.model.SensorClass;
import com.taal.welding.net.ConnectListener;
import com.taal.welding.net.bean.Connect;
import com.taal.welding.net.bean.ConnectConfiguration;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SensorActivity extends AppCompatActivity implements ConnectListener {
    private DatabaseHelper db;
    private List<DeviceClass> newList;
    private String device;
    private TextView ipText;
    private Button setForward;
    private Button setBackward;
    private TextView homeText;
    private TextView calib;
    private Button calibBut;
    private TextView initial;
    private TextView accX;
    private TextView accY;
    private TextView accZ;
    private TextView gyroX;
    private TextView gyroY;
    private TextView gyroZ;
    private TextView magX;
    private TextView magY;
    private TextView magZ;
    private Button saveBut;
    private TextView connectText;
    private Button gyroBut;
    private TextView gear;
    private TextView band;
    private TextView wheel;
    private Button stopSens;
    private String message;
    private Connect connect;
    private int iLoop;

    private String []x;
    private List<SensorClass> mList;
    private List<GearBoxClass> mListGear;
    private String mCircum;
    private String mFullCycle;
    private String mRpmFullRotation;
    private boolean connected;
    private String ip = "192.168.0.22";
    private int port = 20108;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        connect =  MainActivity.connectManager.createTcpClient(ip, port, this);

        db = new DatabaseHelper(getApplicationContext());
        mList = db.getAllSensors();
        mListGear = db.getAllGearBoxes();
        newList = new ArrayList<>();
        newList = db.getAllNewDevices();
        ipText = (TextView) findViewById(R.id.idTitle);

        homeText = (TextView) findViewById(R.id.goHomeText);
        calib = (TextView) findViewById(R.id.calbrate);
        calibBut = (Button) findViewById(R.id.calibrateBut);
        initial = (TextView) findViewById(R.id.Initial);
        setForward = (Button) findViewById(R.id.SetForwordHome);
        setBackward = (Button) findViewById(R.id.setBackward);
        accX = (TextView) findViewById(R.id.accX);
        accY = (TextView) findViewById(R.id.accY);
        accZ = (TextView) findViewById(R.id.accZ);
        gyroX = (TextView) findViewById(R.id.gyroX);
        gyroY = (TextView) findViewById(R.id.gyroY);
        gyroZ = (TextView) findViewById(R.id.gyroZ);
        magX = (TextView) findViewById(R.id.magX);
        magY = (TextView) findViewById(R.id.magY);
        magZ = (TextView) findViewById(R.id.magZ);
        saveBut = (Button) findViewById(R.id.save);
        connectText = (TextView) findViewById(R.id.connection);
        gear = (TextView) findViewById(R.id.gbrText);
        band = (TextView) findViewById(R.id.bracketText);
        wheel = (TextView) findViewById(R.id.gearText);
        gyroBut = (Button) findViewById(R.id.Gyro);
        stopSens = (Button) findViewById(R.id.stopSensor);

        gyroBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), Motor_GairBoxActivity.class));
            }
        });
        stopSens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect.send("{,1,37,1,36,}".getBytes());
            }
        });

        initial.setText("Initial values");
        for (int i = 0; i < newList.size(); i++) {
            if(!newList.get(0).getIp().trim().isEmpty()) {
                device = newList.get(0).getIp() + "::" +newList.get(0).getDevice();
                break;
            }
        }

        if(device != null){
            ipText.setText(device);
        }
        else{
            ipText.setText("192.168.0.101" + "::" + "crc_evans");
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

        try{
            if(!mListGear.isEmpty()) {
                for(int i = 0; i < mListGear.size(); i++) {
                    if(mListGear.get(i).getDeviceNmae().equals("crc_evans")) {
//                        accX.setText(mList.get(i).getAccX());
//                        accY.setText(mList.get(i).getAccY());
//                        accZ.setText(mList.get(i).getAccZ());
//                        gyroX.setText(mList.get(i).getGyroX());
//                        gyroY.setText(mList.get(i).getGyroY());
//                        gyroZ.setText(mList.get(i).getGyroZ());
//                        magX.setText(mList.get(i).getMagX());
//                        magY.setText(mList.get(i).getMagY());
//                        magZ.setText(mList.get(i).getMagZ());
                        gear.setText(mListGear.get(i).getGbr());
                        band.setText(mListGear.get(i).getBandDia());
                        wheel.setText(mListGear.get(i).getGearwheelDia());
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        setForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String x = "{,2,49,1,0,22,}";
                connect.send(x.getBytes());
                //send1("{,2,49,1,0,22,}");
            }
        });
        setBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                connect.send("{,2,49,1,1,22,}".getBytes());
                //send1("{,2,49,1,1,22,}");
            }
        });
        calibBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(gear.getText().toString().trim().isEmpty()) && !(band.getText().toString().trim().isEmpty()) && !(wheel.getText().toString().trim().isEmpty())){
                    String ca =  "{,10,29,1," + gear.getText().toString().trim() + "," + wheel.getText().toString().trim() + "," + band.getText().toString().trim() + "," + mListGear.get(0).getBandId() + "," + mListGear.get(0).getBandOd() + "," + mListGear.get(0).getBandMd() + "," + mListGear.get(0).getPipeId() + "," + mListGear.get(0).getPipeOd() + "," + mListGear.get(0).getPipeMd() + ",323,}";
                    connect.send(ca.getBytes());
                    //send1("{,4,29,1," + gear.getText().toString().trim() + "," + wheel.getText().toString().trim() + "," + band.getText().toString().trim() + ",323,}");
                    calib.setText("Go to home position");
                }

            }
        });
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!(ipText.getText().toString().trim().isEmpty()) && !(accX.getText().toString().trim().isEmpty()) && !(accY.getText().toString().trim().isEmpty() && !(accZ.getText().toString().trim().isEmpty())
                    && !(gyroX.getText().toString().trim().isEmpty()) && !(gyroY.getText().toString().trim().isEmpty()) && !(gyroZ.getText().toString().trim().isEmpty()) && !(magX.getText().toString().trim().isEmpty())
                    && !(magY.getText().toString().trim().isEmpty()) && !(magZ.getText().toString().isEmpty()) && !(gear.getText().toString().trim().isEmpty()) && !(band.getText().toString().trim().isEmpty()) && !(wheel.getText().toString().trim().isEmpty()))) {
                db.createSensor(new SensorClass("crc_evans", accX.getText().toString().trim(), accY.getText().toString().trim(), accZ.getText().toString().trim(), gyroX.getText().toString().trim(), gyroY.getText().toString().trim(), gyroZ.getText().toString().trim(), magX.getText().toString().trim(), magY.getText().toString().trim(), magZ.getText().toString().trim(), gear.getText().toString().trim(), wheel.getText().toString().trim(), band.getText().toString().trim(), mCircum, mFullCycle, mRpmFullRotation));
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                String ca = "{,15,46," + accX.getText().toString().trim() + "," + accY.getText().toString().trim() + "," + accZ.getText().toString().trim() + "," + gyroX.getText().toString().trim() + "," + gyroY.getText().toString().trim() + "," + gyroZ.getText().toString().trim() + "," + magX.getText().toString().trim() + "," + magY.getText().toString().trim() + "," + magZ.getText().toString().trim() + "," + gear.getText().toString().trim() + "," + wheel.getText().toString().trim() + "," + band.getText().toString().trim() + "," + mCircum + "," + mFullCycle + "," + mRpmFullRotation + ",323,}";
                connect.send(ca.getBytes());
                //send1("{,15,46," + accX.getText().toString().trim() + "," + accY.getText().toString().trim() + "," + accZ.getText().toString().trim() + "," + gyroX.getText().toString().trim() + "," + gyroY.getText().toString().trim() + "," + gyroZ.getText().toString().trim() + "," + magX.getText().toString().trim() + "," + magY.getText().toString().trim() + "," + magZ.getText().toString().trim() + "," + gear.getText().toString().trim() + "," + wheel.getText().toString().trim() + "," + band.getText().toString().trim() + "," + mCircum + "," + mFullCycle + "," + mRpmFullRotation + ",323,}");
//                send1("{,15,46," + accX.getText().toString().trim() + "," + accY.getText().toString().trim() + "," + accZ.getText().toString().trim() + "," + gyroX.getText().toString().trim() + "," + gyroY.getText().toString().trim() + "," + gyroZ.getText().toString().trim() + "," + magX.getText().toString().trim() + "," + magY.getText().toString().trim() + "," + magZ.getText().toString().trim() + "," + gear.getText().toString().trim() + "," + wheel.getText().toString().trim() + "," + band.getText().toString().trim() + "," + mCircum + "," + mFullCycle + "," + mRpmFullRotation + ",323,}");
//                send1("{,15,46," + accX.getText().toString().trim() + "," + accY.getText().toString().trim() + "," + accZ.getText().toString().trim() + "," + gyroX.getText().toString().trim() + "," + gyroY.getText().toString().trim() + "," + gyroZ.getText().toString().trim() + "," + magX.getText().toString().trim() + "," + magY.getText().toString().trim() + "," + magZ.getText().toString().trim() + "," + gear.getText().toString().trim() + "," + wheel.getText().toString().trim() + "," + band.getText().toString().trim() + "," + mCircum + "," + mFullCycle + "," + mRpmFullRotation + ",323,}");
//                send1("{,15,46," + accX.getText().toString().trim() + "," + accY.getText().toString().trim() + "," + accZ.getText().toString().trim() + "," + gyroX.getText().toString().trim() + "," + gyroY.getText().toString().trim() + "," + gyroZ.getText().toString().trim() + "," + magX.getText().toString().trim() + "," + magY.getText().toString().trim() + "," + magZ.getText().toString().trim() + "," + gear.getText().toString().trim() + "," + wheel.getText().toString().trim() + "," + band.getText().toString().trim() + "," + mCircum + "," + mFullCycle + "," + mRpmFullRotation + ",323,}");

                //finish();
            }
//            if(myThreadConnectBTdevice!=null){
//                myThreadConnectBTdevice.cancel();
//            }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("Inside resume thread");
                                    mListGear = db.getAllGearBoxes();
                                    if(!mListGear.isEmpty()) {
                                        for(int i = 0; i < mListGear.size(); i++) {
                                            if(mListGear.get(i).getDeviceNmae().equals("crc_evans")) {
                                                gear.setText(mListGear.get(i).getGbr());
                                                band.setText(mListGear.get(i).getBandDia());
                                                wheel.setText(mListGear.get(i).getGearwheelDia());
                                            }
                                        }
                                    }
                                }
                                catch(Exception e) {

                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
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

    @Override
    public void connectSuccess(ConnectConfiguration configuration) {
        System.out.println("------------------>connectSuccess");
        connected = true;
        Toast.makeText(getApplicationContext(), "Connection success ", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void connectBreak(ConnectConfiguration configuration) {
        System.out.println("------------------>connectBreak");
        connected = false;
        //Toast.makeText(getApplicationContext(), "Connection break ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceviceData(ConnectConfiguration configuration, byte[] data) {
        System.out.println("receivedData------------------>" + data);

        String strMsg = null;
        try {
            strMsg = new String(data,"gb-2312");
            //Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_SHORT).show();
            System.out.println("data " + strMsg);
            if (!strMsg.isEmpty()) {
                //String strSub = strMsg.substring(strMsg.indexOf("{"), strMsg.indexOf("}"));
                x = strMsg.split(",");
                for(iLoop = 0; iLoop < x.length; iLoop++){
                    if ((x[iLoop].equals("200") && (x[iLoop-1].equals("1")))) {
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initial.setText("Reached Home");
                                }
                            });
                        }
                        catch(Exception e) {

                        }
                        //rpmVal.setText(x[1]);
                    }
//                    else if(x[iLoop].equals("300") || x[1].equals("300") || x[0].equals("300")){
//                        initial.setText("Packet error ");
//                    }
                    else if ((x[iLoop].equals("206")) && x[iLoop - 1].equals("15")){
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initial.setText("Reached Home ");
                                    calib.setText("Calibration done");
                                    if(x[iLoop].equals("206")){
                                        accX.setText(x[iLoop + 1]);
                                        accY.setText(x[iLoop +2]);
                                        accZ.setText(x[iLoop + 3]);
                                        gyroX.setText(x[iLoop + 4]);
                                        gyroY.setText(x[iLoop + 5]);
                                        gyroZ.setText(x[iLoop + 6]);
                                        magX.setText(x[iLoop + 7]);
                                        magY.setText(x[iLoop + 8]);
                                        magZ.setText(x[iLoop + 9]);
//                                        gear.setText(x[iLoop + 10]);
//                                        band.setText(x[iLoop + 11]);
//                                        wheel.setText(x[iLoop + 12]);
                                        mCircum = x[iLoop + 13];
                                        mFullCycle = x[iLoop + 14];
                                        mRpmFullRotation = x[iLoop + 15];
                                    }
                                }
                            });

                        }
                        catch(Exception e) {
                            System.out.println(e.toString());
                        }

                    }

                    if (((x[iLoop].equals("1")) && (x[iLoop-1].equals("202")))){
                        System.out.println("Inside towards 90 ");
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initial.setText("Towards 90 degree");
                                    calib.setText("Calibrating");
                                    accX.setText(x[iLoop+1]);
                                    accY.setText(x[iLoop+2]);
                                    accZ.setText(x[iLoop+3]);
                                    gyroX.setText(x[iLoop+4]);
                                    gyroY.setText(x[iLoop+5]);
                                    gyroZ.setText(x[iLoop+6]);
                                    magX.setText(x[iLoop+7]);
                                    magY.setText(x[iLoop+8]);
                                    magZ.setText(x[iLoop+9]);
                                }
                            });

                        }
                        catch(Exception e) {
                            System.out.println(e.toString());
                        }
                    }

                    else if (((x[iLoop].equals("2")) && (x[iLoop-1].equals("202")))){
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initial.setText("At 90 degree");
                                    calib.setText("Calibrating");
                                    accX.setText(x[iLoop+1]);
                                    accY.setText(x[iLoop+2]);
                                    accZ.setText(x[iLoop+3]);
                                    gyroX.setText(x[iLoop+4]);
                                    gyroY.setText(x[iLoop+5]);
                                    gyroZ.setText(x[iLoop+6]);
                                    magX.setText(x[iLoop+7]);
                                    magY.setText(x[iLoop+8]);
                                    magZ.setText(x[iLoop+9]);
                                }
                            });

                        }
                        catch(Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                    else if (((x[iLoop].equals("3")) && (x[iLoop-1].equals("202")))){
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initial.setText("Towards 180 degree");
                                    calib.setText("Calibrating");
                                    accX.setText(x[iLoop+1]);
                                    accY.setText(x[iLoop+2]);
                                    accZ.setText(x[iLoop+3]);
                                    gyroX.setText(x[iLoop+4]);
                                    gyroY.setText(x[iLoop+5]);
                                    gyroZ.setText(x[iLoop+6]);
                                    magX.setText(x[iLoop+7]);
                                    magY.setText(x[iLoop+8]);
                                    magZ.setText(x[iLoop+9]);
                                }
                            });

                        }
                        catch(Exception e)  {
                            System.out.println(e.toString());
                        }
                    }

                    else if (((x[iLoop].equals("4")) && (x[iLoop-1].equals("202")))){
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initial.setText("At 180 degree");
                                    calib.setText("Calibrating");
                                    accX.setText(x[iLoop+1]);
                                    accY.setText(x[iLoop+2]);
                                    accZ.setText(x[iLoop+3]);
                                    gyroX.setText(x[iLoop+4]);
                                    gyroY.setText(x[iLoop+5]);
                                    gyroZ.setText(x[iLoop+6]);
                                    magX.setText(x[iLoop+7]);
                                    magY.setText(x[iLoop+8]);
                                    magZ.setText(x[iLoop+9]);
                                }
                            });

                        }
                        catch(Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                    else if (((x[iLoop].equals("5")) && (x[iLoop-1].equals("202")))){
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initial.setText("Towards 270 degree");
                                    calib.setText("Calibrating");
                                    accX.setText(x[iLoop+1]);
                                    accY.setText(x[iLoop+2]);
                                    accZ.setText(x[iLoop+3]);
                                    gyroX.setText(x[iLoop+4]);
                                    gyroY.setText(x[iLoop+5]);
                                    gyroZ.setText(x[iLoop+6]);
                                    magX.setText(x[iLoop+7]);
                                    magY.setText(x[iLoop+8]);
                                    magZ.setText(x[iLoop+9]);
                                }
                            });
                        }
                        catch (Exception e) {
                            System.out.println(e.toString());
                        }

                    }
                    else if (((x[iLoop].equals("6")) && (x[iLoop-1].equals("202")))){
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initial.setText("At  270 degree");
                                    calib.setText("Calibrating");
                                    accX.setText(x[iLoop+1]);
                                    accY.setText(x[iLoop+2]);
                                    accZ.setText(x[iLoop+3]);
                                    gyroX.setText(x[iLoop+4]);
                                    gyroY.setText(x[iLoop+5]);
                                    gyroZ.setText(x[iLoop+6]);
                                    magX.setText(x[iLoop+7]);
                                    magY.setText(x[iLoop+8]);
                                    magZ.setText(x[iLoop+9]);
                                }
                            });
                        }
                        catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                    else if (((x[iLoop].equals("7")) && (x[iLoop-1].equals("202")))) {
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initial.setText("Towards home");
                                    calib.setText("Calibrating");
                                    accX.setText(x[iLoop+1]);
                                    accY.setText(x[iLoop+2]);
                                    accZ.setText(x[iLoop+3]);
                                    gyroX.setText(x[iLoop+4]);
                                    gyroY.setText(x[iLoop+5]);
                                    gyroZ.setText(x[iLoop+6]);
                                    magX.setText(x[iLoop+7]);
                                    magY.setText(x[iLoop+8]);
                                    magZ.setText(x[iLoop+9]);
                                }
                            });

                        }
                        catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }

                }

            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }

}
