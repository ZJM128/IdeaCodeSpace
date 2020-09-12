package com.atguigu.team.domain;

import com.atguigu.team.service.Status;

public class TeamService {
    private static int counter=1;
    private final int MAX_MEMBER=5;

    private Programmer[]team=new Programmer[MAX_MEMBER];

    private int total=0;

    public Programmer[] getTeam(){
        Programmer[]temp=new Programmer[total];
        for(int i=0;i<total;i++){
            temp[i]=team[i];
        }
        return temp;
    }

    /**
     * 失败信息包含以下几种：

     *
     *
     *
     * @param employee
     */
    public void addMember(Employee employee) throws EmployeeException{
        if(total>=MAX_MEMBER){
            throw new EmployeeException("成员已满，无法添加");
        }
        if(!(employee instanceof Programmer)){
            throw new EmployeeException("该成员不是开发人员，无法添加");
        }
        Programmer programmer=(Programmer) employee;
        if("VOCATIN".equals(programmer.getStatus().getName())){
            throw new EmployeeException("该员正在休假，无法添加");
        }else if("BUSY".equals(programmer.getStatus().getName())){
            throw new EmployeeException("该员工是已经是团队成员");
        }
        boolean flag=true;
        for(int i=0;i<total;i++){
            if(team[i].getId()==programmer.getId()){
                flag=false;
                break;
            }
        }
        if(!flag){
            throw new EmployeeException("该员工是团队成员");
        }
            int countA=0,countD=0,countP=0;
            for(int i=0;i<total;i++){
                if(team[i] instanceof  Architect){
                    countA++;
                }else if(team[i] instanceof  Designer){
                    countD++;
                }else if(team[i] instanceof Programmer){
                    countP++;
                }
            }

            if(programmer instanceof  Architect){
                if(countA>=1){
                    throw new EmployeeException("团队中只能有一名架构师");
                }
            }else if(programmer instanceof  Designer){
                if(countD>=2){
                    throw new EmployeeException("团队中只能有两名设计师");
                }
            }else{
                if(countP>=3){
                    throw new EmployeeException("团队中只能有三名程序员");
                }
            }

            programmer.setMember(counter++);
            programmer.setStatus(Status.BUSY);
            team[total++]=programmer;

    }


    public void removeMember(int memberId) throws EmployeeException{

        boolean flag=true;
        for (Programmer programmer : team) {
            if(programmer.getMember()==memberId){
                programmer.setStatus(Status.FREE);
                flag=false;
                break;
            }
        }
        if(flag){
            throw new EmployeeException("没有该团队成员");
        }

        for(int i=memberId;i<total-1;i++){
            team[i]=team[i+1];
        }
        team[--total]=null;
    }
}
