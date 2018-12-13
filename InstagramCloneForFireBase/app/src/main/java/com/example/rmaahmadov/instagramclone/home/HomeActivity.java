package com.example.rmaahmadov.instagramclone.home;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rmaahmadov.instagramclone.R;
import com.example.rmaahmadov.instagramclone.login.LoginActivity;
import com.example.rmaahmadov.instagramclone.login.RegisterActivity;
import com.example.rmaahmadov.instagramclone.utils.BottomNavigationViewHelper;
import com.example.rmaahmadov.instagramclone.utils.SectionsPagerAdapter;
import com.example.rmaahmadov.instagramclone.utils.UniversalImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private ParseUser user;
    private Button btnmyLogin;
    private TextView myUsername;
    private TextView myPassword;
    private Context mcontex = HomeActivity.this;
    private static final int ACTIVIT_NUMBER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
//            ((ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE))
//                    .clearApplicationUserData();
//        } 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate starting...");
        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();
//        
        checkingParseUserLogin();
    }
    
    
    /*
     *     Bottom navigation setup
     * */
    private void setupBottomNavigationView() {
        Log.e(TAG, "sETTING UP BOTTOM NAGIVATION VIEW ");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mcontex, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVIT_NUMBER);
        menuItem.setChecked(true);
    }


    /*
     * Responsible for adding the 3 tabs: Camera , Home ,Messages
     * */


    private void checkingParseUserLogin(){
        if(user.getCurrentUser()==null){
            Intent intent = new Intent(mcontex,LoginActivity.class);
            startActivity(intent);
        }
    }
    
    
    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mcontex);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    

    private void setupViewPager() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.addFragment(new CameraFragment());//0
        sectionsPagerAdapter.addFragment(new HomeFragment());//1
        sectionsPagerAdapter.addFragment(new MessagesFragment());//2
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.layout_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_instagram);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_messages);
    }


    /*
     *
     * -----------------------------------Parse-----------------------------------------*/

    @Override
    protected void onStart() {
        super.onStart();
    }
}
