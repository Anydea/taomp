package taomp.spinning;

import java.util.concurrent.atomic.AtomicBoolean;
import taomp.util.Lock;

public class TTASLock implements Lock{
	AtomicBoolean state = new AtomicBoolean(false);
	public void lock(){
		while (true){
			while(state.get()) {};
			if(!state.getAndSet(true))
					return;
		}
	}
	
	public void unlock(){
		state.set(false);
	}
}
