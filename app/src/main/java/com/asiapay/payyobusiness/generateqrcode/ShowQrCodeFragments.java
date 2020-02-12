package com.asiapay.payyobusiness.generateqrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiapay.payyobusiness.R;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowQrCodeFragments extends Fragment implements ShowQrCodeContract.View {
    private View view;
    private AppCompatTextView txtViewAmtShow;
    private AppCompatImageView qrCodeImage;

    public ShowQrCodeFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.show_qr_code_fragment, container, false);
        initUI();
        addEventListener();
        //Retrieve the value
        String qrAmount = "0";
        if (getArguments() != null)
            qrAmount = getArguments().getString("qrAmount");
        txtViewAmtShow.setText("$" + " " + qrAmount);
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        ShowQrCodePresenter showQrCodePresenter = new ShowQrCodePresenter(this);
        showQrCodePresenter.generateQR(displaymetrics, qrAmount);
        return view;
    }


    @Override
    public void showInvalidInputMessage(String forComponent, String message) {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    public void showQRCode(Bitmap bitmap) {
        qrCodeImage.setImageBitmap(bitmap);
    }

    @Override
    public void initUI() {
        txtViewAmtShow = view.findViewById(R.id.txtViewAmtShow);
        qrCodeImage = view.findViewById(R.id.qrCodeImage);
    }

    @Override
    public void addEventListener() {


    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void hideKeyboard() {

    }
}
