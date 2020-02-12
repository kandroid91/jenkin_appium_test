package com.asiapay.payyobusiness.home.history;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.adapter.HistoryAdapter;
import com.asiapay.payyobusiness.home.HomeActivity;
import com.asiapay.payyobusiness.home.HomeFragmentContracts;
import com.asiapay.payyobusiness.model.CustomTransactionDate;
import com.asiapay.payyobusiness.model.Transaction;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomHistoryTabFragment extends Fragment implements HomeFragmentContracts.View {
    private View view;
    private ArrayList<Transaction> transactionArrayList;
    private CustomHistoryTabFragmentPresenter customFragmentPresenter;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private RecyclerView recyclerView;
    private ContentLoadingProgressBar loadingProgressBar;
    private HistoryAdapter adapter;
    private Context mContext;
    private AppCompatButton showResults, filterBtnHide;
    private LinearLayoutCompat visibleLayout, hideLayout;

    public CustomHistoryTabFragment() {
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
        //  return inflater.inflate(R.layout.custom_histort_tab_fragment, container, false);
        view = inflater.inflate(R.layout.custom_histort_tab_fragment, container, false);
        transactionArrayList = new ArrayList<Transaction>();
        initUI();
        addEventListener();
        Transaction transactionCustom = new Transaction();
        customFragmentPresenter = new CustomHistoryTabFragmentPresenter(this, this);
        return view;
    }


    @Override
    public void initUI() {
        recyclerView = view.findViewById(R.id.recycleView);
        loadingProgressBar = view.findViewById(R.id.progressBar);
        showResults = view.findViewById(R.id.filterBtn);
        filterBtnHide = view.findViewById(R.id.filterBtnHide);
        visibleLayout = view.findViewById(R.id.visibleLayout);
        hideLayout = view.findViewById(R.id.hideLayout);
    }

    @Override
    public void addEventListener() {

        showResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);
                final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(view);
                dialog.show();
                final AppCompatEditText edttranxID = view.findViewById(R.id.edttranxID);
                final AppCompatTextView fromDate = view.findViewById(R.id.tvfromDate1);
                final AppCompatTextView toDate = view.findViewById(R.id.tvToDate1);
                AppCompatButton searchBtn = view.findViewById(R.id.searchBtn);
                AppCompatImageView cancelBtn = view.findViewById(R.id.cancelBtn);

                filterBtnHide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        visibleLayout.setVisibility(View.VISIBLE);
                        edttranxID.getText().clear();
                        fromDate.setText("");
                        toDate.setText("");
                        // hideLayout.setVisibility(View.GONE);
                        dialog.show();
                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                    }
                });
                AppCompatTextView tvStatus = view.findViewById(R.id.tvStatus);
                AppCompatSpinner spnTranx = view.findViewById(R.id.spnTranx);
                searchBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     /*   if (edttranxID.getText().toString().equals("") &&
                                fromDate.getText().toString().equals("") &&
                                toDate.getText().toString().trim().equals("")) {
                            System.out.println("");

                        } else {*/
                        System.out.println(edttranxID.getText().toString());
                        visibleLayout.setVisibility(View.GONE);
                        hideLayout.setVisibility(View.VISIBLE);
                        dialog.hide();
                        Transaction transactionFirstDay = new Transaction();
                        customFragmentPresenter.getTransactionList(transactionFirstDay);
                        // }

                    }
                });

                fromDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        // Launch Date Picker Dialog
                        DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                fromDate.setText((dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth) + "/" + (monthOfYear + 1 < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                        dpd.show();
                    }
                });

                toDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        // Launch Date Picker Dialog
                        DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                toDate.setText((dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth) + "/" + (monthOfYear + 1 < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                        dpd.show();
                    }
                });
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
        loadingProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void apiResponseSuccess(ArrayList<Transaction> transactionList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HistoryAdapter(getActivity(), transactionList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}
