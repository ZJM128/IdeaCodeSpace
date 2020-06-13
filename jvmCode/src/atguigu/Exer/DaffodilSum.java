package atguigu.Exer;

public class DaffodilSum {
    public static void main(String[] args) {
        for(int i=100;i<1000;i++){
            int hundred=i/100;
            int ten=i/10%10;
            int ge=i%100%10;
            if(i==((int)Math.pow(hundred,3))+(int)Math.pow(ten,3)+(int)Math.pow(ge,3)){
                System.out.println(i);
            }
        }
    }
}
