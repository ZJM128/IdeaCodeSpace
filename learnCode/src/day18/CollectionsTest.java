package day18;

import java.util.*;

/**
 * 1.请从键盘随机输入10个整数保存到List中，并按倒序、从大到小的顺序显示出来
 *
 * 2.请把学生名与考试分数录入到Map中，并按分数显示前三名成绩学员的名字。
 */
public class CollectionsTest {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private  static final Object OBJECT=new Object();
     public static  void test02(){
         Scanner scanner=new Scanner(System.in);
         Map<Student, Object> map=new TreeMap(new Comparator() {
             @Override
             public int compare(Object o1, Object o2) {
                 if(o1 instanceof Student && o2 instanceof Student){
                     if(((Student) o1).getScore()==((Student) o2).getScore()){
                         return ((Student) o1).getName().compareTo(((Student) o2).getName());
                     }else {
                         return -(((Student) o1).getScore()-((Student) o2).getScore());
                     }
                 }
                 return 0;
             }
         });
       //Map map=new HashMap();
         System.out.println("请输入5个学生的姓名和成绩");
         for(int i=0;i<5;i++){
             System.out.println("请输入第"+(i+1)+"个学生姓名和成绩");
             Student student =new Student(scanner.next(),scanner.nextInt());
             map.put(student,OBJECT);
         }
        scanner.close();
         System.out.println(map);
         Set<Student> set=map.keySet();
         Object[] objects = set.toArray();

         for(int i=0;i<3;i++){
             System.out.println(objects[i]);
         }

         System.out.println("=====================");
         Iterator<Student> iterator = set.iterator();
         int count=0;
         while(iterator.hasNext()){
             System.out.println(iterator.next());
             count++;
             if(count==3){
                 break;
             }
         }

     }
    public static void test01(){
        Scanner scanner=new Scanner(System.in);
        List list=new ArrayList();
        System.out.println("请输入10个整数");
        for(int i=0;i<10;i++){
            System.out.println("请输入第"+(i+1)+"个整数");
            list.add(scanner.nextInt());
        }
        scanner.close();
        Collections.sort(list);
        Collections.reverse(list);
        System.out.println(list);
    }
}
class Student{
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}