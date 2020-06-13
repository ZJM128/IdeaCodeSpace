package atguigu.Exer;

public class TocalculateTheQuality {
    public static void main(String[] args) {
        //质数是指在大于1的自然数中，
        // 除了1和它本身以外不再有其他因数的自然数。
        boolean flag=true; // 先声明每个都是质数
        for (int i = 2; i <= 100; i++) {
             flag=true;
            for (int j = 2; j < i; j++) {
                if(i%j==0){
                    flag=false;
                }
            }
            if(flag){
                System.out.println(i);
            }
        }
    }
}
