package day07.exer;

public class Test01 {
    public static void main(String []args){
        char[]arr=new char[26];

        for(int i='A';i<'Z';i++){
            arr[i-'A']=(char)(i);
        }

        for(char letter:arr){
            System.out.println(letter);
        }
    }
}
