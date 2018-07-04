
package com.neo.util;
import java.io.File;
import java.io.FileNotFoundException;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
import java.util.Scanner;  
  
public class ImportCSV
{  
    private static Connection con;  
  
    public static void main(String[] args) throws FileNotFoundException, SQLException  
    {  
  
        long startTime = System.currentTimeMillis();  
        File file = new File("D:\\srccode\\spring-boot-thymeleaf\\src\\main\\resources\\20180607132655.csv");
  
        Scanner in = new Scanner(file);

        con=   getConnect();

        System.out.println("数据库连接成功");

       insert_data(in);
  
        long EndTime = System.currentTimeMillis();  
        long time = (EndTime - startTime) / 1000;  
  
        System.out.println("导入数据共用时：" + time);  
    }  
  
    private static void insert_data(Scanner in) throws SQLException  
    {  
        int count = 0;  
        String sql = "insert into t2xiaoliang (zq,pl,laiyuan,jiagedai,shenfen,yanshe,cicun,fudanliang,xiaoliang,jinger,yonghushu)"
                + "values(?,?,?,?,?,?,?,?,?,?,?)";


        con.setAutoCommit(false);  
        PreparedStatement pstmt = con.prepareStatement(sql);  
        in.next();  
        while (in.hasNext())  
        {  
            String temp1 = in.nextLine();  
            String[] temp = temp1.split(",");
  
            if (temp.length < 11)
                continue;  
  
            if (temp.length == 11)
            {  
                pstmt.setString(1, temp[0]);  
                pstmt.setString(2, temp[1]);  
                pstmt.setString(3, temp[2]);  
                pstmt.setString(4, temp[3]);  
                pstmt.setString(5, temp[4]);  
                pstmt.setString(6, temp[5]);  
                pstmt.setString(7, temp[6]);  
                pstmt.setString(8, temp[7]);  
                pstmt.setString(9, temp[8]);  
                pstmt.setString(10, temp[9]);  
                pstmt.setString(11, temp[10]);  
              //  pstmt.setString(12, temp[11]);
             //   pstmt.setString(13, temp[12]);
            }  
  
            pstmt.addBatch();  
  
            count++;  
  
            if (count == 20000)  
            {  
                count = execute(pstmt, count);  
            }  
        }  
        pstmt.executeBatch();  
        con.commit();  
  
    }
    private static void insert_data2(Scanner in) throws SQLException
    {
        int count = 0;
        String sql = "insert into t2xiaoliang (zq,pl,laiyuan,jiagedai,shenfen,yanshe,cicun,fudanliang,xiaoliang,jinger,yonghushu)"
                + "values(?,?,?,?,?,?,?,?,?,?,?)";


        con.setAutoCommit(false);
        PreparedStatement pstmt = con.prepareStatement(sql);
        in.next();
        while (in.hasNext())
        {
            String temp1 = in.nextLine();
            String[] temp = temp1.split(",");

            if (temp.length < 11)
                continue;

            if (temp.length == 11)
            {
                pstmt.setString(1, temp[0]);
                pstmt.setString(2, temp[1]);
                pstmt.setString(3, temp[2]);
                pstmt.setString(4, temp[3]);
                pstmt.setString(5, temp[4]);
                pstmt.setString(6, temp[5]);
                pstmt.setString(7, temp[6]);
                pstmt.setString(8, temp[7]);
                pstmt.setString(9, temp[8]);
                pstmt.setString(10, temp[9]);
                pstmt.setString(11, temp[10]);
                //  pstmt.setString(12, temp[11]);
                //   pstmt.setString(13, temp[12]);
            }

            pstmt.addBatch();

            count++;

            if (count == 20000)
            {
                count = execute(pstmt, count);
            }
        }
        pstmt.executeBatch();
        con.commit();

    }


    private static void selectbenqi(Connection con) throws SQLException
    {
        int count = 0;
        String sql = "select pl,sum(jinger)    from t2xiaoliang  where zq='本期'   group by pl ;";

        con.setAutoCommit(false);
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.executeBatch();
        con.commit();

    }

    public static int execute(PreparedStatement pstmt, int count) throws SQLException
    {

        pstmt.executeBatch();
        con.commit();
        return 0;

    }
  
    private static  Connection  getConnect()
    {
        Connection     icon=null;
        try  
        {  
            Class.forName("com.mysql.jdbc.Driver");
                 icon = DriverManager.getConnection(
                    "jdbc:mysql://47.93.9.175:3306/jd?useUnicode=true&characterEncoding=utf8&useServerPrepStmts=false&rewriteBatchedStatements=true&autoReconnect=true&failOverReadOnly=false",
                    "root", "duxmaster741852");
        }  
        catch (ClassNotFoundException | SQLException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }

        return icon;
    }  
  
}

