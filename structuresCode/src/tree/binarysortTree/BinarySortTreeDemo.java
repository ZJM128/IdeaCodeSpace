package tree.binarysortTree;

/*
*@Description:二叉排序树
*@author:zhijm
*@Date:2020/6/17 13:55
*/
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        BinarySortTree binarySortTree=new BinarySortTree();
        int[]arr={7,3,10,12,5,1,9};
        for(int i=0;i<arr.length;i++){
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.middleOrder();
        System.out.println("删除后");
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(1);
        binarySortTree.delNode(10);

        binarySortTree.middleOrder();
    }
}

class BinarySortTree{
    Node root;

    /**
     * 添加二叉排序树的结点
     * @param node 要添加的结点
     */
    public void add(Node node){
        if(root==null){
            root = node;
        }else {
            root.add(node);
        }
    }

    public void delNode(int value){
        if(root==null){
            System.out.println("没有元素");
            return;
        }
        // 查找需要删除的那个元素
        Node node = searchNode(value);
        if(node==null){
            System.out.println("二叉树中没有找到相对应的元素");
            return ;
        }
        // 此时说明要找的结点只有一个结点
        if(root.right==null && root.left==null){
            root=null;
            return ;
        }
        Node parentNode = searchParent(value);
        // 该结点是叶子结点的情况
        if(node.left==null && node.right==null){
            // 判断当前结点是父节点的左结点还是右结点
            if(parentNode.left!=null && parentNode.left.value==value){// 左结点
                parentNode.left=null;
            }else if(parentNode.right!=null && parentNode.right.value==value){// 右结点
                parentNode.right=null;
            }

        }else if(node.left!=null && node.right!=null){// 有两个结点
           // node.value= searchMinToRight(node.right); 从右子结点找到最小的值
            node.value=searchMaxToLeft(node.left);// 从左子结点找出最大的值

        }else{// 只有一个结点的 要么是左结点,要么就是右结点
            if(node.left!=null){
                //需要判断当前的结点是父节点的左结点还是右结点
                if(parentNode!=null) {// 证明删除的数根节点
                    if (parentNode.left != null && parentNode.left.value == value) {// 左结点
                        parentNode.left = node.left;
                    } else if (parentNode.right != null && parentNode.right.value == value) {// 右结点
                        parentNode.right = node.left;
                    }
                }else{
                    root=node.left;
                }
            }else{
                //需要判断当前的结点是父节点的左结点还是右结点
                if(parentNode!=null) {
                    if (parentNode.left != null && parentNode.left.value == value) {
                        parentNode.left = node.right;
                    } else if (parentNode.right != null && parentNode.right.value == value) {
                        parentNode.right = node.right;
                    }
                }else{
                    root=node.right;
                }
            }
        }

    }

    public int searchMaxToLeft(Node node){
        Node temp=node;
        while(temp.right!=null){
            temp=temp.right;
        }
        delNode(temp.value);
        return temp.value;
    }
    /**
     * 从右结点查找最小的结点返回 并删除
     * @param node 需要删除的root结点
     * @return 返回右子节点中最小的结点
     */
    public int searchMinToRight(Node node){
        Node temp=node;
        while(temp.left!=null){
            temp=temp.left;
        }
        // 删除该节点
        delNode(temp.value);
        return temp.value;
    }
    /**
     * 查找要删除的结点
     * @param value
     * @return
     */
    public Node searchNode(int value){
        if (root == null) {
            return null;
        }else {
            return root.searchNode(value);
        }
    }

    /**
     * 查找要删除结点的父结点
     * @param value
     * @return
     */
    public Node searchParent(int value){
        if(root==null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }
    /**
     * 中序遍历
     */
    public void middleOrder(){
        if(root==null){
            System.out.println("没有元素");
            return;
        }else{
            root.middleOrder();;
        }
    }
}
/**
 * 创建二叉排序树的节点
 */
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加二叉排序数节点
     * @param node
     */
    public void add(Node node){
        if(node==null){
            return;
        }
        if(node.value<this.value){
            if(this.left==null){
                this.left=node;
            }else{
                this.left.add(node);
            }
        }else{
            if(this.right==null){
                this.right=node;
            }else{
                this.right.add(node);
            }
        }
    }

    /**
     * 查找删除的结点
     * @param value 要删除的结点值
     * @return
     */
    public Node searchNode(int value){
        if(this.value==value){
            return this;
        }else if(this.value>value){//往左查找
            if(this.left==null){
                return null;
            }
            return  this.left.searchNode(value);
        }else{
            if(this.right==null){
                return null;
            }
            return this.right.searchNode(value);
        }
    }

    /**
     * 查找当前结点的父节点
     * @param value
     * @return
     */
    public Node searchParent(int value){
        if((this.left!=null &&this.left.value==value) ||(this.right!=null && this.right.value==value)){
            return this;
        }else {
            if (this.left != null && this.value > value) {
                return this.left.searchParent(value);
            }else if(this.right!=null && this.value<value){
               return  this.right.searchParent(value);
            }else{
                return null;
            }
        }
    }

    public void middleOrder(){
        if(this.left!=null){
            this.left.middleOrder();;
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.middleOrder();
        }
    }

}