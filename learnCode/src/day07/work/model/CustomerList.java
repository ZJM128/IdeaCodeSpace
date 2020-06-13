package day07.work.model;

public class CustomerList {
   private Customer[] Customers;
   private int total;
   public CustomerList(){}

   public CustomerList(int total){
       Customers=new Customer[total];
   }

   public boolean addCustomer(Customer customer){

       if(total<Customers.length){
           Customers[total++]=customer;
           //total++;
          return  true;
       }
       return false;
   }

   public boolean replaceCustomer(int index,Customer customer){
          index--;
       if(index>=0 && index<total){
//           if(!"不详".equals(customer.getName())) {
//               Customers[index].setName(customer.getName());
//           }
//           if(customer.getGender()!='1') {
//               Customers[index].setGender(customer.getGender());
//           }
//           if(customer.getAge()!=-1) {
//               Customers[index].setAge(customer.getAge());
//           }
//
//           if(!"不详".equals(customer.getPhone())) {
//               Customers[index].setPhone(customer.getPhone());
//           }
//
//           if(!"不详".equals(customer.getEmail())) {
//               Customers[index].setEmail(customer.getEmail());
//           }
           Customers[index]=customer;
           return true;
       }
       return false;
   }
   public boolean deleteCustomer(int index){
       int len=total-index;
       index--;
       if(index>=0 && index<total){

           for(int i=index;i<len;i++){
               Customers[i]=Customers[i+1];
           }
           //total--;
           Customers[--total]=null;
           return true;
       }
       return false;
   }

   public Customer[]getAllCustomers(){
       Customer[] cus=new Customer[total];
       for(int i=0;i<total;i++){
           cus[i]=Customers[i];
       }
       return cus;
   }

   public Customer getCustomer(int index){
       if(index<0 || index>=total){
           return null;
       }
       return Customers[index];
   }

   public int getTotal(){
       return total;

    }


}
