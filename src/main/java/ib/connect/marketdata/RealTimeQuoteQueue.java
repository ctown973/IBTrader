package ib.connect.marketdata;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RealTimeQuoteQueue<E extends QuoteBuilder> extends AbstractQueue<E> implements BlockingQueue<E> {

	
	static class Node<E> {
		E item;
		Node<E> next;
		
		Node(E x ) {
			item = x;
		}
	}
	
	private final AtomicInteger count = new AtomicInteger(0);
	private final int capacity;
	
	Node <E> head;
	Node <E> last;
	
	private final ReentrantLock takeLock = new ReentrantLock();
	private final Condition notEmpty = takeLock.newCondition();
	
	private final ReentrantLock putLock = new ReentrantLock();
	private final Condition notFull = putLock.newCondition();
	
	
	private void signalNotEmpty() {
		takeLock.lock();
		try {
			notEmpty.signal();
		} finally {
			takeLock.unlock();
		}
	}
	
	private void signalNotFull() {
		this.putLock.lock();
		try {
			notFull.signal();
			
		} finally {
			putLock.unlock();
		}
	}
	
	private void enqueue(Node<E> node) {
		last.next = node;
		last = last.next;
	}
	
	private E dequeue() {
		Node<E> h = head;
		Node<E> first = h.next;
		h.next = h;
		head = first;
		E x = first.item;
		first.item = null;
		return x;
	}
	
	public RealTimeQuoteQueue(int capacity) {
		// TODO Auto-generated method stub
		  if (capacity <= 0) throw new IllegalArgumentException();
		   
		this.capacity = capacity;
		head = new Node<E>(null);
		last = head;
	}
	
	public RealTimeQuoteQueue(Collection<? extends E> c) {
		this(Integer.MAX_VALUE);
		putLock.lock();
		try {
			int n = 0;
			for (E q : c) {
				 if (q == null)
	                    throw new NullPointerException();
	                if (n == capacity)
	                    throw new IllegalStateException("Queue full");
	            
				enqueue(new Node<E>(q));
				
				n++;
			
			}
			count.set(n);
		} finally {
			putLock.unlock();
		}
	}
	
	
	
	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void put(E e) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E take() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remainingCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
