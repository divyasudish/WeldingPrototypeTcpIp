package com.taal.welding.model;

/**
 * Created by divyashreenair on 28/4/16.
 */
public class MachineInfoClass {
    private Boolean check;
    private String deviceName;
    private String value;

    public MachineInfoClass(Boolean checkBox, String devicename, String val){
        check = checkBox;
        deviceName = devicename;
        value = val;
    }
    public MachineInfoClass(String devicename, String val){
        deviceName = devicename;
        value = val;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
