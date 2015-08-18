package taomp;

import taomp.util.Counter;

public class sharedObject {
	
	Counter counter = new Counter(1);

	public void primePrint(int range) {
		long i = 0;
		long limit = range;
		while( i< limit){
			i = counter.getAndIncrement();
			if(isPrime(i))
				System.out.println(i);
		}
	}
	
	
	public boolean isPrime(long val){
		return true;
	}
}
