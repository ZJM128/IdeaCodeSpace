package tree;

public class BinaryTree {
    public static void main(String[] args) {
        Hero hero1=new Hero(1,"宋江");
        Hero hero2=new Hero(2,"吴用");
        Hero hero3=new Hero(3,"卢俊义");
        Hero hero4=new Hero(4,"林冲");
        Hero hero5=new Hero(5,"关胜");
        Hero hero6=new Hero(6,"李敖");
        Hero hero7=new Hero(7,"孙正义");

        hero1.setLeft(hero2);
        hero1.setRight(hero3);
        hero3.setRight(hero4);
        hero3.setLeft(hero5);
        hero5.setLeft(hero6);
        hero5.setRight(hero7);
        HeroTree heroTree=new HeroTree(hero1);
        //heroTree.preorder();// 前序
       // heroTree.middleOrder();// 中序
      // heroTree.lastOrder();
        //heroTree.preorderFind(2);
        //heroTree.middleOrderFind(2);
        System.out.println("前序");
        heroTree.preorder();
        heroTree.deleteNode(3);
        System.out.println("----------------------");
        System.out.println("前序");
        heroTree.preorder();

    }
}
class HeroTree{

    private Hero root;

    public HeroTree(Hero root) {
        this.root = root;
    }

   /* *//**
     * 前序输出
     * @param hero
     *//*
    public void preorder(Hero hero){
        System.out.println(hero);
        if(hero.getLeft()!=null){
            this.preorder(hero.getLeft());
        }
        if(hero.getRight()!=null){
            this.preorder(hero.getRight());
        }
    }

    *//**
     * 中序输出
     * @param hero
     *//*
    public void middleOrder(Hero hero){
        if(hero.getLeft()!=null){
            middleOrder(hero.getLeft());
        }
        System.out.println(hero);

        if(hero.getRight()!=null){
            middleOrder(hero.getRight());
        }
    }

    *//**
     * 后序遍历
     * @param
     *//*
    public void lastOrder(Hero hero){
        if(hero.getLeft()!=null){
            lastOrder(hero.getLeft());
        }
        if(hero.getRight()!=null){
            lastOrder(hero.getRight());
        }
        System.out.println(hero);
    }*/

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
}
class Hero{
    private int no;
    private String name;
    private Hero left;
    private Hero right;

    public Hero(int no, String name) {
        this.no = no;
        this.name = name;
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