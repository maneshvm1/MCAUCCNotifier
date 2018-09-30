package com.vmsystems.mcauccnotifier;

import android.Manifest;
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

public class StudentLoginActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private boolean cameraPermission;

    String admnNo;

    EditText mEditTextName, mEditTextPhone;
    LinearLayout mLinearLayoutScan, mLinearLayoutInput;

    String spUserTypeStudent="stdnt", spUserTypeAdmin="admn", spUserTypeStaff="stdnt",spId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextPhone = (EditText) findViewById(R.id.editTextPhone);
        mLinearLayoutScan = (LinearLayout) findViewById(R.id.linearLayoutScan);
        mLinearLayoutInput = (LinearLayout) findViewById(R.id.linearLayoutInput);

        mLinearLayoutInput.setVisibility(View.INVISIBLE);
        mLinearLayoutScan.setVisibility(View.VISIBLE);

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
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
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
        admnNo = result.getText().trim();

//        admnNo = "MCAL0194";
        Toast.makeText(this, admnNo, Toast.LENGTH_SHORT).show();
//        zXingScannerView.resumeCameraPreview(this);

        if (admnNo.startsWith("MCAL") || admnNo.startsWith("MCAR")) {

            Toast.makeText(this, "MCA", Toast.LENGTH_SHORT).show();
            if (admnNo.matches("(M)(C)(A)(L|R)[0-9]*")) {
                zXingScannerView.stopCamera();

                mLinearLayoutInput.setVisibility(View.VISIBLE);
                mLinearLayoutScan.setVisibility(View.INVISIBLE);


            } else {
                Toast.makeText(this, "Code mismatch. Please retry!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid code", Toast.LENGTH_SHORT).show();
            zXingScannerView.resumeCameraPreview(this);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void btnLoginOnClick(View view) {
        String name = mEditTextName.getText().toString().trim();
        String phone = mEditTextPhone.getText().toString().trim();

        if (admnNo.isEmpty()) {
            Toast.makeText(this, "admn no is null", Toast.LENGTH_SHORT).show();
        } else {
            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Input both your name & phone number to continue", Toast.LENGTH_SHORT).show();
            } else {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                intent.putExtra()
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }

}
