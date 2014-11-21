package together.news.common.threadpool.container;

import java.util.concurrent.LinkedBlockingQueue;

import together.news.common.threadpool.ThreadPool;


public class ThreadPoolScheduledContainer implements ThreadPoolContainer{
	
	private final LinkedBlockingQueue<Runnable> waitingQueue = new LinkedBlockingQueue<Runnable>();//for log
	private ThreadPool m_pool;

	public ThreadPoolScheduledContainer(int minSize, int maxSize, String name) {
		this.m_pool = ThreadPool.create(this,name, minSize, maxSize,true);;
	}

	public void add(Runnable cmd) {
		notifyOnNewTask(cmd);
	}

	public void notifyOnNewTask(Runnable cmd) {
		m_pool.notifyOnNewTask(cmd);
	}

	public void shutdown() {
		m_pool.shutdown();
	}


}
