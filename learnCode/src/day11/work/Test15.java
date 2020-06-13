package day11.work;

class HelloA1{
    public HelloA1(){
        System.out.println("HelloA");
    }
    {
        System.out.println("I'm A Class");
    }
    static{
        System.out.println("static A");
    }
}

 class HelloB1 extends HelloA1{
    public HelloB1(){
        System.out.println("HelloB");
    }
    {
        System.out.println("I'm B Class");
    }
    static{
        System.out.println("static B");
    }

    public static void main(String[] args) {
        new HelloB1();//static A  "static B I'm A Class HelloA I'm B Class HelloB
    }

}
