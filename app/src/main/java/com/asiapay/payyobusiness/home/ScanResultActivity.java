package com.asiapay.payyobusiness.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ContentLoadingProgressBar;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.DialogInterface;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.model.QrScan;
import com.asiapay.payyobusiness.utility.Util;

public class ScanResultActivity extends AppCompatActivity implements ScanResultContracts.View {
    private AppCompatTextView txtScanResult;
    private AppCompatButton btnConfirmAmt;
    private ScanResultPresenter scanResultPresenter;
    private ContentLoadingProgressBar loadingProgressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_qr_result);
        initUI();
        addEventListener();
        scanResultPresenter = new ScanResultPresenter(this, this);
    }

    @Override
    public void showAPIErrorMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchAlert() {
        Builder builder = new Builder(ScanResultActivity.this);
        builder.setTitle("Alert");
        builder.setCancelable(false);
        builder.setMessage("your payment has been success");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

    @Override
    public void initUI() {
        toolbar = (Toolbar) findViewById(R.id.customtollbar);
        toolbar.setTitle(getString(R.string.scan_results));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        txtScanResult = (AppCompatTextView) findViewById(R.id.txtScanResult);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String scanResult = extras.getString("ResultText"); /* Retrieving text of QR Code */
            txtScanResult.setText(scanResult);
        }
        btnConfirmAmt = findViewById(R.id.btnConfirmAmt);
        loadingProgressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void addEventListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnConfirmAmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QrScan qrScan = new QrScan();
                qrScan.setAmount(txtScanResult.getText().toString());
                scanResultPresenter.onScanButtonClick(qrScan);

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
        Util.hideKeyboard(btnConfirmAmt);
    }
}
