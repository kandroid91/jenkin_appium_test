package com.asiapay.payyobusiness.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.asiapay.payyobusiness.model.User;
import com.asiapay.payyobusiness.network.ApiClient;
import com.asiapay.payyobusiness.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel extends SQLiteOpenHelper implements LoginContract.Model {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "payYoforBusiness";
    private static final String TABLE_USER = "User";
    private static final String KEY_ID = "userID";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_EMAIL = "userEmail";
    private static final String KEY_PROFILE_PIC_PATH = "userPicPath";


    public LoginModel(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void validateLogin(final OnFinishedListener onFinishedListener, User user) {

        /*ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<User> call = apiService.validateLogin(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //   User user = response.body();
                User user = new User();
                user.setOtp("349");
                onFinishedListener.onFinished(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/
        User userOP = new User();
        userOP.setOtp("1234");
        onFinishedListener.onFinished(userOP);
    }

    @Override
    public void validateOTP(final OnFinishedListener onFinishedListener, User user) {
        /*ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiService.validateOTP(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //  User user = response.body();
                User user = new User();
                user.setOtp("1234");
                onFinishedListener.onFinished(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/

        User userOP = new User();
        userOP.setOtp("1234");
        onFinishedListener.onFinished(userOP);
    }


    @Override
    public void forgotPassword(final OnFinishedListener onFinishedListener, User user) {
        /*ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiService.forgotPassword(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                onFinishedListener.onFinished(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/
        onFinishedListener.onFinished(user);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_PROFILE_PIC_PATH + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}