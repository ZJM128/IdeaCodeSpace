package day08.work;

import java.util.Random;
import java.util.Scanner;

public class Test01 {
    public static void main(String[]args){

        //printTest01();
        //printTest02();
        //printTest03();
       // statisticsLetter();
        //dealNumber();
        //checkArrayIsSymmetry();
        //compareArray();
       // printScore();
        //printScore1();
        //selectIndexByName();
        //wordTest();
       // randomToStr();
        //YangHui();
        //PrintCard();
        printStudent();

       /* int[] arr1={1,23};
        int[] arr2={5,6};
        testParam(arr1,arr2);*/

    }

    private static void printTest01() {
        int []arr=new int[10];

        for(int i=0;i<arr.length;i++){
            arr[i]=(int)Math.round(Math.random()*100);
        }

        for(int num:arr){
            System.out.print(num+" ");
        }
    }

    private static void printTest02(){
        String []str={"黑桃","红桃","梅花","方片"};
            String []str1={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[]str3=new String[52];
        int count=0;
        for(int i=0;i<str.length;i++){
            for(int j=0;j<str1.length;j++){
                str3[count]=str[i]+str1[j];
                count++;
            }
        }

        for(int i=0;i<str3.length;i++){
            System.out.print(str3[i]+" ");
            if((i+1)%13==0){
                System.out.println();
            }

        }
    }

    private static void printTest03(){
        String []str={"黑桃","红桃","梅花","方片"};
            String []str1={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[]str3=new String[52];
        int count=0;
        for(int i=0;i<str.length;i++){
            for(int j=0;j<str1.length;j++){
                str3[count]=str[i]+str1[j];
                count++;
            }
        }


        System.out.print(str3[1-1]+"\t");

        System.out.print(str3[5-1]+"\t");

        System.out.print(str3[50-1]+"\t");

    }

    private static void statisticsLetter(){
        char [] chArr=new char[]{'a','l','f','m','f','o','b','b','s','n'};

        int count[]=new int[26];
        for(int i=0;i<chArr.length;i++){
            count[chArr[i]-97]++;
        }
        for(int i=0;i<count.length;i++){
            System.out.println((char)(i+97)+"----"+count[i]);
        }

    }

    public static void dealNumber(){
        int[] arr={95, 92, 75, 56, 98, 71, 80, 58, 91, 91};

        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        int average=sum/arr.length;

        int count=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>average){
                count++;
            }
        }

        System.out.println("高于平均值:"+average+"个数有"+count+"个");

    }

    public static void checkArrayIsSymmetry(){
        int[] arr={1,2,3,4,3,2,6};
        int count=0;
        boolean flag=false;
        for(int i=0;i<arr.length/2;i++){
            if(arr[i]==arr[arr.length-1-i]){
                count++;
            }
        }
        if(count==arr.length/2){
            flag=true;
        }

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println("是否对称:"+flag);
    }

    public static void compareArray(){
        int[] arr={1,2,3,4,3,2,6};
        int[] arr1={1,2,3,4,3,2,6};
        boolean flag=false;
        int count=0;
        if(arr.length==arr1.length){
            for(int i=0;i<arr.length;i++){
                arr[i]=arr1[i];
                count++;
            }
            if(count==arr.length){
                flag=true;
            }
        }

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        for(int i=0;i<arr1.length;i++){
            System.out.print(arr1[i]+" ");
        }
        System.out.println();
        System.out.println("是否一致:"+flag);
    }

    public static void printScore(){
        char[] answer={'A','D','B','C','D',};

        char[] c={'D','C','B','A','D',};
        char[] c1={'A','D','B','C','D',};
        char[] c2={'A','D','B','C','A',};
        char[] c3={'A','B','C','D','D',};
        int[] count=new int[4];
        String[] names={"小尚","小谷","小小","大大"};
            for (int i = 0; i < answer.length; i++) {
                if (answer[i] == c[i]) {
                    count[0]+=2;
                }
            }


        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == c1[i]) {
                count[1]+=2;
            }
        }
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == c2[i]) {
                count[2]+=2;
            }
        }
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == c3[i]) {
                count[3]+=2;
            }
        }
        for(int i=0;i<names.length;i++){
            System.out.println(names[i]+":得分"+count[i]);
        }

    }
    public static void printScore1(){
        Scanner sc=new Scanner(System.in);

        System.out.print("请输入学员人数:");
        int number=sc.nextInt();
        int[] scores=new int[number];
        System.out.println("请输入"+number+"个分数");
        for(int i=0;i<number;i++){
            scores[i]=sc.nextInt();
        }

        for(int i=0;i<scores.length-1;i++){
            for(int j=0;j<scores.length-1-i;j++){
                if(scores[j]<scores[j+1]){
                    scores[j]=scores[j]^scores[j+1];
                    scores[j+1]=scores[j]^scores[j+1];
                    scores[j]=scores[j]^scores[j+1];
                }
            }
        }
        System.out.println("分数从高到低");
        for(int socre:scores){
           System.out.print(socre+" ");
        }
    }

    public static void selectIndexByName(){
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入学员人数:");
        int number=sc.nextInt();
        String[] names=new String[number];
        System.out.println("请输入"+number+"学生名字");
        for(int i=0;i<number;i++){
            names[i]=sc.next();
        }

        System.out.print("请输入要查询的名字:");
        String name=sc.next();
        System.out.println();
        for(int i=0;i<names.length;i++){
            if(name.equals(names[i])){
                System.out.println("下标是:"+i);
                break;
            }
        }
    }

    public static void wordTest(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入单词");
        String word=sc.next();
        char[] ch=word.toCharArray();
        boolean flag=false;
        for(char words:ch){
            if(words=='a'){
                flag=true;
                break;
            }
        }

        if(flag){
            System.out.println("存在a");
        }else{
            System.out.println("不存在a");
        }
    }

    public static void randomToStr(){
        String []arrStr=new String[10];
        int num;
        Random r=new Random();
        for(int i=0;i<arrStr.length;i++){
            arrStr[i]="";
            for(int j=0;j<6;j++){
                while(true){
                    num=r.nextInt(123);

                    if(num>=48 && num<=57){
                        break;
                    }else if(num>=65 && num<=90){
                        break;
                    }else if(num>=97 && num<=122){
                        break;
                    }
                }
                arrStr[i]+=(char)(num);
            }

        }

        for(String str:arrStr){
            System.out.println("验证码是:"+str);
        }
    }

    public static void YangHui(){
        int[][]arr=new int[10][];

        for(int i=0;i<arr.length;i++){
            arr[i]=new int[i+1];
            arr[i][0]=1;
            arr[i][i]=1;
            if(i>1){
                for(int j=1;j<arr[i].length-1;j++){
                    arr[i][j]=arr[i-1][j-1]+arr[i-1][j];
                }
            }
        }

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void PrintCard(){
       String[][]cards=new String[2][];

       cards[0]=new String[4];
       cards[1]=new String[13];

       cards[0][0]="♠";
       cards[0][1]="♥";
       cards[0][2]="♣";
       cards[0][3]="♦";

       cards[1][0]="A";
       for(int i=1;i<=9;i++){
           cards[1][i]=i+1+"";
       }
        cards[1][10]="J";
        cards[1][11]="Q";
        cards[1][12]="K";

        for(int i=0;i<cards[0].length;i++){
            for(int j=0;j<cards[1].length;j++){
                System.out.print(cards[0][i]+cards[1][j]+"\t");
            }
            System.out.println();
        }
    }

    public static void printStudent(){

      Scanner sc=new Scanner(System.in);
       System.out.print("请输入有多少组");
       int num=sc.nextInt();
        System.out.println();
        System.out.print("请输入每组有多少人");
        int num1=sc.nextInt();
        int[][]arr=new int[num][num1];
        for(int i=0;i<num;i++){
            System.out.println("请输入第"+(i+1)+"组每个同学的成绩");
            for(int j=0;j<num1;j++){
                System.out.println("请输入"+(j+1)+"个同学的成绩");
                arr[i][j]=sc.nextInt();
            }
        }

        for(int i=0;i<num;i++){
            for(int j=0;j<num1;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        // 最高分
        int[] maxScore=new int[num];
        int max;
        for(int i=0;i<arr.length;i++){
            max=arr[i][0];
            for(int j=0;j<arr[i].length;j++){
                if(max<arr[i][j]){
                    max=arr[i][j];
                }
            }
            maxScore[i]=max;
        }

        for(int i=0;i<maxScore.length;i++){
            System.out.println("第"+(i+1)+"组最高分数 "+maxScore[i]);
        }

        // 每组的最小值
        int[]minScore=new int[num];
        int min;
        for(int i=0;i<arr.length;i++){
            min=arr[i][0];
            for(int j=0;j<arr[i].length;j++){
                if(min>arr[i][j]){
                    min=arr[i][j];
                }
            }
            minScore[i]=min;
        }

        for(int i=0;i<minScore.length;i++){
            System.out.println("第"+(i+1)+"组最小值为"+minScore[i]);
        }

        // 每组的平均值
        double[] averages=new double[num];
        int sum=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                sum+=arr[i][j];
            }
            averages[i]=sum/arr[i].length;
            sum=0;
        }

        for(int i=0;i<averages.length;i++){
            System.out.println("第"+(i+1)+"组的平均值"+averages[i]);
        }

        // 全班的最高分和最低分
        int allStudentMaxScore=arr[0][0];
        int allStudentMinScore=arr[0][0];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(allStudentMaxScore<arr[i][j]){
                    allStudentMaxScore=arr[i][j];
                }
            }
        }

        System.out.println("全班最高分:"+allStudentMaxScore);

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(allStudentMinScore>arr[i][j]){
                    allStudentMinScore=arr[i][j];
                }
            }
        }

        System.out.println("全班最低分:"+allStudentMinScore);

        // 平均分
        int scoreSum=0;
        int numberOfPeople=0;
        //int scoreAverages=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                scoreSum+=arr[i][j];
                numberOfPeople++;
            }
        }

        System.out.println("全班平均分"+scoreSum/numberOfPeople);
        System.out.println("总人数:"+numberOfPeople);
        System.out.println("完成作业");
    }

    public static void testParam(int[]...arrays){
        for(int[] arr:arrays){
            for(int num:arr){
                System.out.print(num+" ");
            }
            System.out.println();
        }
    }



}
