package com.taal.welding.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

/**
 * Copyright 2015 (C) vCarrot
 * Created on : 13-08-2015
 * Author     : Kavin Varnan
 */
public final class FontCache {

    //TODO: Add comments
    private static FontCache sInstance;

    private Typeface mRegular;
    private Typeface mLucida;

    public static FontCache getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new FontCache();
            sInstance.mRegular = Typeface.createFromAsset(context.getAssets(), "font/VarelaRound-Regular.ttf");
            sInstance.mLucida = Typeface.createFromAsset(context.getAssets(), "font/LucidaGrande.ttf");
        }
        return sInstance;
    }

    public static FontCache getInstance() {
        return sInstance;
    }

    public Typeface getFont(FontType fontType) {
        switch (fontType) {
            case REGULAR:
                return sInstance.mRegular;
            case LUCIDA:
                return sInstance.mLucida;
        }
        return null;
    }

    public static void setViewWith(FontType regular, View... views) {
        for (View view : views) {
            ((TextView) view).setTypeface(FontCache.getInstance().getFont(regular));
        }
    }

    public enum FontType {
        REGULAR, LUCIDA
    }
}
