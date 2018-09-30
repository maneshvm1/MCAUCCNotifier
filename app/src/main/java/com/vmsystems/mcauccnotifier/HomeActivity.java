package com.vmsystems.mcauccnotifier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void linearLayoutNoticeOnClick(View view) {
        startActivity(new Intent(getApplicationContext(), ListNoticesActivity.class));
    }

    public void linearLayoutReminderOnClick(View view) {
        startActivity(new Intent(getApplicationContext(), ListRemindersActivity.class));
    }

    public void linearLayoutDocumentOnClick(View view) {
        startActivity(new Intent(getApplicationContext(), ListDocumentsActivity.class));
    }

    public void linearLayoutPhotoOnClick(View view) {
        startActivity(new Intent(getApplicationContext(), ListPhotosActivity.class));
    }

    public void linearLayoutSuggestionOnClick(View view) {
//        startActivity(new Intent(getApplicationContext(), AddSuggestionActivity.class));    //if student
//        startActivity(new Intent(getApplicationContext(), ListSuggestionsActivity.class));  //if admin
    }
}
