package com.searun.providertest;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    MySqliteOpenHelper mSQLiteHelper;
    int count = 1;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
        int id = db.delete(Persons.TABLE_NAME, selection, selectionArgs);
        return id;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
        // 返回的是插入那条数据的id
        long id = db.insert(Persons.TABLE_NAME, null, values);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public boolean onCreate() {
        // 创建MySQLiteOpenHelper对象
        mSQLiteHelper = new MySqliteOpenHelper(getContext());
        return false; // ?
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = null;
        try {
            if (count == 1) { //用来控制初始化的数据只往数据库插一次
                db = mSQLiteHelper.getWritableDatabase();
                mSQLiteHelper.insert();
                count++;
            } else {
                db = mSQLiteHelper.getReadableDatabase();
                Cursor cursor = db.query(Persons.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                return cursor;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
        int id = db
                .update(Persons.TABLE_NAME, values, selection, selectionArgs);
        return id;
    }
}
