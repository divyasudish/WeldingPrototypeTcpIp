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
import com.taal.welding.model.MachinesList;


/**
 * Created by divyashreenair on 27/4/16.
 */
public class MachineAdapter extends BaseAdapter {

    private Context mContext;
    private MachinesList mMachinesList;
    private Intent mIntent;
    private String mKey = "MachineName";
    private String mProgresskey = "ProgressData";

    public MachineAdapter(Context context, MachinesList machinesList) {
        this.mMachinesList = machinesList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.mMachinesList.getList().size();
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
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.activity_machine_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.machineName);
            viewHolder.progressdata = (TextView) convertView.findViewById(R.id.progressData);
            viewHolder.serviceDue = (TextView) convertView.findViewById(R.id.servicedueData);
            viewHolder.progress = (TextView) convertView.findViewById(R.id.progress);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(mMachinesList.getList().get(position).getProgress().trim().equals("100%")) {
            viewHolder.name.setText(mMachinesList.getList().get(position).getMachinename());
            viewHolder.name.setBackgroundResource(R.drawable.progress_profile_complete);
        }
        else if(mMachinesList.getList().get(position).getProgress().trim().equals("0%")) {
            viewHolder.name.setText(mMachinesList.getList().get(position).getMachinename());
            viewHolder.name.setBackgroundResource(R.drawable.progress_profile_complete);
        }
        else {
            viewHolder.name.setText(mMachinesList.getList().get(position).getMachinename());
            viewHolder.name.setBackgroundResource(R.drawable.progress_profile);
        }

        viewHolder.progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(mContext, MachineProgressNewActivity.class);
//                mIntent.putExtra(mKey, viewHolder.name.getText().toString().trim());
//                mIntent.putExtra(mProgresskey, viewHolder.progressdata.getText().toString().trim());
                mContext.startActivity(mIntent);
            }
        });
        viewHolder.progressdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(mContext, MachineProgressNewActivity.class);
//                mIntent.putExtra(mKey, viewHolder.name.getText().toString().trim());
//                mIntent.putExtra(mProgresskey, viewHolder.progressdata.getText().toString().trim());
                mContext.startActivity(mIntent);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.v("OpenDbListAdapter ", "List View Clicked");
            }
        });

        viewHolder.progressdata.setText(mMachinesList.getList().get(position).getProgress());
        viewHolder.serviceDue.setText(mMachinesList.getList().get(position).getServiceDue());
        return convertView;
    }

    class ViewHolder {
        public TextView name;
        public TextView progress;
        public TextView progressdata;
        public TextView serviceDue;

    }
}