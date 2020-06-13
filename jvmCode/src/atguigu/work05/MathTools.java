package atguigu.work05;

public class MathTools {
    public int add(int a,int b){
        return a+b;
    }

    public int subStract(int a ,int b){
        return a-b;
    }

    public int mutiply(int a,int b){
        return a*b;
    }

    public int divide(int a,int b){
        return a/b;
    }

    public int remainder(int a,int b){
        return a%b;
    }

    public int max(int a,int b){
        return a>b?a:b;
    }

    public int min(int a,int b){
        return a>b?b:a;
    }

    public boolean equals(int a,int b){
        return a==b?true:false;
    }

    public boolean isEven(int a){
        return a%2==0?true:false;
    }

    public boolean isPrimeNumber(int a){
        boolean flag=true;
        if(a>1) {
            for (int i = 2; i < Math.sqrt(a); i++) {
                if (a % i == 0) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public int round(double d){
        return (int)Math.round(d);
    }
}
