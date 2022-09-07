package com.kasimkartal866.mybookmedia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.PrivateKey;

public class LoginOrRegisterActivity extends AppCompatActivity {

    private Button btnLogin,btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);

        findViewByIdMethods();
        onClickMethods();

    }
    public void findViewByIdMethods () {

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    public void onClickMethods () {

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(LoginOrRegisterActivity.this,LoginActivity.class);
               startActivity(intent);
           }
       });

       btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(LoginOrRegisterActivity.this,RegisterActivity.class);
               startActivity(intent);
           }
       });

   }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }
}