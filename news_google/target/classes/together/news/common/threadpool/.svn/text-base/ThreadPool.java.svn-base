package together.news.common.threadpool;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import together.news.common.threadpool.container.ThreadPoolContainer;

public class ThreadPool {

	//should in factory?
	private static ArrayList<ThreadPool> threadPools=new ArrayList<ThreadPool>();
	private final LinkedBlockingQueue<Runnable> waitingQueue = new LinkedBlockingQueue<Runnable>();//for log

	private String groupName;
	private int minSize;
	private int maxSize;
	private boolean isDaemon=true;
	private static ArrayList<WorkThread> allThreads=new ArrayList<WorkThread>();
	private LinkedBlockingQueue<WorkThread> freeThreads=new LinkedBlockingQueue<WorkThread>();
	ThreadPoolContainer container=null;
	
	private ThreadPool(ThreadPoolContainer container,String groupName, int minSize, int maxSize,boolean isDaemon) {
		this.groupName=groupName;
		this.minSize=minSize;
		this.maxSize=maxSize;
		this.container=container;
		for(int i=0;i<minSize;i++){
			WorkThread thread=new WorkThread(this,groupName+i,isDaemon);
			allThreads.add(thread);
			thread.start();
		}
	}
	public static ThreadPool create(ThreadPoolContainer container,String groupName, int minSize, int maxSize,boolean isDaemon) {
		ThreadPool result=new ThreadPool(container,groupName,minSize,maxSize,isDaemon);
		registerThreadPool(result);
		return result;
	}

	private static void registerThreadPool(ThreadPool result) {
		threadPools.add(result);
		
	}
	public synchronized boolean notifyOnNewTask(Runnable cmd) {

		WorkThread temp=null;
		while((temp=freeThreads.poll())!=null){
			if(temp.canWork()){
				temp.addTask(cmd);
				return true;
			}//if not free, go to next thread
		}
		while(allThreads.size()<maxSize){
			WorkThread thread=new WorkThread(this,groupName+allThreads.size(),isDaemon);
			allThreads.add(thread);
			thread.start();
			if(thread.canWork()){
				thread.addTask(cmd);
				return true;
			}
		}
		waitingQueue.add(cmd);
		return false;
		
	}
	
	//free a thread
	public synchronized void addToFreeList(WorkThread workThread) {
		Runnable cmd=waitingQueue.poll();
		if(cmd!=null){//no need to add to free list, who is free, who will do the job.
			workThread.addTask(cmd);
			return;
		}
		if(!freeThreads.contains(workThread)){
			freeThreads.add(workThread);
		}
		
	}
	public void shutdown() {
		synchronized (this) {

			for(WorkThread thread:allThreads){
				thread.stopWorker();
			}
		}
	}

}
