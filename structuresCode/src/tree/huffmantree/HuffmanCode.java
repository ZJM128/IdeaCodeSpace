package tree.huffmantree;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
       /* String string = "i like like like java do you like a java,do you like it.";
        byte[] bytes = string.getBytes();
        byte[] huffmanBytes = huffmanCodeZip(bytes);
        System.out.println("赫夫曼编码表:"+hashMapCode);
        System.out.println("压缩后的赫夫曼:"+Arrays.toString(huffmanBytes));
        byte[] decode = decode(hashMapCode, huffmanBytes);
        System.out.println("解压后的赫夫曼:"+new String(decode));*/
       //String srcFile="D:\\12.jpg";
       //String dstFile="D:\\13.zip";
       //zipFile(srcFile,dstFile);

        //String zipFile="D:\\13.zip";
       // String dstFile="D:\\13.jpg";
       // unZipFile(zipFile,dstFile);

    }
    //=----------------------------------------------------------------------通过赫夫曼进行文件的压缩-------------------------------------------------------------------------------

    private static void unZipFile(String zipFile,String dstFile){
        FileInputStream is=null;
        ObjectInputStream ios=null;
        FileOutputStream os = null;
        try {
            is=new FileInputStream(zipFile);
            ios=new ObjectInputStream(is);
           byte[]huffmanBytes = (byte[]) ios.readObject();
           Map<Byte,String>huffmanCode= (Map<Byte, String>) ios.readObject();
            byte[] decode = decode(huffmanCode, huffmanBytes);
            os=new FileOutputStream(dstFile);
            os.write(decode);
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
                ios.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("解压成功");
    }



    /**
     * 将文件进行压缩
     * @param srcFile 源文件
     * @param dstFile 压缩后的文件
     */
    public static void zipFile(String srcFile,String dstFile){
        FileInputStream is = null;
        FileOutputStream os=null;
        ObjectOutputStream oos = null;
        try {
            is=new FileInputStream(srcFile);
            // 创建一个和文件大小一样的 byte数组
            byte[]bytes=new byte[is.available()];
            // 读取文件
            is.read(bytes);
            // 获取压缩后的赫夫曼编码
            byte[] huffmanCodeBytes = huffmanCodeZip(bytes);

            // 创建文件输出流,存放压缩后的文件
            os=new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的objecOutputStream
            oos=new ObjectOutputStream(os);
            // 把压缩后的赫夫曼码 写入压缩文件中
            oos.writeObject(huffmanCodeBytes);
            // 也需要把赫夫曼编码表写入压缩文件中,因为解压的时候需要用到
            oos.writeObject(hashMapCode);



        }catch (Exception e){
            e.printStackTrace();
        }finally{
            // 关闭流
                try {
                    is.close();
                    os.close();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }
        System.out.println("压缩完成");
    }


    //--------------------------------------------------------------------赫夫曼解码的过程--------------------------------------------------------------------------------

    /**
     * 解码
     * @param huffmanCode 赫夫曼编码表
     * @param huffmanBytes 赫夫曼压缩后的字符数组
     * @return 解码后的字节数组
     */
    private  static  byte[] decode(Map<Byte,String>huffmanCode,byte[]huffmanBytes){
        StringBuilder stringBuilder=new StringBuilder();
        boolean flag;
        // 得到了huffmanBytes对应的二进制的字符串,
        for(int i=0;i<huffmanBytes.length;i++){
            // 判断是否是最后一个元素
            flag=i==huffmanBytes.length-1;
            stringBuilder.append(byteToBitSting(!flag,huffmanBytes[i]));
        }
        // 将赫夫曼编码表反转
        Map<String,Byte> map = new HashMap<>();
        Set<Map.Entry<Byte, String>> entries = huffmanCode.entrySet();
        for(Map.Entry<Byte,String>entry:entries){
            map.put(entry.getValue(),entry.getKey());
        }

        // 通过解码出来的二进制 查询出相对应的字节
      List<Byte>byteList=new ArrayList<>();
        for(int i=0;i<stringBuilder.length();){
            int count=1;//
            boolean breakFlag=true;
            Byte temp=null;
            while(breakFlag){
                String substr = stringBuilder.substring(i, i + count);
                temp = map.get(substr);// 如果map中有相对应的value就停止截取
                if(temp==null){
                    count++;// 没有就继续++
                }else{
                    break;// 如果map中有相对应的value就停止截取
                }
            }
            byteList.add(temp);
            i+=count;
        }

        byte[]bytes=new byte[byteList.size()];
        for(int i=0;i<byteList.size();i++){
            bytes[i]=byteList.get(i);
        }

        return bytes;
    }


    /**
     * 讲一个byte转成一个二进制的字符串
     * @param flag fla代表是否需要补高位,true需要,flase不需要
     * @param b 传入的byte
     * @return 是b对应的二进制的字符串.是补码
     */
    private static String byteToBitSting(boolean flag,byte b){
        // 将byte 转为int
        int temp=b;

        // 如果是正数需要补高位
        if(flag){
            temp|=256;
        }
        String reg=Integer.toBinaryString(temp);// 计算的是temp对应的二进制的补码
        if(flag) {
            reg = reg.substring(reg.length() - 8);
        }
        return reg;

    }





   //---------------------------------------------------------------赫夫曼压缩的过程----------------------------------------------------------------------------
    /**
     *
     * 封装的方法 获取压缩后的赫夫曼编码
     * [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * @param bytes
     * @return
     */
    public static byte[] huffmanCodeZip(byte[] bytes){
        //  统计每个字符出现的个数
        List<Node> node = getNode(bytes);
        // 变成赫夫曼树
        Node huffmanTree = hffummTree(node);
        // 将对应的赫夫曼树变成相对应的赫夫曼编码表
        HashMap<Byte, String> huffmanCode = getHuffmanCode(huffmanTree);
        // 将原始字符压缩成赫夫曼编码
        byte[] zipCode = zip(bytes, huffmanCode);
        return zipCode;

    }
    static HashMap<Byte,String>hashMapCode=new HashMap<>();
   static  StringBuilder stringBuilder=new StringBuilder();

    /**
     * 压缩过后的赫夫曼编码
     * @param bytes 原字符串字节数组
     * @param map 经过运算的赫夫曼编码表
     */
    private static byte[] zip(byte[]bytes,HashMap<Byte, String> map){
        StringBuilder stringBuilder = new StringBuilder();
        // 取出每个字节 加到StringBuilder中
        for (byte b:bytes){
            stringBuilder.append(map.get(b));
        }
        // 统计有多少个八位的字节
        int len;
        if(stringBuilder.length()%8==0){
            len=stringBuilder.length()/8;
        }else{
            len=stringBuilder.length()/8+1;
        }

        byte[]bytesHuffmanCode=new byte[len];
        int index=0;
        for(int i=0;i<stringBuilder.length();i+=8){
            String str ;
            // 最后当不足八位的 就不用加8截,有多少位就截取多少为
            if(i+8>stringBuilder.length()){
                str=stringBuilder.substring(i);
            }else{
                str=stringBuilder.substring(i,i+8);
            }
            // 以补码的形式转为相对应的byte字节存储在byte数组中
            bytesHuffmanCode[index++]=(byte) Integer.parseInt(str,2);
        }
        return bytesHuffmanCode;

    }

    /**
     * 重载了获取赫夫曼编码的方法
     * @param root
     * @return
     */
   private static HashMap<Byte,String> getHuffmanCode(Node root){
       if(root==null){
           return null;
       }
       getHuffmanCode(root.left,"0",stringBuilder);
       getHuffmanCode(root.right,"1",stringBuilder);
       return hashMapCode;
   }

    /**
     * 获取赫夫曼编码
     * @param node
     * @param code
     * @param stringBuilder
     */
    private static void getHuffmanCode(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1=new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if(node!=null){
            if(node.data==null){
                // 向左
                getHuffmanCode(node.left,"0",stringBuilder1);
                // 向右
                getHuffmanCode(node.right,"1",stringBuilder1);
            }else{
                hashMapCode.put(node.data,stringBuilder1.toString());
            }
        }


    }
     /**
     *    构建哈弗曼树
      */
    private  static Node hffummTree(List<Node> list){
        while (list.size()>1){
            Collections.sort(list);
            Node left= list.get(0);
            Node right=list.get(1);
            // 根节点的data为null
            Node parent=new Node(null,left.weight+right.weight);
            parent.left=left;
            parent.right=right;
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        // 最后一个字节 就是一颗赫夫曼树
        return list.get(0);
    }
    /**
     *  统计字符和权重
     */
    private static List<Node> getNode(byte[]bytes){
        List<Node>list=new ArrayList<>();
        Map<Byte,Integer>map=new HashMap<>();

        Integer count=0;
        for (Byte aByte : bytes) {
           count=map.get(aByte);
           if(count==null){
               map.put(aByte,1);
           }else{
               map.put(aByte,count+1);
           }

        }
        Set<Map.Entry<Byte, Integer>> entries = map.entrySet();
        for (Map.Entry<Byte, Integer> entry : entries) {
            Byte data=entry.getKey();
            int weight=entry.getValue();
             Node node=new Node(data,weight);
             list.add(node);
        }
        return list;
    }

}

/**
 * 构建二叉树
 */
class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    /**
     * 前序遍历
     */
    public void perOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.perOrder();
        }
        if(this.right!=null){
            this.right.perOrder();
        }
    }

}