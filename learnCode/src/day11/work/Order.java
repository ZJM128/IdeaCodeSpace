package day11.work;

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
    public boolean equals(Object o){
        if(o==this){
            return true;
        }

        if( o instanceof Order){
            if(((Order) o).getOrderId()==this.getOrderId() && ((Order) o).getOrderName().equals(this.getOrderName())){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Order order=new Order(2,"kafka");
        Order order1=new Order(2,"kafka");
        System.out.println(order.equals(order1));// 从object中继承的equals比较的是地址值
    }
}
