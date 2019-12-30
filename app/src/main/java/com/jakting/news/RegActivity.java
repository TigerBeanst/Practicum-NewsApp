package com.jakting.news;

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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.jakting.news.utils.ApiPostLogin;
import com.jakting.news.utils.ApiPostReg;
import com.jakting.news.utils.MD5Encrypt;

public class RegActivity extends AppCompatActivity {

    TextInputEditText edit_username;
    TextInputEditText edit_name;
    TextInputEditText edit_password;
    TextInputEditText edit_password2;
    Button loginButton;
    Button regButton;
    ApiPostReg apiPostReg;
    SharedPreferences.Editor spe;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        spe = getSharedPreferences("data",MODE_PRIVATE).edit();
        checkLogin();
        edit_username = (TextInputEditText) findViewById(R.id.input_username2);
        edit_name = (TextInputEditText) findViewById(R.id.input_name);
        edit_password = (TextInputEditText) findViewById(R.id.input_password2);
        edit_password2 = (TextInputEditText) findViewById(R.id.input_password22);
        regButton = (Button) findViewById(R.id.button_reg2);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_username.getText().toString().equals("")||edit_name.getText().toString().equals("")||edit_password.getText().toString().equals("")||edit_password2.getText().toString().equals("")){
                    Toast.makeText(RegActivity.this, "请勿留空！", Toast.LENGTH_SHORT).show();
                }else if(!edit_password.getText().toString().equals(edit_password2.getText().toString())){
                    Toast.makeText(RegActivity.this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
                }else{
                    String passwordMD5="";
                    try {
                        passwordMD5 = MD5Encrypt.getMD5Str(edit_password.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    apiPostReg = new ApiPostReg(edit_username.getText().toString(),passwordMD5,edit_name.getText().toString(),RegActivity.this,myHandler);
                }

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
        } else {
            Log.d("debug", "checkLogin: 未登录");
        }
    }

    int end;
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 556:
                    end = apiPostReg.end;
                    if(end==1){
                        Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                        spe.putBoolean("checkLogin",true);
                        spe.apply();
                        Intent intent = new Intent(RegActivity.this, MainActivity.class);
                        startActivity(intent);
                        RegActivity.this.finish();
                    }else if(end==-1){
                        Toast.makeText(RegActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
