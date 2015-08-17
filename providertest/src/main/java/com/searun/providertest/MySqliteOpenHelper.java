package com.searun.providertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 陈玉柱 on 2015/8/11.
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {
    // 创建库名：provider.db的数据库
    public MySqliteOpenHelper(Context context) {
        super(context, "provider.db", null, 1);
    }

    // 创建t_person表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS  t_person");
        db.execSQL("CREATE TABLE IF NOT EXISTS  t_person "
                + "(_id integer primary key autoincrement,"
                + " name varchar(20), " + "phone varchar(12))");
    }
    // 数据库版本更新时用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    // 增加3条记录
    public void insert() {
        ContentValues values = new ContentValues();
        values.put(Persons.COLUMN_NAME, "悟空");
        values.put(Persons.COLUMN_PHONE, "13554391999");
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Persons.TABLE_NAME, null, values);
        values.put(Persons.COLUMN_PHONE, "13554391888");
        db.execSQL("insert into t_person(name,phone) values ('唐僧','1383838338')");
        db.execSQL("insert into t_person(name,phone) values ('八戒','8787887')");
    }
}
