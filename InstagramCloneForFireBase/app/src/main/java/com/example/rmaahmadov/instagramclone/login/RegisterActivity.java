package com.example.rmaahmadov.instagramclone.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rmaahmadov.instagramclone.R;
import com.example.rmaahmadov.instagramclone.home.HomeActivity;
import com.example.rmaahmadov.instagramclone.models.UserAccountSettings;
import com.example.rmaahmadov.instagramclone.utils.ParseMethods;
import com.parse.Parse;
import com.parse.ParseConfig;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import static android.widget.Toast.LENGTH_SHORT;


public class RegisterActivity extends AppCompatActivity {
    private ParseUser user;
    private static final String TAG = "RegisterActivity";
    private Context mContex;
    private ProgressBar mProgressBar;
    private EditText mEmail, mPassword, mPhoneNumber, mUsername;
    private TextView pleaceWait;
    private Button btnRegister;
    private ParseMethods fireBaseMethods;
    private String append = "";
    ParseObject parseObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContex = RegisterActivity.this;
        initWidgets();
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        
    }

    private void initWidgets() {
        Log.d(TAG, "initWidgets: initialazing init WINGETS");
        mProgressBar = findViewById(R.id.registerReguestLoadingProgressBar);
        pleaceWait = findViewById(R.id.pleaceWaitTextView);
        mEmail = findViewById(R.id.inputEmail);
        mPassword = findViewById(R.id.inputPassword);
        mUsername = findViewById(R.id.inputUserName);
        mPhoneNumber = findViewById(R.id.inputPhoneNumber);
        btnRegister = findViewById(R.id.btnRegister);
        pleaceWait.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
    }

    private void init() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String username = mUsername.getText().toString();
                String numb = mPhoneNumber.getText().toString();
                Integer phonenumber;
                if (!numb.isEmpty()) {
                    phonenumber = Integer.parseInt(numb);
                } else {
                    phonenumber = 0;
                }
                if (checkInit(email, password, phonenumber, username)) {
                    pleaceWait.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.VISIBLE);
                    user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.put("number", phonenumber);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                addAllInfomationForUser();
                                pleaceWait.setVisibility(View.GONE);
                                mProgressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                                Toast.makeText(RegisterActivity.this, "Succesfull", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                                System.out.println("My message...................................." + e.getLocalizedMessage().toString());
                            }

                        }
                    });
                } else {

                    Toast.makeText(RegisterActivity.this, "Please Fill All Feald!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void addAllInfomationForUser() {
        UserAccountSettings userAccountSettings = new UserAccountSettings();
        String description=userAccountSettings.getDescription();
        long followers=userAccountSettings.getFollowers();
        long following=userAccountSettings.getFollowing();
        long posts=userAccountSettings.getPost();
        String website=userAccountSettings.getWeb();
        parseObject = new ParseObject("User_Informations");
        parseObject.put("user_id", user.getCurrentUser().getObjectId().toString());
        parseObject.put("username", user.getCurrentUser().getUsername().toString());
        parseObject.put("description", description != null ? description : "");
        parseObject.put("followers", followers);
        parseObject.put("following", following);
        parseObject.put("posts", posts);
        parseObject.put("website", website);
//        parseObject.put("profile_photo", profile_image);
//        parseObject.put("post_photos", postPhotos);
        parseObject.saveInBackground();

    }


    private boolean checkInit(String email, String password, Integer phonenumber, String fullname) {
        Log.d(TAG, "checkInit: checking input........................");
        if (email.isEmpty() || password.isEmpty() || phonenumber == 0 || fullname.isEmpty()) {
            Toast.makeText(mContex, "All fealds must be fill!", LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    private boolean checkStringNull(String string) {
        if (string.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    


}
