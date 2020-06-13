package day13.review;

public class TestWeek {
    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);
        Week[] week= Week.values();
        printWeek(week[i-1]);
    }

    public static void printWeek(Week week){
        switch (week){
            case MONDAY:
                System.out.println("星期一");
                break;
            case TUESDAY:
                System.out.println("星期二");
                break;
            case WEDNESDAY:
                System.out.println("星期三");
                break;
            case THURSDAY:
                System.out.println("星期四");
                break;
            case FRIDAY:
                System.out.println("星期五");
                break;
            case SATURDAY:
                System.out.println("星期六");
                break;
            case SUNDAY:
                System.out.println("星期日");
                break;
            default:
                System.out.println("没有相应的星期");
                break;
        }
    }
}
