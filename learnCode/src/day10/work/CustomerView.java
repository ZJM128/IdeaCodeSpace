package day10.work;

import org.junit.Test;

public class CustomerView {
    public static void main(String[] args) {
        CustomerView customerView=new CustomerView();
        customerView.enterMainMenu();
    }
    CustomerList customers=new CustomerList(10);

    @Test
    public void test(){
        CustomerView customerView=new CustomerView();
        customerView.enterMainMenu();
    }

    public void enterMainMenu(){
        CustomerView customerView=new CustomerView();
        boolean flag=true;
        do{
            System.out.println("-----------------客户信息管理软件-------------------");
            System.out.println("                     1,添加客户                     ");
            System.out.println("                     2,修改客户                     ");
            System.out.println("                     3,删除客户                     ");
            System.out.println("                     4,客户列表                     ");
            System.out.println("                     5,退出                     ");
            System.out.println("                     请选择(1-5)_                   ");

            char c = CMUtility.readChar();
            switch(c){
                case '1':
                    customerView.addNewCustomer();
                    break;
                case '2':
                    customerView.modifyCustomer();
                    break;
                case '3':
                    customerView.deleteCustomer();
                    break;
                case '4':
                    customerView.listAllCustomer();
                    break;
                case '5':
                    System.out.print("是否退出(y/n):");
                    char check = CMUtility.readChar();
                    if(check=='y'){
                        flag=false;
                    }
                    break;
            }


        }while(flag);
    }
    private void addNewCustomer(){
        System.out.println();
        System.out.println("-----------添加客户-----------");

        System.out.print("姓名:");
        String name = CMUtility.readString(32,"不详");
        System.out.print("性别:");
        char gender = CMUtility.readChar();

        System.out.print("年龄:");
        int age = CMUtility.readInt();

        System.out.print("电话:");
        String phone = CMUtility.readString(18);
        System.out.print("邮箱:");
        String email = CMUtility.readString(23);

        Customer customer=new Customer(name,gender,age,phone,email);
        boolean flag = customers.addCustomer(customer);

        if(flag){
            System.out.println("----------添加客户成功------------");
        }else{
            System.out.println("---------------添加客户失败--------------");
        }
    }


    private void modifyCustomer(){
        System.out.println();
        Customer customer;
        int index;
        System.out.println("--------------修改客户-----------");
        System.out.print("请选择待修改客户的编号(-1退出)");
        for(;;){
           index = CMUtility.readInt();
            if(index==-1){
                return;
            }
            customer = customers.getCustomer(index-1);
            if(customer!=null){
                break;
            }else{
                System.out.println("没有改客户");
            }
        }

        System.out.print("姓名("+customer.getName()+"):");
        String name=CMUtility.readString(23,"不详");
        if(!"不详".equals(name)){
            customer.setName(name);
        }

        System.out.print("性别("+customer.getGender()+"):");
        char gender = CMUtility.readChar('1');
        if(gender!='1'){
            customer.setGender(gender);
        }

        System.out.print("年龄("+customer.getAge()+"):");
        int age = CMUtility.readInt(-1);
        if(age!=-1){
            customer.setAge(age);
        }

        System.out.print("电话("+customer.getPhone()+")");
        String phone = CMUtility.readString(32, "不详");
        if(!"不详".equals(phone)){
            customer.setPhone(phone);
        }

        System.out.print("邮箱("+customer.getGetPhone()+")");
        String email = CMUtility.readString(32, "不详");
        boolean flag = customers.replaceCustomer(index - 1, customer);
        if(flag){
            System.out.println("---------------修改成功----------------");
        }else{
            System.out.println("----------------修改失败---------------------");
        }

    }

    public void deleteCustomer(){
        int index;
        Customer customer;
        System.out.println();
        System.out.println("-------------客户删除---------------");
        System.out.print("请选择待删除的客户(-1退出):");
        for(;;){
             index = CMUtility.readInt();
             if(index==-1){return; }
            System.out.print("确认是否删除(Y/N)");
            char cheak = CMUtility.readChar();
             if(cheak=='N'){
                 return;
             }
            customer= customers.getCustomer(index-1);
             if(customer==null){
                 System.out.println("没有改客户");
             }else{
                break;
             }

        }
        boolean flag = customers.deleteCustomer(index-1);
        if(flag){
            System.out.println("-------------删除客户成功--------------");
        }else{
            System.out.println("---------------删除客户失败------------------");
        }

    }

    private void listAllCustomer(){
        System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t电话\t\t邮箱");
        Customer[] allCustomers = customers.getAllCustomers();
        for (int i = 0; i < allCustomers.length; i++) {
            System.out.println((i+1)+allCustomers[i].getDetail());
        }
        System.out.println("-----------------客户列表完成---------------------");
    }


}
