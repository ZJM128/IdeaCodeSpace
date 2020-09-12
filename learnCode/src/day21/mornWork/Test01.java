package day21.mornWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 单词频率
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader=new BufferedReader(
                new FileReader("D:\\IOtest\\java.txt"));
        String string = null;
        StringBuilder builder=new StringBuilder();
        while ((string=bufferedReader.readLine())!=null) {
            builder.append(string+" ");
        }
        bufferedReader.close();
        String s=builder.toString();
        String[] split = s.split(" ");
        Map<String,Integer>map=new HashMap<>();
        for(String string1: split){
            /*Integer count = map.get(string1);
            if(count==null){
                map.put(string1,1);
            }else{
                map.put(string1,count+1);
            }*/

            if(!map.containsKey(string1)){
                map.put(string1,1);
            }else{
                map.put(string1,map.get(string1)+1);
            }
        }

        BufferedWriter bw=
                new BufferedWriter(new FileWriter("D:\\IOtest\\countJava.txt"));
       Set<Map.Entry<String,Integer>>entrySet=map.entrySet();
       for(Map.Entry<String,Integer>entry:entrySet){
           bw.write(entry.getKey()+"="+entry.getValue());
           bw.newLine();
       }
       bw.close();
    }

}
