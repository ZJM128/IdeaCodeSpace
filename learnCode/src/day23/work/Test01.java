package day23.work;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/*
*@Description:
*@author:zhijm
*
*@Date:2020/6/23 21:15
*/
public class Test01 {
    public static void main(String[] args) {
        //editFile();
       // allInfo();
       // getName();
        CompareTo1();
    }
    private static void CompareTo1(){
        try {
            Class<?> aClass = Class.forName("day23.work.AtguiiguDemo");
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
            Object o = declaredConstructor.newInstance("class2");
            Object o2 = declaredConstructor.newInstance("class1");
            Method compareTo = aClass.getDeclaredMethod("compareTo",aClass);
            Object invoke = compareTo.invoke(o, o2);
            System.out.println(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void getName(){
        try {
            Class<?> aClass = Class.forName("day23.work.AtguiiguDemo");
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
            Object o = declaredConstructor.newInstance("李娜");
            Field className = aClass.getDeclaredField("className");
            className.set(o,"白居易");
            Object o1 = className.get(o);
            System.out.println(o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void editFile(){
        try {
            Class<?> aClass = Class.forName("day23.work.AtguiiguDemo");
            // 无参构造器
            Object o = aClass.newInstance();
            Field school = aClass.getDeclaredField("school");
            school.setAccessible(true);
            school.set(o,"尚硅谷大学");
            Object o1 = school.get(o);
            System.out.println(o1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void allInfo() {
        try {
            Class<?>aClass=Class.forName("day23.work.AtguiiguDemo");
            /*并获取它的所有信息包括类加载器、包名、类名、父类、父接口、属性、构造器、方法们等*/
            System.out.println("包名:"+aClass.getPackage());
            System.out.println("类名:"+aClass.getName());
            System.out.println("类名:"+aClass.getSimpleName());
            System.out.println("父类:"+aClass.getSuperclass());
            Class<?>[] interfaces = aClass.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                System.out.println("接口:"+anInterface.getName());
            }
            // public修饰的属性
            System.out.println("-------------------public修饰的属性--------------------");
           Field field= aClass.getField("className");
            System.out.println("属性名"+field.getName());
            System.out.println("类型"+field.getType());
            System.out.println("权限修饰符"+ Modifier.toString(field.getModifiers()));
            System.out.println("----------------public修饰所有的属性 包括父类的-------------------");
            Field[] fields = aClass.getFields();
            for (Field field1 : fields) {
                System.out.println("属性名"+field1.getName());
                System.out.println("类型"+field1.getType());
                System.out.println("权限修饰符"+ Modifier.toString(field1.getModifiers()));
            }
            System.out.println("---------------------本类任意的的属性-----------------------------------");
            Field school = aClass.getDeclaredField("school");
            System.out.println("属性名"+school.getName());
            System.out.println("类型"+school.getType());
            System.out.println("权限修饰符"+ Modifier.toString(school.getModifiers()));
            System.out.println("---------------------本类全部的的属性-----------------------------------");
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                System.out.println("名称"+declaredField.getName());
                System.out.println("类型"+declaredField.getType());
                System.out.println("权限"+Modifier.toString(declaredField.getModifiers()));
            }

            System.out.println("----------------------public构造器---------- -----------------------");
            Constructor<?> constructor = aClass.getConstructor();
            System.out.println("名称:"+constructor.getName());
            System.out.println("权限:"+Modifier.toString(constructor.getModifiers()));
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("参数"+parameterType);
            }
            System.out.println("----------------------所有的public构造器---------- -----------------------");
            Constructor<?>[] constructors = aClass.getConstructors();
            for (Constructor<?> constructor1 : constructors) {
                System.out.println("名称:"+constructor1.getName());
                System.out.println("权限:"+Modifier.toString(constructor1.getModifiers()));
                Class<?>[] parameterTypes1 = constructor1.getParameterTypes();
                for (Class<?> parameterType : parameterTypes1) {
                    System.out.println("参数"+parameterType);
                }
            }
            System.out.println("----------------------任意的构造器---------- -----------------------");
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            System.out.println("名称"+declaredConstructor.getName());
            System.out.println("权限"+Modifier.toString(declaredConstructor.getModifiers()));
            Class<?>[] parameterTypes1 = declaredConstructor.getParameterTypes();
            for (Class<?> aClass1 : parameterTypes1) {
                System.out.println(aClass1);
            }
            System.out.println("----------------------所有的构造器---------- -----------------------");
            Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
            for (Constructor<?> declaredConstructor1 : declaredConstructors) {
                System.out.println("名称"+declaredConstructor1.getName());
                System.out.println("权限"+Modifier.toString(declaredConstructor1.getModifiers()));
                Class<?>[] parameterTypes2 = declaredConstructor1.getParameterTypes();
                for (Class<?> aClass1 : parameterTypes2) {
                    System.out.println(aClass1);
                }
            }
            System.out.println("------------------------------public方法---------------------------------");
            Method method = aClass.getMethod("getClassName");
            System.out.println("名称"+method.getName());
            System.out.println("返回值类型"+method.getReturnType());
            System.out.println("权限修饰符"+Modifier.toString(method.getModifiers()));
            Class<?>[] parameterTypes2 = method.getParameterTypes();
            for (Class<?> aClass1 : parameterTypes2) {
                System.out.println("参数列表"+aClass1);
            }

            System.out.println("------------------------------所有public方法---------------------------------");
            Method[] methods = aClass.getMethods();
            for (Method method1 : methods) {
                System.out.println("名称"+method1.getName());
                System.out.println("返回值类型"+method1.getReturnType());
                System.out.println("权限修饰符"+Modifier.toString(method1.getModifiers()));
                Class<?>[] parameterTypes3 = method1.getParameterTypes();
                for (Class<?> aClass1 : parameterTypes3) {
                    System.out.println("参数列表"+aClass1);
                }
                System.out.println("=--------------------------------------------");
            }
            System.out.println("------------------------------获取任意方法---------------------------------");
            Method setSchool = aClass.getDeclaredMethod("setSchool",String.class);

            System.out.println("名称:"+setSchool.getName());
            System.out.println("返回值:"+setSchool.getReturnType());
            System.out.println("权限:"+Modifier.toString(setSchool.getModifiers()));

            System.out.println("------------------------------获取所有方法---------------------------------");
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                System.out.println("名称:"+declaredMethod.getName());
                System.out.println("返回值:"+declaredMethod.getReturnType());
                System.out.println("权限:"+Modifier.toString(declaredMethod.getModifiers()));

                Class<?>[] parameterTypes3 = declaredMethod.getParameterTypes();
                for (Class<?> aClass1 : parameterTypes3) {

                    System.out.println(aClass1);
                }
                System.out.println("--------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
