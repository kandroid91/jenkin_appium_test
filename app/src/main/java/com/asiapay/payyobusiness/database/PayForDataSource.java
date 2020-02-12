package com.asiapay.payyobusiness.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.model.User;
import com.asiapay.payyobusiness.network.Constants;

import java.util.ArrayList;

import static com.asiapay.payyobusiness.database.PayYoBusinessHelper.TABLE_USER;


public class PayForDataSource {
    private SQLiteDatabase database;
    private PayYoBusinessHelper dbHelper;

    public PayForDataSource(Context context) {
        dbHelper = new PayYoBusinessHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void InsertUserData(String userID, String userName, String userEmail, String userProfilePic) {
        ContentValues values = new ContentValues();
        values.put(Constants.USER_ID, userID);
        values.put(Constants.USER_NAME, userName);
        values.put(Constants.USER_EMAIL, userEmail);
        values.put(Constants.USER_PROFILE_PATH, userProfilePic);
        long insertId = database.insert(TABLE_USER, null, values);
        System.out.println("userTable insert>>" + insertId);

    }

    public String GetUserImgPath() {
        String strImgPath = "";
        Cursor c = database.rawQuery("SELECT * FROM " + TABLE_USER, null);
        if (c != null) {
            if (c.moveToFirst()) {
                strImgPath = c.getString(c.getColumnIndex("UserProfilePic"));
            }
        }
        return strImgPath;
    }

    public int GET_COUNT_DB() {
        int CO = 0;
        Cursor cursor = this.database.query(TABLE_USER, new String[]
                {"UserName"}, null, null, null, null, null);
        CO = cursor.getCount();
        cursor.close();
        return CO;
    }

    public int updateProfilePic(User user) {
        String userID = ApplicationClass.getInstance().getPreferences().getString(Constants.USER_ID, null);
        ContentValues values = new ContentValues();
        values.put(Constants.USER_PROFILE_PATH, user.getImagePath());
        values.put(Constants.USER_EMAIL, "a@a.in");
        values.put(Constants.USER_NAME, "99999999");

        int i = this.database.update(TABLE_USER, // table
                values, // update value
                "userID", // selections
                new String[]{userID});

        this.database.close();
        return i;
    }

    public void deleteUserData() {
        this.database.delete(TABLE_USER, null, null);
    }


}
