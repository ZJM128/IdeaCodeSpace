package day08.test;

public class Test03 {
    public static void main(String[]args){

    }
    public int add(){
        return 0;
    }
    public int add(int a,int b){
        return a+b;
    }

    public int add(int a,int b,int c){
        return a+b+c;
    }

    public int add(int ...num){
        int sum=0;
        for(int i=0;i<num.length;i++){
            sum+=i;
        }
        return sum;
    }

   /* public void add(int[]nums){

    }*/
    public void add(String param,int...num){

    }

/*    public void add(String ...str,int ...nums){}*/

}
