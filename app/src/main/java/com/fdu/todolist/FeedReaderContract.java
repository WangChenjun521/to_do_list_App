package com.fdu.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.net.PortUnreachableException;

public  final class FeedReaderContract {
    private  FeedReaderContract(){}

    public static class FeedEntry implements BaseColumns{
        public  static  final String TABLE_NAME="entry";
        public  static final   String COLUMN_NAME_TITLE="title";
        public static  final String COLUMN_NAME_SUBTITLE="subtitle";
    }
    private  static  final String SQL_CREATE_ENTRIES=
            "CREATE TABLE "+FeedEntry.TABLE_NAME+" ("+
            FeedEntry._ID+" INTEGER PRIMARY KEY,"+
            FeedEntry.COLUMN_NAME_TITLE+" TEXT,"+
            FeedEntry.COLUMN_NAME_SUBTITLE+" TEXT)";
    private  static  final  String SQL_DELETE_ENTRIES=
            "DROP TABLE IF EXISTS "+FeedEntry.TABLE_NAME;
    public  class FeedReaderDbHelper extends SQLiteOpenHelper{
        public  static final int DATABASE_VERSION=1;
        public  static  final  String  DATABASE_NAME="FeedReader.db";
        public FeedReaderDbHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }
        public  void  onCreate(SQLiteDatabase db){
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void  onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            db.execSQL((SQL_DELETE_ENTRIES));
            onCreate(db);
        }
        public  void  onDowngrade(SQLiteDatabase db,int oldVersion,int newVersion){
            onUpgrade(db,oldVersion,newVersion);
        }
        public  void insert(SQLiteDatabase db,String title,String subtitle){
            ContentValues values=new ContentValues();
            values.put(FeedEntry.COLUMN_NAME_TITLE,title);
            values.put(FeedEntry.COLUMN_NAME_SUBTITLE,subtitle);
            long newRowId=db.insert(FeedEntry.TABLE_NAME,null,values);
        }
    }


}
