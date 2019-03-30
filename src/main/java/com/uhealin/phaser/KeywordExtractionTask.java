package com.uhealin.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public class KeywordExtractionTask implements Runnable {

	private ConcurrentHashMap<String, Word> globalVoc;
	private ConcurrentHashMap<String, Integer> globalKeywords;

	private ConcurrentLinkedDeque<File> concurrentFileListPhase1;
	private ConcurrentLinkedDeque<File> concurrentFileListPhase2;

	private Phaser phaser;

	private String name;
	private boolean main;

	private int parsedDocuments;
	private int numDocuments;

	public KeywordExtractionTask(ConcurrentLinkedDeque<File> concurrentFileListPhase1,
			ConcurrentLinkedDeque<File> concurrentFileListPhase2, Phaser phaser,
			ConcurrentHashMap<String, Word> globalVoc, ConcurrentHashMap<String, Integer> globalKeywords,
			int numDocuments, String name, boolean main) {
		this.concurrentFileListPhase1 = concurrentFileListPhase1;
		this.concurrentFileListPhase2 = concurrentFileListPhase2;
		this.globalVoc = globalVoc;
		this.globalKeywords = globalKeywords;
		this.phaser = phaser;
		this.main = main;
		this.name = name;
		this.numDocuments = numDocuments;
	}
	// 使用run()方法实现该算法分为三个阶段。首先，调用分段器的arriveAndAwaitAdvance()方法等待其他任务的创建。所有任务都会同时开始执行。然后，正如在该算法的串行版本中提到的，解析所有文档并且构建globalVocConcurrentHashMap类，其中含有所有单词及其全局TF值和DF值。为了完成第一阶段，再次调用arriveAndAwaitAdvance()方法，在第二阶段开始之前等待其他任务结束。

	@Override
	public void run() {
		File file;

		// 第一阶段
		phaser.arriveAndAwaitAdvance();
		System.out.println(name + ": Phase 1");
		while ((file = concurrentFileListPhase1.poll()) != null) {
			Document doc = DocumentParser.parse(file.getAbsolutePath());
			for (Word word : doc.getVoc().values()) {
				globalVoc.merge(word.getWord(), word, Word::merge);
			}
			parsedDocuments++;
		}

		System.out.println(name + ": " + parsedDocuments + " parsed.");
		phaser.arriveAndAwaitAdvance();
		// 正如你看到的，为获取待处理的File对象，使用了ConcurrentLinkedDeque类的poll()方法。该方法检索并且删除Deque的第一个元素，这样下一个任务将获取不同的文件进行解析，并且没有文件会被解析两次。

		// 正如在该算法的串行版中提到的，第二阶段计算了globalKeywords结构。首先，计算每个文档最优的10个关键字，然后将其插入ConcurrentHashMap类。该代码和串行版中的相同，只是将串行数据结构替换为并发数据结构。

		// 第二阶段
		System.out.println(name + ": Phase 2");
		while ((file = concurrentFileListPhase2.poll()) != null) {

			Document doc = DocumentParser.parse(file.getAbsolutePath());
			List<Word> keywords = new ArrayList<>(doc.getVoc().values());

			for (Word word : keywords) {
				Word globalWord = globalVoc.get(word.getWord());
				word.setDf(globalWord.getDf(), numDocuments);
			}
			Collections.sort(keywords);

			if (keywords.size() > 10)
				keywords = keywords.subList(0, 10);
			for (Word word : keywords) {
				addKeyword(globalKeywords, word.getWord());
			}
		}
		System.out.println(name + ": " + parsedDocuments + " parsed.");
		// 对于主任务和其他任务而言最后阶段将有所不同。在将整个文档集合中的100个最佳关键字输出到控制台之前，主任务使用Phaser类的arriveAndAwaitAdvance()方法等待所有任务的第二阶段结束。最后，使用arriveAndDeregister()方法从分段器中注销。

		// 剩下的任务使用arriveAndDeregister()方法标记第二阶段的结束、从分段器注销以及完成其执行。

		// 当所有的任务完成工作后，都将从分段器中注销。最后分段器将有0个参与方，并且将进入终止状态。

		if (main) {
			phaser.arriveAndAwaitAdvance();

			Iterator<Entry<String, Integer>> iterator = globalKeywords.entrySet().iterator();
			Keyword orderedGlobalKeywords[] = new Keyword[globalKeywords.size()];
			int index = 0;
			while (iterator.hasNext()) {
				Entry<String, Integer> entry = iterator.next();
				Keyword keyword = new Keyword();
				keyword.setWord(entry.getKey());
				keyword.setDf(entry.getValue());
				orderedGlobalKeywords[index] = keyword;
				index++;
			}

			System.out.println("Keyword Size: " + orderedGlobalKeywords.length);

			Arrays.parallelSort(orderedGlobalKeywords);
			int counter = 0;
			for (int i = 0; i < orderedGlobalKeywords.length; i++) {
				Keyword keyword = orderedGlobalKeywords[i];
				System.out.println(keyword.getWord() + ": " + keyword.getDf());
				counter++;
				if (counter == 100) {
					break;
				}
			}
		}
		phaser.arriveAndDeregister();

		System.out.println("Thread " + name + " has finished.");
	}

	private static void addKeyword(Map<String, Integer> globalKeywords, String word) {
		globalKeywords.merge(word, 1, Integer::sum);
	}
}
