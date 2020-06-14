package day16.review01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test01 {
    public static void main(String[] args) {
        // 获取牌
        List list = dealCard();
        getCard(list);

    }
    private static List dealCard(){
        String[] color={"♠","♥","♣","♦"};
        String[]numberCard=new String[13];
        numberCard[0]="A";
        for(int i=1;i<10;i++){
            numberCard[i]=(i+1)+"";
        }
        numberCard[10]="J";
        numberCard[11]="Q";
        numberCard[12]="K";
        List list=new ArrayList();
        for(int i=0;i<color.length;i++){
            for(int j=0;j<numberCard.length;j++){
                list.add(color[i]+numberCard[j]);
            }
        }
        list.add("大王");
        list.add("小王");

        for (int i = 0; i < list.size(); i++) {
            if(i!=0 && i%13==0){
                System.out.println();
            }
            System.out.print(list.get(i)+"\t");
        }

        return list;
    }

    private static void getCard(List list){
        System.out.println();
        System.out.println("发牌");
       List list1=new ArrayList();
       List list2=new ArrayList();
       List list3=new ArrayList();
       List list4=new ArrayList();

        Random random=new Random();
        //System.out.println(random.nextInt(list.size()));
        for(int i=0;i<11;i++){
            list1.add(list.remove(random.nextInt(list.size())));
        }
        for(int i=0;i<11;i++){
            list2.add(list.remove(random.nextInt(list.size())));
        }
        for(int i=0;i<11;i++){
            list3.add(list.remove(random.nextInt(list.size())));
        }
        for(int i=0;i<11;i++){
            list4.add(list.remove(random.nextInt(list.size())));
        }
        System.out.println("第一个人"+list1);
        System.out.println("第二个人"+list2);
        System.out.println("第三个人"+list3);
        System.out.println("第四个人"+list4);
        System.out.println("剩余"+list);
    }
}
