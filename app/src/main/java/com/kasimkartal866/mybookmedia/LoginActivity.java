package com.kasimkartal866.mybookmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button btnLogin;
    UserDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dao = App.dao;

        findViewByIdMethods();
        onClickMethods();
    }

    public void findViewByIdMethods () {
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btnLogin);

    }

    public void onClickMethods () {
        btnLogin.setOnClickListener(v -> {

            Boolean state = false;
            if (TextUtils.isEmpty(email.getText().toString())) {
                email.setError("enter e-mail");
                state = true;
            }
            if (TextUtils.isEmpty(pass.getText().toString())) {
                pass.setError("enter password");
                state = true;
            }

            String emailText = email.getText().toString();
            String passwordText = pass.getText().toString();
            if(emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Fill all Fields!", Toast.LENGTH_SHORT).show();
            }else {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        User user = dao.login(emailText, passwordText);
                        if(user == null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "Geçersiz Giriş Bilgileri", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            String name = user.email;
                            startActivity(new Intent(LoginActivity.this,MainPageActivity.class).putExtra("name",name));
                            finish();
                        }

                    }
                }).start();
            }


        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this,LoginOrRegisterActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}