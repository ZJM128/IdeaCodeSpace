package day07;

public class Room {

    private String roomNo;
    private String roomArea;
    private String address;

    public Room(){}

    public Room(String roomNo){
        this.roomNo=roomNo;
    }

    public Room(String roomNo,String roomArea){
        this(roomNo);
        this.roomArea=roomArea;
    }

    public Room(String roomNo,String roomArea,String address){
        this(roomNo,roomArea);
        this.address=address;
    }

    public void setRoomNo(String roomNo){
        this.roomNo=roomNo;
    }

    public void setRoomArea(String roomArea){
        this.roomArea=roomArea;
    }

    public void setAddress(String address){
        this.address=address;
    }
     public String getRoomNo(){
        return roomNo;
     }

     public String getRoomArea(){
        return roomArea;
     }

     public String getAddress(){
        return address;
     }

     public String getDetail(){
        return "房号"+this.roomNo + "面积"+this.roomArea
                +"地址"+this.address;
     }
}
