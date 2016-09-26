package com.herzen.tipcalc;

import android.app.Application;

/**
 * Created by herzen on 26/09/16.
 */
public class TipCalcApp extends Application {
    private final static String ABOUT_URL= "https://github.com/OscarGovea";

    public String getAboutUrl(){
        return ABOUT_URL;
    }
}
