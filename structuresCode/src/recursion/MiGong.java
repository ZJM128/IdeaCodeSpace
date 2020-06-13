package recursion;

/**
 * 求最短路径 修改策略即可
 */
public class MiGong {
    public static void main(String[] args) {
        int[][]map=new int[8][7];
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        for(int j=0;j<8;j++){
            map[j][0]=1;
            map[j][6]=1;
        }
        map[3][1]=1;
        map[3][2]=1;

        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("迷宫出炉图");
        go(map,1,1);
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }


    }

    public static boolean go(int[][]map,int i,int j){
        if(map[6][5]==2){
            return true;
        }else{
            if(map[i][j]==0){// 等于零证明可以走下一步 策略是 下 右 上 左 1为墙 2为已经走过 能走通 3为走不通
                map[i][j]=2;
                if(go(map,i+1,j)){
                    return true;
                }else if(go(map,i,j+1)){
                    return true;
                }else if(go(map,i-1,j)){
                    return true;
                }else if(go(map,i,j-1)){
                    return true;
                }else{
                    map[i][j]=3;
                    return false;
                }
            }else{
                // 1 2 3都是走不通的
                return false;
            }
        }
    }
}
