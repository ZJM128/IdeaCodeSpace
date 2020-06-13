package day14.review;

public class Test12 {
    public static void main(String[] args) {
        String str = "1、 hello 2. world 3. java 4.String 5. hahhhhha 6、HELLO";
        char[] chars = str.replace(" ","").toLowerCase().toCharArray();

         int maxCount=0;
         char charIndex=' ';
         for(int i=0;i<chars.length;i++){
             int count=0;
             for(int j=i;j<chars.length;j++){
                 if(chars[i]==chars[j]){
                     count++;
                 }
             }
             if(count>=maxCount){
                 maxCount=count;
                 charIndex=chars[i];
             }
         }
        System.out.println("出现次数最多的是"+charIndex+" 出现的次数是"+maxCount);
    }

}
