package com.atguigu.compressionCode;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CompressionCodeTest {
    public static void main(String[] args) throws Exception {
        //compress("D:\\compresscodes\\JaneEyre2.txt","org.apache.hadoop.io.compress.BZip2Codec");
       decompressCodec("D:/compresscodes/JaneEyre2.txt.bz2");
    }

    /**
     * 压缩
     * @param fileName
     * @param code
     * @throws Exception
     */
    public static  void compress(String fileName,String code) throws Exception {
        // 获取普通输入流
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        // 获取CompressionCodecFactory工厂
        CompressionCodecFactory factory=new CompressionCodecFactory(new Configuration());
        // 获取compress对象
        final CompressionCodec codec = factory.getCodecByName(code);
        // 创建普通输出流

        FileOutputStream outputStream=new FileOutputStream(new File(fileName+codec.getDefaultExtension()));
        // 获取压缩的输出流
        final CompressionOutputStream dos = codec.createOutputStream(outputStream);
        // 流的拷贝
        IOUtils.copyBytes(fileInputStream,dos,new Configuration());
        // 关闭流对象
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(dos);
        IOUtils.closeStream(fileInputStream);

    }

    /**
     * 文件的解压
     * @param path
     * @throws Exception
     */
    public static void decompressCodec(String path) throws Exception {
        // 获取CompressionCodeFactory 工厂
        CompressionCodecFactory factory=new CompressionCodecFactory(new Configuration());
        // 获取Compression对象
        final CompressionCodec codec = factory.getCodec(new Path(path));
        // 判断文件是否可以解压
        if(codec==null){
            throw new RuntimeException("该文件不可解压");
        }
        // 创建普通输入流
        FileInputStream fileInputStream=new FileInputStream(path);
        // 创建压缩输入流
        final CompressionInputStream inputStream = codec.createInputStream(fileInputStream);
        // 创建普通输出流
        FileOutputStream fileOutputStream=new FileOutputStream(new File(path).getParent()+"/gg.txt");
        // 流的对拷
        IOUtils.copyBytes(inputStream,fileOutputStream,new Configuration());
        // 流对象的关闭
        IOUtils.closeStream(fileOutputStream);
        IOUtils.closeStream(fileInputStream);
        IOUtils.closeStream(inputStream);
    }
}
