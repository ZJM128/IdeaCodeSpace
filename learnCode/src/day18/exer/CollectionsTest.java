package day18.exer;

import org.junit.Test;

import java.util.*;

public class CollectionsTest {
    @Test
    public void test01(){
        List<Integer>list = new ArrayList<>();
        list.add(1);
        list.add(12);
        list.add(13);
        list.add(16);

        System.out.println(list);
        // 反转
        Collections.reverse(list);
        System.out.println(list);
        // 随机打乱
        Collections.shuffle(list);
        System.out.println(list);
        // 排序
        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    public void test02(){
        List<Employee>list = new ArrayList<>();
        list.add(new Employee("李白",23));
        list.add(new Employee("白居易",22));
        list.add(new Employee("杜甫",21));
        list.add(new Employee("苏轼",26));
        for(Employee employee:list){
            System.out.println(employee);
        }

        // 要对里面的元素进行自定义排序
        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if(o1.getAge()==o2.getAge()){
                    return o1.getName().compareTo(o2.getName());
                }else{
                    return o1.getAge()-o2.getAge();
                }


            }
        });
        System.out.println("-------------------");

        for (Employee employee : list) {
            System.out.println(employee);
        }

        System.out.println("==========================");
        //  交换集合元素
        Collections.swap(list,2,3);

        for (Employee employee : list) {
            System.out.println(employee);
        }

    }
    @Test
    public void test03(){
        Collection<Integer> collections =new HashSet<>();
        collections.add(9);
        collections.add(2);
        collections.add(2);
        collections.add(6);
        Integer max = Collections.max(collections);
        System.out.println(max);

        Integer min = Collections.min(collections);
        System.out.println(min);
        System.out.println(Collections.frequency(collections, 6));
    }

    @Test
    public void test04(){
        List<Integer>list = new ArrayList<>();
        list.add(5);
        list.add(5);

        list.add(9);
        list.add(45);
        Integer []arr=new Integer[list.size()];
        List<Integer>list1=new ArrayList<>();
         list1 = Arrays.asList(arr);
        Collections.copy(list1,list);//IndexOutOfBoundsException: Source does not fit in dest
        System.out.println(list1);
    }
    @Test
    public void test05(){
        List list = new ArrayList();
        list.add("AA");
        list.add("AA");
        list.add("AA");
        list.add("BB");
        list.add("CC");

        Collections.replaceAll(list,"AA","666");
        System.out.println(list);
    }
}
class Employee{
    private String name;

    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}