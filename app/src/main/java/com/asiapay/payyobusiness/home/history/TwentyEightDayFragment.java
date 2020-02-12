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

/**
 * A simple {@link Fragment} subclass.
 */
public class TwentyEightDayFragment extends Fragment implements HomeFragmentContracts.View {

    private HistoryAdapter adapter;
    View view;
    private Context mContext;
    private RecyclerView rvTwentyEightDayHistory;
    private TwentyEightDayFragmentPresenter twentyEightDayFragmentPresenter;
    private ArrayList<Transaction> arrayListTwentyEightDay;
    private ContentLoadingProgressBar loadingProgressBar;

    public TwentyEightDayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_twenty_eight_day, container, false);
        view = inflater.inflate(R.layout.history_tab_fragment, container, false);
        arrayListTwentyEightDay = new ArrayList<Transaction>();
        twentyEightDayFragmentPresenter = new TwentyEightDayFragmentPresenter(this, this);
        initUI();
        addEventListener();
        return view;
    }

    @Override
    public void apiResponseSuccess(ArrayList<Transaction> transactionArrayList) {
        rvTwentyEightDayHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HistoryAdapter(getActivity(), transactionArrayList);
        rvTwentyEightDayHistory.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    public void initUI() {
        Transaction transactionFirstDay = new Transaction();
        rvTwentyEightDayHistory = view.findViewById(R.id.recycleView);
        loadingProgressBar = view.findViewById(R.id.pbLoader);
        twentyEightDayFragmentPresenter.getTransactionList(transactionFirstDay);
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
