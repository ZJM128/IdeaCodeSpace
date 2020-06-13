package day11;

public class PersonTest {
    public static void main(String[] args) {
        Student student=new Student("张三",35,"123");
        Student student1=new Student("李四",25,"128");
        Student student2=new Student("王五",65,"153");

        Teacher teacher=new Teacher("赵柳",65,"2");
        Teacher teacher1=new Teacher("pod",75,"3");

        Person[]people=new Person[5];
        people[0]=student;
        people[1]=student1;
        people[2]=student2;
        people[3]=teacher;
        people[4]=teacher1;

        for (Person person : people) {
            System.out.println(person.say());
        }
        System.out.println("----------------------");
        boolean flag=false;
       for(int i=0;i<people.length-1;i++){
           for(int j=0;j<people.length-1-i;j++){
               if(people[j].getAge()>people[j+1].getAge()){
                  Person temp=people[j];
                  people[j]=people[j+1];
                  people[j+1]=temp;
                  flag=true;
               }
           }

           if(!flag){
               break;
           }else {
               flag=false;
           }
       }

        for (Person person : people) {
            System.out.println(person.say());
        }
    }
}
