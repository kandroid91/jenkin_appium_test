package com.asiapay.payyobusiness.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class PayYoBusinessHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PayYoForBusiness";
    public static final String TABLE_USER = "User";

    String CREATE_USER_TABLE = "CREATE TABLE user (" +
            "    user_id          INTEGER PRIMARY KEY," +
            "    user_name        TEXT," +
            "    user_email       TEXT," +
            "    user_mobile      TEXT," +
            "    user_profilepath TEXT" +
            ");";

    public PayYoBusinessHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

      /*  Log.w(PayYoBusinessHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");*/

        onCreate(db);
    }


}
