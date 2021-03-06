package taomp.spinning;

import java.util.concurrent.atomic.AtomicBoolean;
import taomp.util.Lock;


/*
 *  not portable across a range of different machines
 */
public class BackoffLock implements Lock {
	private AtomicBoolean state = new AtomicBoolean(false);
	private static final int MIN_DELAY = 1;   // need tuning
	private static final int MAX_DELAY = 1000; //need tuning
	
	public void lock(){
		Backoff backoff = new Backoff(MIN_DELAY, MAX_DELAY);
		while(true){
			while(state.get()){};
				if(!state.getAndSet(true)){
					return;
			}else {
				try {
					backoff.backoff();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void unlock() {
		state.set(false);
	}
}
