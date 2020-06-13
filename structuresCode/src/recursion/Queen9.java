package recursion;

public class Queen9 {
    private  int max=9;
    private int[]array=new int[max];
    private static int count;

    public static void main(String []args){
        Queen9 q=new Queen9();
        q.check(0);
        System.out.println("一共有"+count);
    }

    public void check(int n){
        if(max==n){
            print();
            return;
        }
        for(int i=0;i<max;i++){
            array[n]=i;
            if(jude(n)){
                check(n+1);
            }
        }
    }

    /**
     * 判断是否可以放皇后
     * @param n
     * @return
     */
    public boolean jude(int n){

        for(int i=0;i<n;i++){
            if(array[i]==array[n] || Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    public void print(){
        count++;
        for(int i=0;i<max;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
