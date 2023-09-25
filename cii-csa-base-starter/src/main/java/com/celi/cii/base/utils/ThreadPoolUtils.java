package com.celi.cii.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolUtils {

	private static ExecutorService  executor ;
	
	// 核心线程数
	static int CORE_POOLSIZE = 20;
	
	private static void createThreadPool(){
		executor = Executors.newFixedThreadPool(CORE_POOLSIZE);
	}
	
	public static void execute(Runnable runnable){
		if(executor == null){
			createThreadPool();
		}
		if(executor != null){
			executor.execute(runnable);
		}
	}

	/**
	* @Description: callable线程
	* @Param: callable
	* @return: java.lang.Object
	* @Author: sundg
	* @Date: 2020/2/22
	*/
	public static FutureTask executeCallable(Callable callable) {
		if(executor == null){
			createThreadPool();
		}
		if(executor != null){
			FutureTask futureTask = new FutureTask<>(callable);
			executor.submit(futureTask);
			return futureTask;
		}
		return null;
	}

	public static void shutdown() {
		if(executor != null){
			try {
				executor.shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) throws Exception{
		FutureTask futureTask = executeCallable(new Callable() {
			@Override
			public Object call() throws Exception {
				Thread.sleep(5000);
				return "将要返回的数据";
			}
		});
		boolean isDone = false;
		while (!isDone) {
			if (!futureTask.isDone()) {
				Thread.sleep(1000);
				System.out.println("子线程还未结束");
			} else {
				isDone = true;
			}
		}
		System.out.println(futureTask.get());

	}
	public static void testRunMultipleThread() throws ExecutionException, InterruptedException {
		List<FutureTask> list = new ArrayList<>();
		for (int i=1; i< 25; i++) {
			try {
				final int value = i;
				FutureTask futureTask = ThreadPoolUtils.executeCallable(new Callable() {
					@Override
					public Object call() throws Exception {
						Thread.sleep(2000);
						return value;
					}
				});
				list.add(futureTask);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		for (FutureTask futureTask : list) {
			System.out.println(futureTask.get());
		}
		ThreadPoolUtils.shutdown();
	}
}
