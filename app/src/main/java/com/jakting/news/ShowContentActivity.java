package com.jakting.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jakting.news.utils.ApiGetContent;
import com.jakting.news.utils.ApiPostLike;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ShowContentActivity extends AppCompatActivity {

    private LevelListDrawable mDrawable = new LevelListDrawable();
    TextView content;
    TextView title;
    TextView ptime;
    TextView like_count;
    TextView comment_count;
    FloatingActionButton fab_comment;
    FloatingActionButton fab_like;
    Toolbar toolbar;
    ApiGetContent apiGetContent;
    ApiPostLike apiPostLike;
    String source;
    String imgsrc;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);
        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        source = intent.getStringExtra("source");
        imgsrc = intent.getStringExtra("imgsrc");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = (ImageView) findViewById(R.id.content_img);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fab_comment = (FloatingActionButton) findViewById(R.id.fab_comment);
        fab_like = (FloatingActionButton) findViewById(R.id.fab_like);
        fab_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowContentActivity.this, CommentActivity.class);
                intent.putExtra("url", url);
                ShowContentActivity.this.startActivity(intent);
            }
        });
        fab_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiPostLike = new ApiPostLike(url, ShowContentActivity.this,handler);
            }
        });

        content = (TextView) findViewById(R.id.content_text);
        title = (TextView) findViewById(R.id.text_title);
        ptime = (TextView) findViewById(R.id.text_time);
        like_count = (TextView) findViewById(R.id.text_like);
        comment_count = (TextView) findViewById(R.id.text_comment);
        //toolbar.setTitle("aaa");
        apiGetContent = new ApiGetContent("https://api.jakting.com/v1/news/netease/?content=" + url, handler);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toolbar.setTitle(source);
    }

    int like_num;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 23333:
                    //Log.d(TAG, "修改后：" + apiGetContent.newsContent.getContent());
                    HtmlImageGetter htmlImageGetter = new HtmlImageGetter();
                    Spanned spanned = Html.fromHtml(apiGetContent.newsContent.getContent(), htmlImageGetter, null);
                    //mtvActNewsContent：这个textView是我测试项目用到的textView
                    content.setText(spanned);
                    title.setText(apiGetContent.newsContent.getTitle());
                    ptime.setText(apiGetContent.newsContent.getPtime());
                    like_num = Integer.parseInt(apiGetContent.newsContent.getLikecount());
                    like_count.setText("点赞(" + like_num + ")");
                    comment_count.setText("评论(" + apiGetContent.newsContent.getCommentcount() + ")");
                    Glide.with(ShowContentActivity.this).load(imgsrc).centerCrop().into(imageView);
                    //Glide.with(newsView).load(news.getImgsrc()).centerCrop().into(holder.newsImgsrc);
                    break;
                case 233333:
                    like_num++;
                    like_count.setText("点赞(" + like_num + ")");
                    Toast.makeText(ShowContentActivity.this, "点赞成功", Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    class HtmlImageGetter implements Html.ImageGetter {

        /**
         * 获取图片
         */
        @Override
        public Drawable getDrawable(String source) {
            LevelListDrawable d = new LevelListDrawable();
            Drawable empty = getResources().getDrawable(
                    R.drawable.loading);
            d.addLevel(0, 0, empty);
            WindowManager wm = (WindowManager) ShowContentActivity.this.getSystemService(Context.WINDOW_SERVICE);
            d.setBounds(0, 0, wm.getDefaultDisplay().getWidth(),
                    empty.getIntrinsicHeight());
            new LoadImage().execute(source, d);
            return d;
        }

        /**
         * 异步下载图片类
         *
         * @author Ruffian
         * @date 2016年1月15日
         */
        class LoadImage extends AsyncTask<Object, Void, Bitmap> {

            private LevelListDrawable mDrawable;

            @Override
            protected Bitmap doInBackground(Object... params) {
                String source = (String) params[0];
                mDrawable = (LevelListDrawable) params[1];
                try {
                    InputStream is = new URL(source).openStream();
                    return BitmapFactory.decodeStream(is);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            /**
             * 图片下载完成后执行
             */
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    BitmapDrawable d = new BitmapDrawable(bitmap);
                    mDrawable.addLevel(1, 1, d);
                    /**
                     * 适配图片大小 <br/>
                     * 默认大小：bitmap.getWidth(), bitmap.getHeight()<br/>
                     * 适配屏幕：getDrawableAdapter
                     */
                    mDrawable = getDrawableAdapter(ShowContentActivity.this, mDrawable,
                            bitmap.getWidth(), bitmap.getHeight());

                    // mDrawable.setBounds(0, 0, bitmap.getWidth(),
                    // bitmap.getHeight());

                    mDrawable.setLevel(1);

                    /**
                     * 图片下载完成之后重新赋值textView<br/>
                     * mtvActNewsContent:我项目中使用的textView
                     *
                     */
                    content.invalidate();
                    CharSequence t = content.getText();
                    content.setText(t);

                }
            }

            /**
             * 加载网络图片,适配大小
             *
             * @param context
             * @param drawable
             * @param oldWidth
             * @param oldHeight
             * @return
             * @author Ruffian
             * @date 2016年1月15日
             */
            public LevelListDrawable getDrawableAdapter(Context context,
                                                        LevelListDrawable drawable, int oldWidth, int oldHeight) {
                LevelListDrawable newDrawable = drawable;
                long newHeight = 0;// 未知数
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                int newWidth = wm.getDefaultDisplay().getWidth();// 默认屏幕宽
                newHeight = (newWidth * oldHeight) / oldWidth;
                // LogUtils.w("oldWidth:" + oldWidth + "oldHeight:" +
                // oldHeight);
                // LogUtils.w("newHeight:" + newHeight + "newWidth:" +
                // newWidth);
                newDrawable.setBounds(0, 0, newWidth, (int) newHeight);
                return newDrawable;
            }
        }

    }

}
