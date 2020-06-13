package atguigu.Exer;

import org.junit.Test;

public class Text12 {
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            for(int j=0;j<5-i-1;j++){
                System.out.print("\t");
            }
            for(int k=0;k<2*i+1;k++){
                System.out.print("♥\t");
            }
            System.out.println();
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<=i;j++){
                System.out.print("\t");
            }
            for(int k=0;k<7-2*i;k++){
                System.out.print("♥\t");
           }
            System.out.println();
        }
    }

    @Test
    public void rhomhus(){

        for(int i=0;i<5;i++){
            for(int j=0;j<5-i-1;j++){
                System.out.print("\t");
            }
            for(int k=0;k<2*i+1;k++){
                if(k==0 || k==2*i) {
                    System.out.print("♥\t");
                }else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<=i;j++){
                System.out.print("\t");
            }
            for(int k=0;k<7-2*i;k++){
                if( k==0 ||k==6-2*i) {
                    System.out.print("♥\t");
                }else{
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
}
