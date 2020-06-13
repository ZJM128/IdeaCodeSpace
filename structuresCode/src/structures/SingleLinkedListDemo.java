package structures;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode h1=new HeroNode(1,"宋江","及时雨");
        HeroNode h2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode h3=new HeroNode(3,"吴用","智多星");
        HeroNode h4=new HeroNode(4,"林冲","豹子头");
        SingleLinkedList list=new SingleLinkedList();
        list.add(h1);
        list.add(h2);
        list.add(h3);
        list.add(h4);
//
//        list.addDateByNo(h3);
//        list.addDateByNo(h1);
//        list.addDateByNo(h2);
//        list.addDateByNo(h4);

//
//        list.showList();
//        HeroNode h5=new HeroNode(1,"小小用","智多星&&&");
//        System.out.println("---------");
//        list.updateNode(h5);
//        list.showList();
//
//        list.deleteNode(h5);
//        System.out.println("---------");
//        list.showList();
//        System.out.println("有效节点数"+list.getListLength(list.getHead()));
//        System.out.println(list.getIndexNode(list.getHead(), 2));
        list.showList();
        System.out.println("================");
        HeroNode h5=new HeroNode(4,"小小用","智多星&&&");
        list.updateNode(h5);
        list.showList();
        ///list.reversalShowList(list.getHead());
    }

}

// 定义链表
class SingleLinkedList{

    private HeroNode head=new HeroNode(0,"","");

    // 把头结点暴露
    public HeroNode getHead(){
        return head;
    }

    /**
     添加元素
     */
    public void add(HeroNode node){
        // 初始化 头结点
        HeroNode temp=head;
        // 遍历链表 得到链表的尾部
        while(true){
            // 得到链表最后一个节点
            if(temp.next==null){
                break;
            }
            // 继续循环
            temp=temp.next;
        }
        temp.next=node;

    }

    /**
     *   按照序号进行添加
     */
    public void addDateByNo(HeroNode node){

        HeroNode temp=head;
        boolean flag=false;
        while(true){
            // 链表为最后一个元素时退出循环
            if(temp.next==null){
                break;
            }else if(temp.next.no>node.no){// 找到了合适的位置
                break;
            }else if(temp.next.no==node.no){
                flag=true;
                break;
            }
            // 当都不满足以上 三种情况 则进行循环
            temp=temp.next;
        }

        if(!flag){
            node.next=temp.next;
            temp.next=node;
        }else{
            System.out.println("该元素已经存在了");
        }

    }

    /**
     *
     * 借助序号对元素进行修改,但序号不能进行修改
     * @param newNode
     */
    public void updateNode(HeroNode newNode){
        // 如果链表为空的话 结束修改
        if(head.next==null){
            System.out.println("链表为空");
            return ;
        }
        // 声明一个变量 用于标志找到了相应的元素
        boolean flag=false;// 默认没有找到
        HeroNode temp=head.next;
        while(true){
            // 链表到了尾部 也就是最后也没有找到 相应的元素
            if(temp==null){
                break;
            }else if(temp.no==newNode.no){//表示已经找到了
                flag=true;
                break;
            }

            temp=temp.next;// 继续循环
        }

        if(flag){
            temp.name=newNode.name;
            temp.niceName=newNode.niceName;
        }else{
            System.out.println("没有相应的元素");
        }
    }

    /**
     * 通过序号进行删除
     * @param node
     */
    public void deleteNode(HeroNode node){
        HeroNode temp=head;
        boolean flag=false;
        while(true){
            if(temp.next==null){
                break;
            }else if(temp.next.no==node.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }

        if(flag){
            temp.next=temp.next.next;
        }else{
            System.out.println("没有啦");
        }
    }
    // 遍历链表
    public void showList(){
        HeroNode temp=head.next;
        while(true){
            // 链表的结束
            if(temp==null){
                break;
            }
            // 输出
            System.out.println(temp);
            // 继续循环
            temp=temp.next;
        }
    }

    /**
     * 获取有效节点的个数
     * @param head
     * @return
     */
    public int getListLength(HeroNode head){

        // 如果链表为空 则返回零
        if(head.next==null){
            return 0;
        }
        // 头结点不算是有效的节点 所以从第一个开始算起
        HeroNode cur=head.next;
        int count=0;
        while(true){
            if(cur==null){
                break;
            }
            count++;
            cur=cur.next;
        }
        return count;
    }

    /**
     * 获取倒数第k个的节点
     * @param head
     * @param index
     * @return
     */
    public HeroNode getIndexNode(HeroNode head,int index){
        if(head.next==null){
            return null;
        }
        // 遍历链表 获取有效的节点数
        int size=getListLength(head);
        // 对索引进行校验
        if(index<0 || index>size){
            System.out.println("无效的数字");
            return null;
        }
        HeroNode temp=head.next;
        for(int i=0;i<size-index;i++){
            temp=temp.next;
        }
        return temp;
    }

    /**
     * 对链表进行反转
     * @param head
     */
    public void reversalList(HeroNode head){
        // 如果是空链表 或一个数据的话直接返回
        if(head.next==null || head.next.next==null){
            System.out.println("无需反转");
            return;
        }

        HeroNode reversal=new HeroNode(0,"","");
        HeroNode next=null; // 用于存储当前节点的下一个节点
        HeroNode cur=head.next; // 当前节点
        while(cur!=null){
            next=cur.next;
            cur.next=reversal.next;
            reversal.next=cur;
            cur=next;
        }
        head.next=reversal.next;

    }

    // 利用栈 先进后出的原理 对链表进行反向输出
    public void reversalShowList(HeroNode head){

        HeroNode cur=head.next;
        Stack<HeroNode> stack=new Stack<>();
        while(cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
         while(stack.size()>0){
             System.out.println(stack.pop());
         }
    }


}

// 定义链表的结构
class HeroNode{
    int no;
    String name;
    String niceName;
    HeroNode next;
    public HeroNode(int num,String name,String niceName){
        this.no=num;
        this.name=name;
        this.niceName=niceName;
    }
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", niceName='" + niceName + '\''  +
        '}';
    }
}