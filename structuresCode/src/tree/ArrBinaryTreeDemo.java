package tree;

/*
*@Description:顺序存储二叉树
*@author:zhijm
*@Date:2020/6/6 23:37
*/
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {

        int[]arr={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree=new ArrBinaryTree(arr);
        arrBinaryTree.perOrder();
        System.out.println("======================");
        arrBinaryTree.middleOrder();

        System.out.println("======================");
        arrBinaryTree.lastOrder();
    }

}

class ArrBinaryTree{

    private int[]arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    public void perOrder(){
        perOrder(0);
    }

    public void middleOrder(){
        this.middleOrder(0);
    }
    /**
     * 顺序存储二叉树的前序遍历
     * @param index
     */
    public void perOrder(int index){
        System.out.println(arr[index]);
        if( arr!=null && 2*index+1<arr.length){
            perOrder(2*index+1);
        }
        if(arr!=null && 2*index+2<arr.length){
            perOrder(index*2+2);
        }
    }

    public void lastOrder(){
        lastOrder(0);
    }

    /**
     *顺序存储的中序遍历
     * @param index
     */
    public void middleOrder(int index){
        if(arr!=null && 2*index+1<arr.length){
            middleOrder(2*index+1);
        }
        System.out.println(arr[index]);
        if(arr!=null && 2*index+2<arr.length){
            middleOrder(2*index+2);
        }
    }

    /**
     * 后序
     * @param index
     */
    public void lastOrder(int index){
        if(arr!=null && 2*index+1<arr.length){
            lastOrder(2*index+1);
        }
        if(arr!=null && 2*index +2 <arr.length){
            lastOrder(2*index+2);
        }
        System.out.println(arr[index]);
    }

}