package com.asiapay.payyobusiness.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.bank.BankAccountActivity;
import com.asiapay.payyobusiness.generateqrcode.QRAmountFragment;
import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.login.ChangeLanguageActivity;
import com.asiapay.payyobusiness.login.LoginActivity;
import com.asiapay.payyobusiness.notification.PushNotificationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static BottomNavigationView bottomNavigationView;
    private DrawerLayout mNavDrawer;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        mNavigationView = findViewById(R.id.nav_view);
        mNavDrawer = findViewById(R.id.mDrawerLayout);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Restaurant Partner App");
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mBarDrawerToggle = new ActionBarDrawerToggle(this, mNavDrawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mNavDrawer.addDrawerListener(mBarDrawerToggle);
        mBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        // [START retrieve_current_token]
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            // Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                    }
                });
        // [END retrieve_current_token]
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            final int previousItem = bottomNavigationView.getSelectedItemId();
            final int nextItem = menuItem.getItemId();
            if (previousItem != nextItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        return true;
                    case R.id.nav_history:
                        selectedFragment = new HistoryFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        return true;
                    case R.id.nav_scan:
                        selectedFragment = new QRAmountFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        return true;
                    case R.id.nav_notification:
                        selectedFragment = new NotificationFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        return true;
                    case R.id.nav_profile:
                        selectedFragment = new ProfileUpdateFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        return true;
                }
            }
            return false;
        }
    };

  /*  @Override
    protected void onDestroy() {
        super.onDestroy();

    }*/

    @Override
    public void onBackPressed() {
        int seletedItemId = bottomNavigationView.getSelectedItemId();
        if (mNavDrawer.isDrawerOpen(GravityCompat.START)) {
            mNavDrawer.closeDrawer(GravityCompat.START);
        } else if (R.id.nav_home != seletedItemId) {
            setHomeItem(HomeActivity.this);
        } else {
            super.onBackPressed();
        }
    }

    public static void setHomeItem(Activity activity) {
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_update_profile:
                mNavDrawer.closeDrawer(GravityCompat.START);
               /* Intent intent = new Intent();
                intent.setClass(HomeActivity.this, UpdateProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
                bottomNavigationView.setSelectedItemId(R.id.nav_profile);
                // Toast.makeText(this, "u clicked on update profile", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_scanQr:
                mNavDrawer.closeDrawer(GravityCompat.START);
                Intent intentScanQr = new Intent();
                intentScanQr.setClass(HomeActivity.this, QRCodeResultActivity.class);
                intentScanQr.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentScanQr);
                break;
            case R.id.nav_bank_account:
                mNavDrawer.closeDrawer(GravityCompat.START);
                Intent intBankAcount = new Intent();
                intBankAcount.setClass(HomeActivity.this, BankAccountActivity.class);
                intBankAcount.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intBankAcount);
                // Toast.makeText(this, "u clicked on bank account ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_notificationTop:
                mNavDrawer.closeDrawer(GravityCompat.START);
                Intent intentNotification = new Intent();
                intentNotification.setClass(HomeActivity.this, PushNotificationActivity.class);
                intentNotification.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentNotification);

                // Toast.makeText(this, "u clicked on Push Notification", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_change_language:
                mNavDrawer.closeDrawer(GravityCompat.START);
                Intent intentLanguage = new Intent();
                intentLanguage.setClass(HomeActivity.this, ChangeLanguageActivity.class);
                intentLanguage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentLanguage);


                break;
            case R.id.nav_logout:
                SharedPreferences pref = ApplicationClass.getInstance().getPreferences();
                SharedPreferences.Editor pref_edit = pref.edit();
                pref_edit.clear();
                pref_edit.commit();
                //  ApplicationClass.getInstance().payForDataSource.deleteUserData();
                Intent i = new Intent(this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                break;
        }
        return true;
    }


}
