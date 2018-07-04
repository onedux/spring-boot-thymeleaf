package com.neo.util;



import java.io.Serializable;

/**
 * Created by onedux on 2017/4/15.
 */

        import java.io.Serializable;

public class MySQLDriver implements Serializable {
    public static String propertyFile = "project";


    public static final String REDIS_TABLE_ELEMENT_XML = "ELEMENT_XML";
    public static final String REDIS_TABLE_TASK_STATUS = "TASK_STATUS";
    public static final String CONFIG_PATH = "/global.xml";
    public static final String SUB_XML_PATH = "/xml/";
    public static final String RESOURCE_PATH = "/mybatis-config.xml";
    public static final String TASK_STATUS_CREATE = "0";
    public static final String TASK_STATUS_START = "1";
    public static final String TASK_STATUS_SUCCESS = "2";
    public static final String TASK_STATUS_FAIL = "3";


    public static final String MYSQLUSERNAME = "root";
    public static final String MYSQLPASSWORD ="duxmaster741852";
    public static final String MYSQLDRIVER = "com.mysql.jdbc.Driver";
    public static final String MYSQLURL ="jdbc:mysql://47.93.9.175:3306/motioncamera?useUnicode=true&characterEncoding=utf-8";


    public static final String SALE_SOURCE = "sale_and_source";
    public static final String PORTRY ="portry";
    public static final String MONTH ="month_line";
    public static final String TOPCOMMENTS = "com.mysql.jdbc.Driver";
    public static final String TOPSERCH ="jdbc:mysql://47.93.9.175:3306/motioncamera?useUnicode=true&characterEncoding=utf-8";

    public MySQLDriver() {
    }
}
