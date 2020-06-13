package day14.review;

import org.junit.Test;

public class StringBufferTest {
    @Test
    public void test(){
        String text="";
        long startTime=0L;
        long endTime=0;
        StringBuffer buffer=new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
        startTime=System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer的执行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder的执行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text = text + i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String的执行时间：" + (endTime - startTime));
    }
    @Test
    public void test01(){
        StringBuffer stringBuffer = new StringBuffer("123456");
        stringBuffer.append("李白")
                .append(true)
                .append(123);
        stringBuffer.append("34");
        System.out.println(stringBuffer);
        stringBuffer.insert(2,"GG");
        System.out.println(stringBuffer);
        stringBuffer.replace(3,4,"FF");
        System.out.println(stringBuffer);
        System.out.println(stringBuffer.indexOf("1"));

        System.out.println(stringBuffer.indexOf("李白", 0));

        System.out.println(stringBuffer.lastIndexOf("李白", stringBuffer.length()));

        System.out.println(stringBuffer.substring(1, 3));

        System.out.println(stringBuffer.delete(1, 3));
        System.out.println(stringBuffer.deleteCharAt(0));

    }
}
