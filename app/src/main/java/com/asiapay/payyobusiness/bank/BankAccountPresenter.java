package com.asiapay.payyobusiness.bank;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.model.Bank;

public class BankAccountPresenter implements BankAccountContracts.Presenter, BankAccountContracts.Model.OnFinishedListener {
    private BankAccountContracts.View bankView;
    private BankAccountContracts.Model bankModel;
    private Activity mContext;
    public BankAccountPresenter(BankAccountContracts.View bankAccount, BankAccountActivity context) {
        this.bankView = bankAccount;
        this.bankModel = new BankAccountModel(context);
        this.mContext=context;
    }

    @Override
    public void onFinished(Bank bank) {
        if (bankView != null) {
            bankView.hideProgress();
        }
        if (bank.getIfscCode() == null) {
            bankView.showAddBank();
        } else if (bank.getIfscCode() != null) {
            bankView.showBankList(bank);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (bankView != null) {
            bankView.hideProgress();
        }
        bankView.showAPIErrorMessage(t.getMessage());

    }

    @Override
    public void onDestroy() {
        bankView = null;
    }

    @Override
    public void addBankButtonClick(Bank bank) {

        if (bank.getBankName().equals("")) {
            bankView.showInvalidMessage("etBankname", "Please enter Bank name");
        } else if (bank.getAccountNo().equals("")) {
            bankView.showInvalidMessage("etAccountNo", "Please enter AccountNo");
        } else if (bank.getIfscCode().equals("")) {
            bankView.showInvalidMessage("etIfscCode", "Please enter IFSC Code");
        } else {
            bankView.showProgress();
            //presenter pass him self to the model
            bankModel.addBankDetails(this, bank);
        }


    }

    @Override
    public void getBankList(Bank bank) {

        bankModel.getBankList(this, bank);

    }

    @Override
    public void deleteBankDetails(Bank bank) {
        bankModel.deleteExistingBankDetails(this, bank);
        BankAccountActivity.edtTxtIfscCode.getText().clear();
        BankAccountActivity.edtTxtAccountName.getText().clear();
        BankAccountActivity.edtTxtIfscCode.getText().clear();
    }
}
