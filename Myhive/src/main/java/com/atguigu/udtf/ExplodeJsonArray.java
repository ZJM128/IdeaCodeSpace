package com.atguigu.udtf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 需求:传入的是一个json数组对象,多个json对象
 */
@Description(name = "explode_json_array", value = " - explode json array .... edit by zhijm")
public class ExplodeJsonArray extends GenericUDTF {

    /**
     * 作用:
     *  1,对输入的数据做检测
     *      a,参数个数满足
     *      b,参数的类型
     *  2,返回一个输出类型的检测器
     *  
     * @param argOIs
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        // 1,对输入的数据作检测
            //1.1 参数个数的检测
         List<? extends StructField> inputFields = argOIs.getAllStructFieldRefs();
        if(inputFields.size()!=1){
            throw new UDFArgumentException("explode_json_array 函数的参数个数必须是1个,你现在传递的个数是:"+inputFields.size());
        }
        //1.2 参数的类型的检测
         ObjectInspector io = inputFields.get(0).getFieldObjectInspector();
        // json是string类型,是一个基本类型
        if(io.getCategory()!= ObjectInspector.Category.PRIMITIVE || !"string".equals(io.getTypeName())){
            throw new UDFArgumentException("explode_json_array 函数的参数类型必须是string类型,你现在传递的是"+io.getTypeName());
        }
        // 返回一个输出类型的检测器
         ArrayList<String> names = new ArrayList<>();
        names.add("item");
         ArrayList<ObjectInspector> ois = new ArrayList<>();
        ois.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        // 有两个参数,一个是列名,一个是列的类型
        return ObjectInspectorFactory.getStandardStructObjectInspector(names,ois);
    }

    /**
     * 处理业务逻辑
     * [{},{}]=>{},{}
     * @param args
     * @throws HiveException
     */
    @Override
    public void process(Object[] args) throws HiveException {
        // 参数中只有一个参数
        String jsonArrayString=args[0].toString();
        // 将json字符串转为json对象
        final JSONArray jsonArray = new JSONArray(jsonArrayString);
        for (int j = 0; j < jsonArray.length(); j++) {
             String obj = jsonArray.getString(j);
            // 把对象封装到数组中
            //问:为什么要封装在数组中
            //答:①炸裂函数,需要有一层包括着,也就是会炸开一层
            //   ②也考虑通用性,可能有会有对个参数
             String[] cols = new String[1];
            cols[0]=obj;
            // forward 一次,炸裂得到一行新数据
            forward(cols);
        }
    }

    /**
     * 关闭资源
     * @throws HiveException
     */
    @Override
    public void close() throws HiveException {

    }
}
