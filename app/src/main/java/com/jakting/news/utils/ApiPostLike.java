package com.jakting.news.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jakting.news.adapter.NewsContent;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class ApiPostLike {

    String json;
    String result;
    SharedPreferences sp;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    public ApiPostLike(String url, final Context context,final Handler myHandler) {
        sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        json = "{\"url\":\"" + url + "\",\"method\":\"post\",\"id\":"+sp.getInt("id",-1)+"}";
        Log.d(TAG, "ApiPostLike: "+json);
        String urll = "https://api.jakting.com/v1/news/netease/conn/like.php";
        RequestBody body = RequestBody.create(json, JSON);
        Log.d(TAG, "ApiPostLike: 到了okhttp");
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


                Message msg = Message.obtain();
                msg.what = 233333;
                myHandler.sendMessage(msg);
            }
        });
    }
}
