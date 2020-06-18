package day18.exer;

public class Coordinate<T> {
    T x;
    T y;

    public Coordinate(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }



    public static void main(String[] args) {
        Coordinate<String>coordinate=new Coordinate<>("12","23");
        System.out.println(coordinate);

        Coordinate<Integer>coordinate1=new Coordinate<>();
        coordinate1.x=52;
        coordinate1.y=96;
        System.out.println(coordinate1);
    }
}

