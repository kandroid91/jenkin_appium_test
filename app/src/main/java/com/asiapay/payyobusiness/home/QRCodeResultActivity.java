package com.asiapay.payyobusiness.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeResultActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView scannerView;
    String resultText = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this); /* Initialize object */
        setContentView(scannerView); /* Set the ScannerView as a content of current activity */

    }

    @Override
    public void onResume() {
        super.onResume();
        /* Asking user to allow access of camera */
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            scannerView.setResultHandler(this); /* Set handler for ZXingScannerView */
            scannerView.startCamera(); /* Start camera */
        } else {
            ActivityCompat.requestPermissions(QRCodeResultActivity.this, new
                    String[]{Manifest.permission.CAMERA}, 1024);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera(); /* Stop camera */
    }

    @Override
    public void handleResult(Result scanResult) {
        ToneGenerator toneNotification = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100); /* Setting beep sound */
        toneNotification.startTone(ToneGenerator.TONE_PROP_BEEP, 150);
        resultText = scanResult.getText(); /* Retrieving text from QR Code */
        Intent intent = new Intent(QRCodeResultActivity.this, ScanResultActivity.class);
        intent.putExtra("ResultText", resultText); /* Sending text to next activity to display */
        startActivity(intent);
        finish();
        /* scannerView.resumeCameraPreview(this); */ /* If you want resume scanning, call this method */
    }
}
