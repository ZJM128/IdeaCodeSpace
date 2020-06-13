package day09.com.atguigu.work;

public class Test {
    @org.junit.Test
    public void test01(){
        PC pc=new PC();
        pc.setCpId("01");
        pc.setCPU("i7");
        pc.setDisk("1T");
        pc.setMemory("32G");
        System.out.println(pc.getDetails());
    }

}
