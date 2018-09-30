package com.vmsystems.mcauccnotifier;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;


public class StaffLoginSignupActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private static final String staff_sign_in = "http://192.168.42.129/mcauccnotifier/staff_log_in.php";

    LinearLayout mLinearLayoutSignUp, mLinearLayoutLogIn;
    TextView mTextViewLogin, mTextViewSignup;
    SharedPreferences sharedPreferences;

    Spinner mSpinnerDesignation, mSpinnerNameTitle;
    EditText mEditTextName, mEditTextPhone,mEditTextUsername,mEditTextPassword;

    Button mButtonStaffLogin,mButtonStaffSignup;

    String preference = "peference";
    public String spUserType = "UserType", spUserId = "UserId", spPublicId = "PublicId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login_signup);

        sharedPreferences = getSharedPreferences(preference, MODE_PRIVATE);

        mLinearLayoutSignUp = (LinearLayout) findViewById(R.id.linearLayoutStaffSignUp);
        mLinearLayoutLogIn = (LinearLayout) findViewById(R.id.linearLayoutStaffLogin);
        mTextViewLogin = (TextView) findViewById(R.id.textViewLogin);
        mTextViewSignup = (TextView) findViewById(R.id.textViewSignup);

        mSpinnerDesignation = (Spinner) findViewById(R.id.spinnerDesignation);
        mSpinnerNameTitle = (Spinner) findViewById(R.id.spinnerNameTitle);
        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextPhone = (EditText) findViewById(R.id.editTextPhone);
        mEditTextUsername = (EditText) findViewById(R.id.editTextUsername);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);

        mButtonStaffLogin=(Button )findViewById(R.id.buttonStaffLogin);
        mButtonStaffSignup=(Button )findViewById(R.id.buttonStaffSignUp);

        viewLogin();

        mButtonStaffLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        mButtonStaffSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void btnLogInOnClick(View view) {







        //TODO Insert login detils into db and set usertype nd user details into sp
      /*
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("spUserType", "admin");
        editor.putString("spUserType", "staff");
        editor.commit();

        sharedPreferences.getString("key", "vval");
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
      */
    }

    public void btnSignUpOnClick(View view) {


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Sign Up successful, Please Log In.");

        alertDialogBuilder.setPositiveButton("Log In", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                viewLogin();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void mTextViewSignUpOnClick(View view) {
        mTextViewLogin.setTextSize(30);
        mTextViewSignup.setTextSize(40);

        mTextViewSignup.setTextColor(Color.BLACK);
        mTextViewLogin.setTextColor(Color.GRAY);
        mLinearLayoutLogIn.setVisibility(View.INVISIBLE);
        mLinearLayoutSignUp.setVisibility(View.VISIBLE);


        final Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 30000);
            }
        };
        handler.postDelayed(r, 0000);
        new SendRequest1().execute();

        new Runnable() {
            @Override
            public void run() {
                new SendRequest1().execute();
            }
        }.run();


    }

    public class SendRequest1 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            try {

                String ip = new IPAddressClass().getIPAddress();
                URL url = new URL("http://" + ip + "staff_sign_up.php");
                JSONObject postDataParams = new JSONObject();

                Log.e("params", postDataParams.toString());

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                // connection.setRequestProperty("Content-Length", "" +
                //  Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
//Send request
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                // wr.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
//Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String dbrfid;
                StringBuffer response = new StringBuffer();
                while ((dbrfid = rd.readLine()) != null) {
                    response.append(dbrfid);
                    response.append('\r');


                }
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(), "hai", Toast.LENGTH_LONG).show();
            //   Toast.makeText(Track.this,s,Toast.LENGTH_LONG).show();
            //String str1;
            //s=s.trim();
            //  lst = (ListView) findViewById(R.id);

            String dbdata = s.trim();
            String str[] = dbdata.split("\\*");
            // mownerview.setText("2 Ltrs");
            // mrfidview.setText("0.802");
            str[0] = str[0].trim();
//            mstatusview.setText(str[0]);
//            malcoholview.setText(str[1]);
//            mtemperatureview.setText(str[2]);
//            mspeedview.setText(str[3]);
          /*  final MediaPlayer mp = MediaPlayer.create(Track.this,R.raw.beep);
            if(Integer.parseInt(str[0])>110)
            {
                mp.start();

            }*/


        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }

    }


    public void mTextViewLogInOnClick(View view) {
        viewLogin();
    }

    public void viewLogin() {
        mTextViewLogin.setTextSize(40);
        mTextViewSignup.setTextSize(30);

        mTextViewSignup.setTextColor(Color.GRAY);
        mTextViewLogin.setTextColor(Color.BLACK);
        mLinearLayoutSignUp.setVisibility(View.INVISIBLE);
        mLinearLayoutLogIn.setVisibility(View.VISIBLE);
    }
}
