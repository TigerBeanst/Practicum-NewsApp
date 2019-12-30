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
import com.jakting.news.adapter.Comment;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ApiGetComment {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public ArrayList<Comment> commentArrayList;

    public ApiGetComment(String url, final Handler myHandler) {
        Log.d(TAG, "ApiGetComment: 到了okhttp");
        String json = "{\"url\":\"" + url + "\",\"method\":\"get\"}";
        OkHttpClient okHttpClient = new OkHttpClient();
        String urll = "https://api.jakting.com/v1/news/netease/conn/comment.php";
        RequestBody body = RequestBody.create(json, JSON);
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
                String data = response.body().string();
                if (data.equals("-1")) {
                    data = "{\"commentlist\":[{\"id\": \"1\",\"author\": \"暂无评论\",\"comment_content\": \"暂无评论\"}]}";
                    Log.d("debug", "onResponse: 假数据" + data);
                } else {
                    data = "{\"commentlist\":" + data + "}";
                    Log.d("debug", "onResponse: 获得数据" + data);
                }
                JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
                JsonArray jsonArray = obj.getAsJsonArray("commentlist");
                Gson gson = new Gson();
                commentArrayList = new ArrayList<>();
                //循环遍历
                for (JsonElement user : jsonArray) {
                    //通过反射 得到UserBean.class
                    Comment comment = gson.fromJson(user, new TypeToken<Comment>() {
                    }.getType());
                    commentArrayList.add(comment);
                }

                Message msg = Message.obtain();
                msg.what = 666;
                myHandler.sendMessage(msg);


            }
        });
    }
}
