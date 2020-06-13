package day12;

class Test {
    public static void main(String[] args) {
        PC pc=new PC("联想","blue",true);
        Equipment equipment=new NotePad("小米","red",true);
        Programmer programmer=new Programmer("李白",26,pc);
        Programmer programmer1=new Programmer("白居易",26,equipment);

        System.out.println(programmer.equals(programmer1));
        System.out.println(programmer);
        System.out.println(programmer1);
    }
}
