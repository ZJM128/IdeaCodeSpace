package day19.homework;

import java.io.File;

public class Test09 {
    public static void main(String[] args) {
        String path="D:\\testoi";
        System.out.println(countLenght(new File(path)));

    }
    private static long countLenght(File file){
        if(file!=null){
            if(file.isDirectory()){
                long count=0;
                File[] files = file.listFiles();
                for (File file1 : files) {
                    count+=countLenght(file1);
                }
                return count;
            }else{
                return file.length();
            }
        }
        return 0;
    }
}
