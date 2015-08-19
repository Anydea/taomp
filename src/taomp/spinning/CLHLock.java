package taomp.spinning;

import java.util.concurrent.atomic.AtomicReference;
import taomp.util.Lock;

/*
 *  implicit list of QNode
 *  first-come-first-served fairness
 *  no cache issue as ALock
 *  it performs poorly on cache-less NUMA architectures
 */
public class CLHLock implements Lock {
	class QNode {
		boolean locked = false;
		//QNode next = null;
	}
	
	AtomicReference<QNode> tail;
	ThreadLocal<QNode> myPred;
	ThreadLocal<QNode> myNode;
	
	public CLHLock() {
		tail = new AtomicReference<QNode>(new QNode());
		myNode = new ThreadLocal<QNode>() {
			protected QNode initialValue(){
				return new QNode();
			}
		};
		myPred = new ThreadLocal<QNode>(){
			protected QNode initialValue(){
				return null;
			}
		};
	}
		public void lock() {
			QNode qnode = myNode.get();
			qnode.locked = true;
			QNode pred = tail.getAndSet(qnode);
			myPred.set(pred);
			while (pred.locked){}
		}
		
		public void unlock() {
			QNode qnode = myNode.get();
			qnode.locked = false;
			myNode.set(myPred.get());
		}
}
	


