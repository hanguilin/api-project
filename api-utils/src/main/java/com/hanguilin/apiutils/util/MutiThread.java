/** 
 * Project Name:api-utils 
 * File Name:MutiThread.java 
 * Package Name:com.hanguilin.apiutils.util 
 * Date:2019年10月1日上午10:37:07 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiutils.util;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/** 
 * 并发控制工具类
* @author  Administrator
* @date 2019年10月1日 上午10:37:07 
* @version 1.0  
* @since   
*/
public abstract class MutiThread<P, R> {
	/** 日志 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MutiThread.class);
	
	/** 线程池 */
	private ExecutorService executor;
	
	/** 阻塞队列 */
	private LinkedBlockingQueue<Future<List<R>>> queue;
	
	/** 结束闭锁 */
	private CountDownLatch endLock;
	
	/** 分区数据 */
	private List<List<P>> partitionDataLsit;
	
	/** 需要的线程数量 */
	private int nThreads;
	
	@SuppressWarnings("unchecked")
	public MutiThread(List<P> data, int partitionSize) {
		Object[] info = getPartitionInfo(data, partitionSize);
		this.nThreads = (int) info[0];
		this.partitionDataLsit = (List<List<P>>) info[1];
		this.executor = Executors.newFixedThreadPool(nThreads);
		this.queue = new LinkedBlockingQueue<Future<List<R>>>(nThreads);
		this.endLock = new CountDownLatch(nThreads);
	}

	/** 
	 * 计算线程数量和分区数据
	 *
	 * @param data 待处理数据
	 * @param partitionSize 单个线程执行的任务数量
	 * @return Object[]
	 */
	private Object[] getPartitionInfo(List<P> data, int partitionSize) {
		if(null == data || data.isEmpty()) {
			throw new IllegalArgumentException("No data to process.");
		}
		if(partitionSize <= 0) {
			throw new IllegalArgumentException("The number of single thread tasks is at least one.");
		}
		Object[] result = new Object[2];
		List<List<P>> partitionDataLsit = Lists.newArrayList();
		int nThreads = 0;
		int size = data.size();
		int fromIndex = 0;
		int toIndex = partitionSize;
		
		while(toIndex <= size) {
			partitionDataLsit.add(data.subList(fromIndex, toIndex));
			fromIndex += partitionSize;
			toIndex += partitionSize;
			nThreads++;
		}
		if(fromIndex < size) {
			partitionDataLsit.add(data.subList(fromIndex, size));
			nThreads++;
		}
		
		result[0] = nThreads;
		result[1] = partitionDataLsit;
		return result;
	}
	
	/** 
	 * 将处理结果一并返回
	 *
	 * @return List<R>
	 * @throws InterruptedException
	 * @throws ExecutionException 
	 */
	public List<R> getResult() throws InterruptedException, ExecutionException{
		List<R> result = Lists.newArrayList();
		try {
			for (int i = 0; i < nThreads; i++) {
				Future<List<R>> future = executor.submit(new Task(i, partitionDataLsit.get(i)) {
					
					@Override
					public List<R> execute(int currentThread, List<P> pendingData) {
						return outExecute(currentThread, pendingData);
					}
				});
				queue.put(future);
			}
			endLock.await();
			for (Future<List<R>> future : queue) {
				result.addAll(future.get());
			}
		} catch (Exception e) {
			LOGGER.error("获取结果集异常", e);
		} finally {
			executor.shutdown();
			// 释放内存
			queue = null;
			endLock = null;
			partitionDataLsit = null;
		}
		return result;
	}
	
	/** 
	 * 外部需要实现的具体方法
	 *
	 * @param currentThread 线程号
	 * @param pendingData 待处理数据
	 * @return List<R>
	 */
	public abstract List<R> outExecute(int currentThread, List<P> pendingData);
	
	/**
	 * 线程实现类
	 */
	private abstract class Task implements Callable<List<R>>{
		/**
		 * 当前线程号
		 */
		private int currentThread;
		
		/**
		 * 待处理数据
		 */
		private List<P> pendingData;
		
		public Task(int currentThread, List<P> pendingData) {
			this.currentThread = currentThread;
			this.pendingData = pendingData;
		}
		
		/** 
		 * 线程主要执行方法
		 *
		 * @return List<R>
		 * @throws Exception 
		 */
		@Override
		public List<R> call() throws Exception {
			List<R> result = null;
			try {
				result = execute(currentThread, pendingData);
			} catch (Exception e) {
				LOGGER.error("外部实现方法异常", e);
			} finally {
				endLock.countDown();
			}
			return result;
		}
		
		/** 
		 * 需要外部实现的具体方法
		 *
		 * @param currentThread 线程号
		 * @param pendingData 待处理数据
		 * @return List<R>
		 */
		public abstract List<R> execute(int currentThread, List<P> pendingData);
	}
}
