package com.asiapay.payyobusiness.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.network.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowMessageFragment extends Fragment {
    private TextView tvMessage;
    private View view;
    private String strWithdrawCash;

    public ShowMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.show_message_fragment, container, false);
        strWithdrawCash = ApplicationClass.getInstance().getPreferences().getString(Constants.CASH_AMOUNT, null);
        initUI();
        CashoutActivity.shouldCloseActivity =true;
        return view;
    }

    private void initUI() {
        ((CashoutActivity) getActivity()).getSupportActionBar().setTitle("Message");
        tvMessage = view.findViewById(R.id.tvSuccessMsg);
        tvMessage.setText(strWithdrawCash + "  " + "Successfully withdrawn from wallet to your HSBC bank Account ");
    }

}
