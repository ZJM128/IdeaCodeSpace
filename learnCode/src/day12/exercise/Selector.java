package day12.exercise;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public interface Selector {
    boolean hasNext();
    Object next();
}

interface Touchable{
    Selector select();
}

class MyArrayList implements Touchable{
    private Object[]all=new Object[2];
    private int total;

    public MyArrayList(){}

    public Object[] getAll() {
        return all;
    }

    public void setAll(Object[] all) {
        this.all = all;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void add(Object element){
        if(total<all.length) {
            all[total] = element;
            total++;
        }else{
            Object[]newArray=new Object[all.length*2];
            for(int i=0;i<all.length;i++){
                newArray[i]=all[i];
            }
            all=newArray;
            all[total]=element;
            total++;
        }
    }

    public void remove(int index){
        if(index<0 || index>=total){
            System.out.println("没有该元素");
            return ;
        }
        for(int i=index;i<total;i++){
            all[i]=all[i+1];
        }
        all[total]=null;
        total--;
    }
    public void set(int index,Object value){
        if(index<0 || index>=total){
            System.out.println("没有该元素");
            return ;
        }
        all[index]=value;

    }

    public Object get(int index){
        if(index<0 || index>=total){
            System.out.println("没有该元素");
            return null;
        }
        return all[index];
    }

    class MySelector implements Selector{
        int cursor=0;

        @Override
        public boolean hasNext() {
            return cursor!=total;
        }

        @Override
        public Object next() {
            return all[cursor++];
        }
    }

    @Override
    public Selector select() {
      return new MySelector();
    }
}

class  Test06_01{

   public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(1);
        list.set(1,"李白");
        Object[] all = list.getAll();
        Selector select = list.select();
        while(select.hasNext()){
            System.out.println(select.next());
        }

    }
}

class Test06_2{
    static MyArrayList list;

    @BeforeClass
    public static  void init(){
        list=new MyArrayList();
        list.add("1");
        list.add(6);
        list.add(9);

    }
    @Before
    public void before(){
        System.out.println("该测试方法开始前list中的数据如下：");
        Selector select = list.select();
        while (select.hasNext()){
            System.out.println(select.next());
        }
    }
    @After
    public void after(){
        System.out.println("该测试方法结束后list中的数据如下：");
        Selector select = list.select();
        while (select.hasNext()){
            System.out.println(select.next());
        }
    }

   @Test
    public void testAdd(){
        System.out.println("打印“现在测试的是testAdd()方法");
        list.add(56);

    }

    @Test
    public void testRemove(){
        System.out.println("打印“现在测试的是testRemove()方法");
        list.remove(1);
    }

    @Test
    public void testSet(){
        System.out.println("现在测试的是testSet()方法");
        list.set(1,9999);
    }

    @Test
    public void testGet(){
        System.out.println("现在测试的是testGet()方法");
        System.out.println(list.get(1));

    }
}