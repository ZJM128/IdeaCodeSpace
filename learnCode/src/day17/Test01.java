package day17;

import java.util.HashMap;import java.util.Map;
import java.util.Set;

public class Test01 {
    public static void main(String[] args) {
        String str = "Your future depends on your dreams, so go to sleep.";
        byte[]bytes=str.replace(" ","").getBytes();
        HashMap<Byte,Integer> count = count(bytes);
        Set<Map.Entry<Byte, Integer>> entries = count.entrySet();
        for (Map.Entry<Byte,Integer>entry:entries){
            Byte key = entry.getKey();
            System.out.println((char)((int)key)+"="+entry.getValue());
        }
    }

    public static HashMap count(byte[]bytes){
        HashMap<Byte,Integer>map=new HashMap<>();
        for (Byte aByte : bytes) {
            Integer count=map.get(aByte);
            if(count==null) {
                map.put(aByte, 1);
            }else{
                map.put(aByte,count+1);
            }
        }
        return map;
    }
}
