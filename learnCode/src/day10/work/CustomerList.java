package day10.work;
/*
*@Description:客户管理类
*@author:zhijm
*@Date:2020/6/4 17:10
*/
public class CustomerList {
    private Customer[]customers;
    private int total;

    public CustomerList(int size) {
        customers=new Customer[size];
    }
    public boolean addCustomer(Customer customer){
        if(total>customers.length){
            return false;
        }
        customers[total++]=customer;
        return true;
    }

    public boolean replaceCustomer(int index,Customer customer){
        if(index<0 || index>total){
            return false;
        }
        customers[index]=customer;
        return true;
    }

    public boolean deleteCustomer(int index){
        if(index<0 || index>total){
            return false;
        }
        for(int i=index;i<total-1;i++){
            customers[index]=customers[i+1];
        }
        customers[total]=null;
        total--;
        return true;
    }
    public Customer[]getAllCustomers(){
        Customer[]temp=new Customer[total];
        for (int i=0;i<total;i++){
            temp[i]=customers[i];
        }
        return temp;
    }

    public Customer getCustomer(int index){
        if(index<0 ||index>total){
            return null;
        }
        return customers[index];
    }
}
