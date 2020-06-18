package day18.exer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Test06 {
    public static void main(String[] args) {
        char[]str=new char[62];
        int count=0;
        for(int i='a';i<='z';i++){
            str[count++]=(char)i;
        }
        for(int j='A';j<='Z';j++){
            str[count++]=(char)j;
        }

        for(int i = '0';i<='9';i++){
            str[count++]=(char)i;
        }

        Random random=new Random();
        List<String>list=new ArrayList<>();
       StringBuilder sb = new StringBuilder("");
        for(int i=0;i<10;i++){
            for(int j=0;j<6;j++){
                sb.append(str[random.nextInt(str.length)]);
            }
            list.add(sb.toString());
            sb=new StringBuilder();
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println("随机验证码:"+iterator.next());
        }

    }
}
