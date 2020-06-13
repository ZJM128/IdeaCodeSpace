package day14.review;

public class Test10 {
    public static void main(String[] args) {
        String num="23.23456789";
        System.out.println("原数字:"+num);
        System.out.println("取整后:"+new Handleeable() {
            @Override
            public String handleString(String num) {

                return num.substring(0, num.indexOf("."));
            }
        }.handleString(num));

        System.out.println("原数字:"+num);

        System.out.println("保留4位小数"+new Handleeable() {
            @Override
            public String handleString(String num) {

                int index=num.indexOf(".");
                int num1=Integer.parseInt(num.substring(index+4,index+5));
                int num2=Integer.parseInt(num.substring(index+5,index+6));
                if(num2>=5){
                    num1++;
                }

                return num.substring(0,index+4)+num1 ;
            }
        }.handleString(num));

    }
}

interface Handleeable{
    String handleString(String num);

}