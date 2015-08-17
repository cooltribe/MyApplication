package com.searun.dbdemo.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.searun.dbdemo.R;
import com.searun.dbdemo.db.News;

import org.litepal.tablemanager.Connector;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SQLiteOpenHelper dbHelper = new MySqliteHelper(this,"demo.db",null,3);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase db = Connector.getDatabase();
//        Comment comment1 = new Comment();
//        comment1.setContent("nice!");
//        comment1.setPublishDate(new Date());
//        comment1.save();
//        Comment comment2 = new Comment();
//        comment2.setContent("赞一个");
//        comment2.setPublishDate(new Date());
//        comment2.save();
//
//
//        News news = new News();
//        news.getCommentList().add(comment1);
//        news.getCommentList().add(comment2);
//        news.setTitle("第二条新闻标题");
//        news.setContent("第二条新闻内容");
//        news.setPublishDate(new Date());
//        news.setCommentCount(news.getCommentList().size());
//        Log.d("TAG1","id is " + news.getId());
//       if (news.save()){
//           Toast.makeText(this,"存储成功",Toast.LENGTH_LONG).show();
//       } else {
//           Toast.makeText(this,"存储失败",Toast.LENGTH_LONG).show();
//       }
//
//        Log.d("TAG2","id is " + news.getId());


//        ContentValues values = new ContentValues();
//        values.put("title", "iphone1");
//        DataSupport.updateAll(News.class,values);
        News updateNews = new News();
//        updateNews.setTitle("iphone发布");
//        updateNews.updateAll("id > ? and title = ?","1" , "iphone1");
//        updateNews.setToDefault("commentCount");
//        updateNews.updateAll();
        updateNews.setCommentCount(2);
        updateNews.update(3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
