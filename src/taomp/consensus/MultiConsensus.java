package taomp.consensus;

import taomp.util.ThreadID;

public class MultiConsensus<T> extends ConsensusProtocol<T> {
	public MultiConsensus(int threads) {
		super(threads);
	}
	private final int NULL = -1;
	Assign23 assign23 = new Assign23(NULL);
	public T decide(T value){
		propose(value);
		int i = ThreadID.get();
		int j = 1 -i;
		assign23.assign(i,i,i,i+1);
		int other = assign23.read((i+2)%3);
		if(other == NULL || other == assign23.read(1))
			return proposed[i];
		else
			return proposed[j];
	}

}
