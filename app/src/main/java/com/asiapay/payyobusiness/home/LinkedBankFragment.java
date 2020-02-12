package com.asiapay.payyobusiness.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.login.ApplicationClass;
import com.asiapay.payyobusiness.network.Constants;

public class LinkedBankFragment extends Fragment {
    public CashoutActivity cashOutActivity;
    private AppCompatTextView linkedTextView, tvLinkedAmt;
    private Toolbar toolbar;
    private AppCompatButton linkedAccountButton;
    private String strCash;
    private View view;
    Fragment showMessageFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.linked_bank, container, false);
        inti();
        return view;
    }

    private void inti() {
        ((CashoutActivity) getActivity()).getSupportActionBar().setTitle("Linked Account");
        linkedTextView = view.findViewById(R.id.edtLinkedAmount);
        linkedAccountButton = view.findViewById(R.id.btnLinkedAccount);
        tvLinkedAmt = view.findViewById(R.id.tvLinkedAmt);
        linkedTextView.setText(strCash);
        tvLinkedAmt.setText(strCash);

  /*  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        strCash = ApplicationClass.getInstance().getPreferences().getString(Constants.CASH_AMOUNT, null);
        setContentView(R.layout.linked_bank);

        linkedTextView = findViewById(R.id.edtLinkedAmount);
        linkedAccountButton = findViewById(R.id.btnLinkedAccount);
        linearLayout = findViewById(R.id.linearLayout);
        tvSuccessMsg = findViewById(R.id.tvSuccessMsg);
        tvLinkedAmt = findViewById(R.id.tvLinkedAmt);
        lLTnxMsgLayout = findViewById(R.id.lLTnxMsgLayout);
        linkedTextView.setText(strCash);
        tvLinkedAmt.setText(strCash);*/


    /*    toolbar = (Toolbar) findViewById(R.id.customtollbar);
        toolbar.setTitle(getString(R.string.linked_bank));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);*/
       /* toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/


        linkedAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  toolbar = (Toolbar) findViewById(R.id.customtollbar);
                toolbar.setTitle(getString(R.string.success_msg));
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
                setSupportActionBar(toolbar);*/
             /*   SpannableString text = new SpannableString("$ 500");
                // make "Iam" (characters 0 to 3) green
                text.setSpan(new ForegroundColorSpan(Color.parseColor("#0085ca")), 0, 5, 0);
                tvSuccessMsg.setText(text + " " + "Successfully withdrawn from wallet to your HSBC bank Account ");
                linearLayout.setVisibility(View.GONE);
                lLTnxMsgLayout.setVisibility(View.VISIBLE);*/
              /*  toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(LinkedBankFragment.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                });*/

                showMessageFragment = new ShowMessageFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.linkFragment, showMessageFragment);
                fragmentTransaction.commit();

            }
        });
    }


}
