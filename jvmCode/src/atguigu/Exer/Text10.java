package atguigu.Exer;

public class Text10 {
    public static void main(String[]args){
        //TextDeal1();
        TextDeal2();
    }

    private static void TextDeal1() {
        for(int i=0;i<5;i++){
           for(int j=0;j<i;j++){
                System.out.print(" ");
            }
            for(int j=0;j<5-i;j++){
               System.out.print("* ");
            }
            System.out.println();
        }
    }

    private static void TextDeal2(){
        for(int i=0;i<5;i++){
            for(int k=0;k<i;k++){
                System.out.print(" ");
            }
            for(int j=5-i;j>0;j--){
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
