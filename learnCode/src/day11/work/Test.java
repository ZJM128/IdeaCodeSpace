package day11.work;

public class Test {
    public static void main(String[] args) {
        Computer []computers=new Computer[3];
       PC pc=new PC("i7","32G","500G",5698,true);
       PC pc1=new PC("i5","32G","1500G",6008,true);
       NotePad  notePad=new NotePad("i7","16G","10240G",56998,true);

       computers[0]=pc;
       computers[1]=pc1;
       computers[2]=notePad;
        for (Computer computer : computers) {
            System.out.println(computer.getDetail());
        }
        boolean flag=false;
        for(int i=0;i<computers.length-1;i++){
            for(int j=0;j<computers.length-1-i;j++){
                if(computers[j].getPrice()>computers[j+1].getPrice()){
                    Computer temp =computers[j];
                    computers[j]=computers[j+1];
                    computers[j+1]=temp;
                    flag=true;
                }
            }

            if(!flag){
                break;
            }else{
                flag=false;
            }

        }
        System.out.println("============'");
        for (Computer computer : computers) {
            System.out.println(computer.getDetail());
        }
        /*System.out.println(pc.isBox());
        System.out.println(pc.getDetail());

        System.out.println(notePad.isCanChange());
        System.out.println(notePad.getDetail());*/
        System.out.println("-==================");
        listPrice(pc);
        listPrice(pc1);
        listPrice(notePad);
    }

    public static void listPrice(Computer computer){

        System.out.println(computer.getPrice());
        if(computer instanceof PC){
            System.out.println(((PC) computer).isBox());
        }

        if(computer instanceof NotePad){
            System.out.println(((NotePad) computer).isCanChange());
        }
    }
}
