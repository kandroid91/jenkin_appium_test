package com.asiapay.payyobusiness.home;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.model.CaseOut;
import com.asiapay.payyobusiness.network.Constants;
import com.asiapay.payyobusiness.utility.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterAmountFragment extends Fragment implements CashoutContracts.View {
    public CashoutActivity cashoutActivity;
    private View view;
    private CashoutPresenter caseOutPresenter;
    private AppCompatEditText edtTxtEnterAmt;
    private AppCompatButton btnAmountConfirm;
    private LinearLayoutCompat llCaseOutEnterAmount;
    private ContentLoadingProgressBar progressBar;
    Fragment linkedBankFragment;

    public EnterAmountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.enter_amount_fragment, container, false);
        initUI();
        CashoutActivity.shouldCloseActivity = true;
        addEventListener();
        caseOutPresenter = new CashoutPresenter(this, this);
        return view;
    }

    @Override
    public void showInvalidInputMessage(String etEnterAmount, String please_enter_amount) {
        if (etEnterAmount.equals("etEnterAmount")) {
            edtTxtEnterAmt.setError(please_enter_amount);
        }
    }

    @Override
    public void showLinkedAccount() {
        SharedPreferences.Editor edit = ApplicationClass.getInstance().getPreferences().edit();
       /* Intent intent = new Intent();
        intent.setClass(CashoutActivity.this, LinkedBankFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
        linkedBankFragment = new LinkedBankFragment();
        edit.putString(Constants.CASH_AMOUNT, "$" + " " + edtTxtEnterAmt.getText().toString().trim());
        edit.commit();
        //startActivity(intent);
        // finish();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.linkFragment, linkedBankFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void showAPIMessage(String srtMessage) {

    }

    @Override
    public void showSuccessView() {

    }

    @Override
    public void initUI() {
        ((CashoutActivity) getActivity()).getSupportActionBar().setTitle("Enter Amount");
        cashoutActivity = (CashoutActivity) this.getActivity();
        edtTxtEnterAmt = view.findViewById(R.id.edtTxtEnterAmt);
        btnAmountConfirm = view.findViewById(R.id.btnAmountConfirm);
    }

    @Override
    public void addEventListener() {
        btnAmountConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CashoutActivity.shouldCloseActivity = false;
                CaseOut caseOut = new CaseOut();
                // Your action on done
                caseOut.setAmount(edtTxtEnterAmt.getText().toString().trim());
                caseOutPresenter.checkEnterAmount(caseOut);
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
        Util.hideKeyboard(btnAmountConfirm);
    }
/*
    @Override
    public boolean onSupportNavigateUp() {
        cashoutActivity.onBackPressed();
        return true;
    }*/
}
