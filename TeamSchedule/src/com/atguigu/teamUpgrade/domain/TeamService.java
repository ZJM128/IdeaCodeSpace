package com.atguigu.teamUpgrade.domain;

import com.atguigu.teamUpgrade.service.Status;

import java.util.ArrayList;
import java.util.List;

public class TeamService {
    private static int counter=1;
    private final int MAX_MEMBER=5;

    //private Programmer[]team=new Programmer[MAX_MEMBER];
    private List<Programmer>team=new ArrayList<>(MAX_MEMBER);


    public List<Programmer> getTeam(){
        return team;
    }

    /**
     * 失败信息包含以下几种：

     *
     *
     *
     * @param employee
     */
    public void addMember(Employee employee) throws EmployeeException {
        if(team.size()>=MAX_MEMBER){
            throw new EmployeeException("成员已满，无法添加");
        }
        if(!(employee instanceof Programmer)){
            throw new EmployeeException("该成员不是开发人员，无法添加");
        }
        Programmer programmer=(Programmer) employee;
        if("VOCATIN".equals(programmer.getStatus().getName())){
            throw new EmployeeException("该员正在休假，无法添加");
        }else if("BUSY".equals(programmer.getStatus().getName())){
            throw new EmployeeException("该员工是已经是其他团队成员");
        }
        boolean flag=true;
        for(int i=0;i<team.size();i++){
            if(team.get(i).getId()==programmer.getId()){
                flag=false;
                break;
            }
        }
        if(!flag){
            throw new EmployeeException("该员工是团队成员");
        }
            int countA=0,countD=0,countP=0;
            for(int i=0;i<team.size();i++){
                if(team.get(i) instanceof Architect){
                    countA++;
                }else if(team.get(i) instanceof Designer){
                    countD++;
                }else if(team.get(i) != null){
                    countP++;
                }
            }

            if(programmer instanceof Architect){
                if(countA>=1){
                    throw new EmployeeException("团队中只能有一名架构师");
                }
            }else if(programmer instanceof Designer){
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
            team.add(programmer);

    }


    public void removeMember(int memberId) throws EmployeeException {

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

        team.remove(memberId);
    }
}
