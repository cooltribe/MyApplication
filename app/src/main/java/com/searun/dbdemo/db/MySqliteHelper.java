package com.searun.dbdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    public static final String CREATE_NEWS = "create table news("
            + "id integer primary key autoincrement,"
            + "title text,"
            + "publishdate integer,"
            + "commentcount integer)";
    public static final  String CREATE_COMMENT = "create table comment("
            + "id integer primary key autoincrement,"
            + "content text,"
            + "publishdate integer)";
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS);
        db.execSQL(CREATE_COMMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists news");
//        onCreate(db);
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_COMMENT);
                break;
            case 2:
                db.execSQL("alter table comment add column publistdate integer");
                break;
        }

    }
}
