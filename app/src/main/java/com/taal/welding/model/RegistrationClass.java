package com.taal.welding.model;

/**
 * Created by Suneesh on 3/26/2016.
 */
public class RegistrationClass {
    private int _id;
    private String _userName;
    private String _password;
    private String _name;
    private String _confirmPassword;

    public RegistrationClass(){

    }

    public RegistrationClass(int id, String name, String password, String confirmPassword){
        this._id = id;
        this._name = name;
        this._password = password;
        this._confirmPassword = confirmPassword;
    }

    // constructor
    public RegistrationClass(String name, String username, String password, String confirmPassword){
        this._name = name;
        this._userName = username;
        this._password = password;
        this._confirmPassword = confirmPassword;
    }
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_confirmPassword() {
        return _confirmPassword;
    }

    public void set_confirmPassword(String _confirmPassword) {
        this._confirmPassword = _confirmPassword;
    }
}
