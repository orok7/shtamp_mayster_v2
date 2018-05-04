package eins.utils.depinject.exmpl;

import eins.utils.depinject.DependencyInjector;
import eins.utils.depinject.annotations.AutoInject;
import eins.utils.depinject.annotations.Component;
import eins.utils.depinject.core.DependencyInjectorImpl;

import java.util.function.Consumer;

@Component
public class Demo {

	private final static Consumer<?> sout = System.out::println;

	@AutoInject
	private SomeTest2 someTest2;

	public static void main(String[] args) {
		DependencyInjector depinject = new DependencyInjectorImpl("eins");
		Demo demo = depinject.getComponent(Demo.class);
		demo.someTest2.doThis();
	}

}
