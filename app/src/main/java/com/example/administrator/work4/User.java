package com.example.administrator.work4;

/**
 * Created by Administrator on 2015/10/29.
 */
public class User {
    public final static String NAME="name";
    public final static String NUM="num";
    public final static String DANWEI="danwei";
    public final static String QQ="qq";
    public final static String DIZHI="dizhi";

    private String name;
    private String num;
    private String danwei;
    private String qq;
    private String dizhi;;
    private int id_DB=-1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public int getId_DB() {
        return id_DB;
    }

    public void setId_DB(int id_DB) {
        this.id_DB = id_DB;
    }

    public String getDizhi() {

        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getQq() {

        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
