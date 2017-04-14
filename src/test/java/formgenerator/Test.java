package formgenerator;

import java.sql.*;

/**
 * Created by D on 2017/4/4.
 */
public class Test {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/recordme";
            String user = "root";
            String pass = "abc012300";
            conn = DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args){
        Connection conn=getConnection();
        String sql="select * from user_info";
        PreparedStatement stmt;
        FormMainBuilder fmb = new FormMainBuilder();
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);
            System.out.println(fmb.generate(rs));
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
        }
    }
}
