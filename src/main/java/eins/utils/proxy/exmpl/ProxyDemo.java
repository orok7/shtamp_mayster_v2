package eins.utils.proxy.exmpl;

import eins.utils.proxy.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyDemo {

    public static void main(String[] args) {
        ArithmeticOperationHandler arithmeticOperationHandler = new ArithmeticOperationHandler(new Calculator());
        ArithmeticOperations proxy = ProxyFactory.createProxy(ArithmeticOperations.class, arithmeticOperationHandler);
        if (proxy == null) return;
        System.out.println(proxy.mul(1, 1));
        System.out.println(proxy.div(1, 1));
    }

    interface ArithmeticOperations {
        double add(double x, double y);

        double sub(double x, double y);

        double mul(double x, double y);

        double div(double x, double y);
    }

    static class ArithmeticOperationHandler implements InvocationHandler {
        private Calculator calculator;

        public ArithmeticOperationHandler(Calculator calculator) {
            this.calculator = calculator;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName());
            return method.invoke(calculator, args);
        }
    }

    static class Calculator implements ArithmeticOperations {
        @Override
        public double add(double x, double y) {
            return x + y;
        }

        @Override
        public double sub(double x, double y) {
            return x - y;
        }

        @Override
        public double mul(double x, double y) {
            return x * y;
        }

        @Override
        public double div(double x, double y) {
            return x / y;
        }
    }

}
