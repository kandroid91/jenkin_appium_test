package com.asiapay.payyobusiness.home;

import android.content.Context;
import android.content.SharedPreferences;

import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.model.User;
import com.asiapay.payyobusiness.network.Constants;

public class ProfileUpdateModel implements ProfileUpdateContracts.Model {
    private Context mContext;

    public ProfileUpdateModel(Context context) {
        this.mContext = context;
    }

    @Override
    public void userProfile(OnFinishedListener onFinishedListener, User updateProfile) {

        SharedPreferences pref = ApplicationClass.getInstance().getPreferences();
        SharedPreferences.Editor pref_edit = pref.edit();
        String strUserName = updateProfile.getUserName();
        String strUserEmail = updateProfile.geteMail();
        String strMobNo = updateProfile.getMobileNo();
        pref_edit.putString(Constants.USER_NAME, strUserName);
        pref_edit.putString(Constants.USER_EMAIL, strUserEmail);
        pref_edit.putString(Constants.USER_MOBILE_No, strMobNo);
        pref_edit.commit();
        onFinishedListener.onFinished(updateProfile);
    }


}