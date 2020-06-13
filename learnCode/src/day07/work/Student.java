package day07.work;

public class Student {

    private int number;
    private int state;
    private int score;

    public Student(){}

    public Student(int number){
        this.number=number;
    }
    public Student(int number,int state){
       this(number);
        this.state=state;
    }
    public Student(int number,int state,int score){
        this(number,state);
        this.score=score;

    }

    public void setNumber(int number){
        this.number=number;
    }
    public void setState(int state){
        this.state=state;
    }
    public void setScore(int score){
         this.score=score;
    }

    public int getNumber(){
        return number;
    }
    public int getState(){
        return state;
    }
    public int getScore(){
        return score;
    }

    public static void main(String []args){
      Student[]studentArr=new Student[20];

      int state=0;
      int score=0;
      for(int i=0;i<studentArr.length;i++){
          state=(int)Math.round(Math.random()*10);
          score=(int)Math.round(Math.random()*10)*10;
          studentArr[i]=new Student(i+1,state,score);
      }
      for(Student s:studentArr){
          if(s.getState()==3){
              System.out.println("学号:"+s.getNumber()+"  年级"+s.getState()+"  分数:"+s.getScore());


          }
      }

    }
}
