package tree.treadedbinarytree;

public class ThreadedbinaryTreaa {
    public static void main(String[] args) {
        Hero root=new Hero(1,"宋江");
        Hero hero1=new Hero(3,"吴用");
        Hero hero2=new Hero(6,"卢俊义");
        Hero hero3=new Hero(8,"关胜");
        Hero hero4 =new Hero(10,"林冲");
        Hero hero5 =new Hero(14,"李敖");
        hero1.setLeft(root);
        root.setRight(hero2);
        hero1.setLeft(hero3);
        hero1.setRight(hero4);
        hero2.setLeft(hero5);
        HeroTree heroTree=new HeroTree(root);
       // heroTree.middleOrderToIndex();
       // heroTree.middleIndex();
        //System.out.println(hero4.getRight());
        heroTree.perOrderToIndex();
        System.out.println(hero4.getRight());

       // heroTree.lastOrderToIndex();
       // heroTree.lastOrderIndex();
    }
}

class HeroTree{

    private Hero root;

    public HeroTree(Hero root) {
        this.root = root;
    }
    public void deleteNode(int no){
        if(root!=null){
            if(root.getNo()==no){
                root = null;
                System.out.println("删除成功");
            }else{
                root.deleteNode(no);
            }
        }else{
            System.out.println("二叉树为空");
        }
    }
    /**
     * 前序
     */
    public void preorder(){
        if(root==null){
            System.out.println("二叉树为空");
        }else{
            root.preorder();
        }
    }

    /**
     * 中序
     */
    public void middleOrder(){
        if(root==null){
            System.out.println("二叉树为空");
        }else{
            root.middleOrder();
        }
    }
    /**
     * 后序
     */
    public void lastOrder(){
        if(root==null){
            System.out.println("二叉树为空");
        }else{
            root.lastOrder();
        }
    }



    /**
     * 前序查找
     */
    public void preorderFind(int no){
        Hero hero=root.perOrderFind(no);
        if(hero!=null){
            System.out.println(hero);
        }else {
            System.out.println("没有找到该雇员");
        }
    }

    /**
     * 后序查找
     */
    public void lastOrderFind(int no){
        Hero hero=root.lastOrderFind(no);
        if(hero!=null){
            System.out.println(hero);
        }else {
            System.out.println("没有找到该雇员");
        }
    }

    /**
     * 中序查看
     */
    public void middleOrderFind(int no){
        Hero hero=root.middleOrderFind(no);
        if(hero!=null){
            System.out.println(hero);
        }else {
            System.out.println("没有找到该雇员");
        }
    }

    /**
     * 中序索引化
     */
    public void middleOrderToIndex(){
        root.middleOrderToIndex(root);
    }

    /**
     * 前序索引化
     */
    public void perOrderToIndex(){
        root.perOrderToIndex(root);
    }

    /**
     * 后序索引化
     */
    public void lastOrderToIndex(){
        root.lastOrderToIndex(root);
    }

    /**
     * 中序索引遍历
     */
    public void middleIndex(){
        root.middleIndex(root);
    }

    /**
     * 前序索引遍历
     */
    public  void perOrderIndex(){
        root.perOrderIndex(root);
    }

    /**
     * 后序索引遍历
     */
    public void lastOrderIndex(){
        root.lastOrderIndex(root);
    }
}
class Hero{
    private int no;
    private String name;
    private Hero left;
    private Hero right;
    private  int leftType;// 0代表指向左边的前驱结点 1代表左边的后继结点
    private int rightType;// 0代表指向右边的前驱结点 1代表右边的后继结点

    private Hero hper;//辅助节点

    public Hero(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hero getLeft() {
        return left;
    }

    public void setLeft(Hero left) {
        this.left = left;
    }

    public Hero getRight() {
        return right;
    }

    public void setRight(Hero right) {
        this.right = right;
    }


    /**
     * 中序索引化
     * @param hero 当前结点
     */
    public void middleOrderToIndex(Hero hero){
        // 如果当前结点为空则结束
        if(hero==null){
            return;
        }
        // 为什么不用判断 因为前面已经判断了
        // 向左索引化
        middleOrderToIndex(hero.left);
        // 中间的索引化
        // 这个操作是当前结点的前驱结点的赋值
        if(hero.getLeft()==null){
            hero.setLeft(hper);
            hero.setLeftType(1) ;
        }

        // 这个操作是上个结点的后继结点的赋值
        if(hper!=null && hper.getRight()==null){
            hper.setRight(hero);
            hper.setRightType(1);
        }
        // 这个操作是很重要的, 记录的是上次的结点
        hper=hero;
        // 向右索引化
        middleOrderToIndex(hero.right);

    }

    /**
     * 前序索引化
     * @param root
     */
    public void perOrderToIndex(Hero root){
        if(root==null){
            return ;
        }
        if(root.getLeft()==null){
            root.setLeft(hper);
            root.setLeftType(1);
        }
        if(hper!=null && hper.getRight()==null){
            hper.setRight(root);
            hper.setRightType(1);
        }
        hper=root;
        // 向左索引化
        if(hper.getLeftType()!=1)
        perOrderToIndex(hper.getLeft());
        // 向右索引化
        if(hper.getRightType()!=1)
        perOrderToIndex(hper.getRight());

    }

    /**
     * 后序索引化
     * @param root
     */
    public void lastOrderToIndex(Hero root){
        if(root==null){
            return;
        }
        lastOrderToIndex(root.getLeft());
        lastOrderToIndex(root.getRight());
        // 当前结点的前驱结点赋值
        if(root.getLeft()==null){
            root.setLeft(hper);
            root.setLeftType(1);
        }
        // 前一个结点的后继结点
        if(hper!=null && hper.getRight()==null){
            hper.setRight(root);
            hper.setRightType(1);
        }
        hper=root;

    }

    /**
     * 中序索引遍历
     * @param root
     */
    public void middleIndex(Hero root){
        Hero hero= root;
        while(hero!=null) {
            // 循环的找到leftType==1的结点,第一个找到就是8结点
            // 后面随着遍历而变化,因为danglefttype==1时,说明该节点是按照线索化
            // 处理后有效的结点
            while (hero.getLeftType() == 0) {
                hero=hero.getLeft();
            }
            // 打印当前结点
            System.out.println(hero);
            // 如果当前结点的有指针指向的是后继结点,就一直输出
            while (hero.getRightType() == 1) {
                // 获取当前结点的后继结点
                hero=hero.getRight();
                System.out.println(hero);
            }
            // 替换这个遍历的结点
            hero = hero.getRight();
        }

    }

    /**
     * 前序索引遍历
     * @param root
     */
    public void perOrderIndex(Hero root){
        Hero hero=root;
        while(hero!=null){
            System.out.println(hero);
            while (hero.getLeftType()==0){
                hero=hero.getLeft();
            }
            while (hero.getRightType()==1){
                hero=hero.getRight();
                System.out.println(hero);
            }
            hero=hero.getRight();
        }

    }

    /**
     * 后序索引遍历
     * @param root
     */
    public void lastOrderIndex(Hero root){
        Hero hero=root;
        while(hero!=null){
            while(hero.getLeftType()==0){
                hero=hero.getLeft();
            }
            while (hero.getRightType()==1){
                hero=hero.getRight();
                System.out.println(hero);
            }
            hero=hero.getRight();
            System.out.println(hero);
        }
    }

    /**
     * 前序遍历
     */
    public void preorder() {
        System.out.println(this);
        if(this.left!=null){
            this.left.preorder();
        }
        if(this.right!=null){
            this.right.preorder();
        }
    }

    /**
     * 中序遍历
     */
    public void middleOrder(){
        if(this.left!=null){
            this.left.middleOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.middleOrder();
        }
    }

    /**
     *
     *
     * 后序遍历
     */
    public void lastOrder(){
        if(this.left!=null){
            this.left.lastOrder();
        }
        if(this.right!=null){
            this.right.lastOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     * @param no
     * @return
     */
    public Hero perOrderFind(int no){
        if(this.no==no){
            return this;
        }
        Hero temp=null;
        if(this.left!=null){
            temp= this.left.perOrderFind(no);
        }
        if(temp!=null){
            return temp;
        }

        if(this.right!=null){
            temp=this.right.perOrderFind(no);
        }
        return temp;
    }

    /**
     * 中序查找
     * @param no
     * @return
     */
    public Hero middleOrderFind(int no){
        Hero hero=null;
        if(this.left!=null){
            hero=this.left.middleOrderFind(no);
        }
        if(hero!=null){
            return hero;
        }
        if(this.no==no){
            return this;
        }

        if(this.right!=null){
            hero=this.right.middleOrderFind(no);
        }
        return hero;
    }

    /**
     *
     * 后序查看
     * @param no
     * @return
     */
    public Hero lastOrderFind(int no) {
        Hero hero = null;
        if (this.left != null) {
            hero = this.left.lastOrderFind(no);
        }
        if (hero != null) {
            return hero;
        }

        if (this.right != null) {
            hero = this.right.lastOrderFind(no);
        }
        if (hero != null) {
            return hero;
        }

        if (this.no == no) {
            hero= this;
        }
        return hero;
    }
    public void deleteNode(int no){
        // 先删根结节点的做左结点
        if(this.left!=null && this.left.no==no){
            if(this.left.left!=null){
                this.left.left.selectLastLeftNode().left=this.left.right;
                this.left=this.left.left;
                return ;
            }
            if(this.left.right!=null){
                this.left=this.right.right;
            }

        }
        // 删除右边的结点
        if(this.right!=null && this.right.no==no){

            if(this.right.left!=null){
                this.right.left.selectLastLeftNode().left=this.right.right;
                this.right=this.right.left;
                return ;
            }
            if(this.right.right!=null){
                this.right=this.right.right;
            }
            /*this.right=null;
            return ;*/
        }

        // 两边子结点都没有则 先递归遍历左边的
        if(this.left!=null){
            this.left.deleteNode(no);
        }

        if(this.right!=null){
            this.right.deleteNode(no);
        }

    }

    /**
     * 找出最左边的子结点
     * @return
     */
    public Hero selectLastLeftNode(){
        if(this.left==null && this.right==null){
            return this;
        }
        Hero temp=null;
        if(this.left!=null){
            temp=this.left.selectLastLeftNode();
        }
        if(temp!=null){
            return temp;
        }
        if(this.right!=null){
            temp=this.right.selectLastLeftNode();
        }
        return temp;
    }
    @Override
    public String toString() {
        return "Hero{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}