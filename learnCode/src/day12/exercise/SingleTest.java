package day12.exercise;

public class SingleTest {
}
class SingleHungry{
    private SingleHungry singleHungry=new SingleHungry();

    private SingleHungry(){}

    public SingleHungry getSingleHungry(){
        return singleHungry;
    }
}

class SingleLazy{
    private SingleLazy singleLazy=null;

    private SingleLazy(){}

    public SingleLazy getSingleLazy(){
        if(singleLazy==null){
            singleLazy=new SingleLazy();
        }
        return singleLazy;
    }
}