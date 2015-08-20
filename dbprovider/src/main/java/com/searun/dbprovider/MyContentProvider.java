package com.searun.dbprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import org.litepal.tablemanager.Connector;

public class MyContentProvider extends ContentProvider {
    int count = 1;
    SQLiteDatabase db;
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        int id = db.delete(DbData.TAB_NAME,selection,selectionArgs);
        return id;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
//        throw new UnsupportedOperationException("Not yet implemented");
        long id = db.insert(DbData.TAB_NAME,null,values);
        return ContentUris.withAppendedId(uri,id);
    }
    private void insertData(){
        Person person = new Person();
        person.setName("孙悟空");
        person.setPhone("11111111111");
        person.save();
        Log.d("TAG","person id is" + person.getId());
        Person person1 = new Person();
        person1.setName("猪八戒");
        person1.setPhone("22222222222");
        person1.save();
        Log.d("TAG", "person1 id is" + person1.getId());
        Person person2 = new Person();
        person2.setName("唐僧");
        person2.setPhone("0000000000");
        person2.save();
        Log.d("TAG", "person2 id is" + person2.getId());
    }
    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        db = Connector.getDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
//        throw new UnsupportedOperationException("Not yet implemented");
        if (1==count){
            insertData();
            count ++;
        } else {
            Cursor cursor = db.query(DbData.TAB_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            return cursor;
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        int id = db.update(DbData.TAB_NAME,values,selection,selectionArgs);
        return id;
    }
}
