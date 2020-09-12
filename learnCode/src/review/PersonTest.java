package review;


import org.junit.Test;

public class PersonTest {
    @Test
    public void test01(){
        Student student = new Student();
       student.setAge(12);
        int[]arr={};
        int[]ar1=new int[]{};
        System.out.println(arr.length);
        System.out.println(ar1.length);
        for (int i = 0; i < ar1.length; i++) {
            System.out.println(i);
        };

    }

    private int add(int...a){
        return 0;
    }
}
