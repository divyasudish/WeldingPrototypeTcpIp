package com.taal.welding;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import com.taal.welding.utils.FontCache;

/**
 * Copyright 2015 (C) vCarrot
 * Created on : 11-08-2015
 * Author     : Kavin Varnan
 */
public class MainApplication extends Application {

    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * Get the global context of the application. Can be used in places where the activity context is not needed
     * NOTE: Don't use UI operation using this context
     * @return application context
     */
    public static Context getGlobalContext() {
        return context;
    }

    /**
     * Helps you get string, color, dimens or any data from the resource folder
     * @return resources object
     */
    public static Resources getAppResources() {
        return context.getResources();
    }

    /**
     * Gets the string from the resource folder and help you embed URL.
     * Ref: http://developer.android.com/reference/android/content/res/Resources.html#getString%28int,%20java.lang.Object...%29
     * @param resourceId name of the resource ex: R.string.my_string
     * @param formatArgs the data which is to be injected in the particular position of the above string
     * @return the complete embedded string
     */
    public static String getAppString(int resourceId, Object... formatArgs) {
        System.out.println("Hello.. How are u ");
        return getAppResources().getString(resourceId, formatArgs);
    }

    /**
     * Gets the string from the resource folder
     * @param resourceId name of the resource ex: R.string.my_string
     * @return the string from the value folder
     */
    public static String getAppString(int resourceId) {
        return getAppResources().getString(resourceId);
    }

    /**
     * Getting the display metrics of the device which is being used
     * @return DisplayMetrics object
     */
    public static DisplayMetrics getDisplayMetrics() {
        return getGlobalContext().getResources().getDisplayMetrics();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = null;
        context = getApplicationContext();
        FontCache.getInstance(context);
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MainApplication.context = context;
    }
}
