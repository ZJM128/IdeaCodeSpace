package atguigu.day4;

import org.junit.Test;

/**
 * 编写程序一，打印1-100之间13的倍数，使用continue语句
 * 编写程序二：从键盘读入个数不确定的整数，并判断读入的正数和负数的个数，输入为0时结束程序。
 */
class BreakOrContinue {
    public static void main(String[] args) {
//        for(int i=1;i<=100;i++){
//            if(i%13==0){
//                System.out.println("===="+i);
//               // break; // 结束当前循环 后台打印到13
//                continue;// 后台打印全部13 的倍数
//               // System.out.println("我还能存在么"); 在同一个作用域内 break continue 语句的后面不能存在语句
//            }
//            //System.out.println(i);
//        }
//
//        Scanner sc=new Scanner(System.in);
//        int positiveNumberCount=0;
//        int minusCount=0;
//        while(true) {
//            System.out.println("请输入整数");
//            int num=sc.nextInt();
//            if(num>0){
//                positiveNumberCount++;
//            }else if(num<0){
//                minusCount++;
//            }else if(num==0){
//                sc.close();
//                break;
//            }
//        }

        /*for(;;){// 无限循环
            System.out.println("请输入整数");
            int num=sc.nextInt();
            if(num>0){
                positiveNumberCount++;
            }else if(num<0){
                minusCount++;
            }else if(num==0){
               break;
            }
        }

        System.out.println("正数的个数:"+positiveNumberCount);
        System.out.println("负数的个数:"+minusCount);*/


    }

    @Test
    public void breakTest() {
        // break的作用就是结束当前的循环
        /*for(int i=1;i<10;i++){
            for(int j=1;j<10;j++){
                System.out.print(j+" ");//1 2 3 4 5 6  8 9 正常循环9次
                if(j%3==0){
                    System.out.println();
                    break;// 结束了整个内循环
                }
            }
        }*/

        // 通过标签可以结束指定的循环
        /*label:for(int i=0;i<10;i++){
            for(int j=1;j<10;j++){
                System.out.print(j+" ");
                if(j%3==0){
                    System.out.println();
                    break label;// 跳出标志循环的语句块 1 2 3
                }


            }
        }*/

        // 通过标志结束整个语句块
        label:
        {
            for (int i = 1; i < 10; i++) {
                System.out.print(i + " ");
                if (i % 3 == 0) {
                    break label;// 结束整个语句块 当执行这里这个语句块就结束了,后面的语句就不会执行了
                }
            }
            System.out.println();
            System.out.println("我还能输出");
        }


    }

    @Test
    public void continueText() {
        // continue结束本次循环
        /*for(int i=1;i<10;i++){
            for(int j=1;j<10;j++){
                if(j%2==0){
                    continue;// 偶数的话 就跳出内循环的本次循环 后面的语句就不执行了
                }
                System.out.print(j+" ");
            }
            System.out.println();
        }*/

        //  通过标签结束指定的循环语句
        label:
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (j % 3 == 0) {
                    continue label; //结束内循环的本次循环,并跳到外循环继续执行
                   // System.out.print(""); 没法编译成功
                }
                System.out.print(j + " ");
            }
            System.out.println();
        }

        // continue 不支持跳到语句块

    }

}
