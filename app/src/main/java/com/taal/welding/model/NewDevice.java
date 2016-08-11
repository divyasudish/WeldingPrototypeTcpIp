package com.taal.welding.model;

/**
 * Created by divyashreenair on 8/7/16.
 */
public class NewDevice {

    private int id;
    private String ip;
    private String deviceName;
    private String operation;
    private Boolean check;
    private String positionvalue;
    private String velocityVal;

    public NewDevice(){

    }
    public NewDevice(int Id, String IP, String DeviceName) {
        id = Id;
        ip = IP;
        deviceName = DeviceName;
    }
    public NewDevice(String IP, String DeviceName) {
        ip = IP;
        deviceName = DeviceName;
    }
    public NewDevice(Boolean checkBox, String devicename, String val, String velVal) {
        check = checkBox;
        deviceName = devicename;
        positionvalue = val;
        velocityVal = velVal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getPositionvalue() {
        return positionvalue;
    }

    public void setPositionvalue(String positionvalue) {
        this.positionvalue = positionvalue;
    }

    public String getVelocityVal() {
        return velocityVal;
    }

    public void setVelocityVal(String velocityVal) {
        this.velocityVal = velocityVal;
    }
}
