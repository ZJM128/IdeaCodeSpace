package com.atguigu.teamUpgrade.domain;

public class Architect extends Designer {
    private int stock;


    public Architect(int id, String name, int age, double sayaly, double bonis, int stock, Equipment equipment) {
        super(id, name, age, sayaly,bonis,equipment);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
       return  getDetail()+"\t\t"+ positionName.ARCHITECT.getName()+"\t"+getStatus().getName()+"\t"+getBonis()+"\t"+stock+"\t"+getEquipment().getDescription();

    }
}
