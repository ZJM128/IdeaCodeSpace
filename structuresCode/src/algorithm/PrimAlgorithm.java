package algorithm;

import java.util.Arrays;

/*
 *@Description:普利姆算法,生成最小树
 *@author:zhijm
 *@Date:2020/6/27 19:56
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {35, 10000, 10000, 9, 10000, 10000, 3},
                {17, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        MGraph graph=new MGraph(verxs);
        MinTree minTree=new MinTree();
        minTree.createGraph(graph,verxs,data,weight);
        minTree.showGraoh(graph);
        // 普利姆算法
        minTree.prim(graph,0);

    }
}

class MinTree {
    /**
     * 初始化图
     *
     * @param gaph   图对象
     * @param vers   图对应的顶点数
     * @param data   图的各个顶点值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph gaph, int vers, char[] data, int[][] weight) {
        for (int i = 0; i < vers; i++) {// 顶点
            gaph.data[i] = data[i];
            for (int j = 0; j < vers; j++) {
                gaph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 展示图
     * @param graph 图的对象
     */
    public void showGraoh(MGraph graph) {
        for (int[] data : graph.weight) {
            System.out.println(Arrays.toString(data));
        }
    }

    /**
     * 普利姆算法实现
     * @param graph 图对象
     * @param v 表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph,int v){
        // 声明标志结点是否被访问过
        int[]visited=new int[graph.vers];// 默认是0

        // 把当前这个结点标志为已经访问过了
        visited[v]=1;
        // 声明 h1和h2 记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        int middleNum=10000;
        for(int k=1;k<graph.vers;k++){// 普利姆算法 有n-1条边
            // 遍历整个已经找过的顶点,和其相邻的未找过的顶点,两个顶点连成的边中 找出权值最小的
            for (int i=0;i< graph.vers;i++){// 已经找过的边
                for(int j=0;j<graph.vers;j++){// 和已经找过的边的相邻的还未找过的边
                    //   visited[i]==1 已经找过的顶点 和 visited[j]==0 未找过的顶底 ,
                    //   graph.weight[i][j]<middleNum代表这两个顶点是否可以连通,可以连通着替换middleNum,然后找到最小的值
                    if(visited[i]==1 && visited[j]==0 && graph.weight[i][j]<middleNum){
                        middleNum=graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            // 找到了一条最小的边
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+">"+"权值"+middleNum);
            // 标志已经找到的条
            visited[h2]=1;
            // 重置中间值
            middleNum=10000;
        }

    }
}

class MGraph {
    int vers;// 图的结点数
    char[] data;// 存放结点数据
    int[][] weight;// 存放边,就是邻接矩阵

    public MGraph(int vers) {
        this.vers = vers;
        data = new char[vers];
        weight = new int[vers][vers];
    }
}