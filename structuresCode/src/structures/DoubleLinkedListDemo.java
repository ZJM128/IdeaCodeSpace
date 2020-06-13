package structures;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNoe2 node1=new HeroNoe2(1,"张三","小福子");
        HeroNoe2 node2=new HeroNoe2(2,"李四","小小");
        HeroNoe2 node3=new HeroNoe2(3,"王五","大侄子");
        HeroNoe2 node4=new HeroNoe2(4,"王五","大侄子");
        DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
//        doubleLinkedList.addData(node1);
//        doubleLinkedList.addData(node2);
//        doubleLinkedList.addData(node3);
//        doubleLinkedList.showList(doubleLinkedList.getHead());
//        HeroNoe2 node4=new HeroNoe2(3,"习大大","理想");
//        // 修改后
//        System.out.println("修改后");
//        doubleLinkedList.updateList(node4);
//        doubleLinkedList.showList(doubleLinkedList.getHead());
//
//        // 删除后
//        System.out.println("删除后");
//        doubleLinkedList.deleteListNodeByNo(2);
//        doubleLinkedList.showList(doubleLinkedList.getHead());

        doubleLinkedList.addDataByNo(node1);
        doubleLinkedList.addDataByNo(node3);
        doubleLinkedList.addDataByNo(node2);
        doubleLinkedList.addDataByNo(node2);
        System.out.println("按序号后");
        doubleLinkedList.showList(doubleLinkedList.getHead());

    }

}

// 创建双向链表
class DoubleLinkedList{
    // 初始头结点
    HeroNoe2 head=new HeroNoe2(0,"","");

    // 返回头结点
    public HeroNoe2 getHead(){
        return head;
    }



    /**
     * 从头到尾遍历双向链表
     * @param head
     */
    public void showList(HeroNoe2 head){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }

        HeroNoe2 temp=head.next;
        while(temp!=null){
            System.out.println(temp);
            temp=temp.next;
        }
    }

    /**
     * 添加数据到双向链表中
     * @param value
     */
    public void addData(HeroNoe2 value){

            // 找到链表的尾部
            HeroNoe2 temp=head;
            while(true){
                if(temp.next==null){
                    break;
                }
                temp=temp.next;
            }
            temp.next=value;
            value.per=temp;

    }

    /**
     * 根据序号的大小进行添加数据
     * @param valueNode
     */
    public void addDataByNo(HeroNoe2 valueNode){
         HeroNoe2 cur=head;
         boolean flag=false;
         while(true){
             if(cur.next==null){
                 break;
             }
             if(cur.next.no>valueNode.no){
                 break;
             }
             if(cur.next.no==valueNode.no){
                 flag=true;
                break;
             }
             cur=cur.next;
         }

         if(!flag){
             // 尾节点
             if (cur.next==null){
                 cur.next=valueNode;
                 valueNode.per=cur;
             }else {
                 cur.next.per = valueNode;
                 valueNode.next = cur.next;
                 valueNode.per = cur;
                 cur.next = valueNode;
             }
         }else {
             System.out.println("该节点已经存在");
         }
    }
    /**
     * 修改链表
     * @param node
     */
    public void updateList(HeroNoe2 node){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNoe2 cur=head;
        boolean flag=false;
        while(true){

            if(cur==null){// 没有相关节点
                break;
            }else if(cur.no==node.no){// 找到该节点
                flag=true;
                break;
            }
            cur=cur.next;// 循环下一个节点
        }

        if(flag){
            cur.name=node.name;
            cur.niceName=node.niceName;

        }else{
            System.out.println("链表中没有该节点");
        }

    }

    /**
     * 根据序号删除节点
     * @param no
     */
    public void deleteListNodeByNo(int no){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNoe2 temp=head.next;
        boolean flag=false;
        while(true){
            if(temp==null){
                break;
            }
            if(temp.no==no){
                flag=true;
                break;
            }

            temp=temp.next;
        }

        if(flag){
            temp.per.next=temp.next;
            if(temp.next!=null){
                temp.next.per=temp.per;
            }
        }else{
            System.out.println("链表中没有该节点");
        }
    }
}

// 声明节点
class HeroNoe2{
    int no;
    String name;
    String niceName;
    HeroNoe2 next;// 指向前一个节点
    HeroNoe2 per;// 指向后一个节点

    // 创建构造器
    public HeroNoe2(int no,String name,String niceName){
        this.no=no;
        this.name=name;
        this.niceName=niceName;
    }

    @Override
    public String toString() {
        return "HeroNoe2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", niceName='" + niceName + '\'' +
                '}';
    }
}