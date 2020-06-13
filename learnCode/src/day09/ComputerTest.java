package day09;
/*
*@Description:在Test类中提供一个静态方法listPrice以Computer引用变量为参数，打印输出电脑价格。
在main方法中，分别以Computer、PC、NotePad对象为参数，调用listPrice方法。
*
* 在方法listPrice中，判断Computer参数的真实对象，并调用不同对象上的特有方法
*
*@author:zhijm
*@Date:2020/6/5 15:47
*/
public class ComputerTest {

    private static void listPrice(Computer computer){
        System.out.println("cup:"+computer.getCPU()+ "价钱:"+computer.getPrice());
        if(computer instanceof PC){
           PC pc=(PC)computer;
            System.out.println(pc.getBox()?"是":"否");
        }
        if(computer instanceof NotePad){
            NotePad notePad=(NotePad)computer;
            System.out.println(notePad.isCanGo()?"是":"否");
        }
    }

    public static void main(String[] args) {
        Computer []computers=new Computer[4];
        computers[0] =new PC(true,"i7","32G","1t",8235);
        computers[1] =new NotePad(true,"i9","16G","2t",9632);
        computers[2] =new PC(false,"i5","16","1t",2536);
        computers[3] =new NotePad(true,"i3","16G","1t",3632);
        for (Computer computer : computers) {
            System.out.println(computer.getDetail());
        }

        boolean flag=false;
        for(int i=0;i<computers.length-1;i++){
            for(int j=0;j<computers.length-1-i;j++){
                if(computers[j].getPrice()>computers[j+1].getPrice()){
                    Computer temp=computers[j];
                    computers[j]=computers[j+1];
                    computers[i]=temp;
                    flag=true;
                }
            }
            if(!flag){
                break;
            }else {
                flag = false;
            }

        }
        System.out.println("-----------------------------------");
        for (Computer computer : computers) {
            //System.out.println(computer.getDetail());
            listPrice(computer);
        }
       /* System.out.println("------------------------------------");
        listPrice(computers[1]);
        System.out.println("------------------------------------");
        listPrice(computers[2]);*/
    }



}
