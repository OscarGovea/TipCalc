package com.herzen.tipcalc.db;
/**
 * Created by herzen on 7/11/16.
 */

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = TipsDatabase.NAME, version = TipsDatabase.VERSION)
public class TipsDatabase {
    public static final String NAME = "Tips";
    public static final int VERSION = 1;

}
