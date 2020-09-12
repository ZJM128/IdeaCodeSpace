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

public class CompressionCodecTest {
    public static void main(String[] args) throws Exception {
        //compress("D:\\compresscodes\\JaneEyre2.txt","org.apache.hadoop.io.compress.GzipCodec");

       deCompressCode("D:/compresscodes/JaneEyre2.txt.gz");
    }

    public static void compress(String path,String code) throws Exception {
        FileInputStream fis=new FileInputStream(path);

        CompressionCodecFactory factory=new CompressionCodecFactory(new Configuration());
        final CompressionCodec codec = factory.getCodecByName(code);
        FileOutputStream fos=new FileOutputStream(path+codec.getDefaultExtension());

        final CompressionOutputStream outputStream = codec.createOutputStream(fos);

        IOUtils.copyBytes(fis,outputStream,new Configuration());

        IOUtils.closeStream(fos);
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(fis);

    }

    public static void deCompressCode(String path) throws Exception {

        CompressionCodecFactory factory=new CompressionCodecFactory(new Configuration());
        final CompressionCodec codec = factory.getCodec(new Path(path));
        if(codec==null){
            throw new RuntimeException("该文件不可解压");
        }

        FileOutputStream fileOutputStream = new FileOutputStream(new File(path).getParent()+"/bb.txt");

        FileInputStream fileInputStream=new FileInputStream(path);
        final CompressionInputStream inputStream = codec.createInputStream(fileInputStream);
        IOUtils.copyBytes(inputStream, fileOutputStream,new Configuration());

        IOUtils.closeStream(fileOutputStream);
        IOUtils.closeStream(fileInputStream);
        IOUtils.closeStream(inputStream);

    }
}
