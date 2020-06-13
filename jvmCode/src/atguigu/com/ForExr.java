package atguigu.com;

import org.junit.Test;

/**
 * for(①;②;③){
 * ④
 * }
 * ①循坏初始值
 * ②循坏条件
 * ③迭代条件
 * ④循环体
 * 执行的顺序
 * ①->②->④->③->②->④-③->②->④->③....②
 */

public class ForExr {
    public static void main(String[] args) {
        int a = 0;
        a = a + ++a;
        System.out.println(a);

    }

    /**
     * 编写程序FooBizBaz.java，从1循环到150并在每行打印一个值，
     * 另外在每个3的倍数行上打印出“foo”,在每个5的倍数行上打印“biz”,
     * 在每个7的倍数行上打印输出“baz”。
     */
    @Test
    public void forText1() {
        for (int i = 0; i <= 150; i++) {
            System.out.print(i);
            if (i % 3 == 0) {
                System.out.print(" foo");
            }

            if (i % 5 == 0) {
                System.out.print(" biz");
            }

            if (i % 7 == 0) {
                System.out.print(" baz");
            }
            System.out.println();
        }
    }

    @Test
    public void forText2() {
//        for(int i=0;i<20;i++){
//            System.out.println("Hello word");
//        }

        // for的执行过程的测试

    }

    /**
     * 1.打印1~100之间所有奇数的和
     * 2.打印1~100之间所有是7的倍数的整数的个数及
     * 总和（体会设置计数器的思想）
     * 3.输出所有的水仙花数，所谓水仙花数是指一个3
     * 位数，其各个位上数字立方和等于其本身。
     * 例如： 153 = 1*1*1 + 3*3*3 + 5*5*5
     */
    @Test
    public void forText3() {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                sum += i;
            }
        }
        System.out.println("1~100之间所有奇数的和" + sum);

        int num = 0, sum1 = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 7 == 0) {
                num++;
                sum1 += i;
            }
        }

        System.out.println("个数" + num + " 和" + sum1);

        //.输出所有的水仙花数，所谓水仙花数是指一个3
        //     * 位数，其各个位上数字立方和等于其本身。
        //     * 例如： 153 = 1*1*1 + 3*3*3 + 5*5*5

        for (int i = 100; i < 1000; i++) {
            // 求百位
            int bai = i / 100;
            int shi = (i / 10) % 10;
            int ge = i % 10;
            int baiSun = (int) Math.pow(bai, 3);
            int shiSum = (int) Math.pow(shi, 3);
            int geSum = ge * ge * ge;
            int sum2 = baiSun + shiSum + geSum;
            if (i == sum2) {
                System.out.println(i);
            }

        }
//        int i=2;
//        for(;;){
//
//        }

        int i = 9;
        do {
            System.out.println("前");
            i++;
            System.out.println("后");
            System.out.println(i);
        } while (i < 10);
    }

    @Test
    public void forText4() {

        int count = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                count += i;
            }
        }
        System.out.println("for" + count);
        count = 0;
        int i = 1;
        while (i <= 100) {
            if (i % 2 == 0) {
                count += i;
            }
            i++;
        }
        System.out.println("while" + count);

        count = 0;
        int j = 1;
        do {
            if (j % 2 == 0) {
                count += j;
            }
            j++;
        } while (j <= 100);

        System.out.println("do while" + count);
    }
}
