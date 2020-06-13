package com.atguigu.team.domain;

import com.atguigu.team.service.Status;

public class Programmer extends Employee{

    private  int memberId;
    private Status status=Status.FREE;
    private Equipment equipment;

    public Programmer() {
    }

    public Programmer(int id, String name, int age, double sayaly) {
        super(id, name, age, sayaly);

    }

    public Programmer(int id, String name, int age, double sayaly,  Equipment equipment) {
        super(id, name, age, sayaly);
        this.equipment = equipment;
    }


    public int getMember() {
        return memberId;
    }

    public void setMember(int member) {
        this.memberId = member;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return getDetail()+"\t\t"+positionName.PROGRAMMER.getName()+"\t"+status.getName()+"\t\t\t\t\t"+equipment.getDescription() ;

    }
}
