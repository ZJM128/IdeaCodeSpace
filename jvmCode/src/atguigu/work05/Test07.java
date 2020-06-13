package atguigu.work05;

public class Test07 {
    public static void main(String[] args) {
        MathTools mathTools=new MathTools();
        System.out.println(mathTools.add(2, 5));
        System.out.println(mathTools.subStract(6, 1));
        System.out.println(mathTools.mutiply(1, 6));
        System.out.println(mathTools.divide(6, 3));
        System.out.println(mathTools.remainder(5, 6));
        System.out.println(mathTools.max(2, 5));
        System.out.println(mathTools.min(1, 2));
        System.out.println(mathTools.equals(2, 4)?"相等":"不相等");

        System.out.println(mathTools.isEven(6)?"是":"不是");
        System.out.println(mathTools.isPrimeNumber(7)?"是":"不是");

        System.out.println(mathTools.round(9.6));
    }
}
