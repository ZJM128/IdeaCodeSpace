package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class HdFsClient {
    /**
     * 创建目录
     * @throws URISyntaxException 路径异常
     * @throws IOException   io异常
     * @throws InterruptedException 抛出异常
     */
    @Test
    public void testMkdir() throws URISyntaxException, IOException, InterruptedException {
        // 获取文件系统duixang
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9820"), new Configuration(), "atguigu");
        // 操作hdfs
        boolean flag = fs.mkdirs(new Path("/java"));
        if(flag){
            System.out.println("创建成功");
        }else{
            System.out.println("创建失败");
        }
        // 关闭hdfs对象
        fs.close();
    }

    /**
     * 上传文件
     * @throws Exception
     */
    @Test
    public void putFile() throws Exception {
        URI uri=new URI("hdfs://hadoop102:9820");
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","2");
        String user="atguigu";
        // 创建HDFS文件系统对象
        FileSystem fileSystem = FileSystem.get(uri, configuration, user);
        // 操作HDFS文件系统的数据
        fileSystem.copyFromLocalFile(false,false,new Path("D:\\hadooptestfile\\11.txt"),new Path("/input"));
        // 关闭文件系统的对象
        fileSystem.close();
    }

    /**
     * 下载文件
     * @throws Exception
     */
    @Test
    public void getFile() throws Exception {
        URI uri = new URI("hdfs://hadoop102:9820");
        Configuration conf=new Configuration();
        // 获取文件系统对象
        FileSystem fs = FileSystem.get(uri, conf, "atguigu");
        // 操作文件系统的数据
        fs.copyToLocalFile(false,new Path("/java/11.txt"),
                new Path("D:/hadooptestfile/12.txt"),true);
        // 关闭文件对象
        fs.close();
    }

    /**
     * 删除文件或目录
     * @throws Exception
     */
    @Test
    public void delFile() throws Exception {
        // 创建文件系统对象
        URI uri=new URI("hdfs://hadoop102:9820");
        Configuration configuration=new Configuration();
        FileSystem fs = FileSystem.get(uri, configuration, "atguigu");
        // 操作hdfs数据 第一个参数是hdfs目标路径 第二个参数是递归删除,true可以删除不为空的目录
        boolean delete = fs.delete(new Path("/output3"), true);
        if(delete){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }

        // 关闭文件对象
        fs.close();
    }
    URI uri;
    Configuration configuration;
    FileSystem fs;
    @Before
    public void init() throws Exception {
        uri=new URI("hdfs://hadoop102:9820");
        configuration=new Configuration();
       fs = FileSystem.get(uri, configuration, "atguigu");
    }
    @After
    public void close() throws IOException {
        fs.close();
    }

    /**
     * 重命名或移动文件
     * @throws IOException
     */
    @Test
    public void mvFile() throws IOException {
       // boolean flag = fs.rename(new Path("/input/age"), new Path("/input/newage"));
        boolean flag = fs.rename(new Path("/input/11.txt"), new Path("/java/12.txt"));
        if(flag){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
    }

    /**
     * HDFS文件详情查看
     * @throws IOException
     */
    @Test
    public void listFile() throws IOException {
        // 第一个参数是目标路径 第二个参数是否递归查找
        RemoteIterator<LocatedFileStatus> fileStatusRemoteIterator = fs.listFiles(new Path("/"), true);
        while (fileStatusRemoteIterator.hasNext()){
            LocatedFileStatus fileStatus = fileStatusRemoteIterator.next();
           // 文件名
            System.out.println(fileStatus.getPath().getName());
            // 长度
            System.out.println(fileStatus.getLen());
            // 权限
            System.out.println(fileStatus.getPermission());
            // 分组
            System.out.println(fileStatus.getGroup());

            // 获取存储的块的信息
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                // 获取块存储主机结点
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }

            System.out.println("=====================================");
        }
    }

    /**
     * 判断文件或目录
     * @throws IOException
     */
    @Test
    public void ListStatus() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            // 文件
            if(fileStatus.isFile()){

                System.out.println("文件"+fileStatus.getPath().getName());
            }else{
                System.out.println(fileStatus.getPath());
                System.out.println("目录"+fileStatus.getPath().getName());
            }
        }
    }


    @Test
    public void test01() throws IOException {
        outputAllFile("/");
    }

    /**
     * 递归查找遍历目录
     * @param path 目录路径
     * @throws IOException
     */
    public void outputAllFile(String path) throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path(path));
        for (FileStatus fileStatus : fileStatuses) {
            if(fileStatus.isFile()){
                System.out.println("文件:"+fileStatus.getPath().getName());
            }else{
                System.out.println("目录:"+fileStatus.getPath().getName());
                outputAllFile(fileStatus.getPath().toString());
            }
        }

    }

    /**
     * 文件上传
     * @throws Exception
     */
    @Test
    public void upFile() throws Exception {
        // 流对像的创建
        FileInputStream fis=new FileInputStream("D:/hadooptestfile/fa");
        // create 对应文件输出流
        FSDataOutputStream fsDataOutputStream = fs.create(new Path("/input/fa"));

        // 数据流的对拷
        byte[]bytes=new byte[1024];
        int len=0;
        while((len=fis.read(bytes))!=-1){
            fsDataOutputStream.write(bytes,0,len);
        }

        IOUtils.closeStream(fsDataOutputStream);
        IOUtils.closeStream(fis);
    }

    /**
     * 文件的下载
     * @throws Exception
     */
    @Test
    public void download() throws Exception {
        // 创建流对象
        FSDataInputStream fis = fs.open(new Path("/input/newage"));
        FileOutputStream fos=new FileOutputStream("D:/hadooptestfile/age");
       // 流的对拷
        byte[]bytes= new byte[1024];
        int len=0;
        while((len=fis.read(bytes))!=-1){
            fos.write(bytes,0,len);
        }
        // 关闭流 先开后关
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
    }

    /**
     * 定位下载文件
     * @throws Exception
     */
    @Test
    public void downloadFile()throws Exception{
        // 创建流对象
         FSDataInputStream fis = fs.open(new Path("/input/newage"));

        FileOutputStream fos=new FileOutputStream("D:/hadooptestfile/age1");
        // 文件的对拷

        // 定位流读取的位置
        fis.seek(1);
        IOUtils.copyBytes(fis,fos, configuration);
        // 关闭流
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
    }
    @Test
    public void downloadFile1()throws Exception{
        // 创建流对象
        FSDataInputStream fis = fs.open(new Path("/input/newage"));
        FileOutputStream fos = new FileOutputStream("D:/hadooptestfile/age2");
        // 流的对拷
        IOUtils.copyBytes(fis,fos,configuration);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);

    }
}
