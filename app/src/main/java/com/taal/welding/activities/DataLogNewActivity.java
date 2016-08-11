package com.taal.welding.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.adapter.DataLogAdapter;
import com.taal.welding.adapter.MachineAdapter;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.DataLogClass;
import com.taal.welding.model.DeviceClass;
import com.taal.welding.model.MachineProgressList;

import java.util.ArrayList;
import java.util.List;

public class DataLogNewActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private List<DeviceClass> newList;
    private List<MachineProgressList> mList;
    private Spinner sp;
    private Button but;
    private ListView listView;
    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_log_new);

        sp = (Spinner) findViewById(R.id.spinner);
        but = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.dataLogList);
        linearLayout = (LinearLayout) findViewById(R.id.relativeLayout1);
        relativeLayout = (RelativeLayout) findViewById(R.id.rel);
        button = (Button) findViewById(R.id.button);

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

        db = new DatabaseHelper(getApplicationContext());
        newList = new ArrayList<>();
        newList = db.getAllNewDevices();
        mList = new ArrayList<>();

        List<String> list = new ArrayList<String>();
        for(int i = 0; i < newList.size(); i++) {
            list.add(newList.get(i).getDevice());
        }
        if(list.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No device in Database", Toast.LENGTH_SHORT).show();
        }
        if(!list.isEmpty()) {
            linearLayout.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        }
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text, list);
        sp.setAdapter(ad);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mList = MachineProgressNewActivity.machineProgressListArrayList;
                    listView.setAdapter(new DataLogAdapter(DataLogNewActivity.this, mList));
                }
                catch(Exception e) {

                }
                //System.out.println(mList.get(0).getBaro() + mList.get(0).getTemp() + ".." + mList.get(0).getAbsEncoder());
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return true;
    }
}
