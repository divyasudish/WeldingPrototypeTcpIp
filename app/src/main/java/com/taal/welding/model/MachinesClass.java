package com.taal.welding.model;

/**
 * Created by divyashreenair on 27/4/16.
 */
public class MachinesClass {
    private String machinename;
    private String progress;
    private String serviceDue;

    public MachinesClass(String cMachine, String cProgress, String cServiceDue){
        machinename = cMachine;
        progress = cProgress;
        serviceDue = cServiceDue;

    }

    public String getMachinename() {
        return machinename;
    }

    public void setMachinename(String machinename) {
        this.machinename = machinename;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getServiceDue() {
        return serviceDue;
    }

    public void setServiceDue(String serviceDue) {
        this.serviceDue = serviceDue;
    }
}
