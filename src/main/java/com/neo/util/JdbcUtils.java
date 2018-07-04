package com.neo.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by onedux on 2017/4/15.
 */

        import java.lang.reflect.Field;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.ResultSetMetaData;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class JdbcUtils {
    private static String USERNAME = "";
    private static String PASSWORD = "";
    private static String DRIVER = "";
    private static String URL = "";
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    public JdbcUtils(String driver, String url, String username, String password) {
        DRIVER = driver;
        URL = url;
        USERNAME = username;
        PASSWORD = password;

        try {
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

    }
    

    public Connection getConnection() {
        return this.connection;
    }

    public boolean updateByPreparedStatement(String sql, List<Object> params) {
        boolean flag = false;

        try {
            boolean e = true;
            this.pstmt = this.connection.prepareStatement(sql);
            int index = 1;
            if(params != null && !params.isEmpty()) {
                for(int i = 0; i < params.size(); ++i) {
                    this.pstmt.setObject(index++, params.get(i));
                }
            }

            int var8 = this.pstmt.executeUpdate();
            flag = var8 > 0;
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return flag;
    }

    public Map<String, Object> findSimpleResult(String sql, List<Object> params) {
        HashMap map = new HashMap();

        try {
            int e = 1;
            this.pstmt = this.connection.prepareStatement(sql);
            if(params != null && !params.isEmpty()) {
                for(int metaData = 0; metaData < params.size(); ++metaData) {
                    this.pstmt.setObject(e++, params.get(metaData));
                }
            }

            this.resultSet = this.pstmt.executeQuery();
            ResultSetMetaData var11 = this.resultSet.getMetaData();
            int col_len = var11.getColumnCount();

            while(this.resultSet.next()) {
                for(int i = 0; i < col_len; ++i) {
                    String cols_name = var11.getColumnName(i + 1);
                    Object cols_value = this.resultSet.getObject(cols_name);
                    if(cols_value == null) {
                        cols_value = "";
                    }

                    map.put(cols_name, cols_value);
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return map;
    }

    public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException {
        ArrayList list = new ArrayList();
        int index = 1;
        this.pstmt = this.connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()) {
            for(int metaData = 0; metaData < params.size(); ++metaData) {
                this.pstmt.setObject(index++, params.get(metaData));
            }
        }

        this.resultSet = this.pstmt.executeQuery();
        ResultSetMetaData var11 = this.resultSet.getMetaData();
        int cols_len = var11.getColumnCount();

        while(this.resultSet.next()) {
            HashMap map = new HashMap();

            for(int i = 0; i < cols_len; ++i) {
                String cols_name = var11.getColumnName(i + 1);
                String cols_value = this.resultSet.getString(cols_name);
                if(cols_value == null) {
                    cols_value = "";
                }

                map.put(cols_name, cols_value);
            }

            list.add(map);
        }

        return list;
    }

    public Object findSimpleRefResult(String sql, List<Object> params, Class<Object> cls) throws Exception {
        Object resultObject = null;
        int index = 1;
        this.pstmt = this.connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()) {
            for(int metaData = 0; metaData < params.size(); ++metaData) {
                this.pstmt.setObject(index++, params.get(metaData));
            }
        }

        this.resultSet = this.pstmt.executeQuery();
        ResultSetMetaData var12 = this.resultSet.getMetaData();
        int cols_len = var12.getColumnCount();

        while(this.resultSet.next()) {
            resultObject = cls.newInstance();

            for(int i = 0; i < cols_len; ++i) {
                String cols_name = var12.getColumnName(i + 1);
                String cols_value = this.resultSet.getString(cols_name);
                if(cols_value == null) {
                    cols_value = "";
                }

                Field field = cls.getDeclaredField(cols_name);
                field.setAccessible(true);
                field.set(resultObject, cols_value);
            }
        }

        return resultObject;
    }

    public <T> List<T> findMoreRefResult(String sql, List<Object> params, Class<T> cls) throws Exception {
        ArrayList list = new ArrayList();
        int index = 1;
        this.pstmt = this.connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()) {
            for(int metaData = 0; metaData < params.size(); ++metaData) {
                this.pstmt.setObject(index++, params.get(metaData));
            }
        }

        this.resultSet = this.pstmt.executeQuery();
        ResultSetMetaData var13 = this.resultSet.getMetaData();
        int cols_len = var13.getColumnCount();

        while(this.resultSet.next()) {
            Object resultObject = cls.newInstance();

            for(int i = 0; i < cols_len; ++i) {
                String cols_name = var13.getColumnName(i + 1);
                Object cols_value = this.resultSet.getObject(cols_name);
                if(cols_value == null) {
                    cols_value = "";
                }

                Field field = cls.getDeclaredField(cols_name);
                field.setAccessible(true);
                field.set(resultObject, cols_value);
            }

            list.add(resultObject);
        }

        return list;
    }

    public void releaseConn() {
        if(this.resultSet != null) {
            try {
                this.resultSet.close();

            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

    }
    public void releaseAll() {

        try {
            if(this.resultSet != null) {
                this.resultSet.close();
            }
            if(this.pstmt != null) {
                this.pstmt.close();
            }
            if(this.connection != null) {
                this.connection.close();
            }

        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }
    public static void main(String[] args) throws SQLException {
        String USERNAME = "root";
        String PASSWORD = "ozjHj6pu~cPvzq7";
        String DRIVER = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://115.182.226.166:3306/dfdb";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException var11) {
            var11.printStackTrace();
        }

        JdbcUtils jdbcUtils = new JdbcUtils(DRIVER, URL, USERNAME, PASSWORD);
        jdbcUtils.getConnection();
        String sql2 = "select * from users ";
        List list = jdbcUtils.findModeResult(sql2, (List)null);

    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库连接成功！");
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }
}
