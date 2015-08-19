package taomp.spinning;

import java.util.Random;

public class Backoff {
	final int minDelay, maxDelay;
	int limit;
	final Random rand;
	
	public Backoff(int min, int max){
		minDelay = min;
		maxDelay = max;
		limit	= minDelay;
		rand = new Random();
	}
	
	public void backoff() throws InterruptedException{
		int delay = rand.nextInt(limit);
		limit = Math.max(maxDelay, 2*limit);
		Thread.sleep(delay);
	}
}
