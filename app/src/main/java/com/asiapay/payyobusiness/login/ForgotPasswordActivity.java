package com.asiapay.payyobusiness.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.home.HomeActivity;
import com.asiapay.payyobusiness.model.User;
import com.asiapay.payyobusiness.utility.Util;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.ContentLoadingProgressBar;

public class ForgotPasswordActivity extends AppCompatActivity implements LoginContract.View {
    private AppCompatEditText etUsername;
    private AppCompatButton btnSubmit;
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
        etUsername = findViewById(R.id.etUsername);
        btnSubmit = findViewById(R.id.btnSubmit);
        pbLoader = findViewById(R.id.progressBarOtp);
    }

    @Override
    public void addEventListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUserName(etUsername.getText().toString());
                loginPresenter.onForgotPasswordButtonClick(user);
            }
        });
    }

    @Override
    public void showInvalidInputMessage(String forComponent, String message) {
        if (forComponent.equals("OTP")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
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

    }

    @Override
    public void showAPIErrorMessage(String message) {

    }

    @Override
    public void hideKeyboard() {
        Util.hideKeyboard(btnSubmit);
    }

    @Override
    public void showProgress() {
        pbLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoader.setVisibility(View.GONE);
    }

    /*@Override
    public void apiResponse(User user) {
        Toast.makeText(this, "Password reset link sent to ur email-id.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
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
}
