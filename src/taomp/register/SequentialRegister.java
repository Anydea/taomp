package taomp.register;


/*
 * SRSW safe
 * Method calls should not overlap
 * Not appropriate to multiprocessor program
 */
public class SequentialRegister<T>  implements Register<T>{
	private T value;
	public T read(){
		return value;
	}
	
	public void write(T v){
		value = v;
	}

}
