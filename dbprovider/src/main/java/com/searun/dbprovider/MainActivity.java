package com.searun.dbprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {
    // 以下解析的字符串是AndroidManifest.xml中<Provider>标签authorities属性的值
    private static final Uri URI_TEST = Uri.parse("content://com.searun.dbprovoider.MyContentProvider");
    // 声明成员变量
    ContentResolver mResolver;
    MyAdapter mAdapter;
    Button btnAdd, btnDelete, btnUpdate, btnQuery;
    private ListView listView;
    List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mResolver = getContentResolver();
//        Cursor cursor = mResolver.query(URI_TEST, null, null, null, null);
//        mAdapter = new SimpleCursorAdapter(this, R.layout.listview_item, cursor, new String[]{DbData.COLUMN_NAME, DbData.COLUMN_PHONE}, new int[]{R.id.tv_name, R.id.tv_phone},0);
//        listView.setAdapter(mAdapter);
        setMyAdapter();
    }

    private void setMyAdapter(){
        Cursor cursor = mResolver.query(URI_TEST, null, null, null, null);
//        if (null != cursor && cursor.moveToNext()){
//            do {
//                int id = cursor.getInt(cursor.getColumnIndex("id"));
//                String name = cursor.getString(cursor.getColumnIndex("name"));
//                String phone = cursor.getString(cursor.getColumnIndex("phone"));
//                Person person = new Person();
//                person.setId(id);
//                person.setName(name);
//                person.setPhone(phone);
//                personList.add(person);
//            } while (cursor.moveToNext());
//        }
        personList = DataSupport.findAll(Person.class);
        mAdapter = new MyAdapter(personList,this);
        listView.setAdapter(mAdapter);;
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
                mResolver.delete(URI_TEST,"id=?",new String[]{"1"});
                break;
            case R.id.btn_update:
                ContentValues values1 = new ContentValues();
                values1.put(DbData.COLUMN_NAME,"三藏法师");
                values1.put(DbData.COLUMN_PHONE,"01010101");
                mResolver.update(URI_TEST,values1,"id=?",new String[]{"2"});
                break;
            case R.id.btn_query:

                break;
        }
//        queryAll();
        setMyAdapter();
    }
    private void queryAll(){
        Cursor cursor = mResolver.query(URI_TEST,null,null,null,null);
//        mAdapter.changeCursor(cursor);
        mAdapter.notifyDataSetChanged();
    }
}
