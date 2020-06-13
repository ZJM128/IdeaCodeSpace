package day10.test13;

public class Test13 {

    public static void main(String[] args) {

        final int EMPLOYEE = 10;//表示普通员工
        final int PROGRAMMER = 11;//表示程序员
        final int DESIGNER = 12;//表示设计师
        final int ARCHITECT = 13;//表示架构师
        String[][] EMPLOYEES = {
                {"10", "1", "段誉", "22", "3000"},
                {"13", "2", "令狐冲", "32", "18000", "15000", "2000"},
                {"11", "3", "任我行", "23", "7000"},
                {"11", "4", "张三丰", "24", "7300"},
                {"12", "5", "周芷若", "28", "10000", "5000"},
                {"11", "6", "赵敏", "22", "6800"},
                {"12", "7", "张无忌", "29", "10800","5200"},
                {"13", "8", "韦小宝", "30", "19800", "15000", "2500"},
                {"12", "9", "杨过", "26", "9800", "5500"},
                {"11", "10", "小龙女", "21", "6600"},
                {"11", "11", "郭靖", "25", "7100"},
                {"12", "12", "黄蓉", "27", "9600", "4800"}
        };
        Employee[]employees=new Employee[EMPLOYEES.length];

        for (int i = 0; i < EMPLOYEES.length; i++) {
                int index=Integer.parseInt(EMPLOYEES[i][0]);
                String id=EMPLOYEES[i][1];
                String name=EMPLOYEES[i][2];
                int age =Integer.parseInt(EMPLOYEES[i][3]);
                double salay=Double.parseDouble(EMPLOYEES[i][4]);
                switch(index){
                    case EMPLOYEE:
                        employees[i]=new Employee(id,name,age,salay);
                        break;
                    case PROGRAMMER:
                        employees[i]=new Programmer(id,name,age,salay);
                        break;
                    case DESIGNER:
                        double award=Double.parseDouble(EMPLOYEES[i][5]);
                        employees[i]=new Designer(id,name,age,salay,award);
                        break;
                    case ARCHITECT:
                        double award1=Double.parseDouble(EMPLOYEES[i][5]);
                        double count=Double.parseDouble(EMPLOYEES[i][6]);
                        employees[i]=new Architect(id,name,age,salay,award1,count);
                        break;
                }

        }

        System.out.println("编号\t姓名\t年龄\t薪资\t语言\t奖金\t股票");
        for (Employee employee : employees) {
            System.out.println(employee.getInfo());
        }
    }
}
