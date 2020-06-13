package day12.exercise;

import java.util.Comparator;

/**
 * 局部内部类
 */
public class InnerClassTest02 {
    public static void main(String[] args) {
        show();
    }
    public static void show(){
        class inner{

        }
        class AA{
            private String name;
        }

        AA aa=new AA();
        aa.name="李白";
        System.out.println(aa.name);
    }

    /**
     * 匿名内部类
     * @return
     */
    public Comparator getComparator1(){
        Comparator comparator=new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
           return comparator;
    }

    public Comparator getComparator2(){
        return (o1,o2)->{ return 0; };
    }
}
