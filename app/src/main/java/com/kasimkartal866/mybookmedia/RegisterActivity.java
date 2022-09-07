package com.kasimkartal866.mybookmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText etMail;
    private EditText etPhone;
    private EditText etPassword;
    private EditText etPassword2;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewByIdMethods();
        onClickMethods();



    }

    public void findViewByIdMethods() {
        etMail = findViewById(R.id.etMail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etPassword2 = findViewById(R.id.etPassword2);
        btnSave = findViewById(R.id.btnSave);

    }

    public void onClickMethods () {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean state = false;
                if (TextUtils.isEmpty(etMail.getText().toString())) {
                    etMail.setError("enter e-mail");
                    state = true;
                }
                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    etPhone.setError("enter phone");
                    state = true;
                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    etPassword.setError("enter password");
                    state = true;
                }
                if (TextUtils.isEmpty(etPassword2.getText().toString())) {
                    etPassword2.setError("try enter password");
                    state = true;
                } else if (!etPassword2.getText().toString().contentEquals(etPassword.getText().toString())) {
                    etPassword2.setError("passwords are incompatible");
                    state = true;
                }
                if (state){
                    return;
                }

                User user = new User();
                user.setEmail(etMail.getText().toString());
                user.setPhone(etPhone.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.setPassword2(etPassword2.getText().toString());
                if(validateInput(user)) {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.addUser(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).start();

                }else {
                    Toast.makeText(RegisterActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }


                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


    }

    private Boolean validateInput (User user) {
        if(user.getEmail().isEmpty() || user.getPassword().isEmpty()||user.getPhone().isEmpty() || user.getPassword2().isEmpty()) {
            return false;
        }
        return true;
    }



}