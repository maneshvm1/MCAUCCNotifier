package com.vmsystems.mcauccnotifier;

import android.Manifest;
import android.arch.lifecycle.MethodCallsLogger;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class StudentLoginScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private boolean cameraPermission;

    private String intentExtraAdmnNo = "intentExtraAdmnNo";
    String admnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login_scan);

        admnNo = "";


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                cameraPermission = false;
            } else {
                cameraPermission = true;
            }
        }
    }

    public void btnScanOnClick(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!cameraPermission) {
                requestCameraPermission();
            } else {

                scanBarCode();
            }
        } else {
            scanBarCode();
        }
    }

    private void scanBarCode() {

        //move this code to handleResult function
        //from here
        admnNo = "MCAL0194";
//        Toast.makeText(this, admnNo, Toast.LENGTH_SHORT).show();
//        zXingScannerView.resumeCameraPreview(this);

        if (admnNo.matches("(M)(C)(A)(L|R)[0-9]*")) {
//            zXingScannerView.stopCamera();            uncomment this
            Intent intent = new Intent(getApplicationContext(), StudentLoginInputActivity.class);
            intent.putExtra(intentExtraAdmnNo, admnNo);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Code mismatch. Please retry!", Toast.LENGTH_SHORT).show();
        }

        //to here.



//        zXingScannerView = new ZXingScannerView(getApplicationContext());
//        setContentView(zXingScannerView);
//        zXingScannerView.setResultHandler(this);
//        zXingScannerView.startCamera();
    }

    public void requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "camera permission granted", Toast.LENGTH_SHORT).show();
                cameraPermission = true;
                scanBarCode();
            } else {

                Toast.makeText(this, "camera permission is required to scan barcode!", Toast.LENGTH_LONG).show();

            }

        }
    }

    @Override
    public void handleResult(Result result) {
//        admnNo = result.getText();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
