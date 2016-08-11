package com.taal.welding.model;

/**
 * Created by divyashreenair on 18/7/16.
 */
public class SensorClass {

    private int Id;
    private String DeviceNmae;
    private String accX;
    private String accY;
    private String accZ;
    private String gyroX;
    private String gyroY;
    private String gyroZ;
    private String magX;
    private String magY;
    private String magZ;
    private String gbr;
    private String bandDia;
    private String GearwheelDia;
    private String circum;
    private String fullCycle;
    private String rpm;


    public SensorClass() {

    }

    public SensorClass(int id, String device, String accx, String accy, String accz, String gyrox, String gyroy, String gyroz, String magx, String magy, String magz, String GBR, String GEARWHEEL, String BAND, String CIRCUM, String FULLCYCLE, String RPM){
        Id = id;
        DeviceNmae = device;
        accX = accx;
        accY = accy;
        accZ = accz;
        gyroX = gyrox;
        gyroY = gyroy;
        gyroZ = gyroz;
        magX = magx;
        magY = magy;
        magZ = magz;
        gbr = GBR;
        bandDia = BAND;
        GearwheelDia = GEARWHEEL;
        circum = CIRCUM;
        fullCycle = FULLCYCLE;
        rpm = RPM;
    }

    public SensorClass(String device, String accx, String accy, String accz, String gyrox, String gyroy, String gyroz, String magx, String magy, String magz, String GBR, String GEARWHEEL, String BAND, String CIRCUM, String FULLCYCLE, String RPM){
        DeviceNmae = device;
        accX = accx;
        accY = accy;
        accZ = accz;
        gyroX = gyrox;
        gyroY = gyroy;
        gyroZ = gyroz;
        magX = magx;
        magY = magy;
        magZ = magz;
        gbr = GBR;
        bandDia = BAND;
        GearwheelDia = GEARWHEEL;
        circum = CIRCUM;
        fullCycle = FULLCYCLE;
        rpm = RPM;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDeviceNmae() {
        return DeviceNmae;
    }

    public void setDeviceNmae(String deviceNmae) {
        DeviceNmae = deviceNmae;
    }

    public String getAccX() {
        return accX;
    }

    public void setAccX(String accX) {
        this.accX = accX;
    }

    public String getAccY() {
        return accY;
    }

    public void setAccY(String accY) {
        this.accY = accY;
    }

    public String getAccZ() {
        return accZ;
    }

    public void setAccZ(String accZ) {
        this.accZ = accZ;
    }

    public String getGyroX() {
        return gyroX;
    }

    public void setGyroX(String gyroX) {
        this.gyroX = gyroX;
    }

    public String getGyroY() {
        return gyroY;
    }

    public void setGyroY(String gyroY) {
        this.gyroY = gyroY;
    }

    public String getGyroZ() {
        return gyroZ;
    }

    public void setGyroZ(String gyroZ) {
        this.gyroZ = gyroZ;
    }

    public String getMagX() {
        return magX;
    }

    public void setMagX(String magX) {
        this.magX = magX;
    }

    public String getMagY() {
        return magY;
    }

    public void setMagY(String magY) {
        this.magY = magY;
    }

    public String getMagZ() {
        return magZ;
    }

    public void setMagZ(String magZ) {
        this.magZ = magZ;
    }

    public String getGbr() {
        return gbr;
    }

    public void setGbr(String gbr) {
        this.gbr = gbr;
    }

    public String getBandDia() {
        return bandDia;
    }

    public void setBandDia(String bandDia) {
        this.bandDia = bandDia;
    }

    public String getGearwheelDia() {
        return GearwheelDia;
    }

    public void setGearwheelDia(String gearwheelDia) {
        GearwheelDia = gearwheelDia;
    }

    public String getCircum() {
        return circum;
    }

    public void setCircum(String circum) {
        this.circum = circum;
    }

    public String getFullCycle() {
        return fullCycle;
    }

    public void setFullCycle(String fullCycle) {
        this.fullCycle = fullCycle;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }
}
