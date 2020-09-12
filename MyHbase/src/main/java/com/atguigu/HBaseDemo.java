package com.atguigu;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.ColumnRangeFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HBaseDemo {
    private Configuration configuration;
    private Connection connection;
    private Admin admin;
// ================================================资源的创建和释放===========================================

    /**
     * 初始化 配置信息
     */
    @Before
    public void init() {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop102:2181,hadoop103:2181,hadoop104:2181");
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭资源
     */
    @After
    public void close() {
        if (admin != null) {
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //================================================================命名空间有关的操作=================================

    /**
     * 创建命名空间
     */
    @Test
    public void createNameSpace() throws Exception {
        // 获取HBase的连接
        final Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop102:2181,hadoop103:2181,hadoop104:2181");
        final Connection connection = ConnectionFactory.createConnection(configuration);
        // 创建Admin
        final Admin admin = connection.getAdmin();
        // 创建命名空间
        final NamespaceDescriptor bigHBase = NamespaceDescriptor.create("bigHBase5").build();
        admin.createNamespace(bigHBase);
        // 关闭连接
        admin.close();
        connection.close();
    }

    /**
     * 显示所有的命名空间
     *
     * @throws Exception
     */
    @Test
    public void listNameSpace() throws Exception {
        // 获取hbase的连接
        final Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop102:2181,hadoop103:2181,hadoop104:2181");
        final Connection connection = ConnectionFactory.createConnection(configuration);
        // 创建admin
        final Admin admin = connection.getAdmin();
        //显示所有的命名空间
        final NamespaceDescriptor[] namespaceDescriptors = admin.listNamespaceDescriptors();
        for (NamespaceDescriptor namespaceDescriptor : namespaceDescriptors) {
            System.out.println(namespaceDescriptor.getName());
        }
        // 关闭连接
        admin.close();
        connection.close();
    }

    /**
     * 查看某个命名空间所有表
     *
     * @throws Exception
     */
    @Test
    public void listNameSpaceTablesByNameSpace() throws Exception {
        // 获取HBase的连接
        final Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop102:2181,hadoop103:2181,hadoop104:2181");
        final Connection connection = ConnectionFactory.createConnection(configuration);
        // 创建admin
        final Admin admin = connection.getAdmin();
        // 获取指定命名空间下的表信息
        final List<TableDescriptor> tableDescriptors = admin.listTableDescriptorsByNamespace("bigHBase".getBytes());
        for (TableDescriptor tableDescriptor : tableDescriptors) {
            final TableName tableName = tableDescriptor.getTableName();
            System.out.println(new String(tableName.getName()));
            System.out.println(tableName.getQualifierAsString());

        }
        // 关闭资源
        admin.close();
        connection.close();
    }

    /**
     * 删除命名空间 要求该命名空间没有表,
     * 有的话 先删除所有的表,然后再删除表
     *
     * @throws IOException
     */
    @Test
    public void dropNameSpace() throws IOException {
        // 获取 命名空间所有的表
        final List<TableDescriptor> tableDescriptors = admin.listTableDescriptorsByNamespace("bigHBase5".getBytes());
        for (TableDescriptor tableDescriptor : tableDescriptors) {
            // 禁用表
            admin.disableTable(tableDescriptor.getTableName());
            // 删除表
            admin.deleteTable(tableDescriptor.getTableName());
        }
        // 删除命名空间
        admin.deleteNamespace("bigHBase5");

    }

    //=====================================对表的操作=============================================================

    /**
     * 创建表 create "命名空间:表名","列族"
     *
     * @throws IOException
     */
    @Test
    public void createTable() throws IOException {
        // 创建表
        // create 表名 列族...
        // 创建列族的描述
        final ColumnFamilyDescriptor base_ifo = ColumnFamilyDescriptorBuilder.newBuilder("base_ifo".getBytes()).build();
        final ColumnFamilyDescriptor extra_info = ColumnFamilyDescriptorBuilder.newBuilder("extra_info".getBytes()).build();
        //  创建表的描述
        final TableDescriptor build = TableDescriptorBuilder.newBuilder(TableName.valueOf("person2"))
                .setColumnFamily(base_ifo)
                .setColumnFamily(extra_info)
                .build();
        //  预分区
        final byte[][] splitKeys = {"10".getBytes(), "20".getBytes(), "30".getBytes()};
        admin.createTable(build,splitKeys);
        // 创建表
        admin.createTable(build);
        //关闭资源
    }

    /**
     * 修改列族 需要注意的是:此修改会覆盖原来的列族,所以需要把之前的列族也要重新设置一次
     *
     * @throws IOException
     */
    @Test
    public void alterTable() throws IOException {
        //  修改extra_info列族的版本数

        // 1 创建列族的描述
        final ColumnFamilyDescriptor base_ifo = ColumnFamilyDescriptorBuilder.newBuilder("base_ifo".getBytes())
                .setMinVersions(2)
                .setMaxVersions(2).build();
        final ColumnFamilyDescriptor extra_info = ColumnFamilyDescriptorBuilder.newBuilder("extra_info".getBytes())
                .setMinVersions(2)
                .setMaxVersions(2)
                .build();
        // 新增一个列族
        final ColumnFamilyDescriptor address_info = ColumnFamilyDescriptorBuilder
                .newBuilder("address_info".getBytes()).build();
        // 创建表的描述 并关联列族描述
        final TableDescriptor build = TableDescriptorBuilder.newBuilder(TableName.valueOf("bigHBase:person"))
                .setColumnFamily(base_ifo)
                .setColumnFamily(extra_info)
                .setColumnFamily(address_info)
                .build();

        // 修改表结构
        admin.modifyTable(build);

    }

    /**
     * 显示所有的表
     *
     * @throws IOException
     */
    @Test
    public void listTable() throws IOException {
        final TableName[] tableNames = admin.listTableNames();
        for (TableName tableName : tableNames) {
            System.out.println(new String(tableName.getName()));
        }
    }

    /**
     * 删除表
     *
     * @throws IOException
     */
    @Test
    public void dropTable() throws IOException {
        // 禁用表
        admin.disableTable(TableName.valueOf("bigHBase:person2"));
        // 删除表
        admin.deleteTable(TableName.valueOf("bigHBase:person2"));
    }

    /**
     * 插入数据
     *
     * @throws IOException
     */
    @Test
    public void put() throws IOException {
        // 创建Table对象
        final Table table = connection.getTable(TableName.valueOf("student"));
        //插入数据 命令空间:表名 rowKey 列族:列限定名 值
        //  设置rowKey
        final Put put = new Put("1001000".getBytes());
        // 设置列族 列限定名 值
        put.addColumn("base_ifo".getBytes(), "name".getBytes(), "李四".getBytes());
       // put.addColumn("base_ifo".getBytes(), "age".getBytes(), Bytes.toBytes(20));
        put.addColumn("base_ifo".getBytes(), "age".getBytes(), "26".getBytes());
        put.addColumn("extra_info".getBytes(), "class".getBytes(), ("2523").getBytes());
      //  put.addColumn("address_info".getBytes(), "address".getBytes(), ("shenzhen").getBytes());
        // 插入数据
        table.put(put);
        // 关闭table
        table.close();
    }

    /**
     * 批量插入数据
     *
     * @throws IOException
     */
    @Test
    public void putList() throws IOException {
        // 创建表table对象
        final Table table = connection.getTable(TableName.valueOf("person2"));
        // 声明List对象 用于存放put对象 对应一个rowKey
        ArrayList<Put> list = new ArrayList<>();
        Put put = null;
        for (int i = 11; i <= 20; i++) {
            //  设置 rowKey
            put = new Put(("100" + i).getBytes());
            // 设置列族,列限定名 值
            put.addColumn("base_ifo".getBytes(), "name".getBytes(), ("zhansan-" + i).getBytes());
            //put.addColumn("base_ifo".getBytes(), "age".getBytes(), Bytes.toBytes(20 + i));
            put.addColumn("base_ifo".getBytes(), "age".getBytes(), ((20+i)+"").getBytes());
            put.addColumn("extra_info".getBytes(), "class".getBytes(), ("2025" + i).getBytes());
           // put.addColumn("address_info".getBytes(), "address".getBytes(), ("shenzhen" + i).getBytes());
            // 插入数据
            table.put(put);
        }
        // 关闭Table对象
        table.close();

    }

    // 通过rowKey查询整行数据
    @Test
    public void getValueByRowKey() throws IOException {
        // 创建table对象
        final Table table = connection.getTable(TableName.valueOf("bigHBase:person"));
        // 创建get对象 并设置要查询那个rowKey
        final Get get = new Get("1000".getBytes());
        // 查询数据,返回一行数
        final Result result = table.get(get);
        // 获取cell对象,每一行都有多个cell
        final List<Cell> cells = result.listCells();
        // 遍历cells 显示每个一个cell对象的值
        for (Cell cell : cells) {
            // 一个cell 由 rowKey+列族+列限定符+时间戳+值组成
            // 由rowKey+列族+列限定符+时间戳确定

            // 获取rowKey
            final String rowKey = new String(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
            // 获取列族
            final String family = new String(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
            // 获取列限定符
            final String qualifier = new String(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            // 获取时间戳
            final long timestamp = cell.getTimestamp();

            // 获取值 value 需要判断一下 值的类型
            if ("base_ifo".equals(family) && "age".equals(qualifier)) {
                int value = Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
            } else {
                String value = new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
            }
        }
        // 关闭table对象
        table.close();
    }

    /**
     * 根据rowKey和列族获取 cell
     *
     * @throws IOException
     */
    @Test
    public void getValueByRowKeyAndQualifier() throws IOException {
        // 创建table对象
        final Table table = connection.getTable(TableName.valueOf("bigHBase:person"));
        // 创建get对象 并设置要查询那个rowKey
        final Get get = new Get("1001000".getBytes());
        // 设置单个列族
        get.addFamily("extra_info".getBytes());
        get.addFamily("base_ifo".getBytes());
        // 设置某个列族的某个列限定符
        get.addColumn("extra_info".getBytes(), "class".getBytes());
        // 获取结果
        final Result result = table.get(get);
        final List<Cell> cells = result.listCells();
        // 遍历cells 显示每个一个cell对象的值
        for (Cell cell : cells) {
            // 一个cell 由 rowKey+列族+列限定符+时间戳+值组成
            // 由rowKey+列族+列限定符+时间戳确定

            // 获取rowKey
            final String rowKey = new String(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
            // 获取列族
            final String family = new String(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
            // 获取列限定符
            final String qualifier = new String(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            // 获取时间戳
            final long timestamp = cell.getTimestamp();

            // 获取值 value 需要判断一下 值的类型
            if ("base_ifo".equals(family) && "age".equals(qualifier)) {
                int value = Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
            } else {
                String value = new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
            }
        }
        // 关闭table对象
        table.close();

    }

    /**
     * 批量查询
     *
     * @throws IOException
     */
    @Test
    public void getBatch() throws IOException {
        final Table table = connection.getTable(TableName.valueOf("person2"));

        // 声明一个list存储get
        List<Get> list = new ArrayList<>();
        Get get = null;
        for (int i = 11; i <= 20; i++) {
            // 创建get对象,并指定rowKey
            get = new Get(("100" + i).getBytes());
            get.addFamily("base_ifo".getBytes());
            list.add(get);
        }
        // 多条数据
        final Result[] results = table.get(list);
        // 遍历得到多条记录
        for (Result result : results) {
            final List<Cell> cells = result.listCells();
            for (Cell cell : cells) {
                final String rowKey = new String(CellUtil.cloneRow(cell));
                final String family = new String(CellUtil.cloneFamily(cell));
                final String qualifier = new String(CellUtil.cloneQualifier(cell));
                final long timestamp = cell.getTimestamp();
                if ("age".equals(family) && "base_ifo".equals(family)) {
                    final int value = Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                } else {
                    String value = new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                }
            }
        }
        // 关闭资源
        table.close();
    }

    /**
     * 指定RowKey删除一行数据
     * @throws IOException
     */
    @Test
    public void deleteByRowKey() throws IOException {
        // 获取table对象
        final Table table = connection.getTable(TableName.valueOf("bigHBase:person"));
        // 创建delete对象 指定删除的rowKey
        final Delete delete = new Delete("1000".getBytes());
        // 删除
        table.delete(delete);
        //关闭资源
        table.close();
    }

    /**
     * 批量删除数据
     * @throws IOException
     */
    @Test
    public void deleteBatch() throws IOException {
        final Table table = connection.getTable(TableName.valueOf("bigHBase:person"));
        ArrayList<Delete>list = new ArrayList<>();
        Delete delete=null;
        for (int i =3; i <= 7; i++) {
            delete=new Delete(("100"+i).getBytes());
            list.add(delete);
        }
        table.delete(list);

        //
        table.close();
    }

    //==================================================scan扫描数据===================================================================

    /**
     * 使用scan全局扫描
     *
     * @throws IOException
     */
    @Test
    public void getValueByScan() throws IOException {
        // 1,获取table对象,并指定要操作那个命名空间那个table表
        final Table table = connection.getTable(TableName.valueOf("bigHBase:person"));
        //  2,查询数据
        // 查询全部的数据
        Scan scan = new Scan();
        // 获得数据集合
        final ResultScanner results = table.getScanner(scan);
        // 3,展示数据
        // 把集合转为iterator类型
        final Iterator<Result> iterator = results.iterator();
        while (iterator.hasNext()) {
            // 遍历获取每行数据
            final Result res = iterator.next();
            // 得到每行的cell=rowKey+列族+列限定符+时间戳+value
            final List<Cell> cells = res.listCells();
            for (Cell cell : cells) {
                final byte[] familyByte = CellUtil.cloneFamily(cell);
                final String family = new String(familyByte);
                final String rowKey = new String(CellUtil.cloneRow(cell));
                final String qualifier = new String(CellUtil.cloneQualifier(cell));
                final long timestamp = cell.getTimestamp();
                if ("age".equals(family) && "base_ifo".equals(family)) {
                    final int value = Bytes.toInt(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                } else {
                    String value = new String(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                }
            }
        }
        // 4,关闭
        table.close();
    }

    /**
     * 使用scan指定family查询数据
     * @throws IOException
     */
    @Test
    public void  getValueByScanFamily() throws IOException {
        // 1获取table 并指定那个命名空间那个表
        final Table table = connection.getTable(TableName.valueOf("bigHBase:person"));

        // 查询数据
        //  创建scan
        Scan scan=new Scan();
        // 设置列族
        scan.addFamily("base_ifo".getBytes());
        // 获取数据
        final ResultScanner scanner = table.getScanner(scan);
        // 展示数据
        // 把结果集转为iterator
        final Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()){
            // 获取一条记录
            final Result result = iterator.next();
            // 获取cell集合
            final List<Cell> cells = result.listCells();
            // 遍历cells 获取cell的组成信息
            for (Cell cell : cells) {
                final String rowKey = new String(CellUtil.cloneRow(cell));
                final String family = new String(CellUtil.cloneFamily(cell));
                final String qualifier = new String(CellUtil.cloneQualifier(cell));
                final long timestamp = cell.getTimestamp();
                if ("age".equals(family) && "base_ifo".equals(family)) {
                    final int value = Bytes.toInt(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                } else {
                    String value = new String(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                }
            }
        }
        // 4 关闭资源
        table.close();

    }

    // =====================================================过滤器=================================================================

    /**
     * 根据值来查询数据
     * 例如 select * from xx where age=25
     */
    @Test
    public void filterByValue() throws IOException {
        // 1获取table对象 并指定那个命名空间那个表
        final Table table = connection.getTable(TableName.valueOf("bigHBase:person"));
        // 2 查询数据
        // 2.1 创建scan
        Scan scan=new Scan();
//        // 2.2 根据value进行过滤,只显示单个cell
//        BinaryComparator comparator=new BinaryComparator(Bytes.toBytes(25));
//        final ValueFilter valueFilter = new ValueFilter(CompareOperator.EQUAL, comparator);
//        scan.setFilter(valueFilter);

        // 根据vlaue进行过滤,显示整行数据 age=25的那一行数据全部查询出来
        final SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("base_ifo".getBytes(), "age".getBytes(), CompareOperator.EQUAL, Bytes.toBytes(25));
        scan.setFilter(singleColumnValueFilter);
        final ResultScanner scanner = table.getScanner(scan);
        // 展示数据
        final Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()){
            // 获取一条记录
            final Result result = iterator.next();
            // 获取cell集合
            final List<Cell> cells = result.listCells();
            // 遍历cells 获取cell的组成信息
            for (Cell cell : cells) {
                final String rowKey = new String(CellUtil.cloneRow(cell));
                final String family = new String(CellUtil.cloneFamily(cell));
                final String qualifier = new String(CellUtil.cloneQualifier(cell));
                final long timestamp = cell.getTimestamp();
                if ("age".equals(family) && "base_ifo".equals(family)) {
                    final int value = Bytes.toInt(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                } else {
                    String value = new String(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                }
            }
        }
        // 4 关闭资源
        table.close();

    }

    /**
     * 实现模糊查询
     * select * form xx where name like "%5%"
     * @throws IOException
     */
    @Test
    public void filterByLike() throws IOException {
        // 1 获取table对象
        final Table table = connection.getTable(TableName.valueOf("bigHBase:person"));
        Scan scan=new Scan();
        // 查询整行的数据
        final SubstringComparator comparator = new SubstringComparator("-5");
        final SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("base_ifo".getBytes(), "name".getBytes(), CompareOperator.EQUAL, comparator);
        scan.setFilter(singleColumnValueFilter);
        final ResultScanner scanner = table.getScanner(scan);
        final Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()){
            // 获取一条记录
            final Result result = iterator.next();
            // 获取cell集合
            final List<Cell> cells = result.listCells();
            // 遍历cells 获取cell的组成信息
            for (Cell cell : cells) {
                final String rowKey = new String(CellUtil.cloneRow(cell));
                final String family = new String(CellUtil.cloneFamily(cell));
                final String qualifier = new String(CellUtil.cloneQualifier(cell));
                final long timestamp = cell.getTimestamp();
                if ("age".equals(family) && "base_ifo".equals(family)) {
                    final int value = Bytes.toInt(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                } else {
                    String value = new String(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                }
            }
        }
        // 4 关闭资源
        table.close();
    }

    /**
     * 关联条件查询
     * select * from xx where name like '%san%' and (age>20 and name='zhangsan-4')
     * @throws IOException
     */
    @Test
    public void  filterByMuti() throws IOException {
        Table table=connection.getTable(TableName.valueOf("bigHBase:person"));
        Scan scan=new Scan();
        // name like "-5"
        final SingleColumnValueFilter like = new SingleColumnValueFilter("base_ifo".getBytes(), "name".getBytes(), CompareOperator.EQUAL, new SubstringComparator("-5"));
        // age>2
        final SingleColumnValueFilter age = new SingleColumnValueFilter("base_ifo".getBytes(), "age".getBytes(), CompareOperator.GREATER, Bytes.toBytes(20));
        // name="shangsan-2"
        final SingleColumnValueFilter name = new SingleColumnValueFilter("base_ifo".getBytes(), "name".getBytes(), CompareOperator.EQUAL, Bytes.toBytes("shangsan-2"));
        //(age>20 or name='zhangsan-4')
        final FilterList nameAndAge = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        nameAndAge.addFilter(name);
        nameAndAge.addFilter(age);
        //name like '%san%' and (age>20 or name='zhangsan-4')
        final FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        filterList.addFilter(like);
        filterList.addFilter(nameAndAge);
        // 关联scan
       scan.setFilter(filterList);
       // 查询数据
        final ResultScanner scanner = table.getScanner(scan);
        final Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()){
            final Result result = iterator.next();
            // 获取cell集合
            final List<Cell> cells = result.listCells();
            // 遍历cells 获取cell的组成信息
            for (Cell cell : cells) {
                final String rowKey = new String(CellUtil.cloneRow(cell));
                final String family = new String(CellUtil.cloneFamily(cell));
                final String qualifier = new String(CellUtil.cloneQualifier(cell));
                final long timestamp = cell.getTimestamp();
                if ("age".equals(family) && "base_ifo".equals(family)) {
                    final int value = Bytes.toInt(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                } else {
                    String value = new String(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                }
            }
        }
        // 关闭资源
        table.close();
    }

    /**
     * 模块查询,就是指定全局查询一个字段的数据
     */
    @Test
    public void rangScan() throws IOException {
        final Table table = connection.getTable(TableName.valueOf("bigHBase:person"));
        Scan scan=new Scan();
        //根据列查询
        // base_info:1  base_info:2
        final ColumnRangeFilter filter = new ColumnRangeFilter("base_ifo".getBytes(), true, null, false);

        scan.setFilter(filter);
        final ResultScanner scanner = table.getScanner(scan);
        final Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()){
            final Result result = iterator.next();
            // 获取cell集合
            final List<Cell> cells = result.listCells();
            // 遍历cells 获取cell的组成信息
            for (Cell cell : cells) {
                final String rowKey = new String(CellUtil.cloneRow(cell));
                final String family = new String(CellUtil.cloneFamily(cell));
                final String qualifier = new String(CellUtil.cloneQualifier(cell));
                final long timestamp = cell.getTimestamp();
                if ("age".equals(family) && "base_ifo".equals(family)) {
                    final int value = Bytes.toInt(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                } else {
                    String value = new String(CellUtil.cloneValue(cell));
                    System.out.println(rowKey + "--" + family + "---" + qualifier + "--" + timestamp + "----" + value);
                }
            }
        }
        // 关闭资源
        table.close();
    }
}
