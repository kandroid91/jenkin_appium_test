package com.asiapay.payyobusiness.bank;

import android.content.Context;
import android.content.SharedPreferences;

import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.model.Bank;
import com.asiapay.payyobusiness.network.Constants;

import java.util.ArrayList;
import java.util.List;

public class BankAccountModel implements BankAccountContracts.Model {
    private Context mContext;

    public BankAccountModel(BankAccountActivity bankAccountActivity1) {
        this.mContext = bankAccountActivity1;
    }

    @Override
    public void addBankDetails(final OnFinishedListener onFinishedListener, Bank bank) {
       /* ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Bank> call = apiService.addBnakApi(bank);
        call.enqueue(new Callback<Bank>() {
            @Override
            public void onResponse(Call<Bank> call, Response<Bank> response) {


            }

          *//*  @Override
            public void onFailure(Call<Bank> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }*//*
        });
        Bank bank = response.body();*/
        /*List<Bank> bankList = new ArrayList<>();
        // create some players
        bank = new Bank();
        bank.setAccountNo("HSBC");
        bank.setIfscCode("SBIN004500");*/
        // add them

        SharedPreferences pref = ApplicationClass.getInstance().getPreferences();
        SharedPreferences.Editor pref_edit = pref.edit();
        pref_edit.putString(Constants.BANK_NAME, bank.getBankName());
        pref_edit.putString(Constants.ACCOUNT_NO, bank.getAccountNo());
        pref_edit.putString(Constants.IFSC_CODE, bank.getIfscCode());
        pref_edit.commit();
        onFinishedListener.onFinished(bank);
    }

    @Override
    public void getBankList(final OnFinishedListener onFinishedListener, Bank bank) {
        // create our sqlite helper class
        // list all Banks
       /* ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Bank> call = apiService.getBankApi(bank);
        call.enqueue(new Callback<Bank>() {
            @Override
            public void onResponse(Call<Bank> call, Response<Bank> response) {
                Bank bank = response.body();
                ArrayList<Bank> bankArrayList = new ArrayList<>();*/

        String strBankName = ApplicationClass.getInstance().getPreferences().getString(Constants.BANK_NAME, null);
        String strAccountNo = ApplicationClass.getInstance().getPreferences().getString(Constants.ACCOUNT_NO, null);
        String strIfscCode = ApplicationClass.getInstance().getPreferences().getString(Constants.IFSC_CODE, null);

        bank = new Bank();
        bank.setBankName(strBankName);
        bank.setAccountNo(strAccountNo);
        bank.setIfscCode(strIfscCode);
        // List<Bank> bankList = db.bankDetails();
        onFinishedListener.onFinished(bank);
        // }

      /*      @Override
            public void onFailure(Call<Bank> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/


    }

    @Override
    public void deleteExistingBankDetails(final OnFinishedListener onFinishedListener, Bank bank) {
        //db.deleteBankDetails(bank);
        //onFinishedListener.onFinished(bank);
       /* ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Bank> call = apiService.getBankApi(bank);
        call.enqueue(new Callback<Bank>() {
            @Override
            public void onResponse(Call<Bank> call, Response<Bank> response) {
                Bank bank = response.body();
                *//*ArrayList<Bank> bankArrayList = new ArrayList<>();
                Bank bank1 = new Bank();
                bank1.setBankName("HSBC");
                bank1.setAccountNo("098778738");
                bank1.setIfscCode("SBIN0034008");*//*
                // List<Bank> bankList = db.bankDetails();

            }*/
        SharedPreferences pref = ApplicationClass.getInstance().getPreferences();
        SharedPreferences.Editor pref_edit = pref.edit();
        pref_edit.putString(Constants.BANK_NAME, "");
        pref_edit.putString(Constants.ACCOUNT_NO, "");
        pref_edit.putString(Constants.IFSC_CODE, "");
        pref_edit.commit();
        bank = new Bank();
        onFinishedListener.onFinished(bank);
       /*     @Override
            public void onFailure(Call<Bank> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/
    }


}
