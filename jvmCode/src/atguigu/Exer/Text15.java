package atguigu.Exer;
/*
分析以下需求，并用代码实现：
(1)打印出四位数字中个位+百位=十位+千位并且个位数为偶数，千位数为奇数的数字,并打印符合条件的数字的个数
(2)符合条件的数字,每行显示5个,用空格隔开,打印格式如下:
1012 1034 1056 1078 1100
1122 1144 1166 1188 1210
//......
符合条件的数字总共有: 165个
 */
public class Text15 {
    public void add(int a,double b){}
    public void add(double c,int i){}
    public static void main(String[] args) {
        int count=0;
        for(int i=1000;i<=9999;i++){
            int thousand=i/1000;
            int hundred=i/100%10;
            int decade=i/10%10;
            int unit=i%10;
            if((unit+hundred==thousand+decade) && (unit%2==0) &&(thousand%2!=0)){
                count++;
                System.out.print(i+" ");
                if(count%5==0){
                    System.out.println();
                }
            }

        }
        System.out.println(count);
    }
}
