package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<String> vertexList; // 存储顶点的集合
    private int[][]edges; // 存储图对应的邻接矩阵
    private int numOfEdges; // 表示边的数目
    private  boolean[] visAble;
    public Graph(int n){
        edges=new int[n][n];
        vertexList=new ArrayList<>(n);
        visAble=new boolean[n];
    }

    public static void main(String[] args) {
        int n=5;
        String[] vertexs={"A","B","C","D","E"};
        Graph graph= new Graph(n);
        for (String vertex : vertexs) {
            graph.addVertex(vertex);
        }
        // 添加边
        graph.buildGraph(0,1,1);
        graph.buildGraph(0,2,1);
        graph.buildGraph(1,2,1);
        graph.buildGraph(1,3,1);
        graph.buildGraph(1,4,1);

        graph.showGraph();

        graph.dfs();
    }

    /**
     * 得到第一个邻接结点的下标
     * @param index
     * @return 如果存在就返回对应的下标,否则就返回-1
     */
    public int getFirstNeighbor(int index){
        for(int i=0;i<vertexList.size();i++){
            if(edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接结点的下标来获取下一个邻接结点
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1,int v2){
        for(int i=v2+1;i<vertexList.size();i++){
            if(edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历算法
     * @param isVisited
     * @param i
     */
    public void dfs(boolean[]isVisited,int i){
        // 首先访问该结,输出
        System.out.print(vertexList.get(i)+"-->");
        // 将结点设置为已经访问
        isVisited[i]=true;

        // 查找结点i的第一个邻接结点
        int w=getFirstNeighbor(i);
        while(w!=-1){
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            // 如果w结点已经访问过
            w=getNextNeighbor(i,w);
        }

    }

    /**
     * 对dfd重载,遍历我们所有的结点,并进行dfs
     */
    public void dfs(){
        // 遍历所有的结点,进行dfs回溯
        for(int i=0;i<vertexList.size();i++){
            if(!visAble[i]){
                dfs(visAble,i);
            }
        }
    }
    /**
     * 获取顶点数
     * @return 顶点的数目
     */
    private  int getNumOfVertex(){
        return vertexList.size();
    }

    /**
     * 获取边数
     * @return 边的数量
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     * 获取结点i对应的数据
     * @param index 索引值
     * @return 对应的数据
     */
    public String getVertex(int index){
        return vertexList.get(index);
    }

    /**
     * 获取v1和v2的权值
     * @return 权值
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    /**
     * 展示邻接矩阵
     */
    public void showGraph(){
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
    /**
     * 添加顶点
     * @param vertex 顶点
     */
    private void addVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 构建图
     * @param v1 顶点的下标
     * @param v2 顶点的下标
     * @param weight 权值 1为有边
     */
    public void buildGraph(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }
}
