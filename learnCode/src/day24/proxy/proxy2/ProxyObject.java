package day24.proxy.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyObject implements InvocationHandler {
    private  Object object;
    public  Object getProxyObject(Object o){
        this.object=o;
        return Proxy.newProxyInstance(o.getClass().getClassLoader(),o.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动物进化过程的方法");
        // 随意切入其他的方法
        Object invoke = method.invoke(object, args);
        System.out.println("动物进化后的方法");
        return invoke;
    }
}
