package atguigu.day05;

public class Text1 {
    public static void main(String[] args) {
        //printPrimeNumber();
        //printRimeNumber();
        System.out.println("和为:"+add(1,2));
        //login(1,2);
       /* boolean isFullYear = checkIsFullYear(2020);
        if(isFullYear){
            System.out.println("闰年");
        }else{
            System.out.println("平年");
        }*/
        //printTriangle();
    }

    /**
     * 1、打印100以内的质数
     */
    public static void printPrimeNumber(){
        boolean flag=true;
        for(int i=2;i<=100;i++){
            for(int j=2;j<i;j++){
                if(i%j==0){
                    flag=false;
                    break;
                }
            }
            if(flag) {
                System.out.println("质数=" + i);
            }
            flag=true;
        }
    }

    public static void printRimeNumber(){
        boolean flag=true;
        for(int i=2;i<=1000;i++){
            for(int j=2;j<Math.sqrt(i);j++){
                if(i%j==0){
                    flag=false;
                    break;
                }
            }
            if(flag){
                System.out.println("质数="+i);
            }

            flag=true;
        }
    }

    /**
     *  2、定义一个方法实现功能：计算任意两个数的和
     */
    public static double add(double num1,double num2){
        return num1+num2;
    }

    /**
     *  3、定义一个方法模拟实现功能：判断登录是否成功，
     *         登录需要用户名和密码。
     *         注：假设用户名密码都为 int, 登陆成功打印“登录成功”，
     *         否则打印“登录失败”
     */
    public static void login(int name,int password){
        if(name==1 && password==2){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }

    /**
     * 4、写一个方法，此方法实现判断某一年份是否是闰年。
     *         闰年的判断依据是：
     *         （1）被4整除不能被100整除，
     *         （2）能被400整除
     * @return
     */
    public static boolean checkIsFullYear(int year){
        if((year%4==0 && year%100!=0) || year%400==0){
            return true;
        }
        return false;
    }

    /**
     *  5、利用程序输出如下图形:
     *         *
     *         * *
     *         * * *
     *         * * * *
     *         * * * * *
     */
    public static void printTriangle(){
        for(int i=0;i<5;i++){
            for(int j=0;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
