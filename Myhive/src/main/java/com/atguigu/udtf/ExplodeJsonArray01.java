package com.atguigu.udtf;

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
 * 需求:传入一个json数组对象,返回多个json对象
 */
public class ExplodeJsonArray01 extends GenericUDTF {
    /**
     * 校验参数,定义返回值的类型
     * @param argOIs
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        // 获取参数
        final List<? extends StructField> jsonField = argOIs.getAllStructFieldRefs();
        // 判断参数个数
        if(jsonField.size()!=1){
            throw new UDFArgumentException("参数必须是一个,你传入的参数的个数是"+jsonField.size());
        }
        // 判断参数的类型
        if(argOIs.getCategory()!= ObjectInspector.Category.PRIMITIVE || "string".equals(argOIs.getTypeName())){
            throw  new UDFArgumentException("参数的类型必须是string类型,你创建的参数的类型为"+argOIs.getTypeName());
        }
        // 定义返回值类型
        // 定义返回列的名称
        List<String> fieldName=new ArrayList<>();
        fieldName.add("item");
        List<ObjectInspector> instant=new ArrayList<>();
        instant.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldName,instant);
    }

    /**
     * 核心的业务流程
     * @param args
     * @throws HiveException
     */
    @Override
    public void process(Object[] args) throws HiveException {
        // 获取数据
        final String jsonObject = args[0].toString();
        final JSONArray array = new JSONArray(jsonObject);
        for (int i = 0; i < array.length(); i++) {
            final String result = array.getString(i);
            final String[] arr = new String[1];
            arr[0]=result;
            forward(arr);
        }

    }

    @Override
    public void close() throws HiveException {

    }
}
