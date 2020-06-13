package hashtable;

import java.util.Scanner;

public class HashTable {
    private EemLinkedList[]emLinkedLists;
    private int size;

    public HashTable(int size) {
        this.size=size;
        emLinkedLists=new EemLinkedList[size];
        for(int i=0;i<size;i++){
            emLinkedLists[i]=new EemLinkedList();
        }
    }

    /**
     * 添加数据
     *
     */
    public void add(){
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入id:");
        int id=scanner.nextInt();
        System.out.print("请输入姓名:");
        String name=scanner.next();
        Emp emp=new Emp(id,name);
        int index=hashIndex(emp.getId());
        emLinkedLists[index].add(emp);
        System.out.println("添加成功");
    }

    /**
     * 展示数据
     */
    public  void show(){
        System.out.println("hashtable表的信息如下");
        for(int i=0;i<size;i++){
            emLinkedLists[i].list(i);
        }
    }
    public void findEmpById(){
        System.out.println("请输入id");
        Scanner scanner=new Scanner(System.in);
        int id = scanner.nextInt();
        int index=hashIndex(id);
        Emp emp = emLinkedLists[index].findEmp(id);
        if(emp==null){
            System.out.println("么有找到该雇员");
        }else{
            System.out.println(emp.toString());
        }
    }

    public void deleteEmp(){
        System.out.println("请输入id");
        Scanner scanner=new Scanner(System.in);
        int id = scanner.nextInt();
        int index=hashIndex(id);
        boolean flag= emLinkedLists[index].delete(id);
        if(flag){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }
    /**
     * 散列数据,使得雇员的数据根据id随机分配到一个链表中
     * @param id
     * @return
     */
    public int hashIndex(int id){
        return id%size;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        Scanner scanner = new Scanner(System.in);
        boolean flag=true;
        while(flag){
            System.out.println("请选择");
            System.out.println("1:添加");
            System.out.println("2:展示列表");
            System.out.println("3:查找雇员");
            System.out.println("4:删除雇员");
            System.out.println("5:退出");
            int select =scanner.nextInt();
            switch(select){
                case 1:
                    hashTable.add();
                    break;
                case 2:
                    hashTable.show();
                    break;
                case 3:
                    hashTable.findEmpById();
                    break;
                case 4:
                    hashTable.deleteEmp();
                    break;
                case 5:
                    scanner.close();
                    flag=false;
                    break;

            }
        }
    }
}
