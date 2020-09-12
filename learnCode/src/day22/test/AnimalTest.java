package day22.test;

public class AnimalTest {
    public static void main(String[] args) {
        new Animal(){
            @Override
            public void fly(){
                System.out.println("飞呀 飞呀");
            }
        }.fly();
        fly(()-> {
            System.out.println("自由的飞翔");
        });
    }

    private Animal getAnimal(){
        return ()->{
            System.out.println("自由的飞翔");
        };
    }

    public static void fly(Animal animal){
        System.out.println("我是小鸟");
        animal.fly();
    }
}
