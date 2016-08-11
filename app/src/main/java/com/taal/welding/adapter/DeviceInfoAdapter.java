package com.taal.welding.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.activities.MachineProgressNewActivity;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.DeviceClass;
import com.taal.welding.model.MachinesList;
import com.taal.welding.model.NewDevice;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by divyashreenair on 27/4/16.
 */
public class DeviceInfoAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<DeviceClass> mDeviceList;
    private Intent mIntent;
    DatabaseHelper db;
    private List<String> list;

    public DeviceInfoAdapter(Context context, ArrayList<DeviceClass> list) {
        this.mDeviceList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.mDeviceList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.activity_new_device_info_item, null);
            viewHolder = new ViewHolder();
            db = new DatabaseHelper(mContext);
            viewHolder.ip = (EditText) convertView.findViewById(R.id.ipValue);
            viewHolder.device = (EditText) convertView.findViewById(R.id.devicenameValue);
            viewHolder.spin = (Spinner) convertView.findViewById(R.id.spinner);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("OpenDbListAdapter ", "List View Clicked");
            }
        });
        viewHolder.ip.setText(mDeviceList.get(position).getIp());
        viewHolder.device.setText(mDeviceList.get(position).getDevice());
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

        return convertView;
    }

    class ViewHolder {
        public EditText ip;
        public EditText device;
        public Spinner spin;
    }
}