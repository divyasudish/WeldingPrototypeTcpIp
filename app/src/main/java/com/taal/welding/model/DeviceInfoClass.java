package com.taal.welding.model;

/**
 * Created by divyashreenair on 9/5/16.
 */
public class DeviceInfoClass {
    private String deviceName;
    private String deviceActivity;

    public DeviceInfoClass(String device, String deviceAct){
        deviceName = device;
        deviceActivity = deviceAct;
    }
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceActivity() {
        return deviceActivity;
    }

    public void setDeviceActivity(String deviceActivity) {
        this.deviceActivity = deviceActivity;
    }
}
