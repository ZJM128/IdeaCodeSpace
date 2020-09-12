package atguigu.day4;



/**
 * 求质数 除了1和本身
 */
public class PrimeNumber {
    public static void main(String []args){
        // 声明一个标志符号 默认是质数
        boolean flag=true;
        //除了1和本身 那就从1开始
        for(int i=2;i<=100;i++){
            for(int j=2;j<Math.sqrt(i);j++){
                if(i%j==0){
                    flag=false;
                    // 只要有一个不符合了就可以终止循环了
                    break;
                }
            }
            if(flag){
                System.out.println(i);
            }

            // 重写把标志重置
            flag=true;
        }

    }


    public  void primeNumTest(){
        boolean flag=true;// 标志位
        // 因为除了1和本身 没有其他的因数 所以初始值从2开始 需要用到嵌套循环,外层控制每个数 内层控制每个因数
        for(int i=2;i<100;i++){
            for(int j=2;j<i;j++){
                if(i%j==0){
                    flag=false;
                    break;
                }
            }
            if(flag){
                System.out.println(i);
            }

            flag=true;
        }
    }


    public void primeNumTest2(){

        long start=System.currentTimeMillis();
        boolean flag=true;
        for(int i=2;i<=100;i++){
            for(int j=2;j<i;j++){
                if(i%j==0){
                    flag=false;// 标志不是质数
                }
            }
            if(flag){
                System.out.println(i);
            }
            // 再次给flag 进行初始化
            flag=true;
        }
    }
}
