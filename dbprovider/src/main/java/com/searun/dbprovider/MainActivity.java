package com.searun.dbprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends Activity implements View.OnClickListener {
    // 以下解析的字符串是AndroidManifest.xml中<Provider>标签authorities属性的值
    private static final Uri URI_TEST = Uri.parse("content://com.searun.dbprovoider.MyContentProvider");
    // 声明成员变量
    ContentResolver mResolver;
    SimpleCursorAdapter mAdapter;
    Button btnAdd, btnDelete, btnUpdate, btnQuery;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mResolver = getContentResolver();
        Cursor cursor = mResolver.query(URI_TEST, null, null, null, null);
        mAdapter = new SimpleCursorAdapter(this, R.layout.listview_item, cursor, new String[]{DbData.COLUMN_NAME, DbData.COLUMN_PHONE}, new int[]{R.id.tv_name, R.id.tv_phone});
        listView.setAdapter(mAdapter);
    }


    private void initView() {
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnQuery = (Button) findViewById(R.id.btn_query);
        listView = (ListView) findViewById(R.id.lv_provider);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                ContentValues values = new ContentValues();
                values.put(DbData.COLUMN_NAME,"沙和尚");
                values.put(DbData.COLUMN_PHONE,"33333333");
                mResolver.insert(URI_TEST,values);
                break;
            case R.id.btn_delete:
                mResolver.delete(URI_TEST,"_id=?",new String[]{"1"});
                break;
            case R.id.btn_update:
                ContentValues values1 = new ContentValues();
                values1.put(DbData.COLUMN_NAME,"三藏法师");
                values1.put(DbData.COLUMN_PHONE,"01010101");
                mResolver.update(URI_TEST,values1,"_id=?",new String[]{"2"});
                break;
            case R.id.btn_query:
                break;
        }
        queryAll();
    }
    private void queryAll(){
        Cursor cursor = mResolver.query(URI_TEST,null,null,null,null);
        mAdapter.changeCursor(cursor);
        mAdapter.notifyDataSetChanged();
    }
}
