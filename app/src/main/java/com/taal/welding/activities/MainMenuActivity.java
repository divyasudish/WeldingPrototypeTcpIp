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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.adapter.GridviewAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainMenuActivity extends AppCompatActivity {

    private GridView mGridView;
    private String dataFromHyperTerminal = "{,10,20.00,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x31,256,}";
    private List<String> listData;
    private List<List<String>> listOfLists = new ArrayList<List<String>>();
    private ArrayList choiceList = new ArrayList<String>();
    private ArrayList dataList = new ArrayList<String>();
    private int j = 3;
    private String [] List={"New Device","Device List","Data Log","Torch Head Position","Calibration","Firmware Upgrade"};
    int [] imageList = { R.drawable.new_device, R.drawable.device_selection, R.drawable.device_status, R.drawable.header_position, R.drawable.calibration, R.drawable.firmware};
    private TextView help;
    //private Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        help = (TextView) findViewById(R.id.helpmenu);
        //log = (Button) findViewById(R.id.logout);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HelpActivity.class));
            }
        });


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
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
            try{
                getSupportActionBar().setTitle("BLDC PoC");

            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        mGridView=(GridView) findViewById(R.id.gridView);
        mGridView.setAdapter(new GridviewAdapter(this, List, imageList));
        functionSeperation(dataFromHyperTerminal);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)){
            Toast.makeText(this,
                    "FEATURE_BLUETOOTH NOT support",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            System.out.println("Inside back button ");
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            //finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void functionSeperation(String dataFromHyperTerminal) {
        String[] arrayList = dataFromHyperTerminal.split(",");
        choiceList.clear();
        dataList.clear();
        listOfLists.clear();
        listOfLists.add(new ArrayList<String>());
        if(arrayList[0].equals("{") && arrayList[arrayList.length - 1].equals("}")) {
            choiceList.add(arrayList[0]);
            choiceList.add(arrayList[1]);
            choiceList.add(arrayList[2]);
            choiceList.add(arrayList[arrayList.length - 2]);
            choiceList.add(arrayList[arrayList.length - 1]);

            int x = Integer.parseInt(arrayList[1]);
            for (int i = 0; i < x; i++) {
                dataList.add(arrayList[j]);
                j++;
            }
            for (int k = 0; k < dataList.size(); k++) {
                String f1 = hexToInt(dataList.get(k).toString());
                listOfLists.get(0).add(f1);
            }
            for(int i = 0; i < listOfLists.get(0).size(); i++) {
                System.out.println(listOfLists.get(0).get(i));
            }
        }
    }

    private String hexToInt(String x) {
        StringBuilder sb = new StringBuilder();
        int ival = Integer.parseInt(x.replace("0x", ""), 16);
        sb.append((char) ival);
        String string = sb.toString();
        return string;
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
