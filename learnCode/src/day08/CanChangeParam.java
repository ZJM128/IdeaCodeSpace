package day08;

public class CanChangeParam {

    public static void main(String []args){
        CanChangeParam c=new CanChangeParam();
        int sum=c.add();// 传0个的参数
        //int sum=c.add(1,2,3);/传多个的参数
        //int sum=c.add("add",1,2,3);
        System.out.println(sum);
    }

    /*public void add(int []arr){

    }*/
    public int add(int ...num){
        System.out.println("可变参数可以传0个或者多个");
        int sum=0;
        for(int i:num){
            sum+=i;
        }
        return sum;
    }

    public int add(int a,int b){
        return 0;
    }

    public int add(String name,int ...nums){
        int sum=0;
        if("add".equals(name)){
            for(int num:nums){
                sum+=num;
            }
        }
        return sum;
    }

    /*public void add (int ...nums,String ...str){}
    public void add(int ...nums,int b){}*/

}
