package com.jakting.news.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jakting.news.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> commentList;
    static View newsView;
    static Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView commentAuthor;
        TextView commentContent;


        public ViewHolder(View view) {
            super(view);
            newsView = view;
            commentAuthor = (TextView) view.findViewById(R.id.item_author);
            commentContent = (TextView) view.findViewById(R.id.item_cconent);
        }
    }

    public CommentAdapter(List<Comment> ccommentList, Activity getAc) {
        commentList = ccommentList;
        activity = getAc;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.commentAuthor.setText(comment.getAuthor());
        holder.commentContent.setText(comment.getComment_content());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

}