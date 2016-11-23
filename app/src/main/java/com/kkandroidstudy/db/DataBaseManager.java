package com.kkandroidstudy.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kkandroidstudy.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiyan on 2016/11/21.
 */

public class DataBaseManager {
    private MySqliteOpenHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        helper = new MySqliteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    //添加用户信息
    public void addUser(UserBean bean) {
        //开启事务
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO " + DataBaseEntry.UserEntry.TABLE_NAME + " VALUES(null,?,?,?)", new Object[]{
                    bean.getUserName(), bean.getUserPwd(), bean.getAge()
            });
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //添加用户信息
    public void addUser2(UserBean bean) {
        //开启事务
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME, bean.getUserName());
            values.put(DataBaseEntry.UserEntry.COLUMN_NAME_USERPWD, bean.getUserPwd());
            values.put(DataBaseEntry.UserEntry.COLUMN_NAME_AGE, bean.getAge());
            //如果指定 null，则框架不会在没有值时插入行
            db.insert(DataBaseEntry.UserEntry.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //添加多条用户信息
    public void addUsers(List<UserBean> beans) {
        db.beginTransaction();
        try {
            for (int i = 0; i < beans.size(); i++) {
                UserBean bean = beans.get(i);
                db.execSQL("INSERT INTO " + DataBaseEntry.UserEntry.TABLE_NAME + " VALUES(null,?,?,?)", new Object[]{
                        bean.getUserName(), bean.getUserPwd(), bean.getAge()
                });
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //添加多条用户信息
    public void addUsers2(List<UserBean> beans) {
        db.beginTransaction();
        try {
            for (int i = 0; i < beans.size(); i++) {
                UserBean bean = beans.get(i);
                ContentValues values = new ContentValues();
                values.put(DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME, bean.getUserName());
                values.put(DataBaseEntry.UserEntry.COLUMN_NAME_USERPWD, bean.getUserPwd());
                values.put(DataBaseEntry.UserEntry.COLUMN_NAME_AGE, bean.getAge());
                //如果指定 null，则框架不会在没有值时插入行
                db.insert(DataBaseEntry.UserEntry.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }


    //删除用户信息
    public void deleteUser(UserBean bean) {
        //开启事务
        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM " + DataBaseEntry.UserEntry.TABLE_NAME + " WHERE " + DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME + " = ?", new Object[]{bean.getUserName()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //删除用户信息
    public void deleteUser2(UserBean bean) {
        //开启事务
        db.beginTransaction();
        try {
            db.delete(DataBaseEntry.UserEntry.TABLE_NAME, DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME + " = ?", new String[]{bean.getUserName()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }


    //删除全部用户信息
    public void deleteAllUser() {
        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM " + DataBaseEntry.UserEntry.TABLE_NAME);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //删除全部用户信息
    public void deleteAllUser2() {
        db.beginTransaction();
        try {
            db.delete(DataBaseEntry.UserEntry.TABLE_NAME, null, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //修改单个用户信息
    public void updateUser(UserBean bean) {
        db.beginTransaction();
        try {
            db.execSQL("UPDATE " + DataBaseEntry.UserEntry.TABLE_NAME + " SET " + DataBaseEntry.UserEntry.COLUMN_NAME_USERPWD + " = ?, " +
                    DataBaseEntry.UserEntry.COLUMN_NAME_AGE + " = ? WHERE " + DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME + " = ?", new Object[]{bean.getUserPwd(), bean.getAge(), bean.getUserName()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //修改单个用户信息
    public void updateUser2(UserBean bean) {
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseEntry.UserEntry.COLUMN_NAME_USERPWD, bean.getUserPwd());
            values.put(DataBaseEntry.UserEntry.COLUMN_NAME_AGE, bean.getAge());
            db.update(DataBaseEntry.UserEntry.TABLE_NAME, values, DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME + " = ?", new String[]{bean.getUserName()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //修改多个用户信息
    public void updateUsers(List<UserBean> beans) {
        db.beginTransaction();
        try {
            for (int i = 0; i < beans.size(); i++) {
                UserBean bean = beans.get(i);

                db.execSQL("UPDATE " + DataBaseEntry.UserEntry.TABLE_NAME + " SET " + DataBaseEntry.UserEntry.COLUMN_NAME_USERPWD + " = ?, " +
                        DataBaseEntry.UserEntry.COLUMN_NAME_AGE + " = ? WHERE " + DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME + " = ?", new Object[]{bean.getUserPwd(), bean.getAge(), bean.getUserName()});
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //修改多个用户信息
    public void updateUsers2(List<UserBean> beans) {
        db.beginTransaction();
        try {
            for (int i = 0; i < beans.size(); i++) {
                UserBean bean = beans.get(i);
                ContentValues values = new ContentValues();
                values.put(DataBaseEntry.UserEntry.COLUMN_NAME_USERPWD, bean.getUserPwd());
                values.put(DataBaseEntry.UserEntry.COLUMN_NAME_AGE, bean.getAge());
                db.update(DataBaseEntry.UserEntry.TABLE_NAME, values, DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME + " = ?", new String[]{bean.getUserName()});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }


    //查询单个用户信息
    public UserBean queryUser(String name) {
        UserBean bean = new UserBean();
        db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("select * from " + DataBaseEntry.UserEntry.TABLE_NAME + " where " + DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME + " = ?", new String[]{name});
            if (cursor.moveToFirst()) {
                bean.setUserName(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME)));
                bean.setUserPwd(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseEntry.UserEntry.COLUMN_NAME_USERPWD)));
                bean.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseEntry.UserEntry.COLUMN_NAME_AGE)));
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return bean;
    }

    //查询单个用户信息
    public UserBean queryUser2(String name) {
        UserBean bean = new UserBean();
        db.beginTransaction();
        try {
            Cursor cursor = db.query(DataBaseEntry.UserEntry.TABLE_NAME, new String[]{DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME, DataBaseEntry.UserEntry.COLUMN_NAME_USERPWD, DataBaseEntry.UserEntry.COLUMN_NAME_AGE,}, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                bean.setUserName(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME)));
                bean.setUserPwd(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseEntry.UserEntry.COLUMN_NAME_USERPWD)));
                bean.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseEntry.UserEntry.COLUMN_NAME_AGE)));
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return bean;
    }

    //查询全部用户信息
    public List<UserBean> queryUsers() {
        List<UserBean> beans = new ArrayList<>();
        db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("select * from " + DataBaseEntry.UserEntry.TABLE_NAME, null);
            while (cursor.moveToNext()) {
                UserBean bean = new UserBean();
                bean.setUserName(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseEntry.UserEntry.COLUMN_NAME_USERNAME)));
                bean.setUserPwd(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseEntry.UserEntry.COLUMN_NAME_USERPWD)));
                bean.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseEntry.UserEntry.COLUMN_NAME_AGE)));
                beans.add(bean);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return beans;
    }
}
