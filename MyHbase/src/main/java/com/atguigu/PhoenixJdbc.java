package com.atguigu;


import org.apache.phoenix.queryserver.client.ThinClientUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class PhoenixJdbc {

    private static Connection connection;
    private static PreparedStatement statement;
    /**
     * 初始化
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        // 1 加载驱动
        Class.forName("org.apache.phoenix.queryserver.client.Driver");
        // 2 获取connection连接
        final String url = ThinClientUtil.getConnectionUrl("hadoop102", 8765);
        connection = DriverManager.getConnection(url);
        // 设置自动提交
     //  connection.setAutoCommit(true);
    }

    /**
     * 关闭资源
     * @throws SQLException
     */
    @After
    public void close() throws SQLException {
        if(statement!=null) statement.close();
        if(connection!=null) connection.close();
    }

    /**
     * 创建表
     * @throws Exception
     */
    @Test
    public void createTable() throws Exception {
        // 1加载驱动
        Class.forName("org.apache.phoenix.queryserver.client.Driver");
        // 2获取connection对象
        // 2.1 设置url
        final String url = ThinClientUtil.getConnectionUrl("hadoop102", 8765);
        // 2.2connection
        final Connection connection = DriverManager.getConnection(url);
        // 3获取Statement对象
        String sql= "create table  if not exists user03" +
                "( id varchar primary key,  name varchar, age varchar) " +
                "COLUMN_ENCODED_BYTES=0";
        final PreparedStatement statement = connection.prepareStatement(sql);
        // 执行sql
        statement.execute();
        // 4关闭
        statement.close();
        connection.close();
    }

    /**
     * 插入&更改数据
     * @throws Exception
     */
    @Test
    public  void upsertValue() throws Exception {
        // 1加载驱动
        Class.forName("org.apache.phoenix.queryserver.client.Driver");
        // 2获取connection
        // 2.1 设置url
        final String url = ThinClientUtil.getConnectionUrl("hadoop102", 8765);
        final Connection connection = DriverManager.getConnection(url);

        // 获取statement对象
            // 3.1 编写sql
        String sql="upsert into person values(?,?,?)";
        final PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,"1002");
        statement.setString(2,"lisi");
        statement.setString(3,"26");
           //3.2执行sql
        statement.execute();
            // 3.3 手动提交
        connection.commit();
        // 关闭
        statement.close();
        connection.close();
    }

    /**
     * 批量插入数据batch
     * @throws SQLException
     */
    @Test
    public void upsertBatch() throws SQLException {
        // 编写sql
        // upsert into person values(?,?,?)
        String sql="upsert into user03 values(?,?,?)";
        // 获取statement对象
        statement=connection.prepareStatement(sql);
        for (int i = 2; i < 50; i++) {
            statement.setString(1,"100"+i);
            statement.setString(2,"wangwu"+i);
            statement.setString(3,"2"+i);
            statement.addBatch();
            if(i%10==0){
                statement.executeBatch();
                statement.clearBatch();
                connection.commit();
            }
        }
        statement.executeBatch();
        connection.commit();
        System.out.println("插入成功");
    }

    /**
     * 查询数据
     * @throws SQLException
     */
    @Test
    public void showData() throws SQLException {
        String sql="select * from person";
        statement=connection.prepareStatement(sql);
        final ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            final String id = resultSet.getString(1);
            final String name = resultSet.getString(2);
            final String age = resultSet.getString(3);
            System.out.println("id:"+id+" name:"+name+" age:"+age);
        }
    }
    /**
     * 删除数据
     * @throws SQLException
     */
    @Test
    public void deleteData() throws SQLException {
        // 编写sql语句
        String sql="delete from person where id=?";
        // 获取statement对象
        statement=connection.prepareStatement(sql);
        statement.setString(1,"1001");
        // 运行sql语句 删除数据
        statement.executeUpdate();
    }

    /**
     * 删除表
     * @throws SQLException
     */
    @Test
    public void dropTable() throws SQLException {
        // 编写sql
        String sql="drop table person";
        // 创建statement对象
        statement=connection.prepareStatement(sql);
        // 执行sql
        statement.execute();

    }

}
