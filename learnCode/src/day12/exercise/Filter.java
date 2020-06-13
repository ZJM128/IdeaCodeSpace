package day12.exercise;

public interface Filter {
    void filterUser(User user);
}
class V1Filter implements Filter {

    @Override
    public void filterUser(User user) {
        user.setUserType("v1");
    }
}
class V2Filter implements Filter {

    @Override
    public void filterUser(User user) {
        user.setUserType("v2");
    }
}
class AFilter implements Filter {

    @Override
    public void filterUser(User user) {
        user.setUserType("A");
    }
}
class Receptionist{
    private  Filter filter;

    public Receptionist(Filter filter) {
        this.filter = filter;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
    public void receptionUser(User user){
        if(user.getUserType()!=null){
            return;
        }
        filter.filterUser(user);
    }

    public static void main(String[] args) {
        User[]users=new User[15];
        for(int i=0;i<15;i++){
            users[i]=new User(i+1);
        }
        Filter filter=new V1Filter();
        Filter filter1=new V2Filter();
        Filter filter2=new AFilter();
        Receptionist receptionist=new Receptionist(filter);
        Receptionist receptionist1=new Receptionist(filter1);
        Receptionist receptionist2=new Receptionist(filter2);

        for(int i=0;i<5;i++){
//            receptionist.receptionUser(users[i]);
            new Filter(){
                @Override
                public void filterUser(User user) {
                    user.setUserType("v1");
                }
            }.filterUser(users[i]);


        }
        for(int i=5;i<10;i++){
            //receptionist1.receptionUser(users[i]);
            new Filter(){
                @Override
                public void filterUser(User user) {
                    user.setUserType("v2");
                }
            }.filterUser(users[i]);
        }
        for (int i = 10; i < 15; i++) {
            //receptionist2.receptionUser(users[i]);
            new Filter() {
                @Override
                public void filterUser(User user) {
                    user.setUserType("A");
                }
            }.filterUser(users[i]);
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

}