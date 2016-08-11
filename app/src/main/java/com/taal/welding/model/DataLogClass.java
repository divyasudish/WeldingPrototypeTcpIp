package com.taal.welding.model;

/**
 * Created by divyashreenair on 18/7/16.
 */
public class DataLogClass {

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
    private String baro;
    private String temp;
    private String homePos;
    private String rel;
    private String abs;


    public DataLogClass() {

    }

    public DataLogClass(int id, String device, String accx, String accy, String accz, String gyrox, String gyroy, String gyroz, String magx, String magy, String magz, String GBR, String BAND, String GEARWHEEL, String Rel, String Abs){
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
        baro = GBR;
        temp = BAND;
        homePos = GEARWHEEL;
        rel = Rel;
        abs = Abs;
    }

    public DataLogClass(String device, String accx, String accy, String accz, String gyrox, String gyroy, String gyroz, String magx, String magy, String magz, String GBR, String BAND, String GEARWHEEL, String Rel, String Abs){
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
        baro = GBR;
        temp = BAND;
        homePos = GEARWHEEL;
        rel = Rel;
        abs = Abs;
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

    public String getBaro() {
        return baro;
    }

    public void setBaro(String baro) {
        this.baro = baro;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHomePos() {
        return homePos;
    }

    public void setHomePos(String homePos) {
        this.homePos = homePos;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }
}
