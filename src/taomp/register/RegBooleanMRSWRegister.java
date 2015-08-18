package taomp.register;

public class RegBooleanMRSWRegister implements Register<Boolean> {
	ThreadLocal<Boolean> last;
	boolean s_value; // safe MRSW register
	RegBooleanMRSWRegister(int capacity){
		last = new ThreadLocal<Boolean>() {
			protected Boolean initialValue() { return false; };
		};
	}
	
	public void write(Boolean x){
		if(x != last.get()){     // OR   x != s_value
			last.set(x);
			s_value = x;
		}
	}
	
	public Boolean read(){
		return s_value;
	}
	
	
	public static void main(String[] args) throws InterruptedException{
		final RegBooleanMRSWRegister r = new RegBooleanMRSWRegister(2);
		Thread t1 = new Thread(){
			public void run(){
				r.write(true);
				System.out.println(r.read());
			}
		};
		
		
		Thread t2 = new Thread(){
			public void run(){
				r.write(false);
				System.out.println(r.read());
			}
		};
		
		t1.start();
		t1.join();
		t2.start();
		t2.join();
	}
}
