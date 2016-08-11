package com.taal.welding.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taal.welding.R;
import com.taal.welding.activities.MachineProgressNewActivity;
import com.taal.welding.model.MachineProgressList;
import com.taal.welding.model.MachinesList;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by divyashreenair on 27/4/16.
 */
public class DataLogAdapter extends BaseAdapter {

    private Context mContext;
    private Intent mIntent;
    private List<MachineProgressList> mList;

    public DataLogAdapter(Context context, List<MachineProgressList> machineProgressLists) {
        this.mList = machineProgressLists;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.mList.size();
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
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.machine_list, null);
            viewHolder = new ViewHolder();
            viewHolder.acc = (TextView) convertView.findViewById(R.id.acc);
            viewHolder.gyro = (TextView) convertView.findViewById(R.id.gyro);
            viewHolder.magneto = (TextView) convertView.findViewById(R.id.mag);
            viewHolder.baro = (TextView) convertView.findViewById(R.id.baro);
            viewHolder.temp = (TextView) convertView.findViewById(R.id.temp);
            viewHolder.velocity = (TextView) convertView.findViewById(R.id.vel);
            viewHolder.encoder = (TextView) convertView.findViewById(R.id.Relencoder);
            viewHolder.abs = (TextView) convertView.findViewById(R.id.Absencoder);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.acc.setText(mList.get(position).getAccX());
        viewHolder.gyro.setText(mList.get(position).getGyroX());
        viewHolder.magneto.setText(mList.get(position).getMagX());
        viewHolder.baro.setText(mList.get(position).getBaro());
        viewHolder.temp.setText(mList.get(position).getTemp());
        viewHolder.velocity.setText(mList.get(position).getActualVel());
        viewHolder.encoder.setText(mList.get(position).getAbsEncoder());
        viewHolder.abs.setText(mList.get(position).getAbsEncoder());


        return convertView;
    }

    class ViewHolder {
        public TextView acc;
        public TextView gyro;
        public TextView magneto;
        public TextView baro;
        public TextView temp;
        public TextView velocity;
        public TextView encoder;
        public TextView abs;

    }
}