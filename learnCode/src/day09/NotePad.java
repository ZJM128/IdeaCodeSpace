package day09;

/*
*@Description:改写Computer类，将所有属性声明为private，getDetails方法用于返回Computer的详细信息
在PC子类中直接访问继承的私有属性，结果如何？
在Computer类中对私有属性添加公有的get/set方法，在PC子类中通过这些公有的get/set方法访问私有属性，结果如何？
*@author:zhijm
*@Date:2020/6/3 16:32
*/
public class NotePad extends Computer {
    boolean canGo;

    public boolean isCanGo() {
        return canGo;
    }

    public void setCanGo(boolean canGo) {
        this.canGo = canGo;
    }

    public NotePad(boolean canGo,String CPU, String memory, String disk, int price) {
        super(CPU, memory, disk, price);
        this.canGo=canGo;
    }

    public String getDetail(){
        String detail = super.getDetail();
        return "我是NotePad:"+detail+" "+(canGo?"有主机":"没有主机");
    }

}
