package day07.work;

public class PrintLetterAndNumber {
    public static void main(String []args){
        char []arr=new char[36];
        for(int i=0;i<arr.length;i++){
            if(i<26){
                arr[i]=(char)('A'+i);
            }else{
                arr[i]=(char)('0'+(i-26));
            }
        }

        for(char ch:arr){
            System.out.println(ch);
        }
    }
}
