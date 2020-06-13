package day10.warkbooks;

 class Db {
     String str;
     Db(){
         str="ee";
     }
     Db(String s){
         str=s;
     }
}
class DbTest1 extends Db{

}
 class DbTest2{
    public static void main(String[] args) {
        Db db = new Db("java");
        //DbTest1 dbTest1 = new DbTest1("guigu");
    }
}