package sort;

public class TimeComplexity {

    /**
     * 常数阶  语句的执行不会随着变量增加而增加 O(1)
     */
    public static void constantOfTheOrder(){
        int i=1;
        int j=12;
        int total=i+j;
    }

    /**
     * 对数阶 n为1024 i每次增量为2,因而是 次数^2=1024--->次数=long2(1024)
     */
    public static void theLogarithmicOrder(){
        int n=1024;
        int i=1;
        while(i<n){
            i=i*2;
        }
    }

    /**
     * 线性阶
     * 这段代码，for循环里面的代码会执行n遍，因此它消耗的时间是随着n的变化而变化的，
     * 因此这类代码都可以用O(n)来表示它的时间复杂度
     */
    public static void linerOrder(){
        int n=10;
        for(int i=0;i<n;i++){
              int j=i++;
        }

    }

    /**
     * 线性对数阶O(nlogN)
     * 线性对数阶O(nlogN) 其实非常容易理解，将时间复杂度为O(logn)的代码循环N遍的话，
     * 那么它的时间复杂度就是 n * O(logN)，也就是了O(nlogN)
     */
    public static void linerAndLogarithmic(){
        int n=100;
        for(int i=0;i<n;i++){
            while(i<n){
               int j=i++;
            }
        }
    }

    /**
     * 平方阶O(n²) 就更容易理解了，如果把 O(n) 的代码再嵌套循环一遍，
     * 它的时间复杂度就是 O(n²)，
     * 这段代码其实就是嵌套了2层n循环，它的时间复杂度就是 O(n*n)，
     * 即  O(n²) 如果将其中一层循环的n改成m，那它的时间复杂度就变成了 O(m*n)
     */
    public static void squareOrder(){
        int n=100;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                i++;
            }
        }
    }
}
