package com.asiapay.payyobusiness.home.history;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Toast;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.model.Refund;
import com.asiapay.payyobusiness.network.Constants;
import com.asiapay.payyobusiness.utility.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class RefundActivity extends AppCompatActivity implements RefundContracts.View {
    private RefundPresenter refundPresenter;
    private AppCompatEditText edtTxtEnterAmt;
    private ContentLoadingProgressBar progressBar;
    private LinearLayoutCompat llCaseOutEnterAmount, lLTnxMsgLayout;
    private AppCompatButton btnLinkedAccount, btnAmountConfirm;
    private Toolbar toolbar;
    private AppCompatTextView tvSuccessMsg, tvAmount, tvWithdrawAmount;
    private String strTranxAmount, strFromTo, strEventType, strDate;
    private float floatAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_refund_amount);
        initUI();
        addEventListener();
        refundPresenter = new RefundPresenter(this, this);


    }

    @Override
    public void showInvalidInputMessage(String etEnterAmount, String please_enter_amount) {
        if (etEnterAmount.equals("etEnterAmount")) {
            edtTxtEnterAmt.setError(please_enter_amount);
        }
    }


    @Override
    public void showAPIMessage(String srtMessage) {
        Toast.makeText(this, "Try some time later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessView(Refund refund) {
        llCaseOutEnterAmount.setVisibility(View.GONE);
        lLTnxMsgLayout.setVisibility(View.VISIBLE);
        tvSuccessMsg.setText("$ " + edtTxtEnterAmt.getText().toString() + " " + "Successfully withdrawn from wallet to your HSBC bank Account ");
        tvWithdrawAmount.setText(edtTxtEnterAmt.getText().toString());
    }

    @Override
    public void initUI() {
        strTranxAmount = ApplicationClass.getInstance().getPreferences().getString(Constants.REFUND_AMOUNT, null);
        strFromTo = ApplicationClass.getInstance().getPreferences().getString(Constants.FROM_TO, null);
        strEventType = ApplicationClass.getInstance().getPreferences().getString(Constants.EVENT_TYPE, null);
        strDate = ApplicationClass.getInstance().getPreferences().getString(Constants.DATE, null);
        floatAmt = ApplicationClass.getInstance().getPreferences().getFloat(Constants.FLOAT_AMOUNT, 0f);
        toolbar = (Toolbar) findViewById(R.id.customtollbar);
        toolbar.setTitle(getString(R.string.refund));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        edtTxtEnterAmt = findViewById(R.id.edtTxtEnterAmt);
        progressBar = findViewById(R.id.progressBar);
        llCaseOutEnterAmount = findViewById(R.id.llCaseOutView);
        btnLinkedAccount = findViewById(R.id.btnLinkedAccount);
        lLTnxMsgLayout = findViewById(R.id.lLTnxMsgLayout);
        tvSuccessMsg = findViewById(R.id.tvSuccessMsg);
        btnAmountConfirm = findViewById(R.id.btnAmountConfirm);
        tvAmount = findViewById(R.id.tvAmount);
        tvAmount.setText(" $ " + strTranxAmount);
        tvWithdrawAmount = findViewById(R.id.tvWithdrawAmount);
    }

    @Override
    public void addEventListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAmountConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.hideKeyboard(btnAmountConfirm);
                Refund refund = new Refund();
                refund.setAmount(edtTxtEnterAmt.getText().toString());
                // Your action on done
                refundPresenter.checkEnterAmount(refund);
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
        Util.hideKeyboard(btnLinkedAccount);
    }
}
