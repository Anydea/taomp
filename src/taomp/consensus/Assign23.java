package taomp.consensus;

public class Assign23 {
	int[]	r = new int[3];
	public Assign23(int init){
		for(int i = 0; i < r.length; i++)
			r[i] = init;
	}
	
	public synchronized void assign(int v0, int v1, int i0, int i1){
		r[i0] = v0;
		r[i1] = v1;
	}
	
	public synchronized int read(int i){
		return r[i];
	}
}
