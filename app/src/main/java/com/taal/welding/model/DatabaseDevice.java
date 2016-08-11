package com.taal.welding.model;

/**
 * Created by divyashreenair on 9/5/16.
 */
public class DatabaseDevice {
    private int _id;
    private String name;
    private String activity;
    private String editActivity;

    public DatabaseDevice(){

    }

    public DatabaseDevice(int id, String Name, String Activity, String EditActivity){
        this._id = id;
        this.name = Name;
        this.activity = Activity;
        this.editActivity = EditActivity;
    }
    public DatabaseDevice(String Name, String Activity, String EditActivity){
        this.name = Name;
        this.activity = Activity;
        this.editActivity = EditActivity;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getEditActivity() {
        return editActivity;
    }

    public void setEditActivity(String editActivity) {
        this.editActivity = editActivity;
    }
}
