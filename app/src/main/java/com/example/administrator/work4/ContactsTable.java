package com.example.administrator.work4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

/**
 * Created by Administrator on 2015/10/29.
 */
public class ContactsTable{
    private final static String TABLENAME="contactsTable";
    private MyDB db;

    public ContactsTable(Context context){
        db=new MyDB(context);
        if(!db.isTableExits(TABLENAME)){
            String createTableSql = "CREATE TABLE IF NOT EXISTS "+
                    TABLENAME + "（id_DB integer primary key AUTIONCREMENT, "+
                    User.NAME + " VARCHAR," +
                    User.DANWEI + " VARCHAR, " +
                    User.NUM + " VARCHAR, " +
                    User.QQ + " VARCHAR, " +
                    User.DIZHI + " VARCHAR) " ;
            db.creatTable(createTableSql);
        }
    }
public boolean addData(User user){
    ContentValues values=new ContentValues();
    values.put(User.NAME,user.getName());
    values.put(User.DANWEI,user.getDanwei());
    values.put(User.NUM,user.getNum());
    values.put(User.QQ,user.getQq());
    values.put(User.DIZHI,user.getDizhi());
    return db.save(TABLENAME,values);
}
    public User[] getAlUser(){
        Vector<User>v=new Vector<User>();
        Cursor cursor=null;
        try{
            cursor=db.find("select * from "+TABLENAME,null);
            while (cursor.moveToNext()){
                User temp=new User();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setDizhi(cursor.getString(cursor.getColumnIndex(User.DIZHI)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setNum(cursor.getString(cursor.getColumnIndex(User.NUM)));
                v.add(temp);
            }
            }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            db.closeConnection();
        }
        if(v.size()>0){
            return v.toArray(new User[]{});
        }else{
            User[] users=new User[1];
            User user=new User();
            user.setName("没有哦");
            users[0]=user;
            return users;
        }
    }
    public User getUserByID(int id){
     Cursor cursor=null;
        try{
            cursor=db.find("select * from "+TABLENAME + " where id_DB=?",new String[]{id+""});
            User temp=new User();
            cursor.moveToNext();
            temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
            temp.setDizhi(cursor.getString(cursor.getColumnIndex(User.DIZHI)));
            temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
            temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
            temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
            temp.setNum(cursor.getString(cursor.getColumnIndex(User.NUM)));
            return temp;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            db.closeConnection();
        }
        return null;
    }

    public User[] findUserByKey(String key){
        Vector<User> v=new Vector<>();
        Cursor cursor=null;
        try{
            cursor=db.find(
                    "select * from " + TABLENAME + "   where "
                            +User.NAME+" like '%"+key+"%'"+
                            " or "+User.NUM+" like '%"+key+"%' "+
                            " or "+User.QQ+" like '%"+key+"%' "
                    ,null);
        while (cursor.moveToNext()){
            User temp=new User();
            temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
            temp.setDizhi(cursor.getString(cursor.getColumnIndex(User.DIZHI)));
            temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
            temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
            temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
            temp.setNum(cursor.getString(cursor.getColumnIndex(User.NUM)));
            v.add(temp);
        }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            db.closeConnection();
        }
        if(v.size()>0){
            return  v.toArray(new User[]{});
        }else{
            User[] users=new User[1];
            User user=new User();
            user.setName("找不到哈哈");
            users[0]=user;
            return users;
        }
    }
    public boolean deleteByUser(User user){
        return db.delete(TABLENAME," id_DB=?",new String[]{user.getId_DB()+""});
    }


    public boolean updateUser(User user) {
        ContentValues values=new ContentValues();
        values.put(User.NAME,user.getName());
        values.put(User.NUM,user.getNum());
        values.put(User.QQ,user.getQq());
        values.put(User.DIZHI,user.getDizhi());
        values.put(User.DANWEI,user.getDanwei());
        return db.update(TABLENAME,values," id_db=?",new String[]{user.getId_DB()+""});
    }
}
