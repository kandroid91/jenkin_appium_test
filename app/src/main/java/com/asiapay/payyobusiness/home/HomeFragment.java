package com.asiapay.payyobusiness.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiapay.payyobusiness.adapter.HistoryAdapter;
import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.model.Transaction;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeFragmentContracts.View {
    private HistoryAdapter adapter;
    private Context mContext;
    private View view;
    private AppCompatTextView tvViewAll;
    private RecyclerView recyclerView;
    private HomeFragmentPresenter homeFragmentPresenter;
    ArrayList<Transaction> transactionArrayList;
    private ContentLoadingProgressBar loadingProgressBar;
    private AppCompatButton btnCaseAmount;

    public HomeFragment() {
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
        view = inflater.inflate(R.layout.home_tab_fragment, container, false);
        transactionArrayList = new ArrayList<Transaction>();
        homeFragmentPresenter = new HomeFragmentPresenter(this, this);
        initUI();
        addEventListener();
        Transaction transaction = new Transaction();
        homeFragmentPresenter.getTransactionList(transaction);
        return view;
    }


    @Override
    public void apiResponseSuccess(ArrayList<Transaction> transactionArrayList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HistoryAdapter(getActivity(), transactionArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    public void initUI() {
        tvViewAll = view.findViewById(R.id.tvViewAll);
        recyclerView = view.findViewById(R.id.recycleView);
        loadingProgressBar = view.findViewById(R.id.progressBar);
        btnCaseAmount = view.findViewById(R.id.btnCaseAmount);
    }

    @Override
    public void addEventListener() {
        tvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.bottomNavigationView.setSelectedItemId(R.id.nav_history);
            }
        });

        btnCaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CashoutActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
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
