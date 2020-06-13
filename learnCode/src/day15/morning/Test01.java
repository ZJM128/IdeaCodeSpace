package day15.morning;

public class Test01 {
    public static void main(String[] args) {
//        String str="123";
//        String str1="456";
//        String str2=str+str1;
//        System.out.println(str2);

        String str = "ABCDE";
        str.substring(3);
        String concat = str.concat("123");
        System.out.println(concat);
      /* int div = div(12, 0);
        System.out.println(div);*/

        /*try {
                int n = Integer.parseInt(args[0]);
                    System.out.println("n = " + n);
                } catch (NumberFormatException e) {
                     System.out.println("捕获第一个异常：" + e);
                     System.out.println(e.getMessage());
                    return;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("捕获第二个异常：" + e);
                } catch (Exception e) {
                    System.out.println("捕获其他可能的所有异常：" + e);
                } finally {  //保证该块的语句总是被执行
                     System.out.println("最终块");
                }
                    System.out.println("Hello World! -- end");*/

    }

    public  static int div(int a,int b){
        int reslu=0;
        try{
            reslu=a/b;
        }catch (Exception e){
           e.printStackTrace();
            reslu=+200;
            return reslu;
        }finally {
            reslu=+100;
            System.out.println("最终执行的代码");
        }

        return 0;
    }
}
