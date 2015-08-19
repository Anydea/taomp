package taomp.consensus;

public interface Consensus<T> {
	T decide(T value);
}
