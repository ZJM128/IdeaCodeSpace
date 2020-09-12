package day24.factory.factory;

public class BananaFactory implements Factory {
    @Override
    public Fruit getFruit() {
        return new Banana();
    }
}
