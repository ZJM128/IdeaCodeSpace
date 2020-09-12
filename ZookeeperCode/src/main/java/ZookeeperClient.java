
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZookeeperClient {
    @Test
    public void test() throws IOException, KeeperException, InterruptedException {
        // 创建zookeeper对象
        // 集群的连接字符串
        String connectString="hadoop102:2181,hadoop103:2181,hadoop104:2181";
        // 连接超时时间 单位毫秒
        int sessionTimeOut=10000;
        // 参数解析:集群连接字符串    连接超时时间  当前客户端默认的监控器
        ZooKeeper zkClient=new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);
                System.out.println("默认的监控器,全局可看");
            }
        });
        // 操作zookeeper对象的数据
        final List<String> children = zkClient.getChildren("/", false);
        System.out.println(children);
        // 关闭zookeeper对象
        zkClient.close();
    }
    private String connectString;
    private int sessionTimeOut;
    private ZooKeeper zooKeeperClient;
    @Before
    public void init() throws IOException {
        connectString="hadoop102:2181,hadoop103:2181,hadoop104:2181";
        sessionTimeOut=10000;
        zooKeeperClient=new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
    @After
    public void close() throws InterruptedException {
        zooKeeperClient.close();
    }

    /**
     * 获取子节点列表,并监听
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void lsAndWatch() throws KeeperException, InterruptedException {
        List<String>child=zooKeeperClient.getChildren("/sanguo", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);
                System.out.println("lllkkkkk");
            }
        });

        System.out.println(child);
        //Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 创建节点(永久的,和短暂的)
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void create() throws KeeperException, InterruptedException {

        // 创建永久结点
        // 参数:1,节点路径  2,节点存储的数据
        // 3,节点的权限(使用Ids 选择open即可)  4,节点类型 短暂, 持久,短暂带序号 持久带序号
        final String path = zooKeeperClient.create("/atguigu", "shangguigu".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);

        // 创建暂时节点
       /* final String paths = zooKeeperClient.create("/atguigu", "hello".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(paths);*/
    }

    /**
     * 判断Znode是否存在
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void exist() throws KeeperException, InterruptedException {
        final Stat exists = zooKeeperClient.exists("/atgugui", false);
        System.out.println(exists==null?"存在":"不存在");
    }

    /**
     * 获取子节点存储的数据,不监听
     */
    @Test
    public void get() throws KeeperException, InterruptedException {
        final Stat exists = zooKeeperClient.exists("/sanguo", false);
        if(exists==null){
            System.out.println("节点不存在.....");
            return;
        }
        final byte[] data = zooKeeperClient.getData("/sanguo", false, exists);
        System.out.println(new String(data));
    }

    @Test
    public void watch() throws KeeperException, InterruptedException {
       List<String>list = zooKeeperClient.getChildren("/", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent+"66666");
            }
        });
        System.out.println(list);
        Thread.sleep(10000);
    }
    /**
     * 获取子节点存储的数据,并监听
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getAndWatch() throws KeeperException, InterruptedException {
        final Stat flag = zooKeeperClient.exists("/atguigu", true);
        if(flag==null){
            System.out.println("节点不存在");
            return;
        }
        final byte[] data = zooKeeperClient.getData("/atguigu", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent+"123456455444");
            }
        }, flag);
        System.out.println(new String(data));
        // 线程阻塞
        Thread.sleep(100000);
    }

    /**
     * 设置结点的值
     * @throws KeeperException
     * @throws Exception
     */
    @Test
    public void set() throws KeeperException, Exception {
        final Stat stat = zooKeeperClient.exists("/sanguo", false);
        if(stat==null){
            System.out.println("没有该节点");
            return ;
        }
        zooKeeperClient.setData("/sanguo","gggg".getBytes(),stat.getVersion());
    }

    /**
     * 删除结点
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void delete() throws KeeperException, InterruptedException {
        final Stat stat = zooKeeperClient.exists("/sanguo/weiguo0000000003", false);
        if(stat==null){
            System.out.println("没有该节点");
            return;
        }
        zooKeeperClient.delete("/sanguo/weiguo0000000003",stat.getVersion());
    }

    /**
     * 递归删除非空结点
     * @param path
     * @param zk
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void deleteAll(String path,ZooKeeper zk) throws KeeperException, InterruptedException {
        // 判断节点是否存在
        final Stat stat = zk.exists(path, false);
        if(stat==null){
            System.out.println("没有该节点");
            return;
        }
        // 先获取当前传入节点下的所有的子节点
        final List<String> children = zk.getChildren(path, false);
        if(children.isEmpty()){
            // 说明传入的子节点,可以直接删除
            zk.delete(path,stat.getVersion());
        }else{
            // 遍历子节点中的所有的子节点
            for (String child : children) {
                // 删除子节点,但是不知道子节点下面还有没有子节点,所以要用递归调用
                deleteAll(path+"/"+child,zk);
            }
            // 删完子节点后,需要把本身也要删除
            zk.delete(path,stat.getVersion());
        }

    }
    @Test
    public void test01() throws KeeperException, InterruptedException {
        deleteAll("/atguigu",zooKeeperClient);
    }
}
