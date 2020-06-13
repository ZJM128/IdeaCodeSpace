package day12.exercise;

public interface FightAble {
    void specialFight();
    default void commonFight(){
        System.out.println("普通打击");
    }
}

class Soldier implements FightAble{

    @Override
    public void specialFight() {
        System.out.println("武器攻击");
    }
}
class Mage implements FightAble {

    @Override
    public void specialFight() {
        System.out.println("法术攻击");
    }
}

class Player{
    public static FightAble select(String str){
        if("武器攻击".equals(str)){
            return new Mage();
        }else if("武力角色".equals(str)){
            return new Soldier();
        }
        return null;
    }

    public static void main(String[] args) {
        FightAble select1 = select("武器攻击");
        select1.commonFight();
        select1.specialFight();
        FightAble select2= select("武器攻击");
        select2.commonFight();
        select2.specialFight();
    }
}