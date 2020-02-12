package com.asiapay.payyobusiness.home.history;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;

import android.view.View;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.model.Transaction;
import com.asiapay.payyobusiness.network.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionDetailsActivity extends AppCompatActivity implements TransactionDetailsContracts.View {
    private Context mContext;
    private TransactionDetailsPresenter transactionDetailsPresenter;
    private ContentLoadingProgressBar progressBar;
    private AppCompatButton btnRefund;
    private Toolbar toolbar;
    private AppCompatTextView tvTranxIdNo, tvTraxDate, tvTraxTime, tvOrderName, tvTranxDoneAmount;
    private String strFromTo, strEventType, strDate, strRefundAmount;
    private Float floatAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_details);
        transactionDetailsPresenter = new TransactionDetailsPresenter(this, this);
        initUI();
        initValues();
        addEventListener();
    }

    @Override
    public void showInvalidInputMessage(String forComponent, String message) {

    }

    @Override
    public void apiResponse(Transaction transaction) {

    }


    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    public void initUI() {
        toolbar = (Toolbar) findViewById(R.id.customtollbar);
        toolbar.setTitle(getString(R.string.Transaction_Details));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        tvTranxIdNo = findViewById(R.id.tvTranxIdNo);
        tvTraxDate = findViewById(R.id.tvTraxDate);
        tvTraxTime = findViewById(R.id.tvTraxTime);
        tvOrderName = findViewById(R.id.tvOrderName);
        progressBar = findViewById(R.id.progressBar);
        btnRefund = findViewById(R.id.btnRefund);
        tvTranxDoneAmount = findViewById(R.id.tvTranxDoneAmount);
    }

    private void initValues() {
        strFromTo = ApplicationClass.getInstance().getPreferences().getString(Constants.FROM_TO, null);
        strRefundAmount = ApplicationClass.getInstance().getPreferences().getString(Constants.REFUND_AMOUNT, null);
        strEventType = ApplicationClass.getInstance().getPreferences().getString(Constants.EVENT_TYPE, null);
        strDate = ApplicationClass.getInstance().getPreferences().getString(Constants.DATE, null);
        floatAmt = ApplicationClass.getInstance().getPreferences().getFloat(Constants.FLOAT_AMOUNT, 0f);
        tvTranxIdNo.setText(strFromTo);
        tvOrderName.setText(strEventType);
        tvTraxDate.setText(strDate.split(" ")[0]);
        tvTraxTime.setText(strDate.split(" ")[1]);
        tvTranxDoneAmount.setText(String.valueOf(strRefundAmount));

    }

    @Override
    public void addEventListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TransactionDetailsActivity.this, RefundActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideKeyboard() {
    }
}
