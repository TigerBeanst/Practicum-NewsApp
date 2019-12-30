package com.jakting.news;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import com.jakting.news.adapter.CommentAdapter;
import com.jakting.news.utils.ApiGetComment;
import com.jakting.news.utils.ApiGetList;
import com.jakting.news.utils.ApiSendComment;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CommentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ApiGetComment apiGetComment;
    ApiSendComment apiSendComment;
    RefreshLayout refreshLayout;
    TextInputEditText textInputEditTextContent;
    SharedPreferences sp;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        Toolbar toolbar = findViewById(R.id.toolbar);

        sp = getSharedPreferences("data",MODE_PRIVATE);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout_Comment);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        //设置 Header 为 Delivery 样式
        refreshLayout.setRefreshHeader(new MaterialHeader(this));
        refreshLayout.setDragRate(0.8f); //阻尼为1
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.d("debug", "onRefresh: 进入刷新");
                apiGetComment = new ApiGetComment(url,myHandler);
            }
        });
        textInputEditTextContent = (TextInputEditText)findViewById(R.id.input_comment);
        button = (Button)findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshLayout.autoRefresh();
                apiSendComment = new ApiSendComment(url,myHandler,sp.getString("name","ERROR"),textInputEditTextContent.getText().toString());
            }
        });
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_comment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void loadingItem() {


        CommentAdapter adapter = new CommentAdapter(apiGetComment.commentArrayList,(Activity)this);
        recyclerView.setAdapter(adapter);
        refreshLayout.finishRefresh();
//        apiGetList.newsArrayList

    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 666:
                    Log.d(TAG, "handleMessage: 到了线程返回消息");
                    loadingItem();
                    break;
                case 777:
                    Log.d(TAG, "handleMessage: 到了线程返回消息");
                    Toast.makeText(CommentActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    loadingItem();
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
