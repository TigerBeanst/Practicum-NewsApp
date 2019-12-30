package com.jakting.news.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakting.news.R;
import com.jakting.news.ShowContentActivity;
import com.jakting.news.utils.ApiGetContent;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> mNewsList;
    static View newsView;
    ApiGetContent apiGetContent;
    static Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView newstitle;
        TextView newsDigest;
        TextView newsPTime;
        TextView newsURL;
        TextView newsSource;
        ImageView newsImgsrc;
        CardView news_list_item;


        public ViewHolder(View view) {
            super(view);
            newsView = view;
            newstitle = (TextView) view.findViewById(R.id.item_title);
            newsDigest = (TextView) view.findViewById(R.id.item_digest);
            newsPTime = (TextView) view.findViewById(R.id.item_ptime);
            newsSource = (TextView) view.findViewById(R.id.item_source);
            newsURL = (TextView) view.findViewById(R.id.item_url);
            newsImgsrc = (ImageView) view.findViewById(R.id.item_img);
            news_list_item = (CardView) view.findViewById(R.id.news_list_item);
        }
    }

    public NewsAdapter(List<News> newsList, Activity getAc) {
        mNewsList = newsList;
        activity = getAc;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.news_list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                News news = mNewsList.get(position);
                String url = news.getUrl();
                String title = news.getTitle();
                Intent intent = new Intent(activity, ShowContentActivity.class);
                intent.putExtra("url", news.getUrl());
                intent.putExtra("source", news.getSource());
                intent.putExtra("imgsrc", news.getImgsrc());
                activity.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.newstitle.setText(news.getTitle());
        holder.newsDigest.setText(news.getDigest() + "...");
        holder.newsPTime.setText("发布时间：" + news.getPtime());
        holder.newsSource.setText("来源：" + news.getSource());
        holder.newsURL.setText(news.getUrl());
        Glide.with(newsView).load(news.getImgsrc()).centerCrop().into(holder.newsImgsrc);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

}