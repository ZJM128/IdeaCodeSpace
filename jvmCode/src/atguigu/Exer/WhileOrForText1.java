package atguigu.Exer;

public class WhileOrForText1 {
    public static void main(String[] args) {
        // 求1到100之间所有偶数的和
        int count=0;
        for(int i=1;i<=100;i++){
            if(i%2==0){
                count+=i;
            }
        }
        System.out.println("for count"+count);

        int j=1;
        count=0;
        while(j<=100){
            if(j%2==0){
                count+=j;
            }
            j++;
        }
        System.out.println("while count"+count);

        int k=1;
        count=0;
        do{
            if(k%2==0){
                count+=k;
            }
            k++;
        }while(k<=100);
        System.out.println("do-while"+count);
    }
}
