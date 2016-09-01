package com.taal.welding.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.assistent.AndroidSharedPreferences;
import com.taal.welding.assistent.MainActivity;
import com.taal.welding.canvas.AnimationArc;
import com.taal.welding.canvas.Arc;
import com.taal.welding.canvas.ArrowView;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.DeviceClass;
import com.taal.welding.model.MachineProgress;
import com.taal.welding.model.MachineProgressList;
import com.taal.welding.model.SensorClass;
import com.taal.welding.net.ConnectListener;
import com.taal.welding.net.bean.Connect;
import com.taal.welding.net.bean.ConnectConfiguration;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MachineProgressNewActivity extends AppCompatActivity implements ConnectListener {
    @Bind(R.id.startProgress)
    protected Button start;
    @Bind(R.id.stopProgress)
    protected Button stop;
    @Bind(R.id.vel)
    protected Button velocityMov;
    @Bind(R.id.posMove)
    protected Button posMove;
    @Bind(R.id.solenoidBut)
    protected Button solenoidBut;
    @Bind(R.id.GoHome)
    protected Button goHome;
    @Bind(R.id.ipText)
    protected TextView ipText;
    @Bind(R.id.accelX)
    protected TextView accelX;
    @Bind(R.id.accelY)
    protected TextView accelY;
    @Bind(R.id.accelZ)
    protected TextView accelZ;
    @Bind(R.id.gyroX)
    protected TextView gyroX;
    @Bind(R.id.gyroY)
    protected TextView gyroY;
    @Bind(R.id.gyroZ)
    protected TextView gyroZ;
    @Bind(R.id.magX)
    protected TextView magnetoX;
    @Bind(R.id.magY)
    protected TextView magnetoY;
    @Bind(R.id.magZ)
    protected TextView magnetoZ;
    @Bind(R.id.baro)
    protected TextView baro;
    @Bind(R.id.temp)
    protected TextView temp;
    @Bind(R.id.homePos)
    protected TextView homepos;
    @Bind(R.id.relText)
    protected TextView relEncoder;
    @Bind(R.id.absText)
    protected TextView absEncoder;
    @Bind(R.id.velocityTextView)
    protected TextView velocityTextView;
    @Bind(R.id.pos)
    protected TextView posTextView;
    @Bind(R.id.degree)
    protected TextView degreeText;
    @Bind(R.id.connection)
    protected TextView connectionText;
    @Bind(R.id.velocity)
    protected TextView Veloc;
    @Bind(R.id.ramp)
    protected TextView ramp;
    @Bind(R.id.direction)
    protected TextView Direction;
    @Bind(R.id.posText)
    protected EditText pos;
    @Bind(R.id.velocityText)
    protected EditText vel;
    @Bind(R.id.rampText)
    protected EditText RampText;

    private ArrowView mArc;
    private Boolean stopFlag = false;
    private DatabaseHelper db;
    private List<DeviceClass> newList;
    private List<SensorClass> mSensorList;
    private List<MachineProgress> mProgressList;
    private List<MachineProgressList> mMachineProgressList;
    public static ArrayList<MachineProgressList> machineProgressListArrayList;
    private float previous;
    private float newangle;
    private Boolean reachedHomeBoolean = false;
    private Boolean flag_repeat = false;
    private final String TCP_CLIENT_PORT = "tcp_client_port";
    private final String TCP_CLIENT_IP = "tcp_client_ip";
    private String mDevice;
    private String device;
    private String[] x;
    private AnimationArc animation;
    private int i = 1;
    private float angle, angle1;
    private FileOutputStream fos;
    private int firstVal;
    private int lastVal;
    private int interVal;
    private Boolean velFlag = false;
    private Boolean posFlag = false;
    private boolean connected;
    private String ip;
    private int port;
    private Connect connect;
    private int iLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_progress);
        ButterKnife.bind(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        try {
            ip = AndroidSharedPreferences.getString(TCP_CLIENT_IP, "");
            String portPre = AndroidSharedPreferences.getString(TCP_CLIENT_PORT,"-1");
            port = Integer.parseInt(portPre);
            connect =  MainActivity.connectManager.createTcpClient(ip, port, this);
        }
        catch (Exception e) {

        }
        newList = new ArrayList<>();
        mSensorList = new ArrayList<>();
        mProgressList = new ArrayList<>();
        mMachineProgressList = new ArrayList<>();
        machineProgressListArrayList = new ArrayList<>();
        db = new DatabaseHelper(getApplicationContext());
        mArc = (ArrowView) findViewById(R.id.arc);
        newList = db.getAllNewDevices();
        for (int i = 0; i < newList.size(); i++) {
            if(newList.get(i).getIp().trim().equals(ip) || newList.get(i).getIp().trim().equals("192.168.0.22")) {
                device = newList.get(i).getIp() + "::" +newList.get(i).getDevice();
                mDevice = newList.get(i).getDevice();
                break;
            }
        }

        if (device != null) {
            ipText.setText(device);
        } else {
            Toast.makeText(getApplicationContext(), "No device in Database", Toast.LENGTH_SHORT).show();
        }
        mSensorList = db.getAllSensors();
        mMachineProgressList = db.getAllMachineProgressList();
        try {
            if (!mMachineProgressList.isEmpty()) {
                for(int i = 0; i < mMachineProgressList.size();i++) {
                    if(newList.get(i).getIp().trim().equals(ip) || newList.get(i).getIp().trim().equals("192.168.0.22")) {
                        pos.setText(mMachineProgressList.get(i).getPosition());
                        vel.setText(mMachineProgressList.get(i).getVelocity());
                        break;
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        if (!mMachineProgressList.isEmpty()) {
            db.deleteMachineProgressList();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        try {
            if (!newList.get(0).getDevice().isEmpty()) {
                fos = new FileOutputStream(new File("/sdcard/" + newList.get(0).getDevice() + "_" + dateFormat.format(date) + ".xml"), true);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if ((Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)) {

        } else {
            window.setStatusBarColor(Color.BLACK);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
            try {
                getSupportActionBar().setTitle("BLDC PoC");

            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    @OnClick(R.id.startProgress)
    protected void start() {
        if (start.getText().toString().trim().equals("Pause")) {
            start.setText("Resume");
            String start = "{,1,43,1,38,}";
            connect.send(start.getBytes());
            mArc.clearAnimation();

        } else if (start.getText().toString().trim().equals("Resume")) {
            start.setText("Pause");
            String pause = "{,1,44,1,22,}";
            connect.send(pause.getBytes());
        }
    }
    @OnClick(R.id.stopProgress)
    protected void stop() {
        stopFlag = true;
        velocityTextView.setText("");
        velFlag = false;
        posFlag = false;
        i = 0;
        start.setText("Pause");
        String stop = "{,1,37,1,36,}";
        connect.send(stop.getBytes());
        try {
            mArc.clearAnimation();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    @OnClick(R.id.solenoidBut)
    protected void solenoid() {
        if (solenoidBut.getText().toString().trim().equals("Solenoid On")) {
            String sx = "{,1,50,1,22,}";
            connect.send(sx.getBytes());
            solenoidBut.setText("Solenoid Off");
        } else if (solenoidBut.getText().toString().trim().equals("Solenoid Off")) {
            String sxo = "{,1,50,0,22,}";
            connect.send(sxo.getBytes());
            solenoidBut.setText("Solenoid On");
        }
    }
    @OnClick(R.id.GoHome)
    protected void goHomeClick() {
        stopFlag = false;
        velFlag = false;
        posFlag = false;
        reachedHomeBoolean = false;
        velocityMov.setText("ClockWise");
        posMove.setText("AntiClockWise");
        pos.setVisibility(View.INVISIBLE);
        posTextView.setVisibility(View.INVISIBLE);
        Veloc.setVisibility(View.INVISIBLE);
        vel.setVisibility(View.INVISIBLE);
        ramp.setVisibility(View.INVISIBLE);
        RampText.setVisibility(View.INVISIBLE);
    }
    @OnClick(R.id.vel)
    protected void velocityClick() {
        stopFlag = false;
        reachedHomeBoolean = false;
        System.out.println("Size is " + mSensorList.size());
        Veloc.setVisibility(View.VISIBLE);
        vel.setVisibility(View.VISIBLE);
        pos.setVisibility(View.INVISIBLE);
        posTextView.setVisibility(View.INVISIBLE);
        ramp.setVisibility(View.VISIBLE);
        RampText.setVisibility(View.VISIBLE);

        if (!(mSensorList.isEmpty()) && velocityMov.getText().toString().trim().equals("Velocity Move")) {
            System.out.println("jjj " + "{,15,46," + mSensorList.get(0).getAccX() + "," + mSensorList.get(0).getAccY() + "," + mSensorList.get(0).getAccZ() + "," + mSensorList.get(0).getGyroX() + "," + mSensorList.get(0).getGyroY() + "," + mSensorList.get(0).getGyroZ() + "," + mSensorList.get(0).getMagX() + "," + mSensorList.get(0).getMagY() + "," + mSensorList.get(0).getMagZ() + "," + mSensorList.get(0).getGbr() + "," + mSensorList.get(0).getGearwheelDia() + "," + mSensorList.get(0).getBandDia() + "," + mSensorList.get(0).getCircum() + "," + mSensorList.get(0).getFullCycle() + "," + mSensorList.get(0).getRpm() + ",323,}");
            String x = "{,15,46," + mSensorList.get(0).getAccX() + "," + mSensorList.get(0).getAccY() + "," + mSensorList.get(0).getAccZ() + "," + mSensorList.get(0).getGyroX() + "," + mSensorList.get(0).getGyroY() + "," + mSensorList.get(0).getGyroZ() + "," + mSensorList.get(0).getMagX() + "," + mSensorList.get(0).getMagY() + "," + mSensorList.get(0).getMagZ() + "," + mSensorList.get(0).getGbr() + "," + mSensorList.get(0).getGearwheelDia() + "," + mSensorList.get(0).getBandDia() + "," + mSensorList.get(0).getCircum() + "," + mSensorList.get(0).getFullCycle() + "," + mSensorList.get(0).getRpm() + ",323,}";
            connect.send(x.getBytes());
        }
        if(velocityMov.getText().toString().trim().equals("Velocity Move")) {
            if (posFlag) {
                velFlag = false;
            } else if (!posFlag) {
                velFlag = true;
            }
        }

        try {
            if(velocityMov.getText().toString().trim().equals("ClockWise")) {
                pos.setVisibility(View.INVISIBLE);
                posTextView.setVisibility(View.INVISIBLE);
                Veloc.setVisibility(View.INVISIBLE);
                vel.setVisibility(View.INVISIBLE);
                ramp.setVisibility(View.INVISIBLE);
                RampText.setVisibility(View.INVISIBLE);
                if(start.getText().toString().trim().equals("Resume")) {
                    start.setText("Pause");
                }
                String x = "{,2,49,1,0,22,}";
                connect.send(x.getBytes());
            }
            else if(((Integer.parseInt(vel.getText().toString().trim())) > (Integer.parseInt(pos.getText().toString().trim()))) || Integer.parseInt((vel.getText().toString().trim())) > 50){
                pos.setVisibility(View.VISIBLE);
                posTextView.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            else {
                if (velocityMov.getText().toString().trim().equals("Clockwise") && velFlag) {
                    Veloc.setVisibility(View.VISIBLE);
                    vel.setVisibility(View.VISIBLE);
                    pos.setVisibility(View.INVISIBLE);
                    posTextView.setVisibility(View.INVISIBLE);
                    ramp.setVisibility(View.VISIBLE);
                    RampText.setVisibility(View.VISIBLE);
                    velocityTextView.setText("Machine in velocity clockwise move");
                    if(start.getText().toString().trim().equals("Resume")) {
                        start.setText("Pause");
                    }
                    //Toast.makeText(getApplicationContext(), "{,4,45,0,1," + pos.getText().toString().trim() + "," + vel.getText().toString().trim() + ",22,}", Toast.LENGTH_LONG ).show();
                    //send1("{,4,45,0,1," + pos.getText().toString().trim() + "," + vel.getText().toString().trim() + ",22,}");
                    String velC = "{,4,45,0,1," + pos.getText().toString().trim() + "," + vel.getText().toString().trim() + ",22,}";
                    connect.send(velC.getBytes());
                } else if (velocityMov.getText().toString().trim().equals("Clockwise") && posFlag) {
                    pos.setVisibility(View.VISIBLE);
                    posTextView.setVisibility(View.VISIBLE);
                    Veloc.setVisibility(View.VISIBLE);
                    vel.setVisibility(View.VISIBLE);
                    ramp.setVisibility(View.VISIBLE);
                    RampText.setVisibility(View.VISIBLE);
                    velocityTextView.setText("Machine in position clockwise move");
                    if(start.getText().toString().trim().equals("Resume")) {
                        start.setText("Pause");
                    }
                    String vlc = "{,4,45,0,0," + pos.getText().toString().trim() + "," + vel.getText().toString().trim() + ",22,}";
                    connect.send(vlc.getBytes());
                }
            }
        }
        catch(Exception e) {

        }
    }
    @OnClick(R.id.posMove)
    protected void posMoveClick() {
        stopFlag = false;
        reachedHomeBoolean = false;
        pos.setVisibility(View.VISIBLE);
        posTextView.setVisibility(View.VISIBLE);
        Veloc.setVisibility(View.VISIBLE);
        vel.setVisibility(View.VISIBLE);
        ramp.setVisibility(View.VISIBLE);
        RampText.setVisibility(View.VISIBLE);
        if (!(mSensorList.isEmpty()) && posMove.getText().toString().trim().equals("Position Move")) {
            String posX = "{,15,46," + mSensorList.get(0).getAccX() + "," + mSensorList.get(0).getAccY() + "," + mSensorList.get(0).getAccZ() + "," + mSensorList.get(0).getGyroX() + "," + mSensorList.get(0).getGyroY() + "," + mSensorList.get(0).getGyroZ() + "," + mSensorList.get(0).getMagX() + "," + mSensorList.get(0).getMagY() + "," + mSensorList.get(0).getMagZ() + "," + mSensorList.get(0).getGbr() + "," + mSensorList.get(0).getGearwheelDia() + "," + mSensorList.get(0).getBandDia() + "," + mSensorList.get(0).getCircum() + "," + mSensorList.get(0).getFullCycle() + "," + mSensorList.get(0).getRpm() + ",323,}";
            connect.send(posX.getBytes());
        }
        if(posMove.getText().toString().trim().equals("Position Move")) {
            if (velFlag) {
                posFlag = false;
            } else if (!velFlag) {
                posFlag = true;
            }
        }
        try {
            if(posMove.getText().toString().trim().equals("AntiClockWise")) {
                pos.setVisibility(View.INVISIBLE);
                posTextView.setVisibility(View.INVISIBLE);
                Veloc.setVisibility(View.INVISIBLE);
                vel.setVisibility(View.INVISIBLE);
                ramp.setVisibility(View.INVISIBLE);
                RampText.setVisibility(View.INVISIBLE);
                if(start.getText().toString().trim().equals("Resume")) {
                    start.setText("Pause");
                }
                connect.send("{,2,49,1,1,22,}".getBytes());
            }
            else if(((Integer.parseInt(vel.getText().toString().trim())) > (Integer.parseInt(pos.getText().toString().trim()))) || Integer.parseInt((vel.getText().toString().trim())) > 50){
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            else {
                if (posMove.getText().toString().trim().equals("Anticlockwise") && velFlag) {
                    pos.setVisibility(View.INVISIBLE);
                    posTextView.setVisibility(View.INVISIBLE);
                    Veloc.setVisibility(View.VISIBLE);
                    vel.setVisibility(View.VISIBLE);
                    ramp.setVisibility(View.VISIBLE);
                    RampText.setVisibility(View.VISIBLE);
                    velocityTextView.setText("Machine in velocity anticlockwise move");
                    if(start.getText().toString().trim().equals("Resume")) {
                        start.setText("Pause");
                    }
                    String posAnt = "{,4,45,1,1," + pos.getText().toString().trim() + "," + vel.getText().toString().trim() + ",22,}";
                    connect.send(posAnt.getBytes());
                } else if (posMove.getText().toString().trim().equals("Anticlockwise") && posFlag) {
                    pos.setVisibility(View.VISIBLE);
                    posTextView.setVisibility(View.VISIBLE);
                    Veloc.setVisibility(View.VISIBLE);
                    vel.setVisibility(View.VISIBLE);
                    ramp.setVisibility(View.VISIBLE);
                    RampText.setVisibility(View.VISIBLE);
                    velocityTextView.setText("Machine in position anticlockwise move");
                    if(start.getText().toString().trim().equals("Resume")) {
                        start.setText("Pause");
                    }
                    String posClk = "{,4,45,1,0," + pos.getText().toString().trim() + "," + vel.getText().toString().trim() + ",22,}";
                    connect.send(posClk.getBytes());
                }
            }

        }
        catch(Exception e) {

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

    @Override
    public void onResume() {
        super.onResume();
        try {
            if(!machineProgressListArrayList.isEmpty()) {
                machineProgressListArrayList.clear();
            }
            mProgressList = db.getAllProgress();
            mSensorList = db.getAllSensors();
            if (!mProgressList.isEmpty()) {
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }


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
                                    int x = Integer.parseInt(vel.getText().toString().trim()) * 60;
                                    System.out.println(x);
                                    RampText.setText(Integer.toString(x));
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
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            String dataWrite = writeXml(machineProgressListArrayList);
            fos.write(dataWrite.getBytes());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private String writeXml(List<MachineProgressList> messages) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "messages");
            serializer.attribute("", "number", String.valueOf(messages.size()));
            for (MachineProgressList msg : messages) {
                serializer.startTag("", "message");
                serializer.attribute("", "date", dateFormat.format(date));
                serializer.startTag("", "velocity");
                serializer.text(msg.getVelocity() + "mm/s");
                serializer.endTag("", "velocity");
                serializer.startTag("", "position");
                serializer.text(msg.getPosition() + "mm");
                serializer.endTag("", "position");
                serializer.startTag("", "accelX");
                serializer.text(msg.getAccX());
                serializer.endTag("", "accelX");
                serializer.startTag("", "accelY");
                serializer.text(msg.getAccY());
                serializer.endTag("", "accelY");
                serializer.startTag("", "accelZ");
                serializer.text(msg.getAccZ());
                serializer.endTag("", "accelZ");
                serializer.startTag("", "gyroX");
                serializer.text(msg.getGyroX());
                serializer.endTag("", "gyroX");
                serializer.startTag("", "gyroY");
                serializer.text(msg.getGyroY());
                serializer.endTag("", "gyroY");
                serializer.startTag("", "gyroZ");
                serializer.text(msg.getGyroZ());
                serializer.endTag("", "gyroZ");
                serializer.startTag("", "magnetoX");
                serializer.text(msg.getMagX());
                serializer.endTag("", "magnetoX");
                serializer.startTag("", "magnetoY");
                serializer.text(msg.getMagY());
                serializer.endTag("", "magnetoY");
                serializer.startTag("", "magnetoZ");
                serializer.text(msg.getMagZ());
                serializer.endTag("", "magnetoZ");

                serializer.startTag("", "barometer");
                serializer.text(msg.getBaro() + "mbar");
                serializer.endTag("", "barometer");

                serializer.startTag("", "temperature");
                serializer.text(msg.getTemp() + "°c");
                serializer.endTag("", "temperature");

                serializer.startTag("", "actualVelocity");
                serializer.text(msg.getActualVel() + "mm/s");
                serializer.endTag("", "actualVelocity");
                serializer.startTag("", "relativeEncoder");
                serializer.text(msg.getRelEncodr() + "mm");
                serializer.endTag("", "relativeEncoder");
                serializer.startTag("", "absoluteEncoder");
                serializer.text(msg.getAbsEncoder() + "mm");
                serializer.endTag("", "absoluteEncoder");
                serializer.endTag("", "message");
            }
            serializer.endTag("", "messages");
            serializer.endDocument();
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            fos.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void connectSuccess(ConnectConfiguration configuration) {
        System.out.println("------------------>connectSuccess");
        String st = "{,1,47,1,22,}";
        connect.send(st.getBytes());
        connected = true;
        Toast.makeText(getApplicationContext(), "Connection success ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void connectBreak(ConnectConfiguration configuration) {
        System.out.println("------------------>connectBreak");
        connected = false;
    }

    @Override
    public void onReceviceData(ConnectConfiguration configuration, byte[] data) {
        System.out.println("receivedData------------------>" + data);

        try {
            String strMsg = new String(data, "UTF-8");
            System.out.println("receivedData aftr loop------------------>" + data);
            System.out.println(strMsg);
            data = null;
            if (!strMsg.isEmpty()) {
                x = new String[10000];
                x = strMsg.split(",");
                System.out.println(strMsg);
                Boolean flag_Loop = false;
                for(iLoop = 0; iLoop < x.length; iLoop++) {
                    if ((x[iLoop].equals("46")) && (velFlag || posFlag)) {
                        System.out.println(x[iLoop]);
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                velocityMov.setText("Clockwise");
                                posMove.setText("Anticlockwise");
                                }
                            });
                        }
                        catch(Exception e) {

                        }
                    } else if (x[iLoop].equals("37") && x[iLoop + 1].equals("1")) {
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    velocityMov.setText("Velocity Move");
                                    posMove.setText("Position Move");
                                    velocityTextView.setText(" ");

                                }
                            });
                        }
                        catch(Exception e) {
                        }
                    }
                    else if ((x[iLoop].equals("23") && x[iLoop + 1].equals("47"))) {
                        try {
                            System.out.println("Hello inside 18" + x[3] + "//" + x[4] + ".." + x[5]);
                            if (x[iLoop].equals("23") && x[iLoop + 1].equals("47")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        db.createMachineProgress(new MachineProgress(mDevice, x[iLoop + 2], x[iLoop + 3], x[iLoop + 19]));
                                        velocityMov.setEnabled(true);
                                        posMove.setEnabled(true);
                                        goHome.setEnabled(true);
                                        solenoidBut.setEnabled(true);
                                        start.setEnabled(true);
                                        stop.setEnabled(true);
                                        pos.setText("Hello");
                                        pos.setText(x[iLoop + 2]);
                                        vel.setText(x[iLoop + 3]);
                                        accelX.setText(x[iLoop+ 4]);
                                        accelY.setText(x[iLoop + 5]);
                                        accelZ.setText(x[iLoop + 6]);
                                        gyroX.setText(x[iLoop + 7]);
                                        gyroY.setText(x[iLoop + 8]);
                                        gyroZ.setText(x[iLoop + 9]);
                                        magnetoX.setText(x[iLoop + 10]);
                                        magnetoY.setText(x[iLoop + 11]);
                                        magnetoZ.setText(x[iLoop + 12]);
                                        String baroM = x[iLoop + 13];
                                        String tempStr = x[iLoop + 14];
                                        try {
                                            baro.setText(baroM.substring(0, 3) + "." + baroM.substring(3, baroM.length()));
                                            temp.setText(tempStr.substring(0, 2) + "." + tempStr.substring(2, tempStr.length()));

                                        } catch (Exception e) {

                                        }
                                        relEncoder.setText(x[iLoop + 15]);
                                        absEncoder.setText(x[iLoop + 16]);
                                        firstVal = Integer.parseInt("0");
                                        lastVal = Integer.parseInt(x[iLoop + 17]);
                                        angle1 = ((firstVal * 360) / (lastVal - firstVal)) - 90;
                                        angle1 = Float.parseFloat(x[iLoop + 23]);
                                        Direction.setText(x[iLoop + 24]);
                                        System.out.println("Angle 1 is " + angle1);
                                        if(x[iLoop + 23].length() == 1 || x[iLoop + 23].length() == 2 || x[iLoop + 23].length() == 3) {
                                            degreeText.setText(angle1 + "°");
                                        }
                                        if(angle1 < 180) {
                                            mArc.setColorCode(Color.RED);
                                        }
                                        else if(angle1 > 180) {
                                            mArc.setColorCode(Color.BLUE);
                                        }
                                        animation = new AnimationArc(mArc, angle1 - 90);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(newangle < 0) {
                                                    mArc.startAnimation(animation);
                                                }

                                            }
                                        });
                                        try {
                                            if (!(mSensorList.isEmpty())) {
                                                System.out.println("Hello " + "llll");
                                                String ms = "{,15,46," + mSensorList.get(0).getAccX() + "," + mSensorList.get(0).getAccY() + "," + mSensorList.get(0).getAccZ() + "," + mSensorList.get(0).getGyroX() + "," + mSensorList.get(0).getGyroY() + "," + mSensorList.get(0).getGyroZ() + "," + mSensorList.get(0).getMagX() + "," + mSensorList.get(0).getMagY() + "," + mSensorList.get(0).getMagZ() + "," + mSensorList.get(0).getGbr() + "," + mSensorList.get(0).getGearwheelDia() + "," + mSensorList.get(0).getBandDia() + "," + mSensorList.get(0).getCircum() + "," + mSensorList.get(0).getFullCycle() + "," + mSensorList.get(0).getRpm() + ",323,}";
                                                connect.send(ms.getBytes());
                                            }

                                        } catch (Exception e) {
                                            System.out.println(e.toString());
                                        }

                                    }
                                });
                            }

                        }
                        catch(Exception e) {

                        }
                    }
                    else if ((x[iLoop].equals("200") && (x[iLoop-1].equals("1")))) {
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    reachedHomeBoolean = true;
                                    velocityMov.setText("Velocity Move");
                                    posMove.setText("Position Move");
                                }
                            });
                        }
                        catch(Exception e) {

                        }
                    }
                    else if (x[iLoop].equals("23") && x[iLoop + 1].equals("203")) {
                        try {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    velocityMov.setEnabled(true);
                                    posMove.setEnabled(true);
                                    solenoidBut.setEnabled(true);
                                    start.setEnabled(true);
                                    stop.setEnabled(true);
                                    if(x[iLoop + 21].equals("0")){
                                        velocityMov.setText("Clockwise");
                                        posMove.setText("Anticlockwise");
                                    }
                                    accelX.setText(x[iLoop + 2]);
                                    accelY.setText(x[iLoop + 3]);
                                    accelZ.setText(x[iLoop + 4]);
                                    gyroX.setText(x[iLoop + 5]);
                                    gyroY.setText(x[iLoop + 6]);
                                    gyroZ.setText(x[iLoop + 7]);
                                    magnetoX.setText(x[iLoop + 8]);
                                    magnetoY.setText(x[iLoop + 9]);
                                    magnetoZ.setText(x[iLoop + 10]);
                                    String baroM = x[iLoop + 11];
                                    String tempStr = x[iLoop + 12];
                                    try {
                                        baro.setText(baroM.substring(0, 3) + "." + baroM.substring(3, baroM.length()));
                                        temp.setText(tempStr.substring(0, 2) + "." + tempStr.substring(2, tempStr.length()));
                                    } catch (Exception e) {

                                    }
                                    relEncoder.setText(x[iLoop + 18]);
                                    absEncoder.setText(x[iLoop + 19]);
                                    homepos.setText(x[iLoop + 20]);
                                    for(int i = 0; i < machineProgressListArrayList.size(); i++) {
                                        if(machineProgressListArrayList.get(i).getAbsEncoder().equals(absEncoder.getText().toString().trim()) && machineProgressListArrayList.get(i).getVelocity().equals(vel.getText().toString().trim()) && machineProgressListArrayList.get(i).getPosition().equals(pos.getText().toString().trim()) && machineProgressListArrayList.get(i).getClockwise().equals(x[iLoop + 14])) {
                                            flag_repeat = true;
                                            break;
                                        }
                                        else {
                                            flag_repeat = false;
                                        }
                                    }
                                    if(flag_repeat == false) {
                                        machineProgressListArrayList.add(new MachineProgressList(mDevice, pos.getText().toString().trim(), vel.getText().toString().trim(), accelX.getText().toString().trim(), accelY.getText().toString().trim(), accelZ.getText().toString().trim(), gyroX.getText().toString().trim(), gyroY.getText().toString().trim(), gyroZ.getText().toString().trim(), magnetoX.getText().toString().trim(), magnetoY.getText().toString().trim(), magnetoZ.getText().toString().trim(), baro.getText().toString().trim(), temp.getText().toString().trim(), homepos.getText().toString().trim(), relEncoder.getText().toString().trim(), absEncoder.getText().toString().trim(), x[iLoop + 14]));
                                    }
                                    if (x[iLoop + 14].equals("0") && x[iLoop + 15].equals("0") && x[iLoop + 21].equals("0")) {
                                        velocityTextView.setText("Machine in position clockwise move");
                                    } else if (x[iLoop + 14].equals("1") && x[iLoop + 15].equals("0") && x[iLoop + 21].equals("0")) {
                                        velocityTextView.setText("Machine in position Anticlockwise move");
                                    } else if (x[iLoop + 14].equals("0") && x[iLoop + 15].equals("1") && x[iLoop + 21].equals("0")) {
                                        velocityTextView.setText("Machine in velocity clockwise move");
                                    } else if (x[iLoop + 14].equals("1") && x[iLoop + 15].equals("1") && x[iLoop + 21].equals("0")) {
                                        velocityTextView.setText("Machine in velocity Anticlockwise move");
                                    }
                                    interVal = Integer.parseInt(x[iLoop + 17]);
                                    try {
                                        angle = Float.parseFloat(x[iLoop + 22]);
                                        String deg = x[iLoop + 22];
                                        System.out.println("Degree is " + deg);
                                        if(deg.length() == 1 || deg.length() == 2 || deg.length() == 3) {
                                            degreeText.setText(deg + "°");
                                        }

                                        connectionText.setText("Time : " + x[iLoop + 23] + "s");
                                        System.out.println("angle is " + angle + "s");
                                        Direction.setText(x[iLoop + 24]);
                                    } catch (Exception e) {
                                        System.out.println(e.toString());
                                    }
                                    if ((velocityMov.getText().toString().trim().equals("Clockwise") || velocityMov.getText().toString().trim().equals("ClockWise")) && x[iLoop + 14].equals("0") && (x[iLoop + 22].length() == 1 || x[iLoop + 22].length() == 2 || x[iLoop + 22].length() == 3) ) {
                                        if(angle < 180) {
                                            mArc.setColorCode(Color.RED);
                                        }
                                        else if(angle > 180) {
                                            mArc.setColorCode(Color.BLUE);
                                        }
                                        animation = new AnimationArc(mArc, angle - 90);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(angle > 0  && angle < 360) {
                                                    mArc.startAnimation(animation);
                                                }

                                            }
                                        });
                                        previous = angle;
                                    }
                                    else if (((posMove.getText().toString().trim().equals("Anticlockwise")) || posMove.getText().toString().trim().equals("AntiClockWise")) && x[iLoop + 14].equals("1") && (x[iLoop + 22].length() == 1 || x[iLoop + 22].length() == 2 || x[iLoop + 22].length() == 3) ) {
                                        System.out.println("Angles are " + newangle + ".." + previous + ".." + angle);
                                        if(angle1 < 180) {
                                            mArc.setColorCode(Color.RED);
                                        }
                                        else if(angle1 > 180) {
                                            mArc.setColorCode(Color.BLUE);
                                        }
                                        animation = new AnimationArc(mArc, (angle - 360) - 90);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                            if(angle > 0  && angle < 360) {
                                                mArc.startAnimation(animation);
                                            }
                                            }
                                        });
                                    }
                                }
                            });

                        }
                        catch(Exception e) {

                        }
                        if(stopFlag) {
                            velocityMov.setText("Velocity Move");
                            posMove.setText("Position Move");
                            velocityTextView.setText(" ");
                        }
                        if(reachedHomeBoolean) {
                            velocityMov.setText("Velocity Move");
                            posMove.setText("Position Move");
                            velocityTextView.setText("");
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
