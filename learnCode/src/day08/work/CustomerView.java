package day08.work;

public class CustomerView {
    public static void main(String []args){
         CustomerView view =new CustomerView();

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
                        flag=false;
                        return;
                    }
                    break;
                default:
                    System.out.println("没有可执行的");

            }
        }while(flag);



    }

    private CustomerList customers=new CustomerList(10);

    public void enterMainMenu(){

    }

    public void addNewCustomer(){
        System.out.println("----------添加客户----------");
        System.out.print("姓名:");
        String name=CMUtility.readString(32);

        System.out.print("性别");
        char gender=CMUtility.readChar();

        System.out.print("年龄:");
        int age=CMUtility.readInt(32);

        System.out.print("电话:");
        String phone=CMUtility.readString(32);

        System.out.print("邮件:");
        String email=CMUtility.readString(32);

        Customer customer=new Customer(name,gender,age,phone,email);

        if(customers.addCustomer(customer)){
            System.out.println("----------添加完成----------");
        }else{
            System.out.println("----------添加失败----------");
        }

    }

    public void modifyCustomer(){
        System.out.println("----------修改客户----------");
        Customer customer;
        int index;
        for(;;){
            System.out.print("请选择待修改客户编号(-1退出)");
            index=CMUtility.readInt();
             if(-1==index){
                return ;
             }
            customer= customers.getCustomer(index-1);
             if(customer==null){
                 System.out.println("没有该客户");
             }else{
                 break;
             }
        }

        System.out.print("姓名:("+customer.getName()+")");
        String name=CMUtility.readString(32,"不详");
        if(!"不详".equals(name)){
            customer.setName(name);
        }

        System.out.print("性别("+customer.getGender()+")");
        char gender=CMUtility.readChar('1');
        if(gender!=-1){
            customer.setGender(gender);
        }
        System.out.print("年龄:("+customer.getAge()+")");
        int age=CMUtility.readInt(-1);
        if(age!=-1){
            customer.setAge(age);
        }

        System.out.print("电话:("+customer.getPhone()+")");
        String phone=CMUtility.readString(32,"不详");
        if(!"不详".equals(phone)){
            customer.setPhone(phone);
        }

        System.out.print("邮件("+customer.getEmail()+"):");
        String email=CMUtility.readString(32,"不详");
        if(!"不详".equals(email)){
            customer.setEmail(email);
        }
        if(customers.replaceCustomer(index-1,customer)){
            System.out.println("----------修改完成----------");
        }else{
            System.out.println("----------修改失败----------");
        }


    }

    private void deleteCustomer(){
        System.out.println("----------删除客户----------");
        int index;
        for(;;){
            System.out.print("请选择待删除客户编号(-1退出)");
            index=CMUtility.readInt();
            if(-1==index){
                return ;
            }
           Customer customer= customers.getCustomer(index-1);
            System.out.println("确定退出?(Y/N)");
            char check=CMUtility.readChar();
            if('N'==check){
                return;
            }
            if(customer==null){
                System.out.println("没有该客户");
            }else{
                break;
            }
        }

        if(customers.deleteCustomer(index-1)){
            System.out.println("----------删除客户----------");
        }else{
            System.out.println("----------删除失败----------");
        }


    }

    private void listAllCustomers(){
        System.out.println("-------------客户列表--------------");
        System.out.println("编号\t姓名\t年龄\t电话号码\t邮件\t");
        Customer[] allCustomer = customers.getAllCustomer();
        if(allCustomer!=null) {
            for (int i = 0; i < allCustomer.length; i++) {
                System.out.println((i + 1) + "\t" + allCustomer[i].toString());
            }
        }else{
            System.out.println("没有客户");

        }
    }



}
