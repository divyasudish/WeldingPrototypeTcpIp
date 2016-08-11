package com.taal.welding.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.taal.welding.model.DataLogClass;
import com.taal.welding.model.DatabaseDevice;
import com.taal.welding.model.DatabaseMachine;
import com.taal.welding.model.DeviceClass;
import com.taal.welding.model.GearBoxClass;
import com.taal.welding.model.MachineProgress;
import com.taal.welding.model.MachineProgressList;
import com.taal.welding.model.NewDevice;
import com.taal.welding.model.RegistrationClass;
import com.taal.welding.model.SensorClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suneesh on 3/26/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    // Database Version
    private static final int DATABASE_VERSION = 25;
    // Database Name
    private static final String DATABASE_NAME = "Welding";

    private static final String TABLE_REG = "Registration";
    private static final String KEY_REG_ID = "reg_id";
    private static final String KEY_REG_NAME = "regname";
    private static final String KEY_REG_USERNAME = "regUsername";
    private static final String KEY_REG_PASSWORD = "regPassword";
    private static final String KEY_REG_CONFIRM_PASSWORD = "regConfirmPassword";

    private static final String TABLE_DEVICE_NEW = "NewDevice";
    private static final String KEY_NEW_DEVICE_ID = "new_id";
    private static final String KEY_NEW_DEVICE_IP = "new_deviceIp";
    private static final String KEY_DEVICE_NAME = "new_device_name";
    private static final String NEW_DEVICE_OPERATION = "new_device_operation";

    private static final String TABLE_MACHINE_DEVICE_NEWDEVICE_NEW = "M_NewDevice";
    private static final String KEY_M_NEW_DEVICE_ID = "m_new_id";
    private static final String KEY_M_NEW_DEVICE_IP = "m_new_deviceIp";
    private static final String KEY_M_DEVICE_NAME = "m_new_device_name";
    private static final String NEW_M_DEVICE_OPERATION = "m_new_device_operation";

    private static final String TABLE_GEARBOX = "GearBox";
    private static final String KEY_GEARBOX_ID = "gearBox_id";
    private static final String KEY_Gear_DEVICE_NAME = "deviceName";
    private static final String KEY_band_id = "bandId";
    private static final String KEY_band_od = "bandOd";
    private static final String KEY_band_md = "bandMd";
    private static final String KEY_pipe_id = "pipeId";
    private static final String KEY_pipe_od = "pipeOd";
    private static final String KEY_pipe_md = "pipeMd";
    private static final String KEY_GEARBOX_RATIO = "gbr";
    private static final String KEY_BAND_DIA = "band_dia";
    private static final String KEY_GEAR_WHEEL_DIA = "gear_wheel_dia";

    private static final String TABLE_SENSOR = "Sensor";
    private static final String KEY_Sensor_ID = "sensor_id";
    private static final String KEY_Sensor_DEVICE_NAME = "sensordeviceName";
    private static final String KEY_accX = "accX";
    private static final String KEY_accY = "accY";
    private static final String KEY_accZ = "accZ";
    private static final String KEY_gyroX = "gyroX";
    private static final String KEY_gyroY = "gyroY";
    private static final String KEY_gyroZ = "gyroZ";
    private static final String KEY_magXX = "magX";
    private static final String KEY_magY = "magY";
    private static final String KEY_magZ = "magZ";
    private static final String KEY_SENSOR_GBR = "gbr";
    private static final String KEY_Sen_BAND_DIA = "bandDia";
    private static final String KEY_SENSOR_GEAR_WHEEL_DIA = "gearwheelDia";
    private static final String KEY_SEN_FULL_CYCLE = "fullcycle";
    private static final String KEY_SEN_CIRCUM = "circum";
    private static final String KEY_SEN_RPM = "rpm";


    private static final String TABLE_DATALOG = "DAtalog";
    private static final String KEY_Datalog_ID = "datalog_id";
    private static final String KEY_Datalog_DEVICE_NAME = "sensordeviceName";
    private static final String KEY_Data_accX = "D_accX";
    private static final String KEY_Data_accY = "D_accY";
    private static final String KEY_Data_accZ = "D_accZ";
    private static final String KEY_Data_gyroX = "D_gyroX";
    private static final String KEY_Data_gyroY = "D_gyroY";
    private static final String KEY_Data_gyroZ = "D_gyroZ";
    private static final String KEY_Data_magXX = "D_magX";
    private static final String KEY_Data_magY = "D_magY";
    private static final String KEY_Data_magZ = "D_magZ";
    private static final String KEY_Data_baro = "D_baro";
    private static final String KEY_Data_temp = "D_temp";
    private static final String KEY_Home_position = "D_Home_position";
    private static final String KEY_Data_rel = "D_rel";
    private static final String KEY_Data_abs = "D_Abs";


    private static final String TABLE_DEVICE_INFO = "DeviceInfo";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "devicename";
    private static final String KEY_ACTIVITY = "deviceActivity";
    private static final String KEY_EDIT = "deviceActivityEdit";

    private static final String TABLE_MACHINE_INFO = "MachineInfo";
    private static final String KEY_MACHINE_ID = "mid";
    private static final String KEY_MACHINE_NAME = "machinename";
    private static final String KEY_MACHINE_DEVICE_NAME = "machineDevice";
    private static final String KEY_MACHINE_EDIT = "machineEdit";

    private static final String TABLE_MACHINE_PROGRESS_List = "MachineProgressList";
    private static final String KEY_MACHINE_PROGRESS_List_ID = "machinepid";
    private static final String KEY_MACHINE_PROGRESS_LIST_DEVICE_NAME = "machineListDevicename";
    private static final String KEY_MACHINE_PROGRESSLIST_ACCX = "machineListAccX";
    private static final String KEY_MACHINE_PROGRESSLIST_ACCY = "machineListAccY";
    private static final String KEY_MACHINE_PROGRESSLIST_ACCZ = "machineListAccZ";
    private static final String KEY_MACHINE_PROGRESSLIST_GYROX = "machineListGyroX";
    private static final String KEY_MACHINE_PROGRESSLIST_GYROY= "machineListGyroY";
    private static final String KEY_MACHINE_PROGRESSLIST_GYROZ = "machineListGyroZ";
    private static final String KEY_MACHINE_PROGRESSLIST_MAGX = "machineListMagX";
    private static final String KEY_MACHINE_PROGRESSLIST_MAGY = "machineListMagY";
    private static final String KEY_MACHINE_PROGRESSLIST_MAGZ = "machineListMagZ";
    private static final String KEY_MACHINE_PROGRESSLIST_Baro = "machineListBaro";
    private static final String KEY_MACHINE_PROGRESSLIST_Temp = "machineListTemp";
    private static final String KEY_MACHINE_PROGRESSLIST_ActualVel = "machineListActualVelocity";
    private static final String KEY_MACHINE_PROGRESSLIST_RelEncoder = "machineListRelEncoder";
    private static final String KEY_MACHINE_PROGRESSLIST_AbsEncoder = "machineListAbsEncoder";

    private static final String TABLE_MACHINE_PROGRESS = "MachineProgress";
    private static final String KEY_MACHINE_PROGRESS_ID = "mpid";
    private static final String KEY_MACHINE_P_DEVICE_NAME = "machineDevicename";
    private static final String KEY_MACHINE_Pos = "machinePos";
    private static final String KEY_MACHINE_Velocity = "machineVelocity";
    private static final String KEY_MACHINE_ABS_PULSE = "abdpulse";


    private static final String CREATE_TABLE_REGISTRATION = "CREATE TABLE "
            + TABLE_REG + "(" + KEY_REG_ID + " INTEGER PRIMARY KEY," + KEY_REG_NAME + " TEXT," + KEY_REG_USERNAME
            + " TEXT," + KEY_REG_PASSWORD + " TEXT," + KEY_REG_CONFIRM_PASSWORD + " TEXT" + ")";

    private static final String CREATE_TABLE_NEW_DEVICE = "CREATE TABLE "
            + TABLE_DEVICE_NEW + "(" + KEY_NEW_DEVICE_ID + " INTEGER PRIMARY KEY," + KEY_NEW_DEVICE_IP + " TEXT," + KEY_DEVICE_NAME + " TEXT," +
            NEW_DEVICE_OPERATION + " TEXT" + ")";

    private static final String CREATE_TABLE_MACHINE_NEW_DEVICE = "CREATE TABLE "
            + TABLE_MACHINE_DEVICE_NEWDEVICE_NEW + "(" + KEY_M_NEW_DEVICE_ID + " INTEGER PRIMARY KEY," + KEY_M_NEW_DEVICE_IP + " TEXT," + KEY_M_DEVICE_NAME + " TEXT," +
            NEW_M_DEVICE_OPERATION + " TEXT" + ")";

    private static final String CREATE_TABLE_GEARBOX = "CREATE TABLE "
            + TABLE_GEARBOX + "(" + KEY_GEARBOX_ID + " INTEGER PRIMARY KEY," + KEY_Gear_DEVICE_NAME + " TEXT," + KEY_band_id + " TEXT," +
            KEY_band_od + " TEXT," + KEY_band_md + " TEXT," + KEY_pipe_id + " TEXT," +
            KEY_pipe_od + " TEXT," + KEY_pipe_md + " TEXT," + KEY_GEARBOX_RATIO + " TEXT," +
            KEY_BAND_DIA + " TEXT," + KEY_GEAR_WHEEL_DIA + " TEXT" + ")";

    private static final String CREATE_TABLE_SENSOR = "CREATE TABLE "
            + TABLE_SENSOR + "(" + KEY_Sensor_ID + " INTEGER PRIMARY KEY," + KEY_Sensor_DEVICE_NAME + " TEXT," +
            KEY_accX + " TEXT," + KEY_accY + " TEXT," + KEY_accZ + " TEXT," +
            KEY_gyroX + " TEXT," + KEY_gyroY + " TEXT," + KEY_gyroZ + " TEXT," +
            KEY_magXX + " TEXT," + KEY_magY + " TEXT," + KEY_magZ + " TEXT," +
            KEY_SENSOR_GBR + " TEXT," + KEY_Sen_BAND_DIA + " TEXT," + KEY_SENSOR_GEAR_WHEEL_DIA + " TEXT," +
            KEY_SEN_CIRCUM + " TEXT," + KEY_SEN_FULL_CYCLE + " TEXT," + KEY_SEN_RPM + " TEXT" + ")";

    private static final String CREATE_TABLE_DATALOG = "CREATE TABLE "
            + TABLE_DATALOG + "(" + KEY_Datalog_ID + " INTEGER PRIMARY KEY," + KEY_Datalog_DEVICE_NAME + " TEXT," +
            KEY_Data_accX + " TEXT," + KEY_Data_accY + " TEXT," + KEY_Data_accZ + " TEXT," +
            KEY_Data_gyroX + " TEXT," + KEY_Data_gyroY + " TEXT," + KEY_Data_gyroZ + " TEXT," +
            KEY_Data_magXX + " TEXT," + KEY_Data_magY + " TEXT," + KEY_Data_magZ + " TEXT," +
            KEY_Data_baro + " TEXT," + KEY_Data_temp + " TEXT," + KEY_Home_position + " TEXT," +
            KEY_Data_rel + " TEXT," + KEY_Data_abs + " TEXT" + ")";


    private static final String CREATE_TABLE_DEVICE = "CREATE TABLE "
            + TABLE_DEVICE_INFO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_ACTIVITY
            + " TEXT," + KEY_EDIT + " TEXT" + ")";

    private static final String CREATE_TABLE_MACHINE = "CREATE TABLE "
            + TABLE_MACHINE_INFO + "(" + KEY_MACHINE_ID + " INTEGER PRIMARY KEY," + KEY_MACHINE_NAME + " TEXT," + KEY_MACHINE_DEVICE_NAME
            + " TEXT," + KEY_MACHINE_EDIT + " TEXT"+ ")";

    private static final String CREATE_TABLE_Machine_Progress = "CREATE TABLE "
            + TABLE_MACHINE_PROGRESS + "(" + KEY_MACHINE_PROGRESS_ID + " INTEGER PRIMARY KEY," + KEY_MACHINE_P_DEVICE_NAME + " TEXT," + KEY_MACHINE_Pos
            + " TEXT," + KEY_MACHINE_Velocity + " TEXT," + KEY_MACHINE_ABS_PULSE + " TEXT" + ")";

    private static final String CREATE_TABLE_MACHINEPROGRESS_LIST = "CREATE TABLE "
            + TABLE_MACHINE_PROGRESS_List + "(" + KEY_MACHINE_PROGRESS_List_ID + " INTEGER PRIMARY KEY," + KEY_MACHINE_PROGRESS_LIST_DEVICE_NAME + " TEXT," +
            KEY_MACHINE_PROGRESSLIST_ACCX + " TEXT," + KEY_MACHINE_PROGRESSLIST_ACCY + " TEXT," + KEY_MACHINE_PROGRESSLIST_ACCZ + " TEXT," +
            KEY_MACHINE_PROGRESSLIST_GYROX + " TEXT," + KEY_MACHINE_PROGRESSLIST_GYROY + " TEXT," + KEY_MACHINE_PROGRESSLIST_GYROZ + " TEXT," +
            KEY_MACHINE_PROGRESSLIST_MAGX + " TEXT," + KEY_MACHINE_PROGRESSLIST_MAGY + " TEXT," + KEY_MACHINE_PROGRESSLIST_MAGZ + " TEXT," +
            KEY_MACHINE_PROGRESSLIST_Baro + " TEXT," + KEY_MACHINE_PROGRESSLIST_Temp + " TEXT," + KEY_MACHINE_PROGRESSLIST_ActualVel + " TEXT," +
            KEY_MACHINE_PROGRESSLIST_RelEncoder + " TEXT," + KEY_MACHINE_PROGRESSLIST_AbsEncoder + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REGISTRATION);
        db.execSQL(CREATE_TABLE_DEVICE);
        db.execSQL(CREATE_TABLE_MACHINE_NEW_DEVICE);
        db.execSQL(CREATE_TABLE_MACHINE);
        db.execSQL(CREATE_TABLE_NEW_DEVICE);
        db.execSQL(CREATE_TABLE_GEARBOX);
        db.execSQL(CREATE_TABLE_SENSOR);
        db.execSQL(CREATE_TABLE_DATALOG);
        db.execSQL(CREATE_TABLE_Machine_Progress);
        db.execSQL(CREATE_TABLE_MACHINEPROGRESS_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVICE_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MACHINE_INFO);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_DEVICE_NEW);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_GEARBOX);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_SENSOR);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_MACHINE_DEVICE_NEWDEVICE_NEW);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_DATALOG);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_MACHINE_PROGRESS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_MACHINE_PROGRESS_List);
        onCreate(db);
    }

    public void createReg(RegistrationClass todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REG_NAME, todo.get_name());
        values.put(KEY_REG_USERNAME, todo.get_userName());
        values.put(KEY_REG_PASSWORD, todo.get_password());
        values.put(KEY_REG_CONFIRM_PASSWORD, todo.get_confirmPassword());
        System.out.println("hello" + todo.get_id() + todo.get_name() + todo.get_userName() + todo.get_password());
        // insert row
        db.insert(TABLE_REG, null, values);
        System.out.println("successfully");
    }

    public List<RegistrationClass> getAllToDos() {
        List<RegistrationClass> todos = new ArrayList<RegistrationClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_REG;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                RegistrationClass td = new RegistrationClass();
                td.set_id(c.getInt((c.getColumnIndex(KEY_REG_ID))));
                td.set_name((c.getString(c.getColumnIndex(KEY_REG_NAME))));
                td.set_userName(c.getString(c.getColumnIndex(KEY_REG_USERNAME)));
                td.set_password(c.getString(c.getColumnIndex(KEY_REG_PASSWORD)));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }
    public void update_byId(RegistrationClass todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REG_NAME, todo.get_name());
        values.put(KEY_REG_PASSWORD, todo.get_password());
        values.put(KEY_REG_CONFIRM_PASSWORD, todo.get_confirmPassword());
        System.out.println("Inside Update ");
        db.update(TABLE_REG, values, KEY_REG_ID + "=" + todo.get_id(), null);
    }

    public void createNewDevice(DeviceClass todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NEW_DEVICE_IP, todo.getIp());
        values.put(KEY_DEVICE_NAME, todo.getDevice());
        values.put(NEW_DEVICE_OPERATION, todo.getOperation());
        // insert row
        db.insert(TABLE_DEVICE_NEW, null, values);
        List<DeviceClass> list = getAllNewDevices();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getIp().trim().equals(todo.getIp().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false) {
            db.insert(TABLE_DEVICE_NEW, null, values);
        }
        if(flag == true) {
            deleteDeviceName(todo.getIp().trim());
            db.insert(TABLE_DEVICE_NEW, null, values);
        }

        System.out.println("successfully" + todo.getIp());
        System.out.println("successfully");
    }

    public List<DeviceClass> getAllNewDevices() {
        List<DeviceClass> todos = new ArrayList<DeviceClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_DEVICE_NEW;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DeviceClass td = new DeviceClass();
                td.setId(c.getInt((c.getColumnIndex(KEY_NEW_DEVICE_ID))));
                td.setIp((c.getString(c.getColumnIndex(KEY_NEW_DEVICE_IP))));
                td.setDevice(c.getString(c.getColumnIndex(KEY_DEVICE_NAME)));
                td.setOperation(c.getString(c.getColumnIndex(NEW_DEVICE_OPERATION)));
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void deleteDeviceName(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEVICE_NEW,
                KEY_NEW_DEVICE_IP + " = ?",
                new String[]{st});
        System.out.println("Delete device name successfully " + st);
    }

    public void create_M_NewDevice(DeviceClass todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_M_NEW_DEVICE_IP, todo.getIp());
        values.put(KEY_M_DEVICE_NAME, todo.getDevice());
        values.put(NEW_M_DEVICE_OPERATION, todo.getOperation());
        // insert row
        db.insert(TABLE_MACHINE_DEVICE_NEWDEVICE_NEW, null, values);
        List<DeviceClass> list = getAll_M_NewDevices();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getIp().trim().equals(todo.getIp().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false) {
            db.insert(TABLE_MACHINE_DEVICE_NEWDEVICE_NEW, null, values);
        }
        if(flag == true) {
            delete_M_DeviceName(todo.getIp().trim());
            db.insert(TABLE_MACHINE_DEVICE_NEWDEVICE_NEW, null, values);
        }

        System.out.println("successfully" + todo.getIp());
        System.out.println("successfully");
    }

    public List<DeviceClass> getAll_M_NewDevices() {
        List<DeviceClass> todos = new ArrayList<DeviceClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_MACHINE_DEVICE_NEWDEVICE_NEW;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DeviceClass td = new DeviceClass();
                td.setId(c.getInt((c.getColumnIndex(KEY_M_NEW_DEVICE_ID))));
                td.setIp((c.getString(c.getColumnIndex(KEY_M_NEW_DEVICE_IP))));
                td.setDevice(c.getString(c.getColumnIndex(KEY_M_DEVICE_NAME)));
                td.setOperation(c.getString(c.getColumnIndex(NEW_M_DEVICE_OPERATION)));
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void delete_M_DeviceName(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MACHINE_DEVICE_NEWDEVICE_NEW,
                KEY_M_NEW_DEVICE_IP + " = ?",
                new String[]{st});
        System.out.println("Delete device name successfully " + st);
    }

    public void createGearBox(GearBoxClass todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Gear_DEVICE_NAME, todo.getDeviceNmae());
        values.put(KEY_band_id, todo.getBandId());
        values.put(KEY_band_od, todo.getBandOd());
        values.put(KEY_band_md, todo.getBandMd());
        values.put(KEY_pipe_id, todo.getPipeId());
        values.put(KEY_pipe_od, todo.getPipeOd());
        values.put(KEY_pipe_md, todo.getPipeMd());
        values.put(KEY_GEARBOX_RATIO, todo.getGbr());
        values.put(KEY_BAND_DIA, todo.getBandDia());
        values.put(KEY_GEAR_WHEEL_DIA, todo.getGearwheelDia());


        // insert row
        db.insert(TABLE_GEARBOX, null, values);
        List<GearBoxClass> list = getAllGearBoxes();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getDeviceNmae().trim().equals(todo.getDeviceNmae().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false) {
            db.insert(TABLE_GEARBOX, null, values);
        }
        if(flag == true) {
            deleteGearBox(todo.getDeviceNmae().trim());
            db.insert(TABLE_GEARBOX, null, values);
        }

        System.out.println("successfully" + todo.getDeviceNmae());
        System.out.println("successfully");
    }

    public List<GearBoxClass> getAllGearBoxes() {
        List<GearBoxClass> todos = new ArrayList<GearBoxClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_GEARBOX;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                GearBoxClass td = new GearBoxClass();
                td.setId(c.getInt((c.getColumnIndex(KEY_GEARBOX_ID))));
                td.setDeviceNmae((c.getString(c.getColumnIndex(KEY_Gear_DEVICE_NAME))));
                td.setBandId(c.getString(c.getColumnIndex(KEY_band_id)));
                td.setBandOd(c.getString(c.getColumnIndex(KEY_band_od)));
                td.setBandMd(c.getString(c.getColumnIndex(KEY_band_md)));
                td.setPipeId(c.getString(c.getColumnIndex(KEY_pipe_id)));
                td.setPipeOd(c.getString(c.getColumnIndex(KEY_pipe_od)));
                td.setPipeMd(c.getString(c.getColumnIndex(KEY_pipe_md)));
                td.setGbr(c.getString(c.getColumnIndex(KEY_GEARBOX_RATIO)));
                td.setBandDia(c.getString(c.getColumnIndex(KEY_BAND_DIA)));
                td.setGearwheelDia(c.getString(c.getColumnIndex(KEY_GEAR_WHEEL_DIA)));
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void deleteGearBox(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GEARBOX,
                KEY_Gear_DEVICE_NAME + " = ?",
                new String[]{st});
        System.out.println("Delete device name successfully " + st);
    }

    public void createSensor(SensorClass todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Sensor_DEVICE_NAME, todo.getDeviceNmae());
        values.put(KEY_accX, todo.getAccX());
        values.put(KEY_accY, todo.getAccY());
        values.put(KEY_accZ, todo.getAccZ());
        values.put(KEY_gyroX, todo.getGyroX());
        values.put(KEY_gyroY, todo.getGyroY());
        values.put(KEY_gyroZ, todo.getGyroZ());
        values.put(KEY_magXX, todo.getMagX());
        values.put(KEY_magY, todo.getMagY());
        values.put(KEY_magZ, todo.getMagZ());
        values.put(KEY_SENSOR_GBR, todo.getGbr());
        values.put(KEY_Sen_BAND_DIA, todo.getBandDia());
        values.put(KEY_SENSOR_GEAR_WHEEL_DIA, todo.getGearwheelDia());
        values.put(KEY_SEN_CIRCUM, todo.getCircum());
        values.put(KEY_SEN_FULL_CYCLE, todo.getFullCycle());
        values.put(KEY_SEN_RPM, todo.getRpm());
        // insert row
        db.insert(TABLE_SENSOR, null, values);
        List<SensorClass> list = getAllSensors();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getDeviceNmae().trim().equals(todo.getDeviceNmae().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false) {
            db.insert(TABLE_SENSOR, null, values);
        }
        if(flag == true) {
            deleteSensor(todo.getDeviceNmae().trim());
            db.insert(TABLE_SENSOR, null, values);
        }

        System.out.println("successfully" + todo.getDeviceNmae());
        System.out.println("successfully");
    }

    public List<SensorClass> getAllSensors() {
        List<SensorClass> todos = new ArrayList<SensorClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_SENSOR;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                SensorClass td = new SensorClass();
                td.setId(c.getInt((c.getColumnIndex(KEY_Sensor_ID))));
                td.setDeviceNmae((c.getString(c.getColumnIndex(KEY_Sensor_DEVICE_NAME))));
                td.setAccX(c.getString(c.getColumnIndex(KEY_accX)));
                td.setAccY(c.getString(c.getColumnIndex(KEY_accY)));
                td.setAccZ(c.getString(c.getColumnIndex(KEY_accZ)));
                td.setGyroX(c.getString(c.getColumnIndex(KEY_gyroX)));
                td.setGyroY(c.getString(c.getColumnIndex(KEY_gyroY)));
                td.setGyroZ(c.getString(c.getColumnIndex(KEY_gyroZ)));
                td.setMagX(c.getString(c.getColumnIndex(KEY_magXX)));
                td.setMagY(c.getString(c.getColumnIndex(KEY_magY)));
                td.setMagZ(c.getString(c.getColumnIndex(KEY_magZ)));
                td.setGbr(c.getString(c.getColumnIndex(KEY_SENSOR_GBR)));
                td.setBandDia(c.getString(c.getColumnIndex(KEY_Sen_BAND_DIA)));
                td.setGearwheelDia(c.getString(c.getColumnIndex(KEY_SENSOR_GEAR_WHEEL_DIA)));
                td.setCircum(c.getString(c.getColumnIndex(KEY_SEN_CIRCUM)));
                td.setFullCycle(c.getString(c.getColumnIndex(KEY_SEN_FULL_CYCLE)));
                td.setRpm(c.getString(c.getColumnIndex(KEY_SEN_RPM)));
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void deleteSensor(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SENSOR,
                KEY_Sensor_DEVICE_NAME + " = ?",
                new String[]{st});
        System.out.println("Delete device name successfully " + st);
    }

    public void createMachineProgress(MachineProgress todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MACHINE_P_DEVICE_NAME, todo.getDevice());
        values.put(KEY_MACHINE_Pos, todo.getPos());
        values.put(KEY_MACHINE_Velocity, todo.getVelocity());
        values.put(KEY_MACHINE_ABS_PULSE, todo.getAbspulse());
        // insert row
        db.insert(TABLE_MACHINE_PROGRESS, null, values);
        List<MachineProgress> list = getAllProgress();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getDevice().trim().equals(todo.getDevice().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false) {
            db.insert(TABLE_MACHINE_PROGRESS, null, values);
        }
        if(flag == true) {
            deleteMachineProgress(todo.getDevice().trim());
            db.insert(TABLE_MACHINE_PROGRESS, null, values);
        }

        System.out.println("successfully" + todo.getDevice());
        System.out.println("successfully");
    }

    public List<MachineProgress> getAllProgress() {
        List<MachineProgress> todos = new ArrayList<MachineProgress>();
        String selectQuery = "SELECT  * FROM " + TABLE_MACHINE_PROGRESS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                MachineProgress td = new MachineProgress();
                td.setId(c.getInt((c.getColumnIndex(KEY_MACHINE_PROGRESS_ID))));
                td.setDevice((c.getString(c.getColumnIndex(KEY_MACHINE_P_DEVICE_NAME))));
                td.setPos(c.getString(c.getColumnIndex(KEY_MACHINE_Pos)));
                td.setVelocity(c.getString(c.getColumnIndex(KEY_MACHINE_Velocity)));
                td.setAbspulse(c.getString(c.getColumnIndex(KEY_MACHINE_ABS_PULSE)));
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void deleteMachineProgress(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MACHINE_PROGRESS,
                KEY_MACHINE_P_DEVICE_NAME + " = ?",
                new String[]{st});
        System.out.println("Delete device name successfully " + st);
    }

    public void createMachineProgressList(MachineProgressList todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MACHINE_PROGRESS_LIST_DEVICE_NAME, todo.getDevice());
        values.put(KEY_MACHINE_PROGRESSLIST_ACCX, todo.getAccX());
        values.put(KEY_MACHINE_PROGRESSLIST_ACCY, todo.getAccY());
        values.put(KEY_MACHINE_PROGRESSLIST_ACCZ, todo.getAccZ());
        values.put(KEY_MACHINE_PROGRESSLIST_GYROX, todo.getGyroX());
        values.put(KEY_MACHINE_PROGRESSLIST_GYROY, todo.getGyroY());
        values.put(KEY_MACHINE_PROGRESSLIST_GYROZ, todo.getGyroZ());
        values.put(KEY_MACHINE_PROGRESSLIST_MAGX, todo.getMagX());
        values.put(KEY_MACHINE_PROGRESSLIST_MAGY, todo.getMagY());
        values.put(KEY_MACHINE_PROGRESSLIST_MAGZ, todo.getMagZ());

        values.put(KEY_MACHINE_PROGRESSLIST_Baro, todo.getBaro());
        values.put(KEY_MACHINE_PROGRESSLIST_Temp, todo.getTemp());
        values.put(KEY_MACHINE_PROGRESSLIST_ActualVel, todo.getActualVel());
        values.put(KEY_MACHINE_PROGRESSLIST_RelEncoder, todo.getRelEncodr());
        values.put(KEY_MACHINE_PROGRESSLIST_AbsEncoder, todo.getAbsEncoder());
        // insert row
        db.insert(TABLE_MACHINE_PROGRESS_List, null, values);
//        List<MachineProgress> list = getAllProgress();
//        for(int i = 0; i < list.size(); i++){
//            if(!list.isEmpty()){
//                if(list.get(i).getDevice().trim().equals(todo.getDevice().trim())){
//                    flag = true;
//                    break;
//                }
//                else{
//                    flag = false;
//                }
//            }
//        }
//        if(flag == false) {
//            db.insert(TABLE_MACHINE_PROGRESS, null, values);
//        }
//        if(flag == true) {
//            deleteMachineProgress(todo.getDevice().trim());
//            db.insert(TABLE_MACHINE_PROGRESS, null, values);
//        }

        System.out.println("successfully" + todo.getDevice() + todo.getBaro());
        System.out.println("successfully");
    }

    public List<MachineProgressList> getAllMachineProgressList() {
        List<MachineProgressList> todos = new ArrayList<MachineProgressList>();
        String selectQuery = "SELECT  * FROM " + TABLE_MACHINE_PROGRESS_List;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                MachineProgressList td = new MachineProgressList();
                td.setId(c.getInt((c.getColumnIndex(KEY_MACHINE_PROGRESS_List_ID))));
                td.setDevice((c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESS_LIST_DEVICE_NAME))));
                td.setAccX(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_ACCX)));
                td.setAccY(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_ACCY)));
                td.setAccZ(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_ACCZ)));
                td.setGyroX(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_GYROX)));
                td.setGyroY(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_GYROY)));
                td.setGyroZ(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_GYROZ)));
                td.setMagX(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_MAGX)));
                td.setMagY(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_MAGY)));
                td.setMagZ(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_MAGZ)));
                td.setBaro(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_Baro)));
                td.setTemp(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_Temp)));
                td.setActualVel(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_ActualVel)));
                td.setRelEncodr(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_RelEncoder)));
                td.setAbsEncoder(c.getString(c.getColumnIndex(KEY_MACHINE_PROGRESSLIST_AbsEncoder)));

                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void deleteMachineProgressList() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MACHINE_PROGRESS_List, null, null);
        System.out.println("Delete device name successfully ");
    }


    public void createDataLog(DataLogClass todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Datalog_DEVICE_NAME, todo.getDeviceNmae());
        values.put(KEY_Data_accX, todo.getAccX());
        values.put(KEY_Data_accY, todo.getAccY());
        values.put(KEY_Data_accZ, todo.getAccZ());
        values.put(KEY_Data_gyroX, todo.getGyroX());
        values.put(KEY_Data_gyroY, todo.getGyroY());
        values.put(KEY_Data_gyroZ, todo.getGyroZ());
        values.put(KEY_Data_magXX, todo.getMagX());
        values.put(KEY_Data_magY, todo.getMagY());
        values.put(KEY_Data_magZ, todo.getMagZ());
        values.put(KEY_Data_baro, todo.getBaro());
        values.put(KEY_Data_temp, todo.getTemp());
        values.put(KEY_Home_position, todo.getHomePos());


        // insert row
        db.insert(TABLE_DATALOG, null, values);
        List<DataLogClass> list = getAllDataLog();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getDeviceNmae().trim().equals(todo.getDeviceNmae().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false) {
            db.insert(TABLE_DATALOG, null, values);
        }
        if(flag == true) {
            deleteDataLog(todo.getDeviceNmae().trim());
            db.insert(TABLE_DATALOG, null, values);
        }

        System.out.println("successfully" + todo.getDeviceNmae());
        System.out.println("successfully");
    }

    public List<DataLogClass> getAllDataLog() {
        List<DataLogClass> todos = new ArrayList<DataLogClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_DATALOG;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DataLogClass td = new DataLogClass();
                td.setId(c.getInt((c.getColumnIndex(KEY_Datalog_ID))));
                td.setDeviceNmae((c.getString(c.getColumnIndex(KEY_Datalog_DEVICE_NAME))));
                td.setAccX(c.getString(c.getColumnIndex(KEY_Data_accX)));
                td.setAccY(c.getString(c.getColumnIndex(KEY_Data_accY)));
                td.setAccZ(c.getString(c.getColumnIndex(KEY_Data_accZ)));
                td.setGyroX(c.getString(c.getColumnIndex(KEY_Data_gyroX)));
                td.setGyroY(c.getString(c.getColumnIndex(KEY_Data_gyroY)));
                td.setGyroZ(c.getString(c.getColumnIndex(KEY_Data_gyroZ)));
                td.setMagX(c.getString(c.getColumnIndex(KEY_Data_magXX)));
                td.setMagY(c.getString(c.getColumnIndex(KEY_Data_magY)));
                td.setMagZ(c.getString(c.getColumnIndex(KEY_Data_magZ)));
                td.setBaro(c.getString(c.getColumnIndex(KEY_Data_baro)));
                td.setTemp(c.getString(c.getColumnIndex(KEY_Data_temp)));
                td.setHomePos(c.getString(c.getColumnIndex(KEY_Home_position)));
                td.setRel(c.getString(c.getColumnIndex(KEY_Data_rel)));
                td.setAbs(c.getString(c.getColumnIndex(KEY_Data_abs)));
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void deleteDataLog(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATALOG,
                KEY_Datalog_DEVICE_NAME + " = ?",
                new String[]{st});
        System.out.println("Delete device name successfully " + st);
    }


    public void createDevice(DatabaseDevice todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, todo.getName());
        values.put(KEY_ACTIVITY, todo.getActivity());
        values.put(KEY_EDIT, todo.getEditActivity());
        // insert row
        db.insert(TABLE_DEVICE_INFO, null, values);
        List<DatabaseDevice> list = getAllDeviceactivities();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getName().trim().equals(todo.getName().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false){
            db.insert(TABLE_DEVICE_INFO, null, values);
        }
        if(flag == true){
            deleteTask(todo.getName().trim());
            db.insert(TABLE_DEVICE_INFO, null, values);
        }

        System.out.println("successfully");
        System.out.println("successfully");
    }

    public List<DatabaseDevice> getAllDeviceactivities() {
        List<DatabaseDevice> todos = new ArrayList<DatabaseDevice>();
        String selectQuery = "SELECT  * FROM " + TABLE_DEVICE_INFO;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DatabaseDevice td = new DatabaseDevice();
                td.set_id(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                td.setActivity(c.getString(c.getColumnIndex(KEY_ACTIVITY)));
                td.setEditActivity(c.getString(c.getColumnIndex(KEY_EDIT)));
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void deleteTask(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEVICE_INFO,
                KEY_NAME + " = ?",
                new String[]{st});
        System.out.println("Delete successfully " + st);
    }

    public void createMachine(DatabaseMachine todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MACHINE_NAME, todo.getmName());
        values.put(KEY_MACHINE_DEVICE_NAME, todo.getmDeviceName());
        values.put(KEY_MACHINE_EDIT, todo.getmDeviceEdit());
        // insert row
        db.insert(TABLE_MACHINE_INFO, null, values);
        List<DatabaseMachine> list = getAllMachineDevices();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getmName().trim().equals(todo.getmName().trim())){
                    if(list.get(i).getmDeviceName().trim().equals(todo.getmDeviceName().trim())){
                        deleteMachineDevice(todo.getmName().trim(), todo.getmDeviceName().trim());
                        db.insert(TABLE_MACHINE_INFO, null, values);
                    }
//                    flag = true;
//                    break;
                }
                else{
                    db.insert(TABLE_MACHINE_INFO, null, values);
                }
            }
        }
//        if(flag == false){
//            db.insert(TABLE_MACHINE_INFO, null, values);
//        }
//        if(flag == true){
//            deleteMachineDevice(todo.getmName().trim());
//            db.insert(TABLE_MACHINE_INFO, null, values);
//        }
        System.out.println("successfully");
    }

    public List<DatabaseMachine> getAllMachineDevices() {
        List<DatabaseMachine> todos = new ArrayList<DatabaseMachine>();
        String selectQuery = "SELECT  * FROM " + TABLE_MACHINE_INFO;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DatabaseMachine td = new DatabaseMachine();
                td.set_id(c.getInt((c.getColumnIndex(KEY_MACHINE_ID))));
                td.setmName((c.getString(c.getColumnIndex(KEY_MACHINE_NAME))));
                td.setmDeviceName(c.getString(c.getColumnIndex(KEY_MACHINE_DEVICE_NAME)));
                td.setmDeviceEdit(c.getString(c.getColumnIndex(KEY_MACHINE_EDIT)));

                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void deleteMachineDevice(String st, String st1) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MACHINE_INFO,
                KEY_MACHINE_NAME + " = ? AND " + KEY_MACHINE_DEVICE_NAME + " =?" ,
                new String[] { st, st1 });

        System.out.println("Delete successfully " + st + "...." + st1);
    }
    public void deleteMachineName(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MACHINE_INFO,
                KEY_MACHINE_NAME + " = ?",
                new String[] { st });

        System.out.println("Delete successfully " + st);
    }
}
