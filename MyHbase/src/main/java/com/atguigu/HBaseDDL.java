package com.atguigu;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseDDL {
    public static void main(String[] args) throws IOException {
        System.out.println(isTableExist("bigdata:student1"));
    }
    public static boolean isTableExist(String tableName) throws IOException {
        // 创建配置信息并配置
        final Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop102,hadoop1103,hadoop104");
        // 获取与HBase的连接
        final Connection connection = ConnectionFactory.createConnection(configuration);
        // 获取DDL操作对象
        final Admin admin = connection.getAdmin();
        // 判断表是是否存在
        final boolean b = admin.tableExists(TableName.valueOf(tableName));
        // 关闭连接
        admin.close();
        connection.close();
        return b;
    }
    public static void createTable(String tableName,String...cfs) throws IOException {
        // 判断是否存在列族信息
        if(cfs.length<=0){
            System.out.println("请输入列族信息");
            return;
        }
        // 判断表是否存在
        if(isTableExist(tableName)){
            System.out.println("表已存在");
            return;
        }
        // 创建配置信息并配置
        final Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop102,hadoop1103,hadoop104");
        // 获取HBase的连接
        final Connection connection = ConnectionFactory.createConnection(configuration);
        // 获取DDL操作对象
        final Admin admin = connection.getAdmin();
        // 创建表描述器构造器
        final TableDescriptorBuilder table = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
        // 循环添加列族信息
        for (String cf : cfs) {
           // 创建列族的描述器
            final ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf)).build();
            table.setColumnFamily(columnFamilyDescriptor);
        }
        // 执行创建表的操作
        admin.createTable(table.build());
        // 关闭资源
        admin.close();
        connection.close();
    }
    public static void deleteTable(String tableName) throws IOException {
        // 判断表是否存在
        if(!isTableExist(tableName)){
            System.out.println("表不存在");
            return;
        }
        // 创建配置信息 并配置
        final Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop102,hadoop1103,hadoop104");
        // 获取HBase的连接
        final Connection connection = ConnectionFactory.createConnection(configuration);
        // 获取DDL操作对象
        final Admin admin = connection.getAdmin();

        // 使表下线,也就是禁用表
        admin.disableTable(TableName.valueOf(tableName));
        // 执行删除表操作
        admin.deleteTable(TableName.valueOf(tableName));
        // 关闭资源
        admin.close();
        connection.close();

    }

}
