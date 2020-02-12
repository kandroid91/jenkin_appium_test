package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.Transaction;

import java.util.ArrayList;

public class HomeFragmentModel implements HomeFragmentContracts.Model {
    @Override
    public void getTransactionList(final OnFinishedListener onFinishedListener, Transaction transactionReq) {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setTransactionID("8746541");
        transaction.setFromTo("Food Panda Rider");
        transaction.setAmount((float) 20.00);
        transaction.setDate("2019-12-05 10:02:32");
        transaction.setEventType("Money received");
        transactionList.add(transaction);
        //2nd
        transaction = new Transaction();
        transaction.setTransactionID("6546769");
        transaction.setFromTo("Standard Chartered Bank");
        transaction.setAmount((float) -50.23);
        transaction.setDate("2019-12-05 10:02:32");
        transaction.setEventType("Cashout");
        transactionList.add(transaction);
        //3rd
        transaction = new Transaction();
        transaction.setTransactionID("435678");
        transaction.setFromTo("Food Panda Rider");
        transaction.setAmount((float) 50.23);
        transaction.setDate("2019-12-05 10:02:32");
        transaction.setEventType("Money received");
        transactionList.add(transaction);
        //4th
        transaction = new Transaction();
        transaction.setTransactionID("1342567");
        transaction.setFromTo("Food Panda Rider");
        transaction.setAmount((float) -500.23);
        transaction.setDate("2019-12-05 10:02:32");
        transaction.setEventType("Money received");
        transactionList.add(transaction);
        //2nd
        transaction = new Transaction();
        transaction.setTransactionID("6546769");
        transaction.setFromTo("Standard Chartered Bank");
        transaction.setAmount((float) -50.23);
        transaction.setDate("2019-12-05 10:02:32");
        transaction.setEventType("Cashout");
        transactionList.add(transaction);
        //3rd
        transaction = new Transaction();
        transaction.setTransactionID("435678");
        transaction.setFromTo("Food Panda Rider");
        transaction.setAmount((float) 50.23);
        transaction.setDate("2019-12-05 10:02:32");
        transaction.setEventType("Money received");
        transactionList.add(transaction);
        //4th
        transaction = new Transaction();
        transaction.setTransactionID("1342567");
        transaction.setFromTo("Food Panda Rider");
        transaction.setAmount((float) -500.23);
        transaction.setDate("2019-12-05 10:02:32");
        transaction.setEventType("Money received");
        transactionList.add(transaction);
        //2nd
        transaction = new Transaction();
        transaction.setTransactionID("6546769");
        transaction.setFromTo("Standard Chartered Bank");
        transaction.setAmount((float) -50.23);
        transaction.setDate("2019-12-05 10:02:32");
        transaction.setEventType("Cashout");
        transactionList.add(transaction);
        //3rd
        transaction = new Transaction();
        transaction.setTransactionID("435678");
        transaction.setFromTo("Food Panda Rider");
        transaction.setAmount((float) 50.23);
        transaction.setDate("2019-12-05 10:02:32");
        transaction.setEventType("Money received");
        transactionList.add(transaction);
        //4th
        transaction = new Transaction();
        transaction.setTransactionID("1342567");
        transaction.setFromTo("Food Panda Rider");
        transaction.setAmount((float) -500.23);
        transaction.setDate("2019-12-05 10:02:32");
        transaction.setEventType("Money received");
        transactionList.add(transaction);

        onFinishedListener.onFinished(transactionList);

        /*ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Transaction> historyCall = apiService.transactionHistory(transactionReq);

        historyCall.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {

            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/


    }
}
