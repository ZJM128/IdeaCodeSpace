package day19.homework;

import java.io.File;
import java.io.FileFilter;

public class Test08 {
    public static void main(String[] args) {
        String path="D:\\testoi";
       // getAllFile(path);

        getAllFile2(new File(path));
    }
    private static void getAllFile(String filePath){
        File file=new File(filePath);
        if(file!=null){
            if(file.isDirectory()){
                File[] files=file.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.getName().contains(".java");
                    }
                });
                for (File file1 : files) {
                    System.out.println(file1.getName());
                }

            }
        }
    }

    private static void getAllFile2(File file){
        if(file!=null){
            if(file.isDirectory()){
                File[] files=file.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.getName().contains(".java")||pathname.isDirectory();
                    }
                });
                for (File file1 : files) {
                    if(file1.isDirectory()){
                        getAllFile2(file1);
                    }else {
                        System.out.println(file1.getName());
                    }
                }

            }
        }
    }

}
