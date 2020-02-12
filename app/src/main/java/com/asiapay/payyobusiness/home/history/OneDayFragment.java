package com.asiapay.payyobusiness.home.history;


import android.content.Context;
import android.os.Bundle;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.adapter.HistoryAdapter;
import com.asiapay.payyobusiness.home.HomeFragmentContracts;
import com.asiapay.payyobusiness.model.Transaction;

import java.util.ArrayList;

public class OneDayFragment extends Fragment implements HomeFragmentContracts.View {
    private HistoryAdapter adapter;
    View view;
    private Context mContext;
    private RecyclerView rvFirstDayHistory;
    private OneDayFragmentPresenter firstDayFragmentPresenter;
    private ArrayList<Transaction> arrayListFirstDay;
    private ContentLoadingProgressBar loadingProgressBar;

    public OneDayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.one_day_tab_fragment, container, false);
        view = inflater.inflate(R.layout.history_tab_fragment, container, false);
        arrayListFirstDay = new ArrayList<Transaction>();
        firstDayFragmentPresenter = new OneDayFragmentPresenter(this, this);
        initUI();
        addEventListener();
        Transaction transactionFirstDay = new Transaction();
        firstDayFragmentPresenter.getTransactionList(transactionFirstDay);
        return view;
    }

    @Override
    public void apiResponseSuccess(ArrayList<Transaction> transactionArrayList) {
        rvFirstDayHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HistoryAdapter(getActivity(), transactionArrayList);
        rvFirstDayHistory.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    public void initUI() {
        rvFirstDayHistory = view.findViewById(R.id.recycleView);
        loadingProgressBar = view.findViewById(R.id.pbLoader);
    }

    @Override
    public void addEventListener() {

    }

    @Override
    public void showProgress() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideKeyboard() {

    }
}
