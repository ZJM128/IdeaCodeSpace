package day14.review;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class OtherTest {

    @Test
    public void test01(){
        Date date=new Date();
        System.out.println(date);
        long time = date.getTime();
        System.out.println(time);
        date = new Date(time);
        System.out.println(date);
    }
    @Test
    public void test02() throws ParseException {
        Date date=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        String format = dateFormat.format(date);
        System.out.println(format);

        Date parse = dateFormat.parse(format);
        System.out.println(parse);
    }
    @Test
    public void test03(){
        LocalDateTime ldt= LocalDateTime.now();
        System.out.println(ldt);
        LocalDateTime of = LocalDateTime.of(2020, 12, 6, 23, 6, 36);
        System.out.println(of);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss E");
        String format = ldt.format(dateTimeFormatter);
        System.out.println(format);


    }
    @Test
    public void test04(){
        System.out.println(Math.ceil(15.4));
        System.out.println(Math.floor(15.4));
        System.out.println(Math.round(15.6));
        System.out.println(Math.abs(-12));
        System.out.println(Math.max(1,9635));
        System.out.println(Math.min(5.1,6));
    }
    @Test
    public void test05(){
        double d1=Math.random();
        System.out.println(d1);
        System.out.println((int)(Math.random()*100)+1);
    }
    @Test
    public void test06(){
        BigInteger integer=new BigInteger("123456789123");
        BigInteger integer1=new BigInteger("12569845");
        System.out.println(integer1);
        System.out.println(integer);
        System.out.println(integer.subtract(integer1));
        System.out.println(integer.add(integer1));
        System.out.println(integer.multiply(integer1));
        System.out.println(integer.divide(integer1));

        BigDecimal bigDecimal=new BigDecimal("12345.36");
        BigDecimal bigDecimal1=new BigDecimal("1.36");
        System.out.println(bigDecimal.add(bigDecimal1));
        System.out.println(bigDecimal.divide(bigDecimal1,12,BigDecimal.ROUND_HALF_UP));
    }
}
