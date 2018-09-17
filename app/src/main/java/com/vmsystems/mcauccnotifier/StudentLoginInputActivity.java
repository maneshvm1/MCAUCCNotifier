package com.vmsystems.mcauccnotifier;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class StudentLoginInputActivity extends AppCompatActivity  {

    String admnNo;
    EditText editTextName, editTextPhone;
    private String intentExtraAdmnNo="intentExtraAdmnNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login_input);

        Intent intent = getIntent();
        admnNo = intent.getExtras().getString(intentExtraAdmnNo);
        Toast.makeText(this, admnNo, Toast.LENGTH_SHORT).show();

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);


    }

    public void btnLoginOnClick(View view) {
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();

//        if (!admnNo.isEmpty()) {
//            if (!name.isEmpty() ||!phone.isEmpty()) {
        Intent intent=new Intent(getApplicationContext(), StudentHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//            }
//        }
    }

}
