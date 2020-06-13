package com.atguigu.team.domain;

import com.atguigu.team.service.TSUtility;

public class TeamView {

    private NameListService listSvc=new NameListService();
    private TeamService teamSvc=new TeamService();

    private void enterMainMenu(){
        boolean flag=true;

        do{
            System.out.println("----------------------开发团队调度软件--------------------------------------");
            System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
            Employee[] allEmployees = listSvc.getAllEmployees();
            for (Employee allEmployee : allEmployees) {
                System.out.println(allEmployee);
            }

            System.out.println("---------------------------------------------------------------------------");
            System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
            int anInt = TSUtility.readInt();
            switch (anInt){
                case 1:
                    listAllEmployees();
                    TSUtility.readReturn();
                    break;
                case 2:
                    addMember();
                    break;
                case 3:
                    deleteMember();
                    break;
                case 4:
                    flag=false;
                    break;
            }

        }while (flag);

    }

    private void listAllEmployees(){
        System.out.println("----------------------团队列表--------------------------------------");
        Programmer[] team = teamSvc.getTeam();
        if(team.length==0){
            System.out.println("团队还没有员工");
            return;
        }
        System.out.println("TDI/ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
        for (Programmer programmer : team) {
            System.out.println("\t"+programmer.getMember()+"/"+programmer);
        }
        System.out.println("---------------------------------------------------------------------------");
    }
    private void addMember(){

        System.out.println("----------------添加成员----------------------");
        System.out.print("请输入要添加的员工ID:");
        int id = TSUtility.readInt();

        try {
            Employee employee = listSvc.getEmployee(id);
            teamSvc.addMember(employee);
            System.out.println("添加成功");
            TSUtility.readReturn();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private void deleteMember(){

        System.out.println("---------------------删除成员---------------------");
        System.out.print("请输入要删除的员工TID:");
        int id = TSUtility.readInt();
        try {

            System.out.print("确认是否删除(Y/N):");
            char c = TSUtility.readConfirmSelection();
            if(c=='Y'){
                teamSvc.removeMember(id-1);
                System.out.println("删除成功");
                TSUtility.readReturn();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        TeamView t=new TeamView();
        t.enterMainMenu();
    }

}
