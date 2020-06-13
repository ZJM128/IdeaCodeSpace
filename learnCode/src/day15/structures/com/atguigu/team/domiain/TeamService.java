package day15.structures.com.atguigu.team.domiain;

import day15.structures.com.atguigu.team.service.Status;

public class TeamService {

    private static int counter=1;
    private static final int MAX_MEMBER=5;
    private Programmer[] team=new Programmer[MAX_MEMBER];
    private int total;

    public Programmer[]getTeam(){
        Programmer[]temp=new Programmer[total];
        for (int i = 0; i < total; i++) {
            temp[i]=team[i];
        }
        return temp;
    }

    /**
     * 该
     *
     *
     *
     * @param e
     */
    public void addMember(Employee e) throws EmployeeException{
        if(total>=team.length){
            throw new EmployeeException("成员已满，无法添加");
        }
        if(!(e instanceof Programmer)){
            throw new EmployeeException("该成员不是开发人员，无法添加");
        }

        Programmer programmer= (Programmer) e;
        switch (programmer.getStatus()){
            case BUSY:
                throw new EmployeeException("该员已是团队成员");
            case VOCATIN:
                throw new EmployeeException("该员正在休假，无法添加");
        }

        for(int i=0;i<total;i++){
            if(team[i].getId()==programmer.getId()){
                throw new EmployeeException("员工已是团队成员");
            }

        }

        int countA=0,countD=0,countP=0;
        for(int i=0;i<total;i++){
            if(team[i] instanceof  Architect){
                countA++;
            }else if(team[i] instanceof Designer){
                countD++;
            }else if(team[i] instanceof  Programmer){
                countP++;
            }
        }

        if(programmer instanceof  Architect){
            if(countA>=1){
                throw new EmployeeException("团队中只能有一名架构师");
            }
        }else if( programmer instanceof  Designer){
            if(countD>=2){
                throw new EmployeeException("团队中只能有两名设计师");
            }
        }else {
            if(countP>=3){
                throw new EmployeeException("团队中只能有三名程序员");
            }
        }
        programmer.setMember(counter++);
        programmer.setStatus(Status.BUSY);
        team[total++]=programmer;

    }

    public void removeMember(int memberId) throws EmployeeException{
        int i;
        for(i=0;i<total;i++){
            if(team[i].getMember()==memberId){
                break;
            }
        }
        if(i==total){
            throw new EmployeeException("团队没有该成员");
        }
        team[i].setStatus(Status.FREE);
        for(int j=memberId;j<total-1;j++){
            team[j]=team[j+1];
        }
        team[--total]=null;

    }

}
