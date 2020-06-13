package day11.work;

 class Perosn {
    private String name;

    public Perosn() {
        System.out.print("1");
    }

    public Perosn(String name) {
        System.out.print("2");
        this.name = name;
    }
}
class Child extends Perosn {
    Perosn father;

    public Child(String name) {
        System.out.print("3");
        father = new Perosn(name + " F");
    }

    public Child() {
        System.out.print("4");
    }
}
 class Test01 {
    public static void main(String[] args) {
        new Child("mike");
    }
}