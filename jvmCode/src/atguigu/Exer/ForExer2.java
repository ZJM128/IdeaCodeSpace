package atguigu.Exer;

public class ForExer2 {
    public static void main(String[] args) {
        int count=0;
        int sum=0;
        for(int i=1;i<100;i++){
            if(i%7==0){
                sum+=i;
                count++;
            }
        }
        System.out.println(sum);
        System.out.println(count);
    }
}
