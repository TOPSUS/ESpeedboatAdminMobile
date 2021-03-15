package com.example.espeedboatadmin.utils;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.fragment.app.FragmentManager;

import com.example.espeedboatadmin.ui.scanner.BottomBarcode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

public class ImageAnalyzer implements ImageAnalysis.Analyzer {
    private BottomBarcode bottomBarcode;
    private FragmentManager fragmentManager;

    public ImageAnalyzer(FragmentManager fm) {
        this.fragmentManager = fm;
        this.bottomBarcode = BottomBarcode.newInstance();
    }

    @Override
    public void analyze(@NonNull ImageProxy image) {
        scanBarcode(image);
    }

    @SuppressLint("UnsafeExperimentalUsageError")
    private void scanBarcode(ImageProxy imageProxy) {
        if (imageProxy == null || imageProxy.getImage() == null) {
            return;
        }

        Image mediaImage = imageProxy.getImage();
        int rotationDegrees = imageProxy.getImageInfo().getRotationDegrees();
        InputImage image = InputImage.fromMediaImage(mediaImage, rotationDegrees);
        BarcodeScanner scanner = BarcodeScanning.getClient(new BarcodeScannerOptions.Builder()
                                                .setBarcodeFormats(Barcode.FORMAT_QR_CODE, Barcode.FORMAT_AZTEC)
                                                .build());
        Task<List<Barcode>> task = scanner.process(image);
        task.addOnCompleteListener(new OnCompleteListener<List<Barcode>>() {
                    @Override
                    public void onComplete(@NonNull Task<List<Barcode>> task) {
                        imageProxy.close();
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                    @Override
                    public void onSuccess(List<Barcode> barcodes) {
                        readBarcodeData(barcodes);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Fail [ImageAnalyzer]", e.getMessage());
                    }
                });
    }

    private int convertDegrees(int degrees) {
        switch (degrees) {
            case 0:
                return Surface.ROTATION_0;
            case 90:
                return Surface.ROTATION_90;
            case 180:
                return Surface.ROTATION_180;
            case 270:
                return Surface.ROTATION_270;
            default:
                throw new IllegalArgumentException(
                        "Rotation must be 0, 90, 180, or 270.");
        }
    }

    private void readBarcodeData(List<Barcode> barcodes) {
        for (Barcode barcode : barcodes) {
            Log.d("Barcode [readBarcodeData]", barcode.getDisplayValue());
            switch (barcode.getValueType()) {
                case Barcode.TYPE_TEXT:
                    String kode_tiket = barcode.getDisplayValue();
                    if (!bottomBarcode.isAdded()) {
                        Bundle args = new Bundle();
                        args.putString(Utils.KODE_TIKET, kode_tiket);
                        bottomBarcode.setArguments(args);
                        bottomBarcode.show(fragmentManager, "");
                    }
            }
        }
    }
}
