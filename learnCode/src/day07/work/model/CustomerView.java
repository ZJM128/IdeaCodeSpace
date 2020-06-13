package day07.work.model;

public class CustomerView {
    private CustomerList customers=new CustomerList(2);

    public static void enterMainMenu(){
        CustomerView view=new CustomerView();
        String str="-----------客户信息管理软件------------\n"
                +"\n\t\t\t1添加客户\n\t\t\t2修改客户\n\t\t\t3删除客户\n\t\t\t4客户列表\n\t\t\t5退\t出"+
                "\n\n\t\t\t请选择(1-5):_";
           boolean flag=true;
           do{
               System.out.println(str);
               char  select = CMUtility.readMenuSelection() ;
               switch(select){
                   case '1':
                       view.addNewCustomer();
                       break;
                   case '2':
                       view.modifyCustomer();
                       break;
                   case '3':
                       view.deleteCustomer();
                       break;
                   case '4':
                       view.listAllCustomers();
                       break;
                   case '5':
                       System.out.println("确定退出?(Y/N)");
                       char check=CMUtility.readChar();
                       if('Y'==check){
                           return;
                       }
                       flag=false;
               }
           }while(flag);


    }

    public void addNewCustomer(){
        System.out.println("-----------添加客户------------");
        System.out.print("姓名:");
        String name=CMUtility.readString(32,"不详");
        System.out.print("性别:");
        char gender=CMUtility.readChar();
        System.out.print("年龄:");
        int age=CMUtility.readInt(-1);
        System.out.print("电话:");
        String phone=CMUtility.readString(32,"不详");
        System.out.print("邮箱:");
        String email=CMUtility.readString(32,"不详");
        Customer customer =new Customer(name,gender,age,phone,email);
        if(customers.addCustomer(customer)){
            System.out.println("-----------添加完成------------");
        }else{
            System.out.println("-----------客户已满------------");
        }
    }

    public void modifyCustomer(){
        System.out.println("-----------修改客户------------");
        Customer Customer=null;
        int index;
        for(;;) {
            System.out.print("请选择待修改客户编号(-1退出)");
            index = CMUtility.readInt(-1);
            if (index == -1) {
                System.out.println("-----------客户没有找到------------");
                return;
            }

            Customer = customers.getCustomer(index - 1);
            if (Customer == null) {
                System.out.println("没有编号为:"+index+"的客户");
            }else{
                break;
            }
        }
        System.out.print("姓名("+Customer.getName()+"):");
        String nameModify=CMUtility.readString(32,"不详");
        if(!"不详".equals(nameModify)){
            Customer.setName(nameModify);
        }
        System.out.print("性别("+Customer.getGender()+"):");
        char genderModify=CMUtility.readChar('1');
        if(((char)genderModify)!='1'){
            Customer.setGender(genderModify);
        }
        System.out.print("年龄("+Customer.getAge()+"):");
        int ageModify=CMUtility.readInt(-1);
        if(ageModify!=-1){
            Customer.setAge(ageModify);
        }
        System.out.print("电话("+Customer.getPhone()+"):");
        String phoneModify=CMUtility.readString(32,"不详");
        if(!"不详".equals(phoneModify)){
            Customer.setPhone(phoneModify);
        }
        System.out.print("邮箱("+Customer.getEmail()+"):");
        String emailModify=CMUtility.readString(32,"不详");
        if(!"不详".equals(emailModify)){
            Customer.setEmail(emailModify);
        }

        if(customers.replaceCustomer(index,Customer)){
            System.out.println("-----------修改完成------------");
        }else{
            System.out.println("-----------修改失败------------");
        }
    }

    public void deleteCustomer(){
        System.out.println("-----------删除客户------------");
        int deleteIndex;
        Customer Customer;
        for(;;) {
            System.out.print("请选择待删除客户编号(-1退出)");
            deleteIndex = CMUtility.readInt(-1);
            if (deleteIndex == -1) {
                System.out.println("-----------删除失败-----------");
                return;
            }

            Customer = customers.getCustomer(deleteIndex - 1);
            if (Customer == null) {
                System.out.println("没有编号为:"+deleteIndex+"的客户");
            }else{
                break;
            }
        }
        System.out.println("确定删除?(Y/N)");
        char check = CMUtility.readChar();
        if ('N' == check) {
            return;
        }
        if(customers.deleteCustomer(deleteIndex)){
            System.out.println("-----------删除完成-------------");
        }else{
            System.out.println("-----------删除失败-----------");
        }
    }

    public void listAllCustomers(){
        System.out.println("----------------------------客户列表-----------------------------");
        System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t电话\t\t邮箱");
        Customer[] Customers=customers.getAllCustomers();
        System.out.println("");
        for(int i=0;i<Customers.length;i++){
            if(Customers[i]!=null) {
                System.out.println((i + 1) + "\t\t\t" + Customers[i].getName() + "\t\t" + Customers[i].getGender() + "\t\t\t" + Customers[i].getAge() + "\t\t\t" + Customers[i].getPhone() + "\t\t\t" + Customers[i].getEmail());
            }
        }
        System.out.println("----------------------------客户列表完成-----------------------------");
    }

    public static void main(String[]args){
        enterMainMenu();
    }
}
