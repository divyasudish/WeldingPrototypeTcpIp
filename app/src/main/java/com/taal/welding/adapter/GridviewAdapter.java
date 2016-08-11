package com.taal.welding.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taal.welding.R;

import com.taal.welding.activities.DataLogNewActivity;
import com.taal.welding.activities.HelpActivity;
import com.taal.welding.activities.MachineInfoActivity;
import com.taal.welding.activities.MachineProgressNewActivity;
import com.taal.welding.activities.MainMenuActivity;
import com.taal.welding.activities.Motor_GairBoxActivity;
import com.taal.welding.activities.NewDeviceActivity;
import com.taal.welding.activities.SensorActivity;
import com.taal.welding.activities.UpgradeFirmwareActivity;

/**
 * Created by divyashree_nair on 29/2/16.
 */
public class GridviewAdapter extends BaseAdapter {

    String [] result;
    Context context;
    Intent mIntent;
    int [] imageId;
    private String mKey = "MachineName";
    private String mProgresskey = "ProgressData";
    private LayoutInflater mInflater=null;
    public GridviewAdapter(MainMenuActivity mainActivity, String[] kurtasName, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=kurtasName;
        imageId = prgmImages;
        context=mainActivity;
        mInflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder=new Holder();
        View rowView;

        rowView = mInflater.inflate(R.layout.gridview_item, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img = (ImageView) rowView.findViewById(R.id.img);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (holder.tv.getText().toString().trim().equals("New Device")) {
                    mIntent = new Intent(context, NewDeviceActivity.class);
                    context.startActivity(mIntent);
                } else if (holder.tv.getText().toString().trim().equals("Device List")) {
                    mIntent = new Intent(context, MachineInfoActivity.class);
                    mIntent.putExtra(mKey, "Device");
                    context.startActivity(mIntent);
                } else if (holder.tv.getText().toString().trim().equals("Data Log")) {
                    mIntent = new Intent(context, DataLogNewActivity.class);
                    context.startActivity(mIntent);
                } else if (holder.tv.getText().toString().trim().equals("Torch Head Position")) {
                    mIntent = new Intent(context, MachineProgressNewActivity.class);
                    context.startActivity(mIntent);
                } else if (holder.tv.getText().toString().trim().equals("Calibration")) {
                    mIntent = new Intent(context, SensorActivity.class);
                    context.startActivity(mIntent);
                } else if (holder.tv.getText().toString().trim().equals("GearBox Status")) {
                    mIntent = new Intent(context, Motor_GairBoxActivity.class);
                    context.startActivity(mIntent);
                }
                else if (holder.tv.getText().toString().trim().equals("Firmware Upgrade")) {
                    mIntent = new Intent(context, UpgradeFirmwareActivity.class);
                    context.startActivity(mIntent);
                }
                else if (holder.tv.getText().toString().trim().equals("Help")) {
                    mIntent = new Intent(context, HelpActivity.class);
                    context.startActivity(mIntent);
                }
            }
        });

        return rowView;
    }

}