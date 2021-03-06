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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataLogNewActivity extends AppCompatActivity {
    @Bind(R.id.spinner)
    protected Spinner sp;
    @Bind(R.id.button)
    protected Button but;
    @Bind(R.id.dataLogList)
    protected ListView listView;
    @Bind(R.id.relativeLayout1)
    protected LinearLayout linearLayout;
    @Bind(R.id.rel)
    protected RelativeLayout relativeLayout;

    private DatabaseHelper db;
    private List<DeviceClass> newList;
    private List<MachineProgressList> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_log_new);
        ButterKnife.bind(this);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if ((Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT))
        {

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
            but.setVisibility(View.VISIBLE);
        }
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text, list);
        sp.setAdapter(ad);

    }
    @OnClick(R.id.button)
    protected void click() {
        try {
            mList = MachineProgressNewActivity.machineProgressListArrayList;
            listView.setAdapter(new DataLogAdapter(DataLogNewActivity.this, mList));
        }
        catch(Exception e) {

        }
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
