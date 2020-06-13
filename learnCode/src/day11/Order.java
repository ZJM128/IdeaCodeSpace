package day11;

public class Order {
    private int orderId;
    private String orderName;

    public Order() {
    }

    public Order(int orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    public boolean equals(Object object){
        if(object instanceof Order){
            if(this==object){
                return true;
            }

            if(((Order) object).getOrderName().equals(this.getOrderName()) && ((Order) object).getOrderId()==this.getOrderId()){
                return true;
            }else{
                return false;
            }

        }else{
            return false;
        }
    }
    public String toString(){
        return orderId+" "+orderName;
    }
}
