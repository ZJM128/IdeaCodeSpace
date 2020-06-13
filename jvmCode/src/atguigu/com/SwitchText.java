package atguigu.com;

/**
 * ① 表达式结果的数据类型只能是 byte short char int string(1.7后) 枚举
 * ②default 是可选的,当全部case都不符合的时候 执行default里面的语句
 * ③break 是可选的 一旦表达式结果与case后的值匹配成功,执行相关的语句 直达遇到break为止
 * ④case 后只能写常量值 不能写表达式
 */
public class SwitchText {
    public static void main(String[]args){
        int num=5;
        switch(num){
            case 1:
                System.out.println("1");
                break;
            case 2:System.out.println("2");
                break;
            case 5:
                System.out.println("5");
                break;
            default:
                System.out.println("没有相关的数据");
        }

        char ch='1';
        switch(ch){
            case '2':
                System.out.println("2");
                break;
            case '4':
                System.out.println("4");
                break;
            case 5:// char类型会自动转为int的类型
                System.out.println("int 5");
                break;
           /* case "2": 编译不通过 因为char 类型和String类型不能转换
                System.out.println("String 2");
                break;*/
           /* case 1>2:case后只能写常量值 不能是表达式
                System.out.println("String 1>2");
                break;*/
            default:System.out.println("没有相关数据");
        }

        //需求：若一个数大于等于2 并且，小于等于5，则打印 "2-5"
        int num1=3;
        switch(num1){
            case 1:
                System.out.println("1");
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("2-5");
                break;
            case 6:
                System.out.println("6");
                break;
            default:
                System.out.println("没有相关数据");

        }
    }
}
