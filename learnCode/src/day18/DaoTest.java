package day18;

import org.junit.Test;

import java.util.List;

public class DaoTest {
    @Test
   public void test01(){
        DAO<User>dao=new DAO<>();
        dao.save("1",new User(1,23,"李白"));
        dao.save("2",new User(2,24,"白居易"));
        dao.save("3",new User(3,25,"杜甫"));

        System.out.println(dao.get("2"));
        dao.update("2",new User(4,63,"李贺"));
        System.out.println(dao.get("2"));

        System.out.println("=====================");
        List<User> list = dao.list();
        for(User user:list){
            System.out.println(user);
        }
        dao.delete("1");

        List<User> list1 = dao.list();
        System.out.println("=====================");
        for(User user:list1){
            System.out.println(user);
        }

    }
}
