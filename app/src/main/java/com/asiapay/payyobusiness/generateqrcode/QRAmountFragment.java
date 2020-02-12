package com.asiapay.payyobusiness.generateqrcode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.utility.Util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 */
public class QRAmountFragment extends Fragment implements QRAmountContract.View {
    private View view;
    private AppCompatEditText edtTxtEnterAmt;
    private AppCompatButton btnConfirm;
    private QRAmountPresenter qrAmountPresenter;
    private String strEnterAmountValue;
    private Toolbar toolbar;

    public QRAmountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.scan_bottom_nav_fragment, container, false);
        initUI();
        addEventListener();
        qrAmountPresenter = new QRAmountPresenter(this);
        return view;
    }

    @Override
    public void initUI() {
        edtTxtEnterAmt = view.findViewById(R.id.edtTxtEnterAmt);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Generate QR");
    }

    @Override
    public void addEventListener() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.hideKeyboard(btnConfirm);
                QRCodePresent scanCode = new QRCodePresent();
                scanCode.setEnterAmount(edtTxtEnterAmt.getText().toString());
                strEnterAmountValue = edtTxtEnterAmt.getText().toString();
                qrAmountPresenter.onScanCodeClick(scanCode);

            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void hideKeyboard() {
        Util.hideKeyboard(btnConfirm);
    }

    @Override
    public void showInvalidInputMessage(String forComponent, String message) {
        if (forComponent.equals("edtTxtEnterAmt")) {
            edtTxtEnterAmt.setError(message);
        }
    }

    @Override
    public void apiResponse(QRCodePresent scanCode) {
        lunchQrCodeFragment();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        lunchQrCodeFragment();

    }

    @Override
    public void showQRcode() {
        lunchQrCodeFragment();
    }

    private void lunchQrCodeFragment() {
        Fragment fragment = null;
        fragment = new ShowQrCodeFragments();
        Bundle args = new Bundle();
        args.putString("qrAmount", strEnterAmountValue);
        fragment.setArguments(args);
        replaceFragment(fragment);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

    }
}
