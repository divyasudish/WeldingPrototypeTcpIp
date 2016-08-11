package com.taal.welding.model;

/**
 * Created by divyashreenair on 18/7/16.
 */
public class DeviceClass {

    private int id;
    private String Ip;
    private String Device;
    private String operation;

    public DeviceClass() {

    }

    public DeviceClass(String ip, String device, String sub){
        Ip = ip;
        Device = device;
        operation = sub;
    }
    public DeviceClass(String ip, String device){
        Ip = ip;
        Device = device;
    }


    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
