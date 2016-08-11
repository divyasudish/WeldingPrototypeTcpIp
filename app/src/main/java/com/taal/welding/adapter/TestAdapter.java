package com.taal.welding.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.taal.welding.model.MachineInfoClass;

/**
 * Created by divyashreenair on 27/5/16.
 */
public class TestAdapter extends ArrayAdapter<MachineInfoClass> {
    public TestAdapter(Context context, int resource) {
        super(context, resource);
    }
}
