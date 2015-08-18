package taomp.util;


public class Peterson implements Lock{
	private volatile boolean[] flag = new boolean[2];
	private volatile int victim;
	
	public void lock(){
		int i = (int)Thread.currentThread().getId();
		int j = 1 - i;
		flag[i] = true;
		victim = i;
		while(flag[j] && victim == i) {}
	}
	
	public void unlock(){
		int i = (int)Thread.currentThread().getId();
		flag[i] = false;
	}
}
