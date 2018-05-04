package eins.utils.depinject.exmpl;

import eins.utils.depinject.annotations.AutoInject;
import eins.utils.depinject.annotations.Component;

@Component
public class SomeTestImpl2 implements SomeTest2, SomeTest {
	
	@AutoInject(qualifier = SomeTestImpl.class)
	private SomeTest someTest;
	
	public void doThis() {
		someTest.doThis();
		System.out.println("Hello from SomeTest2");
	}

}
