package recursion;

public class Queen8 {
    private static int max=8;
    private int array[]=new int[8];
    private static int count;

    public static void main(String []args){
        Queen8 q=new Queen8();
        q.check(0);
        System.out.println("一共有多少种"+count);
    }

    public void check(int n){
        if(n==max){
            print();
            return;
        }

        for(int i=0;i<max;i++){
            array[n]=i;// 把皇后放在第几列
            if(jude(n)){
                check(n+1); //可以的话继续下一个
            }
            // 如果不可以 也会回溯上一个皇后进行调整
        }
    }

    public boolean jude(int n){
        for(int i=0;i<n;i++){
            if(array[i]==array[n] || Math.abs(n-i)==Math.abs(array[n]-array[i])){//不符合规则
                return false;
            }
        }
        return true;
    }

    public void print(){
        count++;
        for(int i=0;i<max;i++){
            System.out.print(array[i]+"\t");
        }
        System.out.println();
    }
}
