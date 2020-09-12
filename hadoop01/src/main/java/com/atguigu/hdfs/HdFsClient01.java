package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdFsClient01 {
    /**
     * 创建文件
     *
     * @throws Exception
     */
    @Test
    public void mkdirFile() throws Exception {
        // 创建文件系统对象
        URI uri = new URI("hdfs://hadoop102:9820");
        Configuration configuration = new Configuration();
        String user = "atguigu";
        FileSystem fs = FileSystem.get(uri, configuration, user);
        // 操作文件系统对象
        boolean flag = fs.mkdirs(new Path("/input"));
        if (flag) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
        // 关闭文件系统对象
        fs.close();
    }

    /**
     * 上传文件
     *
     * @throws Exception
     */
    @Test
    public void copyFromLocalFileTest() throws Exception {
        // 创建文件系统对象
        URI uri = new URI("hdfs://hadoop102:9820");
        Configuration configuration = new Configuration();
        String user = "atguigu";
        FileSystem fileSystem = FileSystem.get(uri, configuration, user);
        // 对文件系统进行操作

        /**
         * 第一个参数:上传后是否需要删除
         * 第二个参数:如果目标路径已经存在文件是否需要覆盖
         * 第三个参数:源文件路径
         * 第四个参数:目标文件路径
         */
        fileSystem.copyFromLocalFile(false, false,
                new Path("D:\\hadooptestfile\\age"),
                new Path("/output/age3"));
        // 关闭文件系统对象
        fileSystem.close();

    }

    /**
     * 下载文件
     *
     * @throws Exception
     */
    @Test
    public void copyToLocalFileTest() throws Exception {
        URI uri = new URI("hdfs://hadoop102:9820");
        Configuration configuration = new Configuration();
        String user = "atguigu";
        FileSystem fileSystem = FileSystem.get(uri, configuration, user);
        /**
         * 第一个参数:下载后是否需要删除源文件
         * 第二个参数:源文件目录
         * 第三个:目标文件目录
         * 第四个参数:是否需要进行文件的校验 false:代表需要校验 true:代表不需要校验
         */
        fileSystem.copyToLocalFile(false, new Path("/output/age")
                , new Path("D:\\hadooptestfile\\ageNumber1"),
                true);
        // 关闭文件系统对象
    }

    /**
     * 删除目录
     *
     * @throws Exception
     */
    public void delFile() throws Exception {
        // 创建文件对象
        URI uri = new URI("hdfs://hadoop102:9820");
        Configuration con = new Configuration();
        String user = "atguigu";
        FileSystem fileSystem = FileSystem.get(uri, con, user);
        // 操作文件系统
        // 第一个参数为源文件的路径,第二个参数:是否递归删除,true可以删除不为空的目录
        fileSystem.delete(new Path(""), false);

        // 释放文件系统对象
        fileSystem.close();
    }

    /**
     * 文件重命名或移动
     *
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void renameTest() throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("hdfs://hadoop102:9820");
        Configuration configuration = new Configuration();
        String user = "atguigu";

        // 获取文件系统对象
        FileSystem fileSystem = FileSystem.get(uri, configuration, user);

        // 操作文件系统
        boolean flag = fileSystem.rename(new Path("/output/age1"), new Path("/input/age"));
        if (flag) {
            System.out.println("重命名成功");
        } else {
            System.out.println("重命名失败");
        }
        // 关闭对象
        fileSystem.close();
    }

    /**
     * 获取根路径的全部文件的信息
     * @throws Exception
     */
    @Test
    public void listFileTest() throws Exception {
        URI ursi = new URI("hdfs://hadoop102:9820");
        Configuration configuration = new Configuration();
        String user = "atguigu";
        FileSystem fileSystem = FileSystem.get(ursi, configuration, user);
        // 操作系统对象
        RemoteIterator<LocatedFileStatus> list = fileSystem.listFiles(new Path("/"), true);
        while (list.hasNext()) {
            LocatedFileStatus files = list.next();
            // 文件名
            System.out.println(files.getPath().getName());
            // 长度
            System.out.println(files.getLen());
            // 权限
            System.out.println(files.getPermission());
            // 分组
            System.out.println(files.getGroup());
            // 获取存储的块的信息
            BlockLocation[] blockLocations = files.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                final String[] hosts = blockLocation.getHosts();
                // 获取块存储主机结点
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.out.println("=================================================================");
        }

    }

    /**
     * 判断是文件或者目录,不能递归
     * @throws Exception
     */
    @Test
    public void listStatusTest() throws Exception {
        URI uri=new URI("hdfs://hadoop102:9820");
        Configuration configuration= new Configuration();
        String user="atguigu" ;
        final FileSystem fileSystem = FileSystem.get(uri, configuration, user);

        final FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/"));

        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isFile()){
                System.out.println("文件:"+fileStatus.getPath().getName());
            }else{
                System.out.println("目录:"+fileStatus.getPath().getName());
            }
            System.out.println("=================================");
        }
    }

    /**
     * 递归遍历目录
     * @throws Exception
     */
    @Test
   public void test11() throws Exception {
        URI uri=new URI("hdfs://hadoop102:9820");
        Configuration configuration=new Configuration();
        String user="atguigu";
       final FileSystem fileSystem = FileSystem.get(uri, configuration, user);
       listStatusAllTest(fileSystem,"/");
   }
    public void listStatusAllTest(FileSystem fileSystem,String path) throws Exception {
        final FileStatus[] fileStatuses = fileSystem.listStatus(new Path(path));
        for (FileStatus fileStatus : fileStatuses) {
            if(fileStatus.isFile()){
                System.out.println("文件:"+fileStatus.getPath().getName());
            }else{
                System.out.println("目录:"+fileStatus.getPath().getName());
                listStatusAllTest(fileSystem,fileStatus.getPath().toString());
            }
        }
    }

    /**
     * io流上传文件
     * @throws Exception
     */
    @Test
    public void putFile() throws Exception {
        URI uri=new URI("hdfs://hadoop102:9820");
        Configuration configuration=new Configuration();
        String user="atguigu";
        final FileSystem fileSystem = FileSystem.get(uri, configuration, user);
        // 获取流对象
        FileInputStream fis=new FileInputStream("D:\\hadooptestfile\\age");
        final FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/input/age12"));
        byte[]bytes=new byte[1024];
        int len=0;
        while ((len=fis.read(bytes))!=-1){
            fsDataOutputStream.write(bytes,0,len);
        }
        IOUtils.closeStream(fsDataOutputStream);
        IOUtils.closeStream(fis);

        System.out.println("上传成功");
    }


    @Test
    public void downLocalFile() throws Exception {
        URI uri=new URI("hdfs://hadoop102:9820");
        Configuration configuration=new Configuration();
        String user="atguigu";

        final FileSystem fileSystem = FileSystem.get(uri, configuration, user);

        final FSDataInputStream inputStream = fileSystem.open(new Path("/input/age12"));
        FileOutputStream outputStream = new FileOutputStream("D:\\hadooptestfile\\age10");
        IOUtils.copyBytes(inputStream,outputStream,configuration);
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(inputStream);

    }


}


