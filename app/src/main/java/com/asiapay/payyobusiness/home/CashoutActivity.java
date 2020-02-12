package com.asiapay.payyobusiness.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.asiapay.payyobusiness.R;

public class CashoutActivity extends AppCompatActivity {
    private CashoutPresenter caseOutPresenter;
    private AppCompatEditText edtTxtEnterAmt;
    private ContentLoadingProgressBar progressBar;
    private LinearLayoutCompat llCaseOutEnterAmount;
    private AppCompatButton btnAmountConfirm;
    private Toolbar toolbar;
    Fragment enterAmountFragment;
    FragmentManager fragmentManager;
    public static boolean shouldCloseActivity = false;
    private String currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cash_out_activity);
        initUI();
    }


    public void initUI() {

        toolbar = (Toolbar) findViewById(R.id.customtollbar);
        toolbar.setTitle(getString(R.string.cash_out));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        edtTxtEnterAmt = findViewById(R.id.edtTxtEnterAmt);
        llCaseOutEnterAmount = findViewById(R.id.llCaseOutView);
        btnAmountConfirm = findViewById(R.id.btnAmountConfirm);
        enterAmountFragment = new EnterAmountFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linkFragment, enterAmountFragment);
        fragmentTransaction.commit();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishCurrentFragments();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishCurrentFragments();
    }

    private void finishCurrentFragments() {
        if (shouldCloseActivity) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
    }

}
