package structures;

public class JosepFu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList=new CircleSingleLinkedList();
        circleSingleLinkedList.addNode(5);
        circleSingleLinkedList.showList();
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

/**
 * 构建环形链表
 */
class CircleSingleLinkedList{
    // 声明第一个节点
    Boy first=null;
    /**
     * 添加节点
     * @param num
     */
    public  void addNode(int num){
        // 先对节点进行判断
        if(num<1){
            System.out.println("节点个数太少,无法创建");
            return;
        }
        Boy boyCur=null;// 声明一个辅助节点 用于指向尾部节点
        for(int i=1;i<=num;i++){
            Boy boy=new Boy(i);
            // 构建第一个节点
            if(i==1){
                first=boy;
                first.setNext(boy);
                boyCur=first;
            }else{
                boyCur.setNext(boy);
                boy.setNext(first);
                boyCur=boy;
            }
        }
    }
    /**
     * 遍历链表
     */
    public void showList(){
        if(first==null){
            System.out.println("该链表没有数据");
            return;
        }
        Boy boyCur= first;
        while(true){
            System.out.println("小孩的编号"+boyCur.getNo());
            if(boyCur.getNext()==first){
                break;
            }
            boyCur=boyCur.getNext();
        }
    }

    /**
     * 小孩子出圈
     * @param start 从第几个开始数起
     * @param countNum 数多少个
     * @param nums 一共有多少个
     */
    public void countBoy(int start,int countNum,int nums){
        if(first==null || start<0 || nums<start){
            System.out.println("无法操作");
            return;
        }
        // 声明一个中间变量 用于找出最后一个小孩
        Boy helper=first;
        while(true){
            if(helper.getNext()==first){
                break;
            }
            helper=helper.getNext();
        }

        // 因为不确定从第几个小孩开始数起 所以先把helper 和 first 先移到相应的位置
        for(int i=0;i<start-1;i++){
            helper= helper.getNext();
            first=first.getNext();
        }

        // 开始数小孩
        while(true){
            if(helper==first){// 圈中只剩下一个小孩了
                break;
            }
            for(int i=0;i<countNum-1;i++){
                helper=helper.getNext();
                first=first.getNext();
            }
            System.out.println("小孩:"+first.getNo());

            // 小孩出圈后 要把圈重新连起来
            first=first.getNext();
            helper.setNext(first);
        }

        System.out.println("最后一个小孩是"+first.getNo());

    }
}

/**
 * 构建节点
 */
class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}