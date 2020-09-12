package day25.train;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Test01 {
    public static void main(String[] args) {
        SubA subA = new SubA();
        System.out.println(subA.getType());
        SubB subB=new SubB();
        System.out.println(subB.getType());
        try {
           Class aClass=Class.forName("day26.train.SubA");
            Type genericSuperclass = aClass.getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] types = parameterizedType.getActualTypeArguments();
            for (Type type : types) {
                System.out.println(type);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
abstract class Base<T>{
    private Class type;
    public Base(){
        try {
            Class<? extends Base> aClass = this.getClass();
            Type genericSuperclass = aClass.getGenericSuperclass();
            ParameterizedType pt= (ParameterizedType) genericSuperclass;
            type= (Class) pt.getActualTypeArguments()[0];
        }catch (Exception e){
            type=Object.class;
        }



    }
    public Class getType(){
        return type;
    }
}
class SubA extends Base<String>{

}
class  SubB extends Base{

}