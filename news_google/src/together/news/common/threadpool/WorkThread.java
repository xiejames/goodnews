package together.news.common.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class WorkThread extends Thread {

	public WorkThread next;// keep a linked queue of worked thread
	
	private ThreadPool m_pool;
	private final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
	private String groupName;
	private final AtomicBoolean isFree = new AtomicBoolean(true);
	private boolean isUp=true;
	

	public WorkThread(ThreadPool threadPool, String name, boolean isDaemon) {
		super(name);
		this.m_pool = threadPool;
		this.groupName = name;
		this.setDaemon(isDaemon);
	}

	@Override
	public void run() {
		while (isUp) {
			isFree.set(true);
			m_pool.addToFreeList(this);
			try {
				Runnable o=queue.poll(10000, TimeUnit.MILLISECONDS);
				if(o==null){
					if(isUp==false){
						return;
					}
					continue;
				}
				isFree.set(false);
				o.run();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}// wait
		}
	}


	public boolean canWork() {
		if(isFree.get()){
			isFree.set(false);
			return true;
		}
		return false;
	}

	public void stopWorker() {
		isUp=false;
	}

	public void addTask(Runnable takeOneTaskNoWait) {
		queue.add(takeOneTaskNoWait);
		
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
