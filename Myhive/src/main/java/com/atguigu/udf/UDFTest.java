package com.atguigu.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

public class UDFTest extends GenericUDF {
    /**
     * 限定传入的参数是否符合要求,和返回参数的类型
     * @param objectInspectors
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        // 1,判断参数的个数是否满足
        if(objectInspectors.length!=1){
            throw new UDFArgumentLengthException("you need get me only one arg");
        }
        // 2,判断参数的类型是否满足
        if(!objectInspectors[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)){
            throw new UDFArgumentTypeException(1,"arg is not primitive");
        }
        // 3,定义返回类型
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }

    /**\
     * 处理逻辑
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

    @Override
    public String getDisplayString(String[] strings) {
        return "";
    }
}
