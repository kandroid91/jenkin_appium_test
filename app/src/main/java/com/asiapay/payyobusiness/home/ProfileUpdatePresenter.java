package com.asiapay.payyobusiness.home;

import android.content.Context;

import com.asiapay.payyobusiness.model.User;

public class ProfileUpdatePresenter implements ProfileUpdateContracts.Presenter, ProfileUpdateContracts.Model.OnFinishedListener {
    private ProfileUpdateContracts.View updateProfileView;
    private ProfileUpdateContracts.Model updateModel;
    private Context mContext;

    public ProfileUpdatePresenter(ProfileUpdateContracts.View profileUpdateFragment, Context context) {
        this.updateProfileView = profileUpdateFragment;
        this.updateModel = new ProfileUpdateModel(context);
        this.mContext = context;
    }

    @Override
    public void onFinished(User updateProfile) {
        if (updateProfileView != null) {
            updateProfileView.hideProgress();
        }
        if (updateProfile.getImagePath() != null) {
          String strUserpath=  updateProfile.getImagePath();
            updateProfileView.UploadSuccess(strUserpath);
        } else {
            String message = "";
            updateProfileView.showAPIErrorMessage(message);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (updateProfileView != null) {
            updateProfileView.hideProgress();
        }
        updateProfileView.showAPIErrorMessage(t.getMessage());
    }

    @Override
    public void onDestroy() {
        updateProfileView = null;
    }


    public void onSaveButtonClick(User updateProfile) {
        if (updateProfile.getUserName().equals("")) {
            updateProfileView.showInvalidInputMessage("userName", "Please enter user name");
        } else if (updateProfile.geteMail().equals("")) {
            updateProfileView.showInvalidInputMessage("EmailID", "Please enter Email");
        } else if (updateProfile.getMobileNo().equals("")) {
            updateProfileView.showInvalidInputMessage("Mobile No", "Please  enter Mobile No");
        } else {
            updateProfileView.showProgress();
            updateModel.userProfile(this, updateProfile);
        }
    }



}


