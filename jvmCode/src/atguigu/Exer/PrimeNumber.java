package atguigu.Exer;

/**
 * 求质数  平方根 如果一个数必须是两个数的乘积 如果小的那边已经符合了那么大的那边就可以不用检查了
 */
public class PrimeNumber {
    public static void main(String []args){
        long start = System.currentTimeMillis();
        boolean flag=false;
        for(int i=2;i<=100;i++){
            //for(int j=2;j<Math.sqrt(i);j++)
            for(int j=2;j<Math.sqrt(i);j++){
                if(i%j==0){
                    flag=true;
                    break;// 如果已经检查出不是质数了
                }
            }
            if(!flag) {
                System.out.println(i);
            }
            flag=false;
        }
        long end = System.currentTimeMillis();
        System.out.println("时间"+(end-start));
    }
}
