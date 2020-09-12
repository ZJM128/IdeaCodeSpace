package com.atguigu.udf;

        import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
        import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
        import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
        import org.apache.hadoop.hive.ql.metadata.HiveException;
        import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
        import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
        import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

//给定基本数据类型，返回给定数据的长度
public class MyUDF extends GenericUDF {
    /**
     *initialize(ObjectInspector[] arguments)
     * 拿来判断数据类型是否正确  并抛出异常
     * 判断参数个数是否正确  并抛出异常
     * 约定返回值类型
     * @param arguments
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        //  判断参数的个数是否有问题
        if(arguments.length != 1){
            throw new UDFArgumentLengthException(" ARGS NUM ERROR please give me only one arg");
        }
        //判断参数的类型是否有问题
        if(!arguments[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)) {
            throw new UDFArgumentTypeException(1,"ARGS TYPE ERROR please give me PRIMITIVE");
        }
        // 约定返回值类型
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }

    /**
     * evaluate(DeferredObject[] arguments) 写具体逻辑的方法
     * @param arguments
     * @return
     * @throws HiveException
     */
    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        Object o = arguments[0].get();
        if(o==null){
            return 0;
        }
        return o.toString().length();
    }

    /**
     * getDisplayString 获取解释的字符串 给你的hadooop看的
     * @param children
     * @return
     */
    @Override
    public String getDisplayString(String[] children) {
        return "";
    }
}
