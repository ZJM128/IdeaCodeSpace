package atguigu.com;

/**
 * //只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法
 */
public class Frog {
    public static void main(String args[]){

        System.out.println(getNum(5));
        System.out.println(getNum2(5));
        System.out.println(climb(0, 5));

    }

    /**
     *暴力法
     * @param i
     * @param n
     * @return
     */
    private static int climb(int i,int n){
        if(i>n){
            return 0;
        }else if(i==n){
            return 1;
        }
            return climb(i+1,n)+climb(i+2,n);
    }

    /**
     *  斐波那契 斐波那契数列
     */
    private static int getNum(int n){
        if(n<=1){
            return n;
        }
        int db[]=new int[n+1];
        // 初始化值
        db[1]=1;
        db[2]=2;
        for(int i=3;i<=n;i++){
            db[i]=db[i-1]+db[i-2];
        }
        return db[n];
    }

    /**
     * 优化后的斐波那契数列算法
     * @param n
     * @return
     */
    public static int getNum2(int n){
        if(n==1){
            return 1;
        }
        int first=1;
        int second=2;
        //int third=0;
        for (int i = 3; i <=n ; i++) {
           int third=first+ second;
            first=second;
            second=third;
        }
        return second;
    }
}
