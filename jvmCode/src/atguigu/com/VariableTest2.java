package atguigu.com;

public class VariableTest2 {
    public static void  main(String []args){
        // 自动类型转
        byte by=123;
        // 注意byte的范围是-128~127 超出了这个范围的值就是int类型了
        //byte by2=156;
        short s1=by;// 自动类型转换
        System.out.println(s1);

        int i=100;
        int i2=i+by;// 自动类型转换(自动升级) byte类型自动转为int 然后再运算
        System.out.println(i2);

        // 注意
        long l1=1235479L;
        float f=1.2F;
        //long l2=l1+f; 虽然long是64位 float是32位 但是float计算机存储的时候是比long大的
        // 所以自动转换位float
        float f2=l1+f;
        System.out.println(f2);

        // 强制转换 有可能失去精度
        int i3=297;
        byte by1=(byte)i3;// 导致精度掉失
        System.out.println(by1);

        float f3=(float)1.3;// 默认是double类型 需要强转

    }
}
