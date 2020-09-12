package algorithm;
/*
*@Description:汉若塔 分治算法
*@author:zhijm
*@Date:2020/6/20 12:40
*/
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }
    private static void hanoiTower(int num, char a, char b, char c){
        if(num==1){
            System.out.println("第1个盘从"+a+"->"+c);
        }else{
            // 先把最上面的所有的盘A->B,移动过程会使用c
            hanoiTower(num-1,a,c,b);
            // 最下面的盘A->C
            System.out.println("第"+num+"盘从"+a+"->"+c);
            //把B塔大的所有的盘从B->C 移动过程使a
            hanoiTower(num-1,b,a,c);
        }


    }
}
