package algorithm;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[]w={1,4,3};
        int[] val={1500,3000,2000};
        int m=4;
        int n=val.length;

        // 表示在前i个物品中能装入容量为就j的背包中最大的价值
        int[][]v=new int[n+1][m+1];
        int[][]path=new int[n+1][m+1];
        // 初始化第一行和第一列 默认为0
        for(int i=0;i<v.length;i++){
            v[i][0]=0;
        }

        for(int i=0;i<v[0].length;i++){
            v[0][i]=0;
        }

        for(int i=1;i<v.length;i++){
            for(int j=1;j<v[0].length;j++){
                if(w[i-1]>j){// 如果当前放入背包的重量大于背包承受的重量,则放入前一个物品
                    v[i][j]=v[i-1][j];
                }else{
                    // 如果不是则放入当前物品和之前放入的物品的能放下的最大的物品
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if(v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j]=1;
                    }else{
                        v[i][j]=v[i-1][j];
                    }
                }
            }
        }
        for(int i=0;i<v.length;i++){
            for(int j=0;j<v[i].length;j++){
                System.out.print(v[i][j]+"\t");
            }
            System.out.println();
        }

        int i=path.length-1;
        int j=path[0].length-1;
        while(i>0 && j>0){
            if(path[i][j]==1){
                System.out.print("第"+i+"商品放入了背包");
                j-=w[i-1];
            }
            System.out.println();
            i--;
        }
    }
}
