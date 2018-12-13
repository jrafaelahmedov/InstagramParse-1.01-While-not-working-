package com.example.rmaahmadov.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("22c82f1c8c1994185a56398fc4de505a17ad6771")
                // if desired
                .clientKey("c866e67d9c91b5a4d75a46b1e1246a08920baafb")
                .server("http://18.219.149.56:80/parse")
                .build()
        );
    }
}
