package atguigu.structure;

public class ListStackDome {
    public static void main(String[] args) {
        ListNode node1=new ListNode(1,"j");
        ListNode node2=new ListNode(2,"j");
        ListStack listStack=new ListStack();
        listStack.push(node1);
        listStack.push(node2);

        System.out.println(listStack.pop());
        System.out.println(listStack.pop());
    }

}
class ListStack{
    // 初始化链表的头结点
    ListNode head=new ListNode(0,"");


    public void push(ListNode value ){
        ListNode temp=head;
        temp.setNext(value);
        temp=temp.getNext();
    }

    public ListNode pop(){
        ListNode temp=head.getNext();
        ListNode relust=null;
        while(true){
            if(temp.getNext()==null){
                relust=temp.getNext();
                temp.setNext(null);
                break;
            }
            temp=temp.getNext();
        }
        return relust;
    }



}
// 定义链表的节点
class ListNode{
    private int no;
    private String name;
    private ListNode next;
    public ListNode(int no,String name){
        this.no=no;
        this.name=name;
    }

    public int getNo() {
        return no;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}