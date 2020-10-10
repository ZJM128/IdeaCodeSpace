package com.atguigu;
import java.sql.*;

public class KylinTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1加载驱动
        Class.forName("org.apache.kylin.jdbc.Driver");
        // 获取连接
        String url="jdbc:kylin://hadoop102:7070/order";
        Connection con = DriverManager.getConnection(url, "ADMIN", "KYLIN");
        PreparedStatement statement = con.prepareStatement("select " +
                "sum(FINAL_AMOUNT_D)  " +
                "from DWD_FACT_ORDER_DETAIL " +
                "where DWD_FACT_ORDER_DETAIL.dt='2020-08-01'");
        // 操作数据
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            final int setInt = resultSet.getInt(1);
            System.out.println(setInt);
        }
        // 关闭连接
        resultSet.close();
        statement.close();
        con.close();
    }
}
