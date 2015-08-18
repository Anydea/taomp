package taomp.util;

public class Counter {
	private long value;
	public Counter( int i ){
		value = i;
	}
	
	public synchronized long getAndIncrement(){
		return value++;
	}
}
