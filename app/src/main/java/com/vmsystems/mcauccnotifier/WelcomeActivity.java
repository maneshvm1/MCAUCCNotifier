package com.vmsystems.mcauccnotifier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void btnStudentOnClick(View view){
        startActivity(new Intent(getApplicationContext(),StudentLoginScanActivity.class));
    }
    public void btnStaffOnClick(View view){
        startActivity(new Intent(getApplicationContext(),StaffLoginActivity.class));
    }
}
