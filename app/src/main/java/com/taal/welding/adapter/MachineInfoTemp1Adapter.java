package com.taal.welding.adapter;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.taal.welding.R;
import com.taal.welding.model.DeviceClass;
import com.taal.welding.model.MachineInfoList;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by divyashreenair on 28/4/16.
 */
public class MachineInfoTemp1Adapter extends BaseAdapter {

    private Context mContext;
    public List<DeviceClass> mMachineInfoList;
    private Intent mIntent;
    private String mKey = "DeviceName";
    private InputMethodManager imm;
    private List<String> list;

    public MachineInfoTemp1Adapter(Context context, List<DeviceClass> machinesList) {
        this.mMachineInfoList = machinesList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.mMachineInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.activity_machine_info_item, null);
            viewHolder = new ViewHolder();
            imm = (InputMethodManager)mContext.getSystemService(Service.INPUT_METHOD_SERVICE);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            viewHolder.devicename = (TextView) convertView.findViewById(R.id.deviceName);
            viewHolder.spin = (Spinner) convertView.findViewById(R.id.spinner);
            list = new ArrayList<String>();
            list.add("BLDC Motor");
            list.add("IMU");
            list.add("Relative encoder");
            list.add("Absolute encoder");
            list.add("Digital Input");
            list.add("Proximity Input");
            list.add("Solenoid");
            list.add("All");
            ArrayAdapter<String> ad = new ArrayAdapter<String>(mContext, R.layout.spinner_text, list);
            viewHolder.spin.setAdapter(ad);

            View view = ( (Activity) mContext ).getCurrentFocus();
            if ( view != null ) {
                imm.hideSoftInputFromWindow( view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.devicename.setText(mMachineInfoList.get(position).getDevice());
        return convertView;
    }
    class ViewHolder {
        public CheckBox checkBox;
        public TextView devicename;
        public Spinner spin;
    }

    public void hideKeyBoard() {
        View view = ( (Activity) mContext ).getCurrentFocus();
        if ( view != null ) {
            imm.hideSoftInputFromWindow( view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
        }
    }
}