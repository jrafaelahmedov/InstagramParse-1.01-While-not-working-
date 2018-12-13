package com.example.rmaahmadov.instagramclone.profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rmaahmadov.instagramclone.R;
import com.example.rmaahmadov.instagramclone.login.LoginActivity;
import com.parse.ParseUser;

public class SignOutFragment extends Fragment {
    private static final String TAG = "EditProfileFragment";
    private ProgressBar mProgressBar;
    private TextView mTextViewSignUp,mTextViewSignUpProgressBar;
    private ParseUser user;
    private Button signOut;
    

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signout,container,false);
        mTextViewSignUp=view.findViewById(R.id.signUpTextView);
        mProgressBar=view.findViewById(R.id.progressBarSignOut);
        mTextViewSignUpProgressBar=view.findViewById(R.id.textViewSignUpProgressBar);
        signOut=view.findViewById(R.id.btnSignOut);
        
        mProgressBar.setVisibility(View.GONE);
        mTextViewSignUpProgressBar.setVisibility(View.GONE);
        
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mTextViewSignUpProgressBar.setVisibility(View.VISIBLE);
                user.logOut();
                getActivity().finish();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        
        
        return view;
    }
}
