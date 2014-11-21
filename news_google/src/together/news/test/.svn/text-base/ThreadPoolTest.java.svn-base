package together.news.test;

import together.news.common.threadpool.container.SimpleThreadPoolContainer;

public class ThreadPoolTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		SimpleThreadPoolContainer container = new SimpleThreadPoolContainer(1, 5,"testContainner");
		for(int i=0;i<15;i++){
			container.add(new MyTask(i+""));
			Thread.sleep(1);
		}
		Thread.sleep(10000);
	}

}

class MyTask implements Runnable{

	private String id;
	public MyTask(String id){
		this.id=id;
	}
	public void run() {
		System.out.println("Task"+id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}