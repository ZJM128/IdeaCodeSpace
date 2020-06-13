package atguigu.day05;

public class SwitchDemo {
    public static void main(String[] args) {
        System.out.println('1'-48);
        int n=2;
        switch(n){
            case  1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                return;
            case 3:
                System.out.println("3");
                break;
        }
    }
}
