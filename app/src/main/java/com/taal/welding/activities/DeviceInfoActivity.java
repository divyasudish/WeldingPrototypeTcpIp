package com.taal.welding.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.taal.welding.R;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.DatabaseDevice;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceInfoActivity extends AppCompatActivity {
    @Bind(R.id.saveBut)
    protected Button mButton;
    @Bind(R.id.readioGroup)
    protected RadioGroup radioGroup;
    @Bind(R.id.temperature)
    protected RadioButton mRadioButton1;
    @Bind(R.id.velocity)
    protected RadioButton mRadioButton2;
    @Bind(R.id.pressure)
    protected RadioButton mRadioButton3;
    @Bind(R.id.volume)
    protected RadioButton mRadioButton4;
    @Bind(R.id.tempEdit)
    protected EditText temp;
    @Bind(R.id.volumeEdit)
    protected EditText vol;
    @Bind(R.id.velocityEdit)
    protected EditText velocity;
    @Bind(R.id.pressureEdit)
    protected EditText pressure;

    private String mKey = "DeviceName";
    private String mTitle;
    private Bundle mBundle;
    private List<DatabaseDevice> arrayList;
    private String mSelectedText = null;
    private DatabaseHelper databaseHelper;
    private int pos;
    private int pos1;
    private String mEditString = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
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
        databaseHelper = new DatabaseHelper(this);

        temp.setEnabled(false);
        vol.setEnabled(false);
        velocity.setEnabled(false);
       pressure.setEnabled(false);
        arrayList = new ArrayList<>();
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mTitle = mBundle.getString(mKey);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
            getSupportActionBar().setTitle(mTitle + " Info");
        }
        radioGroup = (RadioGroup) findViewById(R.id.readioGroup);
        arrayList = databaseHelper.getAllDeviceactivities();
        System.out.println("arraylist size is " + arrayList.size());
        for(int i = 0; i < arrayList.size(); i++){
            System.out.println("inside for size ");
            if(mTitle.trim().equals(arrayList.get(i).getName())){
                System.out.println("2 dvices " + mTitle.toString().trim() + ".. " + arrayList.get(i).getName() + ".. " + arrayList.get(i).getActivity());
                if(arrayList.get(i).getActivity().trim().equals("Temperature")){
                    System.out.println("ddd" + arrayList.get(i).getActivity());
                    mRadioButton1.setChecked(true);
                    temp.setText(arrayList.get(i).getEditActivity());
                    velocity.setText("");
                    pressure.setText("");
                    vol.setText("");
                }
                else if(arrayList.get(i).getActivity().trim().equals("Velocity")){
                    System.out.println("eee" + arrayList.get(i).getActivity());
                    mRadioButton2.setChecked(true);
                    velocity.setText(arrayList.get(i).getEditActivity());
                    temp.setText("");
                    vol.setText("");
                    pressure.setText("");
                }
                else if(arrayList.get(i).getActivity().equals("Pressure")){
                    System.out.println("fff" + arrayList.get(i).getActivity());
                    mRadioButton3.setChecked(true);
                    pressure.setText(arrayList.get(i).getEditActivity());
                    velocity.setText("");
                    temp.setText("");
                    vol.setText("");
                }
                else if(arrayList.get(i).getActivity().equals("Volume")){
                    System.out.println("ggg " + arrayList.get(i).getActivity());
                    mRadioButton4.setChecked(true);
                    vol.setText(arrayList.get(i).getEditActivity());
                    temp.setText("");
                    velocity.setText("");
                    pressure.setText("");
                }
            }
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                pos=radioGroup.indexOfChild(findViewById(checkedId));

                pos1=radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
                switch (pos)
                {
                    case 0 :
                        temp.setEnabled(true);
                        velocity.setEnabled(false);
                        pressure.setEnabled(false);
                        vol.setEnabled(false);
                        velocity.setText("");
                        pressure.setText("");
                        vol.setText("");
                        mSelectedText = "Temperature";
                        System.out.println("Temperature is " + temp.getText().toString().trim());
                        break;
                    case 1 :
                        velocity.setEnabled(true);
                        temp.setEnabled(false);
                        pressure.setEnabled(false);
                        vol.setEnabled(false);
                        temp.setText("");
                        pressure.setText("");
                        vol.setText("");
                        mSelectedText = "Velocity";
                        break;
                    case 2 :
                        pressure.setEnabled(true);
                        temp.setEnabled(false);
                        velocity.setEnabled(false);
                        vol.setEnabled(false);
                        mSelectedText = "Pressure";
                        velocity.setText("");
                        vol.setText("");
                        temp.setText("");
                        break;
                    case 3 :
                        vol.setEnabled(true);
                        temp.setEnabled(false);
                        velocity.setEnabled(false);
                        pressure.setEnabled(false);
                        mSelectedText = "Volume";
                        temp.setText("");
                        velocity.setText("");
                        pressure.setText("");
                    default :
                        break;
                }
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
    @OnClick(R.id.saveBut)
    protected void save(){
        try{
            if(!(temp.getText().toString().trim().isEmpty()) && mRadioButton1.isChecked()) {
                mEditString = temp.getText().toString().trim();
            }
            else if(!(velocity.getText().toString().trim().isEmpty()) && mRadioButton2.isChecked()) {
                mEditString = velocity.getText().toString().trim();
            }
            else if(!(vol.getText().toString().trim().isEmpty()) && mRadioButton4.isChecked()) {
                mEditString = vol.getText().toString().trim();
            }
            else if(!(pressure.getText().toString().trim().isEmpty()) && mRadioButton3.isChecked()) {
                mEditString = pressure.getText().toString().trim();
            }
            System.out.println("Edittext " +mSelectedText.toString().trim() + ".. " + mTitle.toString().trim() + ".." +  mEditString.toString().trim());
            if((!mTitle.toString().trim().isEmpty()) && (!mSelectedText.toString().trim().isEmpty()) && (!mEditString.toString().trim().isEmpty())) {
                databaseHelper.createDevice(new DatabaseDevice(mTitle.toString().trim(), mSelectedText.toString().trim(), mEditString.toString().trim()));
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        this.onBackPressed();
    }
}
