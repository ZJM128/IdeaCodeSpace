package atguigu.work04;

public class FamilyAccount {
    public static void main(String[] args) {

       String tableStr="-----------------家庭收支记账软件-----------------\n"+
                "\n\t\t\t\t\t1 收支明细"+"\n\t\t\t\t\t2 登记收入"+"\n\t\t\t\t\t3 登记支出"
                +"\n\t\t\t\t\t4 退出\n"+"\n\t\t\t\t\t请选择(1-4):_";
        int balance=10000;
        String allStr="";
        boolean flag=true;
        while (flag){
            System.out.print(tableStr);
            char cha=Utility.readMenuSelection();
            switch (cha){
                case '1':
                    System.out.println("-----------------当前收支明细记录-----------------");
                    System.out.println("\t\t收支\t"+"账户金额\t\t"+"收支金额\t\t"+"说明");
                    System.out.println(allStr);
                    System.out.println();
                    System.out.println("--------------------------------------");
                    break;
                case '2':
                    System.out.print("本人收入金额:");
                    int income=Utility.readNumber();
                    System.out.print("本次收入说明:");
                    String incomeRemark=Utility.readString();
                    balance+=income;
                    allStr=allStr+"\n\t\t收入\t\t"+balance+"\t\t"+income+"\t\t"+incomeRemark;
                    break;
                case '3':
                    System.out.print("本人收入金额:");
                    int expend=Utility.readNumber();
                    System.out.print("本次收入说明:");
                    String expendRemark=Utility.readString();
                    balance-=expend;
                    allStr=allStr+"\n\t\t支出\t\t"+balance+"\t\t"+expend+"\t\t"+expendRemark;
                    break;
                case '4':
                    System.out.println("确定是否退出(Y/N):");
                    char commit=Utility.readConfirmSelection();
                    if(commit=='Y'){
                        flag=false;
                    }
                    break;
            }
        }
    }
}
