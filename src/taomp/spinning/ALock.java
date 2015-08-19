package taomp.spinning;

import java.util.concurrent.atomic.AtomicInteger;
import taomp.util.Lock;


/*
 *  should introduce padding to map distinct elements to distinct cache line
 *  For example: cache line is 4-word (64 bit), the size of boolean is 1 bit, then the size of flag[] should be 8*64
 */
public class ALock implements Lock{
	ThreadLocal<Integer> mySlotIndex = new ThreadLocal<Integer> () {
		protected Integer initialValue(){
			return 0;
		}
	};
	
	AtomicInteger tail;
	boolean[] flag;
	int size;
	
	public ALock(int capacity){
		size = capacity;
		tail = new AtomicInteger(0);
		flag = new boolean[capacity];
		flag[0] = true;
	}
	
	public void lock(){
		int slot = tail.getAndIncrement() % size;
		while(! flag[slot]) {};
	}
	
	public void unlock() {
		int slot = mySlotIndex.get();
		flag[slot] = false;
		flag[(slot+1)%size] = true;
	}
}
