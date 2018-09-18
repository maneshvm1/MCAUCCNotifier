package com.vmsystems.mcauccnotifier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StaffLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
    }

    public void btnLoginOnClick(View view) {
        startActivity(new Intent(getApplicationContext(), StaffHomeActivity.class));

    }
}
