package taomp.consensus;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import taomp.util.ThreadID;


/*
 *  2-thread consensus using a FIFO queue
 */
public class QueueConsensus<T>  extends ConsensusProtocol<T> {
	private static final Integer WIN = 0;
	private static final Integer LOSE = 1;
	
	Queue<Integer> queue;
	
	public QueueConsensus(){
		super(2);
		queue = new LinkedBlockingQueue<Integer>(2);
		
		queue.offer(WIN);
		queue.offer(LOSE);
		
	}
	
	public T decide(T value){
		propose(value);
		int status = (int) queue.poll();
		int i = ThreadID.get();
		if(status == WIN)
			return proposed[i];
		else
			return proposed[1-i];
	}
	
	

}
