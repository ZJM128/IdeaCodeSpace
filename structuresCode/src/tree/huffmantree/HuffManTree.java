package tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 哈弗曼树
 */
public class HuffManTree {
    public static void main(String[] args) {
        int[]arr={1,3,4,6,5};
        List<TreeNoce>list=new ArrayList<>();
        for (int num : arr) {
            list.add(new TreeNoce(num));
        }

        while (list.size()>1) {
            Collections.sort(list);
            TreeNoce left = list.get(0);
            TreeNoce right = list.get(1);
            TreeNoce parent = new TreeNoce(left.getValue() + right.getValue());
            parent.setLeft(left);
            parent.setRight(right);
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        TreeNoce root=list.get(0);
        root.proOrdr();

    }
}

class TreeNoce implements  Comparable<TreeNoce>{
    private int value;
    private TreeNoce left;
    private TreeNoce right;

    public TreeNoce(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNoce getLeft() {
        return left;
    }

    public void setLeft(TreeNoce left) {
        this.left = left;
    }

    public TreeNoce getRight() {
        return right;
    }

    public void setRight(TreeNoce right) {
        this.right = right;
    }

    public String toString(){
        return value+"";
    }

    public void proOrdr(){
        System.out.println(this);
        if(this.left!=null){
            this.left.proOrdr();
        }
        if(this.right!=null){
            this.right.proOrdr();
        }
    }
    @Override
    public int compareTo(TreeNoce o) {
        return this.value-o.value;
    }
}