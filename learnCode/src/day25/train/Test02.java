package day25.train;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Test02 {
    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("day26.train.User");
            Table table = aClass.getAnnotation(Table.class);
            String value = table.value();
            String name = aClass.getSimpleName();
            System.out.println(name+"类对应的数据库表"+value);

            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                String fieldName=field.getName();
                Column annotation = field.getAnnotation(Column.class);
                String columnName=annotation.name();
                String type=annotation.type();
                System.out.println(fieldName+"属性对应的数据库表字段:"+columnName+"  类型:"+type);

            }
            System.out.println("-----------------------------------------------");
            Annotation[] annotations = aClass.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation.annotationType());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
