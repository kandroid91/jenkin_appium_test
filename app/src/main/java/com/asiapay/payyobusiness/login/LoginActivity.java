package com.asiapay.payyobusiness.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.home.HomeActivity;
import com.asiapay.payyobusiness.model.User;
import com.asiapay.payyobusiness.network.Constants;
import com.asiapay.payyobusiness.utility.Util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.ContentLoadingProgressBar;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private AppCompatEditText etUsername, etPassword;
    private AppCompatButton btnLogin;
    private ContentLoadingProgressBar pbLoader;
    private LoginPresenter loginPresenter;
    private String UserId;

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        if (ApplicationClass.getInstance().getPreferences().getString(Constants.USER_ID, UserId) != null) {
            Intent i = new Intent(this, HomeActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
        initUI();
        addEventListener();
        loginPresenter = new LoginPresenter(this, this);
    }

    @Override
    public void initUI() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        pbLoader = findViewById(R.id.progressBar);
    }

    @Override
    public void addEventListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUserName(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());
                loginPresenter.onLoginButtonClick(user);
            }
        });
    }

    @Override
    public void showInvalidInputMessage(String forComponent, String message) {
        if (forComponent.equals("etUsername")) {
            etUsername.setError(message);
        } else if (forComponent.equals("etPassword")) {
            etPassword.setError(message);
        } else {
            etUsername.setError(message);
            etPassword.setError(message);
        }
    }

    @Override
    public void launchActivity() {
        // ApplicationClass.getInstance().payForDataSource.InsertUserData("sanjaybarfa", "userName", "userEmail", "");
        Intent intent = new Intent(LoginActivity.this, LoginOTPActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showAPIErrorMessage(String message) {

    }

    @Override
    public void hideKeyboard() {
        Util.hideKeyboard(btnLogin);
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
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, LoginOTPActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        System.exit(0);
    }

}
