package com.example.rmaahmadov.instagramclone.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rmaahmadov.instagramclone.R;
import com.example.rmaahmadov.instagramclone.home.HomeActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.apache.commons.validator.routines.EmailValidator;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private Context mContex;
    private ProgressBar mProgressBar;
    private EditText mEmail, mPassword;
    private TextView pleaceWait;
    private ParseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressBar = findViewById(R.id.loginReguestLoadingProgressBar);
        pleaceWait = findViewById(R.id.pleaceWaitTextView);
        mEmail = findViewById(R.id.inputEmail);
        mPassword = findViewById(R.id.inputPassword);
        mContex = LoginActivity.this;
        pleaceWait.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        setupFirabaseAuth();
        init();
    }

    private boolean checkStringNull(String string) {
        if (string.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    public void redirectRegistration(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }


    private void init() {
        Button loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                boolean valid = EmailValidator.getInstance().isValid(email);
                String password = mPassword.getText().toString();

                if (!checkStringNull(password)) {
                    if (valid) {
                        user.logInInBackground(email, password, new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (e==null) {
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(mContex, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(mContex, "Please enter valid email!!!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(mContex, "You must fill all fealds!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    /*
     *
     * -----------------------------------FireBase-----------------------------------------*/

    private void setupFirabaseAuth() {


    }
}