package com.kasimkartal866.mybookmedia;

public class MyPref {
    private static final String PREF_USER_NAME = "username";

    public void saveUserName(String userName) {
        App.getPrefEditor().putString(PREF_USER_NAME, userName).apply();
    }

    public static String getUserName() {
        return App.getPref().getString(PREF_USER_NAME, "");
    }
}
