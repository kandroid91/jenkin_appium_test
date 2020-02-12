package com.asiapay.payyobusiness.network;

import com.asiapay.payyobusiness.model.Bank;
import com.asiapay.payyobusiness.generateqrcode.QRCodePresent;
import com.asiapay.payyobusiness.model.CaseOut;
import com.asiapay.payyobusiness.model.QrScan;
import com.asiapay.payyobusiness.model.Transaction;
import com.asiapay.payyobusiness.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("validateLogin")
    Call<User> validateLogin(@Body User user);

    @POST("validateOTP")
    Call<User> validateOTP(@Body User user);

    @POST("prepareQRAmountValue")
    Call<QRCodePresent> prepareQRAmountValue(@Body QRCodePresent qrCodePresent);

    @POST("validateAmount")
    Call<Transaction> transactionHistory(@Body Transaction transaction);

    @POST("forgotPassword")
    Call<User> forgotPassword(@Body User user);

    @POST("TransactionDetails")
    Call<Transaction> transactionDetails(Transaction transaction);

    @POST("BankAccount")
    Call<Bank> getBankApi(Bank bank);

    @POST("addBankAccount")
    Call<Bank> addBnakApi(Bank bank);
    @POST("addBankAccount")
    Call<CaseOut> getTransferPayment(CaseOut caseOut);

    @POST("scanQr")
    Call<QrScan> scanQrApi(QrScan qrScan);


}
