package day15.structures.com.atguigu.team.domiain;
import static day15.structures.com.atguigu.team.service.Data.*;

public class NameListService {
   private Employee[]employees;

   public NameListService(){
       // 创建数组
       String[][]employeeList=EMPLOYEES;
       employees=new Employee[employeeList.length];
       // 初始化数组
       for(int i=0;i<employeeList.length;i++){
           int flag=Integer.parseInt(employeeList[i][0]);
           int id=Integer.parseInt(employeeList[i][1]);
           String name=employeeList[i][2];
           int age =Integer.parseInt(employeeList[i][3]);
           double salary=Double.parseDouble(employeeList[i][4]);
           switch (flag){
               case EMPLOYEE:
                   employees[i]=new Employee(id,name,age,salary);
                   break;
               case PROGRAMMER:
                   employees[i]=new Programmer(id,name,age,salary,getEquipment(i));
                   break;
               case DESIGNER:
                   double bonus= Double.parseDouble(employeeList[i][5]);
                   employees[i]=new Designer(id,name,age,salary,bonus,getEquipment(i));
                   break;
               case ARCHITECT:
                   double bonus1= Double.parseDouble(employeeList[i][5]);
                   int stock=Integer.parseInt(employeeList[i][6]);
                   employees[i]=new Architect(id,name,age,salary,bonus1,stock,getEquipment(i));
                   break;
           }
       }
   }
    private Equipment getEquipment(int i){
        String[][]eqipments=EQIPMENTS;
        int index=Integer.parseInt(eqipments[i][0]);
        Equipment equipment=null;
        switch (index){
            case PC:
                equipment=new PC(eqipments[i][1],eqipments[i][2]);
                break;
            case NOTEBOOK:
                equipment=new NoteBook(eqipments[i][1],Double.parseDouble(eqipments[i][2]));
                break;
            case PRINTER:
                equipment=new Printer(eqipments[i][1],eqipments[i][2]);
        }
        return equipment;
    }
   public Employee[]getAllEmployees(){
       return employees;
   }
   public Employee getEmployee(int id)throws EmployeeException{
       for (Employee employee : employees) {
           if(employee.getId()==id){
               return employee;
           }
       }
       throw new EmployeeException("找不到指定的员工");
   }
}
