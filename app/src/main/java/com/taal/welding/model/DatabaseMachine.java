package com.taal.welding.model;

/**
 * Created by divyashreenair on 11/5/16.
 */
public class DatabaseMachine {
    private int _id;
    private String mName;
    private String mDeviceName;
    private String mDeviceEdit;
    private Boolean mCheck;

    public DatabaseMachine() {

    }
    public DatabaseMachine(String machineName, String deviceName, String deviceEdit){
        mName = machineName;
        mDeviceName = deviceName;
        mDeviceEdit = deviceEdit;
        //mCheck = checkVal;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDeviceName() {
        return mDeviceName;
    }

    public void setmDeviceName(String mDeviceName) {
        this.mDeviceName = mDeviceName;
    }

    public String getmDeviceEdit() {
        return mDeviceEdit;
    }

    public void setmDeviceEdit(String mDeviceEdit) {
        this.mDeviceEdit = mDeviceEdit;
    }

    public Boolean getmCheck() {
        return mCheck;
    }

    public void setmCheck(Boolean mCheck) {
        this.mCheck = mCheck;
    }
}
