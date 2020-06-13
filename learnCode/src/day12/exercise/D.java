package day12.exercise;

interface D{
    int x = 0;
}
class G{
    int x = 1;
}
class C extends G implements D{
    int x=23;
    public void printX(){
        System.out.println(x);
    }
    public static void main(String[] args) {
        new C().printX();
    }
}
 class Test10 {
    public static void main(String[] args) {
        Out1 out = new Out1();
        out.Print(3);
        /*Out.In in = new Out().new In();
        in.print();*/
    }
}
class Out {
    private int age = 12;
    class In {
        private int age = 13;
        public void print() {
            int age = 14;
            System.out.println("局部变量：" + age);
            System.out.println("内部类变量：" + this.age);
            System.out.println("外部类变量：" + Out.this.age);
        }
    }
}class Out1 {
    private int age = 12;
    public void Print(final int x) {
        class In {
            public void inPrint() {
                System.out.println(x);
                System.out.println(age);
            }
        }
        new In().inPrint();
    }
}