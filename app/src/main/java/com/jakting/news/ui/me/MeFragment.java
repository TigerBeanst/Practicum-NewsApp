package com.jakting.news.ui.me;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.jakting.news.ChangeInfoActivity;
import com.jakting.news.LoginActivity;
import com.jakting.news.R;
import com.jakting.news.utils.ApiPostLogin;
import com.jakting.news.utils.MD5Encrypt;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MeFragment extends Fragment implements View.OnClickListener {

    private MeViewModel meViewModel;
    TextView me_name;
    TextView me_id;
    TextView me_username;
    TextView like_count;
    CardView card_change_info;
    RefreshLayout refreshLayout;
    ApiPostLogin apiPostLogin;
    SharedPreferences sp;
    int page = 0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        refreshLayout = (RefreshLayout) getActivity().findViewById(R.id.refreshLayout_Me);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        me_name = (TextView) getActivity().findViewById(R.id.me_name);
        me_id = (TextView) getActivity().findViewById(R.id.me_id);
        me_username = (TextView) getActivity().findViewById(R.id.me_username);
        like_count = (TextView)getActivity().findViewById(R.id.tv_like_count);
        card_change_info = (CardView) getActivity().findViewById(R.id.card_change_info);
        card_change_info.setClickable(true);
        card_change_info.setOnClickListener(this);
        sp = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
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
                apiPostLogin = new ApiPostLogin(sp.getString("username", ""), sp.getString("password", ""), getActivity(), myHandler);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_change_info:
                Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        meViewModel =
                ViewModelProviders.of(this).get(MeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_me, container, false);

        //loadingItem();
        return root;
    }

    public void loadingItem() {
        me_name.setText(sp.getString("name", "ERROR"));
        me_id.setText("(id:" + sp.getInt("id", -1) + ")");
        me_username.setText(sp.getString("username", "ERROR"));
        like_count.setText(String.valueOf(sp.getInt("like_count",-1)));
        refreshLayout.finishRefresh();
//        apiGetList.newsArrayList

    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 555:
                    Log.d(TAG, "handleMessage: 到了线程返回消息");
                    loadingItem();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}