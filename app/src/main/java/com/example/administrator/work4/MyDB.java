package com.example.administrator.work4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/10/29.
 */
public class MyDB extends SQLiteOpenHelper{
    private static String DB_NAME = "My_DB.db";
    private static int DB_VERSION = 2 ;
    private SQLiteDatabase db;


    public MyDB(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        db=getWritableDatabase();

    }
//链接数据库
    public SQLiteDatabase openConnection(){
        if(!db.isOpen()){
            db=getWritableDatabase();
        }

        return db;
    }
//关闭数据库
    public void closeConnection() {
        try{
            if(db!=null&&db.isOpen())
                db.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
//创建数据表
    public boolean creatTable(String createTablesql){
        try {
            openConnection();
            db.execSQL(createTablesql);

        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    //保存数据
    public boolean save(String tableName,ContentValues values){
        try {
            openConnection();
            db.insert(tableName, null, values);

        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }

    public boolean update(String table,ContentValues valuse,String whereClause,String whereArgs[]){
        try {
            openConnection();
            db.update(table, valuse, whereClause, whereArgs);

        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }

    public boolean delete(String table,String deletesql,String obj[]){
        try {
            openConnection();
            db.delete(table, deletesql, obj);

        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    public Cursor find(String  fingsql,String obj[]){
        try {
            openConnection();
            Cursor cursor=db.rawQuery(fingsql,obj);
            return cursor;
        }catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public boolean isTableExits(String tablename){
        try {
            openConnection();
            String str = "select count(*)xcount from " + tablename;
            db.rawQuery(str,null).close();

        }catch (Exception ex){

            return false;
        }
        return true;
    }
    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int i, int i1) {

    }
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }
}
