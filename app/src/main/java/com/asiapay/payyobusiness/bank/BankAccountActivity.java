package com.asiapay.payyobusiness.bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.ContentLoadingProgressBar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.model.Bank;
import com.asiapay.payyobusiness.utility.Util;

public class BankAccountActivity extends AppCompatActivity implements BankAccountContracts.View {
    private AppCompatTextView tvIfsceCode, tvAccountNo, tvBankName;
    private BankAccountPresenter bankAccountPresenter;
    public static AppCompatEditText edtTxtIfscCode, edtTxtAccountNo, edtTxtAccountName;
    private LinearLayoutCompat llBankdetails;
    private CardView addBankCard;
    private AppCompatButton deleteBank, btnSave;
    private Bank bank;
    private Toolbar toolbar;
    private ContentLoadingProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_account_activity);
        initUI();
        addEventListener();
        bank = new Bank();
        bankAccountPresenter = new BankAccountPresenter(this, this);
        bankAccountPresenter.getBankList(bank);

    }

    @Override
    public void showInvalidMessage(String forComponent, String message) {

        if (forComponent.equals("etBankname")) {
            edtTxtAccountName.setError(message);
        } else if (forComponent.equals("etAccountNo")) {
            edtTxtAccountNo.setError(message);
        } else if (forComponent.equals("etIfscCode")) {
            edtTxtIfscCode.setError(message);
        }

    }

  /*  @Override
    public void onResponseSuccess(Bank bank) {
        if (bank.getIfscCode().equals("")) {

        } else {
            llBankdetails.setVisibility(View.GONE);
            addBankCard.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

        Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show();

    }*/

    @Override
    public void showBankList(Bank bank) {
        String strBankName = bank.getBankName();
        tvBankName.setText(bank.getBankName());
        tvAccountNo.setText(bank.getAccountNo());
        tvIfsceCode.setText(bank.getIfscCode());
        llBankdetails.setVisibility(View.VISIBLE);
        addBankCard.setVisibility(View.GONE);

    }

    @Override
    public void showAddBank() {
        addBankCard.setVisibility(View.VISIBLE);
        llBankdetails.setVisibility(View.GONE);
    }

    @Override
    public void DeleteRecordMsg() {


    }

    @Override
    public void showAPIErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initUI() {

        toolbar = (Toolbar) findViewById(R.id.customtollbar);
        toolbar.setTitle(getString(R.string.add_account));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        tvIfsceCode = findViewById(R.id.tvIfsceCode);
        tvAccountNo = findViewById(R.id.tvAccountNo);
        tvBankName = findViewById(R.id.tvBankName);
        edtTxtIfscCode = findViewById(R.id.edtTxtIfscCode);
        edtTxtAccountNo = findViewById(R.id.edtTxtAccountNo);
        edtTxtAccountName = findViewById(R.id.edtTxtAccountName);
        llBankdetails = findViewById(R.id.llBankdetails);
        addBankCard = findViewById(R.id.addBankCard);
        deleteBank = findViewById(R.id.deleteBank);
        btnSave = findViewById(R.id.btnSave);
        progressBar = findViewById(R.id.progressBar);


    }

    @Override
    public void addEventListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bank bank = new Bank();
                bank.setBankName(edtTxtAccountName.getText().toString());
                bank.setAccountNo(edtTxtAccountNo.getText().toString());
                bank.setIfscCode(edtTxtIfscCode.getText().toString());
                bankAccountPresenter.addBankButtonClick(bank);
            }
        });

        deleteBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bank bank = new Bank();
                bankAccountPresenter.deleteBankDetails(bank);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        Util.hideKeyboard(btnSave);
        Util.hideKeyboard(deleteBank);

    }
}
