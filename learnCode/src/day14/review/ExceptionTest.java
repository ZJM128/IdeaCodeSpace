package day14.review;

public class ExceptionTest {
  public static  void main(java.lang.String[]args){
        // Error
        //byte[]bytes=new byte[1024*10000*1000*10000];

        // Exception
        // 编译时
        /*try {
            FileInputStream fileInputStream = new FileInputStream("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        // 运行时异常
      // ①ClassCastException
       /* Object o=new String();
        Integer integer=(Integer) o;*/

       // ArithmeticException
      /* int i=10/0;*/

      // ArrayIndexOutOfBoundsException:
      /*int[]arr=new int[4];
      arr[5]=1;*/
      // NullPointerException/*Customer[]customers=new Customer[2];
      //      customers[0].show();*/


    }
}
class Customer{
    public void show(){
        System.out.println("good");
    }
}