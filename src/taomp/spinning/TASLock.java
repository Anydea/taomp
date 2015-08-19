package taomp.spinning;

import java.util.concurrent.atomic.AtomicBoolean;
import taomp.util.Lock;


public class TASLock implements Lock{
	AtomicBoolean state = new AtomicBoolean(false);
	public void lock(){
		while (state.getAndSet(true)){}
	}
	
	public void unlock(){
		state.set(false);
	}
}
