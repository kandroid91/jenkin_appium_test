package com.asiapay.payyobusiness.generateqrcode;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.DisplayMetrics;

import com.asiapay.payyobusiness.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class ShowQrCodePresenter implements ShowQrCodeContract.Presenter {
    private ShowQrCodeContract.View showQrCodeView;

    public ShowQrCodePresenter(ShowQrCodeContract.View showQrCodeFragments) {
        this.showQrCodeView = showQrCodeFragments;
    }

    @Override
    public void generateQR(DisplayMetrics displayMetrics, String data) {
        Bitmap bitmap;
        try {
            int width = (int) (displayMetrics.densityDpi * 1.4);
            int height = (int) (displayMetrics.densityDpi * 1.4);
            bitmap = amountToImageEncode(width, height, data);
            showQrCodeView.showQRCode(bitmap);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Override
    public void onDestroy() {

    }

    Bitmap amountToImageEncode(int width, int height, String data) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    data,
                    BarcodeFormat.QR_CODE,
                    width, height, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ? Color.parseColor("#000000") : Color.parseColor("#ffffff");
                //getResources().getColor(R.color.QRCodeBlackColor) : getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, bitMatrixWidth, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
