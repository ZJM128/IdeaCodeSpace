package day07;

public class TestRoom {
    public static void main(String[] args){
        Room room=new Room();
        System.out.println(room.getDetail()) ;
        Room room1=new Room("2");
        System.out.println(room1.getDetail());
        Room room2=new Room("3","36");
        System.out.println(room2.getDetail());
        Room room3=new Room("4","36","深圳");
        System.out.println(room3.getDetail());
    }
}
