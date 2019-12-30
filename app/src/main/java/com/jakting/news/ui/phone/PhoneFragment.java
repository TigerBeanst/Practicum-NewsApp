package com.jakting.news.ui.phone;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakting.news.R;
import com.jakting.news.adapter.NewsAdapter;
import com.jakting.news.ui.finance.FinanceViewModel;
import com.jakting.news.utils.ApiGetList;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PhoneFragment extends Fragment {

    private PhoneViewModel phoneViewModel;
    RecyclerView recyclerView;
    RefreshLayout refreshLayout;
    ApiGetList apiGetList;
    int page = 0;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_phone);

        refreshLayout = (RefreshLayout) getActivity().findViewById(R.id.refreshLayout_Phone);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        //设置 Header 为 Delivery 样式
        refreshLayout.setRefreshHeader(new DeliveryHeader(getActivity()));
        refreshLayout.setDragRate(0.8f); //阻尼为1
        refreshLayout.setFooterTriggerRate(2f);
        refreshLayout.autoRefresh();
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.d("debug", "onRefresh: 进入刷新");
                apiGetList = new ApiGetList("https://api.jakting.com/v1/news/netease/?num=0&type=phone", myHandler);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                Log.d("debug", "onLoadMore: 进入下拉刷新");
                ++page;
                apiGetList = new ApiGetList("https://api.jakting.com/v1/news/netease/?num="+page+"&type=phone", myHandler);

            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        phoneViewModel =
                ViewModelProviders.of(this).get(PhoneViewModel.class);
        View root = inflater.inflate(R.layout.fragment_phone, container, false);

        //loadingItem();
        return root;
    }

    public void loadingItem() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(apiGetList.newsArrayList,(Activity)getActivity());
        recyclerView.setAdapter(adapter);
        refreshLayout.finishRefresh();
        recyclerView.smoothScrollToPosition(0);
//        apiGetList.newsArrayList

    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 233:
                    Log.d(TAG, "handleMessage: 到了线程返回消息");
                    loadingItem();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}