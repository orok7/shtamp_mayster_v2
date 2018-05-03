package eins.utils.depinject;

public interface DependencyInjector {

	<T> T getComponent(Class<T> type);

}
