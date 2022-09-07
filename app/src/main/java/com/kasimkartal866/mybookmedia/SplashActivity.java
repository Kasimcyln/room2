package com.kasimkartal866.mybookmedia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (MyPref.getUserName().length() == 0) {
            Thread thread = new Thread() {
                @Override
                public void run() {

                    try {

                        sleep(3000);
                    } catch (Exception e) {

                        e.printStackTrace();
                    } finally {

                        startActivity(new Intent(SplashActivity.this, MainPageActivity.class));
                        finish();
                    }

                }
            };
            thread.start();
        }

        else {
            threadMethods();

        }

    }

    public static class SaveSharedPreference {
        static final String PREF_USER_NAME = "username";

        static SharedPreferences getSharedPreferences(Context ctx) {
            return PreferenceManager.getDefaultSharedPreferences(ctx);
        }

        public static void setUserName(Context ctx, String userName) {
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putString(PREF_USER_NAME, userName);
            editor.commit();
        }

        public static void clearUserName(Context ctx) {
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.clear();
            editor.commit();
        }


        public static String getUserName(Context ctx) {
            return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
        }


    }


    public void threadMethods() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {

                    sleep(3000);
                } catch (Exception e) {

                    e.printStackTrace();
                } finally {

                    startActivity(new Intent(SplashActivity.this, LoginOrRegisterActivity.class));
                    finish();
                }

            }
        };
        thread.start();

    }
}
