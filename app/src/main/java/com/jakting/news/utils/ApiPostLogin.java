package com.jakting.news.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class ApiPostLogin {

    String json;
    String result;
    public int end;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    public ApiPostLogin(final String username, final String password, final Context context,final Handler myHandler) {
        json = "{\"username\":\"" + username + "\",\"password\":\""+password+"\"}";
        Log.d(TAG, "ApiPostLogin: "+json);
        String urll = "https://api.jakting.com/v1/news/netease/conn/login.php";
        RequestBody body = RequestBody.create(json, JSON);
        Log.d(TAG, "ApiPostLogin: 到了okhttp");
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(urll)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: 失败了嗷");
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: 成功了嗷");
                result = response.body().string();
                JsonObject obj = new JsonParser().parse(result).getAsJsonObject();

                String status = obj.get("status").getAsString();
                if(status.equals("1")){
                    Log.d(TAG, "onResponse: 登录校验通过");
                    SharedPreferences.Editor sp = context.getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                    sp.putInt("id",obj.get("id").getAsInt());
                    sp.putString("username",username);
                    sp.putString("password",password);
                    sp.putString("name",obj.get("name").getAsString());
                    sp.putInt("like_count",Integer.parseInt(obj.get("like_count").getAsString()));
                    sp.apply();
                    end = 1;
                }else if(status.equals("-2")){
                    Log.d(TAG, "onResponse: 密码错误");
                    end = -2;
                }else if(status.equals("-1")){
                    Log.d(TAG, "onResponse: 用户不存在");
                    end = -1;
                }

                Message msg = Message.obtain();
                msg.what = 555;
                myHandler.sendMessage(msg);
            }
        });
    }
}
