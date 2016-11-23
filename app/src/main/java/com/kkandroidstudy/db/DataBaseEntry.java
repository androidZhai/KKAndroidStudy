package com.kkandroidstudy.db;

import android.provider.BaseColumns;

/**
 * Created by shiyan on 2016/11/21.
 */

public class DataBaseEntry {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    /**
     * 私有化构造函数
     */
    private DataBaseEntry() {
    }
    ;


    //用户表
    public static class UserEntry implements BaseColumns {
        //表名
        public static final String TABLE_NAME = "User";
        //列名(姓名)
        public static final String COLUMN_NAME_USERNAME = "userName";
        //列名(密码)
        public static final String COLUMN_NAME_USERPWD = "userPwd";
        //列名(年龄)
        public static final String COLUMN_NAME_AGE = "age";

        //创建表
        public static final String SQL_CREATE_ENTRY = "CREATE TABLE " +
                UserEntry.TABLE_NAME + "("
                + UserEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + UserEntry.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP
                + UserEntry.COLUMN_NAME_USERPWD + TEXT_TYPE + COMMA_SEP
                + UserEntry.COLUMN_NAME_AGE + INTEGER_TYPE + ")";

        //删除表
        public static final String SQL_DELETE_ENTRY = "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
    }

}
