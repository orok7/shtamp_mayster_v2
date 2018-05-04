package eins.utils.depinject.exmpl;

import eins.utils.depinject.annotations.AutoInject;
import eins.utils.depinject.annotations.Component;

@Component
public class SomeTestImpl implements SomeTest{
	
	@AutoInject(qualifier = SomeTestImpl2.class)
	private SomeTest2 someTest2;
		
	public void doThis() {
		System.out.println("Hello from SomeTest");
	}

}
