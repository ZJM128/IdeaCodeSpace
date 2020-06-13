package recursion;

public class Queen {
    private int max=8;
    private  int[]array=new int[max];// 使用了一维数组下标i为行数,值arr[i]为列数
    private static int count=0;
    public static void main(String []args){
            Queen queen=new Queen();
            queen.check(0);

            System.out.println("一共有"+count);

    }

    public  void check(int n){
        if(n==max){// 一共8行 当到达8行的时候 就回溯
            print();
            return;
        }
        for(int i=0;i<max;i++){
            // 先把当前这个皇后n 放到该行的第1列
            array[n]=i;
            // 判断当前放第n个皇后到i列时,是否冲突
            if(judge(n)){// 不冲突
                // 接着放n+1个皇后,即开始递归
                check(n+1);
            }
            // i++ 如果冲突,就继续执行arr[n]=i 即将din个皇后,放置在本行的后移的一个位置
        }
    }
    public  boolean judge(int n){
        for(int i=0;i<n;i++){
            //1.array[i]==array[n] 表示判断第n个皇后是否和前面的n-1改皇后在同一列
            //2.Math.abs(n-i)==Math.abs(array[n]-array[i])表示判断第n个皇后是否和第i个皇后在同一斜线
            if(array[i]==array[n] || Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }
    /**
     * 打印
     */
    public void print(){
        count++;
        for(int i=0; i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

}
