package atguigu.Exer;

public class Text6 {
    public static void main(String[] args) {

        int jj=20,a=0,b=0;
        for(int i=0;i<jj;i+=2,jj--){
            if(i%3==0){
                a=i;
                System.out.println(i+" Hello");
            }else{
                b=i;
                System.out.println(i+" Word");
            }
        }

        System.out.println("a和b的乘积"+a*b);

    }
}
