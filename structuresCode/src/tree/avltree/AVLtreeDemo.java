package tree.avltree;

/*
*@Description:平衡二叉树
*@author:zhijm
*@Date:2020/6/17 18:20
*/
public class AVLtreeDemo {
    public static void main(String[] args) {
        //int[] arr={4,3,6,5,7,8};
        //int[] arr={10,12,8,9,7,6};
        int[] arr={10,11,7,6,8,9};
        AVLTree avlTree= new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.middleOrder();
        System.out.println("高度");
        System.out.println(avlTree.getRoot().getHeight());
        System.out.println("左高度");
        System.out.println(avlTree.getRoot().getLeftHeight());
        System.out.println("右高度");
        System.out.println(avlTree.getRoot().getRightHeight());
        System.out.println(avlTree.getRoot());

    }
}
class  AVLTree{
    Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

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

    /**
     * 从左节点中查找最大的结点返回 并删除当前结点
     * @param node 最大的结点
     * @return
     */
    private int searchMaxToLeft(Node node){
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
    private Node searchNode(int value){
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
    private Node searchParent(int value){
        if(root==null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }
    /**
     * 中序遍历
     */
    void middleOrder(){
        if(root==null){
            System.out.println("没有元素");
        }else{
            root.middleOrder();;
        }
    }
}
class Node{
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 计算左子结点的高度
     * @return 左子结点的高度
     */
    int getLeftHeight(){
        if(left==null){
            return 0;
        }
        return left.getHeight();
    }

    /**
     * 计算右子结点的高度
     * @return 右子结点的高度
     */
    int getRightHeight(){
        if(right==null){
            return 0;
        }
        return right.getHeight();
    }
    /**
     * 计算当前结点的高度
     * @return 当前结点的高度
     */
    int getHeight(){
        return Math.max((left==null?0:left.getHeight()),(right==null?0:right.getHeight()))+1;
    }

    /**
     * 左旋转
     */
    private void leftRotate(){
        // 创建新的结点,以当前根结点的值
        Node newNode=new Node(value);
        // 把新的结点的左子树设置成当前结点的左子树
        newNode.left=left;
        // 把新的结的右子树设置成当前自结点的右子树的左结点
        newNode.right=right.left;
        // 把当前结点的值替换成右子结点的值
        value=right.value;
        // 把当前结点的右子树设置成当前结点右子树的右子树
        right=right.right;
        //把当前结点的左子树(左子结点)设置成新的结点
        left=newNode;
    }



    private void rightRotate(){
        // 创建一个新的结点 值等于当前结点的值
        Node newNode=new Node(value);
        // 把新结点的的右子树设置成当前结点的右子树
        newNode.right=right;
        // 把新结点的左子树设置为当前结点的左子树的右子结树
        newNode.left=left.right;
        // 把当前结点的值换为左子结点的值
        value=left.value;
        // 把当前结点的左子树设置成左子树的左子树
        left=left.left;
        // 把当前结点的右子树设置为新结点
        right=newNode;
    }

    /**
     * 添加二叉排序数节点
     * @param node 需要添加的结点
     */
    void add(Node node){
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

        if(getRightHeight()-getLeftHeight()>1){// 当前右子树的高度大于左子树 实现左旋转操作
            // 如果当前结点的右子树的左子树的高度大于右子树的高度
            if(right!=null && right.getLeftHeight()>right.getRightHeight()){
                // 先对当前结点的右子树进行右旋转,
                right.rightRotate();
                // 再对当前结点进行左旋转
                leftRotate();
            }else {
                leftRotate();
            }
            return;// 必须加!!!!!
        }

        if(getLeftHeight()-getRightHeight()>1){//当前左子树的高度大于右子树 实现右旋转操作
           // 如果当前结点的左子树的右子树的高度大于左子树的高度
            if(left!=null && left.getRightHeight()>left.getLeftHeight()){
                // 当前结点的左子树进行左旋转
                left.leftRotate();
                // 再对当前结点进行右旋转
                rightRotate();
            }else {
                rightRotate();
            }
        }
    }

    /**
     * 查找删除的结点
     * @param value 要删除的结点值
     * @return
     */
    Node searchNode(int value){
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
     * @param value 当前结点
     * @return 返回当前结点的父结点
     */
    Node searchParent(int value){
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

    void middleOrder(){
        if(this.left!=null){
            this.left.middleOrder();;
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.middleOrder();
        }
    }

}