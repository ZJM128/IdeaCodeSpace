package com.atguigu.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

public class MyUDF01 extends GenericUDF {
    /**
     * 传入参数的约束
     * @param objectInspectors
     * @return
     * @throws UDFArgumentException
     */
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        if(objectInspectors.length != 1){
            throw new UDFArgumentLengthException("只需一个参数");
        }
        // 判断是否是基本数据类型
        if(!objectInspectors[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)){
            throw new UDFArgumentTypeException(1,"需要传入一个基本数据类型");
        }
        // 返回需要返回的数据类型
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }

    /**
     * 逻辑代码的处理
     * @param deferredObjects
     * @return
     * @throws HiveException
     */
    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        final Object o = deferredObjects[0].get();
        if(o==null){
            return 0;
        }
        return o.toString().length();
    }

    /**
     *
     * @param strings
     * @return
     */
    @Override
    public String getDisplayString(String[] strings) {
        return "";
    }
}
