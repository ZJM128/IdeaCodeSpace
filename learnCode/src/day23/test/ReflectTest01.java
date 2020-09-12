package day23.test;

import java.lang.reflect.*;

public class ReflectTest01 {
    public static void main(String[] args) {
        //test01();
       /* try {
            methodTest();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //fieldTest();
        //constructorTest();
       // initConstructor();
        //useField();
        useMethod();
    }
    private static void useMethod(){
        try {
            Class<?> aClass = Class.forName("day23.test.Student");

            // 初始化对象,利用有参的构造器
            Constructor<?> constructor = aClass.getDeclaredConstructor(int.class);
            // 私有的构造器需要暴力破解
            constructor.setAccessible(true);
            Object o = constructor.newInstance(123);
            // 调用方法
            Method eat = aClass.getMethod("eat", String.class);
            eat.invoke(o,"李白");
            // 私用的方法需要暴力破解
            Method eat1 = aClass.getDeclaredMethod("eat");
            eat1.setAccessible(true);
            eat1.invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void useField(){
        Student student=new Student();
        // 获取student对象的Class对象
        Class<?>aClass=student.getClass();

        try {
            Object o = aClass.newInstance();
            Field studentId = aClass.getField("studentId");
            studentId.set(o,12);
            System.out.println(o);

           // System.out.println("--------------任意属性------------------");
           /* // 缺省的
            Field height = aClass.getDeclaredField("height");
            height.set(o,169);
            // 保护的
            Field parent = aClass.getDeclaredField("parent");
            parent.set(o,"good");
            // 私有的
            Field school = aClass.getDeclaredField("school");
            school.setAccessible(true);
            school.set(o,"hello");
            System.out.println(o);*/

            System.out.println("-----------------------遍历------------------------");
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
               String modifier= Modifier.toString(declaredField.getModifiers());
                Class<?> type = declaredField.getType();
               // System.out.println(type==int.class);
                if("private".equals(modifier)){
                    declaredField.setAccessible(true);
                 }
                if(type==int.class){
                    declaredField.set(o,23);
                }
                if(type==String.class){
                    declaredField.set(o,"李娜");
                }

            }

            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  static void initConstructor(){
        Class<Student>studentClass= Student.class;
        try {
            Student student = studentClass.newInstance();// Class的newInstance()初始化无参的构造器
            System.out.println(student);
            System.out.println("-------------------------------------");
           // 获取有参的构造器 需要显示声明对象
            Constructor<Student> constructor = studentClass.getConstructor(int.class, String.class);
            Student libai = constructor.newInstance(12, "李白");
            System.out.println(libai);
            // 是有的构造器
            System.out.println("------------------------------------------");
            Constructor<Student> declaredConstructor = studentClass.getDeclaredConstructor(int.class);
            // 使用构造器调用需要暴力破解
            declaredConstructor.setAccessible(true);
            Student student1 = declaredConstructor.newInstance(21);
            System.out.println(student1);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void constructorTest(){
      try {
          Class<?> aClass= Class.forName("day23.test.Student");
          // 获取本类公有的构造器
         /*Constructor<?>constructor= aClass.getConstructor();
          System.out.println("构造器名称:"+constructor.getName());
          System.out.println("构造器的权限修饰符"+Modifier.toString(constructor.getModifiers()));
          Parameter[] parameters = constructor.getParameters();
          System.out.println("--------参数列表--------");
          for (Parameter parameter : parameters) {
              System.out.println(parameter);
          }
        */
          // 获取任意的构造器
          /*Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(int.class, String.class);
          System.out.println(declaredConstructor.getName());
          System.out.println(Modifier.toString(declaredConstructor.getModifiers()));
          System.out.println("-----参数列表-----");
          Parameter[] parameters = declaredConstructor.getParameters();
          for (Parameter parameter : parameters) {
              System.out.println(parameter);
          }
          System.out.println("--------参数的类型-----------");
          Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
          for (Class<?> parameterType : parameterTypes) {
              System.out.println(parameterType);
          }*/

          // 获取本类所有公有的构造器
          /*Constructor<?>[] constructors = aClass.getConstructors();
          for (Constructor<?> constructor : constructors) {
              System.out.println(constructor.getName());
              System.out.println(Modifier.toString(constructor.getModifiers()));
              Parameter[] parameters = constructor.getParameters();
              System.out.println("------------参数列表---------");
              for (Parameter parameter : parameters) {
                  System.out.println(parameter);
              }
              System.out.println("-----------------------------");
          }*/

          // 获取本类所有的构造器
          Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
          for (Constructor<?> constructor : declaredConstructors) {
              System.out.println(constructor.getName());
              System.out.println(Modifier.toString(constructor.getModifiers()));
              System.out.println("------------参数列表----------------");
              Parameter[] parameters = constructor.getParameters();
              for (Parameter parameter : parameters) {
                  System.out.println(parameter);
              }
              System.out.println("-------------------------");
          }

      } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void methodTest() throws Exception {
        // 类加载的过程中 会生成一个Class对对象来记录类型的信息
        Class<?> aClass = Class.forName("day23.test.Student");
        // 可以获取本类和父类的公用的方法
        //Method method= aClass.getMethod("sleep");// 无参数的
        // System.out.println(method.getName());
        // Method eat = aClass.getMethod("eat", String.class);
        //  System.out.println(eat);

        // 可以获取本类的所有的方法,不能获取父类的方法
       // Method method=aClass.getDeclaredMethod("eat");
       // System.out.println(method);
        //Method sleep = aClass.getDeclaredMethod("sleep", String.class);
        //System.out.println(sleep);

        // 获取本类和父类的全部的公有的方法
       /* Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            System.out.println("------------参数列表--------------");
            for (Parameter parameter : parameters) {
                System.out.println("参数名"+parameter.getName());
                System.out.println("参数的类型"+parameter.getType());
                System.out.println(parameter);
            }
            System.out.println("---------------------------");
            System.out.println("方法名"+method.getName());
            System.out.println("返回值类型"+method.getReturnType());
            System.out.println("权限修饰符"+Modifier.toString(method.getModifiers()));
        }*/

       // 获取本类的所有的方法
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter);
            }

            System.out.println(method.getName());
            System.out.println(method.getReturnType());
            System.out.println(Modifier.toString(method.getModifiers()));

            System.out.println();
        }

    }

    private static void fieldTest() {

        try {
            Class<?> aClass = Class.forName("day23.test.Student");
            Field studentId = aClass.getField("studentId");// 可以获取本类的公有属性和父类的公有属性
            // Field age = aClass.getField("age");
            //Field height = aClass.getField("height");
            // System.out.println(height);
            // System.out.println(studentId);
            // System.out.println(age);

           /* Field school = aClass.getDeclaredField("school");// 可以获取本类的所有的属性,但获取不来父类的
            Field age = aClass.getDeclaredField("age");//
            System.out.println(school);
            System.out.println(age);*/

         /* Field[]fields= aClass.getFields();// 获取到本类所有的公有属性 包含父类的
            for (Field field : fields) {
                System.out.println("属性名:"+field.getName());
                System.out.println("属性数据类型:"+field.getType());
                System.out.println("权限修饰符:"+Modifier.toString(field.getModifiers()));

            }*/

            /*Field[] declaredFields = aClass.getDeclaredFields();// 可以获取本类所有的属性,不包括父类的
            for (Field field : declaredFields) {
                System.out.println("属性名:"+field.getName());
                System.out.println("属性数据类型:"+field.getType());
                System.out.println("属性的权限修饰符:"+Modifier.toString(field.getModifiers()));
                System.out.println("----------------------------------------");
            }*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test01() {
        Class<String> stringClass = String.class;
        Field[] declaredFields = stringClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("属性名" + field.getName());// 属性名
            System.out.println("数据类型:" + field.getType());// 属性数据类型
            System.out.println("权限修饰符:" + Modifier.toString(field.getModifiers())); // 权限修饰符
        }
    }
}
