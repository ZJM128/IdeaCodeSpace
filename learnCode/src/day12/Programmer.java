package day12;

public class Programmer {
    private String name;
    private int age;
    private Equipment equipment;

    public Programmer(String name, int age, Equipment equipment) {
        this.name = name;
        this.age = age;
        this.equipment = equipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(o instanceof Programmer){
            if(((Programmer) o).age==this.age && ((Programmer) o).name.equals(this.name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", equipment=" + equipment +
                '}';
    }
}
