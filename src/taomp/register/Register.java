package taomp.register;

public interface Register<T> {
	T read();
	void write(T v);

}
