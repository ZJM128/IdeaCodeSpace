package day18.exer;

import org.junit.Test;

public class Genericity<K> {

    /**
     * 泛型方法
     * @param t
     * @param <T>
     * @return
     */
    public <T>T get(T t){
        return t;
    }

    public <K> K get1(){
       return null;
    }
    @Test
    public void test01(){
        Integer integer = get(2);
        System.out.println(integer);
        System.out.println("================================");

        Genericity<Integer> genericity=new Genericity<>();
        Object genericity1 = genericity.get1();
        System.out.println(genericity1);
    }
}
