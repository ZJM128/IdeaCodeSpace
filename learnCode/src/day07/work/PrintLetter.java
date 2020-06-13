package day07.work;

public class PrintLetter {
    public static void main(String[]args){
        char []arr=new char[26];
        for(int i='A';i<='Z';i++){
            arr[i-'A']=(char)(i);
        }
        for(char ch:arr){
            System.out.println(ch);
        }
    }
}
