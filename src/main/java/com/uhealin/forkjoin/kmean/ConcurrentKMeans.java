package com.uhealin.forkjoin.kmean;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentKMeans {

	public static ConcurrentDocumentCluster[] calculate(ConcurrentDocument[] documents, int numberClusters, int vocSize,
			int seed, int maxSize) {
		ConcurrentDocumentCluster[] clusters = new ConcurrentDocumentCluster[numberClusters];

		Random random = new Random(seed);
		for (int i = 0; i < numberClusters; i++) {
			clusters[i] = new ConcurrentDocumentCluster(vocSize);
			clusters[i].initialize(random);
		}
//然后，重复指派阶段和更新阶段，直到所有文档所属的簇都不再改变为止。在进入循环之前，创建ForkJoinPool对象来执行该任务及其所有子任务。一旦循环完成，与其他Executor对象一样，必须对Fork/Join池使用shutdown()方法以结束其执行。最后，返回含有文档最终组织结果的簇数组。

		boolean change = true;
		ForkJoinPool pool = new ForkJoinPool();

		int numSteps = 0;
		while (change) {
			change = assignment(clusters, documents, maxSize, pool);
			update(clusters, maxSize, pool);
			numSteps++;
		}
		pool.shutdown();
		System.out.println("Number of steps: " + numSteps);
		return clusters;
	}

	private static boolean assignment(ConcurrentDocumentCluster[] clusters, ConcurrentDocument[] documents, int maxSize,
			ForkJoinPool pool) {

		boolean change = false;

		for (ConcurrentDocumentCluster cluster : clusters) {
			cluster.clearDocuments();
		}
//然后，初始化必要的对象：用于存放已指派簇发生变化的文档数的AtomicInteger对象，以及用于启动处理过程的AssignmentTask对象。AtomicInteger类支持原子操作。也就是说，其他线程无法通过中间状态查看该操作。对于剩余线程来说，该操作可执行也可不执行。这两个对象还在set()操作和随后的get()操作之间建立了happens-before关系。使用AtomicInteger对象确保所有线程都可以以线程安全的方式更新其值。

		AtomicInteger numChanges = new AtomicInteger(0);
		AssignmentTask task = new AssignmentTask(clusters, documents, 0, documents.length, numChanges, maxSize);

//然后，使用ForkJoinPool的execute()方法以异步方式执行池中的任务，并且使用AssignmentTask对象的join()方法等待其结束，如下所示：

		pool.execute(task);
		task.join();
//最后，检查已改变指派簇的文档数。如果存在发生改变的文档，将返回true值，否则返回false值。该代码如下所示：

		System.out.println("Number of Changes: " + numChanges);
		return numChanges.get() > 0;
	}

	private static void update(ConcurrentDocumentCluster[] clusters, int maxSize, ForkJoinPool pool) {
		UpdateTask task = new UpdateTask(clusters, 0, clusters.length, maxSize, pool);
		pool.execute(task);
		task.join();
	}

	public static class UpdateTask extends ForkJoinTask<Document> {

		public UpdateTask(ConcurrentDocumentCluster[] clusters, int i, int length, int maxSize, ForkJoinPool pool) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Document getRawResult() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void setRawResult(Document value) {
			// TODO Auto-generated method stub

		}

		@Override
		protected boolean exec() {
			// TODO Auto-generated method stub
			return false;
		}

	}

	public static class AssignmentTask extends ForkJoinTask<Document> {

		public AssignmentTask(ConcurrentDocumentCluster[] clusters, ConcurrentDocument[] documents, int i, int length,
				AtomicInteger numChanges, int maxSize) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Document getRawResult() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void setRawResult(Document value) {
			// TODO Auto-generated method stub

		}

		@Override
		protected boolean exec() {
			// TODO Auto-generated method stub
			return false;
		}

	}

}
