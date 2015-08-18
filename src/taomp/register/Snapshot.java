package taomp.register;

public interface Snapshot<T> {
	public void update(T v);
	public T[] scan();
}
