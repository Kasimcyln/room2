package com.kasimkartal866.mybookmedia;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {
    private static UserDatabase userDatabase;
    public static UserDao dao;
    private static SharedPreferences pref;
    private static SharedPreferences.Editor prefEditor;
    @Override
    public void onCreate() {
        super.onCreate();
        userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        dao = userDatabase.userDao();

        pref = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        prefEditor = pref.edit();
    }

    public static SharedPreferences getPref() {
        return pref;
    }

    public static SharedPreferences.Editor getPrefEditor() {
        return prefEditor;
    }
}
