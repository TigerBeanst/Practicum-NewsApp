package com.jakting.news;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.jakting.news.utils.ApiPostChange;
import com.jakting.news.utils.MD5Encrypt;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ChangeInfoActivity extends AppCompatActivity {

    TextInputEditText username2;
    TextInputEditText name2;
    TextInputEditText password2;
    TextInputEditText password22;
    Button button_save;
    SharedPreferences sp;
    ApiPostChange apiPostChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        sp  = getSharedPreferences("data",MODE_PRIVATE);
        initData();
    }

    public void initData(){
        username2 = (TextInputEditText)findViewById(R.id.input_username2);
        name2 = (TextInputEditText)findViewById(R.id.input_name2);
        password2 = (TextInputEditText)findViewById(R.id.input_password2);
        password22 = (TextInputEditText)findViewById(R.id.input_password22);
        button_save = (Button)findViewById(R.id.button_save);
        username2.setText(sp.getString("username",""));
        username2.setKeyListener(null);
        name2.setText(sp.getString("name",""));
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password2.getText().toString().equals(password22.getText().toString())){
                    //两次密码相等
                    String md55 = "";
                    if(!password2.getText().toString().equals("")){
                        try {
                            md55 = MD5Encrypt.getMD5Str(password2.getText().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    apiPostChange = new ApiPostChange(sp.getInt("id",-1),name2.getText().toString(),md55,ChangeInfoActivity.this, myHandler);
                }else {
                    Toast.makeText(ChangeInfoActivity.this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 999:
                    Log.d(TAG, "handleMessage: 到了线程返回消息");
                    Toast.makeText(ChangeInfoActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
