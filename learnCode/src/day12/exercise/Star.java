package day12.exercise;

public class Star {
    public void shine(){
        System.out.println("start:星星一闪一闪亮晶晶");
    }
}
class Sun extends Star implements Universe{
    public void shine(){
        System.out.println("sun:光照八分钟，到达地球");
    }

    @Override
    public void doAnything() {
        System.out.println("sun:太阳吸引着9大行星旋转");
    }

    public static void main(String[] args) {
        Star star=new Star();
        star.shine();
        Star star1=new Sun();
        star1.shine();
        Universe universe=new Sun();
        universe.doAnything();
    }
}