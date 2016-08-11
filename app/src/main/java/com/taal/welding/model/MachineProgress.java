package com.taal.welding.model;

/**
 * Created by divyashreenair on 18/7/16.
 */
public class MachineProgress {

    private int Id;
    private String Device;
    private String Pos;
    private String Velocity;
    private String Abspulse;

    public MachineProgress() {

    }

    public MachineProgress(int id, String device, String pos, String vel, String pulse){
        Id = id;
        Device = device;
        Pos = pos;
        Velocity = vel;
        Abspulse = pulse;
    }
    public MachineProgress(String device, String pos, String vel, String pulse){
        Device = device;
        Pos = pos;
        Velocity = vel;
        Abspulse = pulse;
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

    public String getPos() {
        return Pos;
    }

    public void setPos(String pos) {
        Pos = pos;
    }

    public String getVelocity() {
        return Velocity;
    }

    public void setVelocity(String velocity) {
        Velocity = velocity;
    }

    public String getAbspulse() {
        return Abspulse;
    }

    public void setAbspulse(String abspulse) {
        Abspulse = abspulse;
    }
}
