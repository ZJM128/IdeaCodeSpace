package atguigu.Exer;

public class SwitchText2 {
    public static void main(String[]args){
        String season="summer";
        switch(season){
            case "spring":
                System.out.println("春暖花开");
                break;
            case "summer":
                System.out.println("夏日炎炎");
                break;
            case "autumn":
                System.out.println("秋天");
                break;
            case "winter":
                System.out.println("冬天");
                break;
            default:
                System.out.println("没有相对的季节");
                break;
        }
    }
}
