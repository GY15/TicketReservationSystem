package web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class AddTestNum {
    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            for (int i =0; i < 1000;i++) {
                Random r = new Random();
                int num = r.nextInt(16) + 1;

                int price = (r.nextInt(10)+1) * 100;
                String sql = "INSERT INTO `order` (orderid,userid,productid,num,time,price) VALUES ("+i+",1234,'"+(r.nextInt(10))+"',"+num+",NOW(),"+price+")";
//                String sql = "INSERT INTO `store` (productid,name,inventory) VALUES ("+i+",'iphone"+(i+1)+"',10)";
//                System.out.println(sql);
                stmt.executeUpdate(sql);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/order?serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=UTF-8&useSSL=false&useServerPrepStmts=false&rewriteBatchedStatements=true";
            con = DriverManager.getConnection(url, "root", "qwertyuiop");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return con;
    }
}
