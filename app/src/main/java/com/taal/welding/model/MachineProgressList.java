package com.taal.welding.model;

/**
 * Created by divyashreenair on 18/7/16.
 */
public class MachineProgressList {

    private int Id;
    private String Device;
    private String position;
    private String velocity;
    private String AccX;
    private String AccY;
    private String AccZ;
    private String GyroX;
    private String GyroY;
    private String GyroZ;
    private String MagX;
    private String MagY;
    private String MagZ;
    private String Baro;
    private String Temp;
    private String ActualVel;
    private String RelEncodr;
    private String AbsEncoder;
    private String Clockwise;

    public MachineProgressList() {

    }

    public MachineProgressList(int id, String device, String accx, String accy, String accz, String gyrox, String gyroy, String gyroz, String magx, String magy, String magz, String baro, String temp, String actualVel, String relE, String absEn) {
        Id = id;
        Device = device;
        AccX = accx;
        AccY = accy;
        AccZ = accz;
        GyroX = gyrox;
        GyroY = gyroy;
        GyroZ = gyroz;
        MagX = magx;
        MagY = magy;
        MagZ = magz;
        Baro = baro;
        Temp = temp;
        ActualVel = actualVel;
        RelEncodr = relE;
        AbsEncoder = absEn;
    }

    public MachineProgressList(String device, String accx, String accy, String accz, String gyrox, String gyroy, String gyroz, String magx, String magy, String magz, String baro, String temp, String actualVel, String relE, String absEn, String clk) {
        Device = device;
        AccX = accx;
        AccY = accy;
        AccZ = accz;
        GyroX = gyrox;
        GyroY = gyroy;
        GyroZ = gyroz;
        MagX = magx;
        MagY = magy;
        MagZ = magz;
        Baro = baro;
        Temp = temp;
        ActualVel = actualVel;
        RelEncodr = relE;
        AbsEncoder = absEn;
        Clockwise = clk;
    }

    public MachineProgressList(String device, String pos, String vel, String accx, String accy, String accz, String gyrox, String gyroy, String gyroz, String magx, String magy, String magz, String baro, String temp, String actualVel, String relE, String absEn, String clk) {
        Device = device;
        position = pos;
        velocity = vel;
        AccX = accx;
        AccY = accy;
        AccZ = accz;
        GyroX = gyrox;
        GyroY = gyroy;
        GyroZ = gyroz;
        MagX = magx;
        MagY = magy;
        MagZ = magz;
        Baro = baro;
        Temp = temp;
        ActualVel = actualVel;
        RelEncodr = relE;
        AbsEncoder = absEn;
        Clockwise  = clk;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVelocity() {
        return velocity;
    }

    public void setVelocity(String velocity) {
        this.velocity = velocity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    public String getAccX() {
        return AccX;
    }

    public void setAccX(String accX) {
        AccX = accX;
    }

    public String getAccY() {
        return AccY;
    }

    public void setAccY(String accY) {
        AccY = accY;
    }

    public String getAccZ() {
        return AccZ;
    }

    public void setAccZ(String accZ) {
        AccZ = accZ;
    }

    public String getGyroX() {
        return GyroX;
    }

    public void setGyroX(String gyroX) {
        GyroX = gyroX;
    }

    public String getGyroY() {
        return GyroY;
    }

    public void setGyroY(String gyroY) {
        GyroY = gyroY;
    }

    public String getGyroZ() {
        return GyroZ;
    }

    public void setGyroZ(String gyroZ) {
        GyroZ = gyroZ;
    }

    public String getMagX() {
        return MagX;
    }

    public void setMagX(String magX) {
        MagX = magX;
    }

    public String getMagY() {
        return MagY;
    }

    public void setMagY(String magY) {
        MagY = magY;
    }

    public String getMagZ() {
        return MagZ;
    }

    public void setMagZ(String magZ) {
        MagZ = magZ;
    }

    public String getBaro() {
        return Baro;
    }

    public void setBaro(String baro) {
        Baro = baro;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public String getActualVel() {
        return ActualVel;
    }

    public void setActualVel(String actualVel) {
        ActualVel = actualVel;
    }

    public String getRelEncodr() {
        return RelEncodr;
    }

    public void setRelEncodr(String relEncodr) {
        RelEncodr = relEncodr;
    }

    public String getAbsEncoder() {
        return AbsEncoder;
    }

    public void setAbsEncoder(String absEncoder) {
        AbsEncoder = absEncoder;
    }

    public String getClockwise() {
        return Clockwise;
    }

    public void setClockwise(String clockwise) {
        Clockwise = clockwise;
    }
}
