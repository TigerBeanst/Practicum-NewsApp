package com.jakting.news.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jakting.news.adapter.News;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ApiGetList {

    public ArrayList<News> newsArrayList;

    public ApiGetList(String url, final Handler myHandler) {
        Log.d(TAG, "ApiGetList: 到了okhttp");
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
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
                final String data = response.body().string();
                Log.d("debug", "onResponse: 获得数据"+data);
                JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
                JsonArray jsonArray = obj.getAsJsonArray("list");
                Gson gson = new Gson();
                newsArrayList = new ArrayList<>();
                //循环遍历
                for (JsonElement user : jsonArray) {
                    //通过反射 得到UserBean.class
                    News news = gson.fromJson(user, new TypeToken<News>() {
                    }.getType());
                    newsArrayList.add(news);
                }

                Message msg = Message.obtain();
                msg.what = 233;
                myHandler.sendMessage(msg);
            }
        });
    }
}
