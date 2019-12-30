package com.jakting.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.jakting.news.utils.ApiPostLogin;
import com.jakting.news.utils.MD5Encrypt;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edit_username;
    TextInputEditText edit_password;
    Button loginButton;
    Button regButton;
    ApiPostLogin apiPostLogin;
    SharedPreferences.Editor spe;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spe = getSharedPreferences("data",MODE_PRIVATE).edit();
        checkLogin();
        edit_username = (TextInputEditText) findViewById(R.id.input_username);
        edit_password = (TextInputEditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.button_login);
        regButton = (Button) findViewById(R.id.button_reg);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passwordMD5="";
                try {
                    passwordMD5 = MD5Encrypt.getMD5Str(edit_password.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                apiPostLogin = new ApiPostLogin(edit_username.getText().toString(), passwordMD5,LoginActivity.this,myHandler);
            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }

    private void checkLogin() {
        sp = getSharedPreferences("data", Context.MODE_PRIVATE);
        if ((sp.getBoolean("checkLogin", false))) {
            //已登录
            Log.d("debug", "checkLogin: 已登录");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        } else {
            Log.d("debug", "checkLogin: 未登录");
        }
    }

    int end;
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 555:
                    end = apiPostLogin.end;
                    if(end==1){
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                        spe.putBoolean("checkLogin",true);
                        spe.apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }else if(end==-2){
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }else if(end==-1){
                        Toast.makeText(LoginActivity.this, "用户不存在，请注册", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
