package eins.utils.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> interf, InvocationHandler handler) {
        if (!interf.isInterface()) {
            return null;
        }
        return (T) Proxy.newProxyInstance(interf.getClassLoader(), new Class[] { interf }, handler);
    }

}
