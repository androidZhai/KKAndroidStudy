package com.kkandroidstudy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by shiyan on 2016/11/21.
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper {
    //数据库名字
    private static final String DATABASE_NAME = "kkandroidstudy.db";
    //数据库版本
    private static final int DATA_VERSION = 2;

    public MySqliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL(DataBaseEntry.UserEntry.SQL_CREATE_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //删除表
        db.execSQL(DataBaseEntry.UserEntry.SQL_DELETE_ENTRY);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
    }
}
