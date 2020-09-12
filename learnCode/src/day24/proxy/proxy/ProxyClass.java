package day24.proxy.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyClass {
    public static  Object getProxyClass(Object o){
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(o);

        return Proxy.newProxyInstance(o.getClass().getClassLoader(),o.getClass().getInterfaces(),myInvocationHandler);
    }
}

class MyInvocationHandler implements InvocationHandler{

    private  Object object ;

    //  传入被代理的对象
    public MyInvocationHandler(Object object) {
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被代理方法执行前");
        method.invoke(object, args);
        System.out.println("被代理方法执行后");
        return null;
    }
}