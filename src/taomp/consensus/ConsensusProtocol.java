package taomp.consensus;

import taomp.util.ThreadID;

public abstract class ConsensusProtocol<T> implements Consensus<T> {
	protected T[] proposed;
	
	void propose(T value) {
		proposed[ThreadID.get()] = value;
	}
	
	public ConsensusProtocol(int threads){
		proposed =  (T[]) new Object[threads];
	}
	
	abstract public T decide(T value);

}
