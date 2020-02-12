package com.asiapay.payyobusiness.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.home.HomeActivity;
import com.asiapay.payyobusiness.model.User;
import com.asiapay.payyobusiness.network.Constants;
import com.asiapay.payyobusiness.utility.Util;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.ContentLoadingProgressBar;

public class LoginOTPActivity extends AppCompatActivity implements LoginContract.View {
    private AppCompatEditText et1, et2, et3, et4;
    private AppCompatButton btnOTPSubmit;
    private ContentLoadingProgressBar pbLoader;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_otp_activity);
        initUI();
        addEventListener();
        loginPresenter = new LoginPresenter(this,this);
    }

    @Override
    public void initUI() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        btnOTPSubmit = findViewById(R.id.btnOTPSubmit);
        pbLoader = findViewById(R.id.progressBarOtp);
    }

    @Override
    public void addEventListener() {
        btnOTPSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setOtp(et1.getText().toString() + et2.getText().toString() + et3.getText().toString() + et4.getText().toString());
                loginPresenter.onOTPButtonClick(user);
            }
        });

        et1.addTextChangedListener(new GenericTextWatcher(et1));
        et2.addTextChangedListener(new GenericTextWatcher(et2));
        et3.addTextChangedListener(new GenericTextWatcher(et3));
        et4.addTextChangedListener(new GenericTextWatcher(et4));
    }

    @Override
    public void showInvalidInputMessage(String forComponent, String message) {
        if (forComponent.equals("OTP")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginOTPActivity.this);
            builder.setMessage(message);
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public void launchActivity() {
        lunchOtpActivity();
    }

    @Override
    public void showAPIErrorMessage(String message) {

    }

    @Override
    public void hideKeyboard() {
        Util.hideKeyboard(btnOTPSubmit);
    }

    @Override
    public void showProgress() {
        pbLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoader.setVisibility(View.GONE);
    }

   /* @Override
    public void apiResponse(User user) {
        lunchOtpActivity();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        lunchOtpActivity();
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }

    private void lunchOtpActivity() {
        SharedPreferences.Editor edit = ApplicationClass.getInstance().getPreferences().edit();
        edit.putString(Constants.USER_ID, "12345");
        edit.putString(Constants.USER_MOBILE_No, "9900456321");
        edit.putString(Constants.USER_NAME, "SanjayKumarBarfa");
        edit.putString(Constants.USER_EMAIL, "sanjaykumarbarfa@gmail.com");
        edit.putString(Constants.USER_PROFILE_PATH, "");
        edit.commit();
        Intent intent = new Intent(LoginOTPActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.et1:
                    if (text.length() == 1)
                        et2.requestFocus();
                    break;
                case R.id.et2:
                    if (text.length() == 1)
                        et3.requestFocus();
                    else if (text.length() == 0)
                        et1.requestFocus();
                    break;
                case R.id.et3:
                    if (text.length() == 1)
                        et4.requestFocus();
                    else if (text.length() == 0)
                        et2.requestFocus();
                    break;
                case R.id.et4:
                    if (text.length() == 0)
                        et3.requestFocus();
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }
}
