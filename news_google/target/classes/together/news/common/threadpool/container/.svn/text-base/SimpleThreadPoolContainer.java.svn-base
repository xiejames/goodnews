package together.news.common.threadpool.container;

import java.util.ArrayList;

import together.news.common.threadpool.ThreadPool;


public class SimpleThreadPoolContainer implements ThreadPoolContainer{
	private ThreadPool m_pool;
	private ArrayList<Runnable> cmds=new ArrayList<Runnable>();

	public SimpleThreadPoolContainer(int minSize, int maxSize, String name) {
		this.m_pool = ThreadPool.create(this,name, minSize, maxSize,true);;
	}
	
	public void addToBatch(Runnable cmd){
		cmds.add(cmd);
	}
	public void executeBatch(){
		for(Runnable cmd:cmds){
			add(cmd);
		}
	}
	public void add(Runnable cmd) {
		notifyOnNewTask(cmd);
	}

	private void notifyOnNewTask(Runnable cmd) {
		m_pool.notifyOnNewTask(cmd);
	}

	public void shutdown() {
		m_pool.shutdown();
	}


}
