package day14;



public class StringTest {


    public void test01(){

        StringBuffer stringBuffer = new StringBuffer("12365");
        stringBuffer.append(123)
                .append(true)
                .append("ggg")
                .append(12.9f)
                .append(344);
        System.out.println(stringBuffer);

        stringBuffer.insert(1,"hello");
        System.out.println(stringBuffer);
        // 原字符串从[start,end) 包头不包尾
        stringBuffer.replace(2,4,"cgBBBBBB");
        System.out.println(stringBuffer);

        stringBuffer.insert(1,"fjfj");
        System.out.println("insert==>"+stringBuffer);
        int index = stringBuffer.indexOf("h");
        System.out.println(index);
        int index2 = stringBuffer.lastIndexOf("h");
        System.out.println("lastIndexOf"+index2);


        // 从右往左找,第一个参数是要找的字符串,第二个参数是从哪个索引开始往左找
        int index1 = stringBuffer.lastIndexOf("h" ,0);
        System.out.println(index1);

        // 包括头 不包括尾
        String substring = stringBuffer.substring(1, 3);
        System.out.println(substring);

        // 也是包括头 不包括尾的 也可以这边理解 从索引为零处 开始删除2ge
        StringBuffer delete = stringBuffer.delete(1, 2);
        System.out.println("删除后的"+delete);

         //删除指定位置的字符 删除,添加操作都是对原字符串起作用的
        System.out.println(stringBuffer.deleteCharAt(1));
        System.out.println("原来的"+stringBuffer.toString());
    }

    public void test03(){
        String str="123";
        String substring = str.substring(1);
        System.out.println(substring);
    }
}
