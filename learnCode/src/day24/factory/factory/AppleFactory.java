package day24.factory.factory;

public class AppleFactory implements Factory{
    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
