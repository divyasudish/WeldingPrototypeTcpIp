package com.taal.welding.model;

/**
 * Created by divyashreenair on 18/7/16.
 */
public class GearBoxClass {

    private int Id;
    private String DeviceNmae;
    private String BandId;
    private String BandOd;
    private String BandMd;
    private String PipeId;
    private String PipeOd;
    private String PipeMd;
    private String Gbr;
    private String BandDia;
    private String GearwheelDia;


    public GearBoxClass() {

    }

    public GearBoxClass(int id, String device, String bandId, String bandOd, String bandMd, String pipeId, String pipeOd, String pipeMd, String gbr, String banddia, String gearwheeldia){
        Id = id;
        DeviceNmae = device;
        BandId = bandId;
        BandOd = bandOd;
        BandMd = bandMd;
        PipeId = pipeId;
        PipeOd = pipeOd;
        PipeMd = pipeMd;
        Gbr = gbr;
        BandDia = banddia;
        GearwheelDia = gearwheeldia;
    }
    public GearBoxClass(String device, String bandId, String bandOd, String bandMd, String pipeId, String pipeOd, String pipeMd, String gbr, String banddia, String gearwheeldia){
        DeviceNmae = device;
        BandId = bandId;
        BandOd = bandOd;
        BandMd = bandMd;
        PipeId = pipeId;
        PipeOd = pipeOd;
        PipeMd = pipeMd;
        Gbr = gbr;
        BandDia = banddia;
        GearwheelDia = gearwheeldia;
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

    public String getBandId() {
        return BandId;
    }

    public void setBandId(String bandId) {
        BandId = bandId;
    }

    public String getBandOd() {
        return BandOd;
    }

    public void setBandOd(String bandOd) {
        BandOd = bandOd;
    }

    public String getBandMd() {
        return BandMd;
    }

    public void setBandMd(String bandMd) {
        BandMd = bandMd;
    }

    public String getPipeId() {
        return PipeId;
    }

    public void setPipeId(String pipeId) {
        PipeId = pipeId;
    }

    public String getPipeOd() {
        return PipeOd;
    }

    public void setPipeOd(String pipeOd) {
        PipeOd = pipeOd;
    }

    public String getPipeMd() {
        return PipeMd;
    }

    public void setPipeMd(String pipeMd) {
        PipeMd = pipeMd;
    }

    public String getGbr() {
        return Gbr;
    }

    public void setGbr(String gbr) {
        Gbr = gbr;
    }

    public String getBandDia() {
        return BandDia;
    }

    public void setBandDia(String bandDia) {
        BandDia = bandDia;
    }

    public String getGearwheelDia() {
        return GearwheelDia;
    }

    public void setGearwheelDia(String gearwheelDia) {
        GearwheelDia = gearwheelDia;
    }
}
