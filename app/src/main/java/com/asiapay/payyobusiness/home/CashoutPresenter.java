package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.CaseOut;

public class CashoutPresenter implements CashoutContracts.Presenter, CashoutContracts.Model.OnFinishedListener {
    private CashoutContracts.View caseOutView;
    private CashoutContracts.Model caseOutModel;

    public CashoutPresenter(CashoutContracts.View caseOutView, EnterAmountFragment context) {
        this.caseOutView = caseOutView;
        this.caseOutModel = new CashoutModel();
    }

    @Override
    public void onFinished(CaseOut caseOut) {

        if (caseOutView != null) {
            caseOutView.hideProgress();
            caseOutView.showSuccessView();

        }
        if (caseOut.getAmount() == null) {
            caseOutView.showAPIMessage("try some times later ");
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (caseOutView != null) {
            caseOutView.hideProgress();
        }
        caseOutView.showAPIMessage(t.getMessage());
    }

    @Override
    public void onDestroy() {
        caseOutView = null;
    }

    @Override
    public void getCashOutList(CaseOut caseOut) {

    }

    @Override
    public void checkEnterAmount(CaseOut caseOut) {
        if (caseOut.getAmount().equals("")) {
            caseOutView.showInvalidInputMessage("etEnterAmount", "Please enter amount");
        } else {
            caseOutView.showLinkedAccount();
        }
    }
    @Override
    public void confirmPaymentTransfer(CaseOut caseOut) {
        caseOutModel.transferPayment(this, caseOut);

    }
}
