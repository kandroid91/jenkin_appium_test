package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.model.Refund;

public class RefundPresenter implements RefundContracts.Presenter, RefundContracts.Model.OnFinishedListener {
    private RefundContracts.View refundView;
    private RefundContracts.Model refundModel;

    public RefundPresenter(RefundContracts.View refundView, RefundActivity context) {
        this.refundView = refundView;
        this.refundModel=new RefundModel();
    }

    @Override
    public void onFinished(Refund refund) {
        if (refundView != null) {
            refundView.hideProgress();
            refundView.showSuccessView(refund);

        }
        if (refund.getAmount() == null) {
            refundView.showAPIMessage("try some times later ");
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (refundView != null) {
            refundView.hideProgress();
        }
        refundView.showAPIMessage(t.getMessage());
    }

    @Override
    public void onDestroy() {
        refundView = null;
    }



    @Override
    public void checkEnterAmount(Refund refund) {
        if (refund.getAmount().equals("")) {
            refundView.showInvalidInputMessage("etEnterAmount", "Please enter amount");
        } else {
            //caseOutView.
            refundModel.transferPayment(this, refund);
        }
    }


}
