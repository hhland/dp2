package com.uhealin.forkjoin.kmean;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws IOException {
    Path pathVoc = Paths.get("data", "movies.words");

    Map<String, Integer> vocIndex=VocabularyLoader.load(pathVoc);
    System.out.println("Voc Size: "+vocIndex.size());

    Path pathDocs = Paths.get("data", "movies.data");
    Document[] documents = DocumentLoader.load(pathDocs,
                                               vocIndex);
    System.out.println("Document Size: "+documents.length);
//然后，它初始化要生成的簇数以及随机数生成器的“种子”。如果它们并未作为main()方法的参数进行传递，可以使用如下的默认值。

if (args.length != 2) {
  System.err.println("Please specify K and SEED");
  return;
  }
  int K = Integer.valueOf(args[0]);
  int SEED = Integer.valueOf(args[1]);

//最后，启动算法，度量其执行时间，并且输出为每个簇分配的文档数。

    Date start, end;
    start=new Date();
    DocumentCluster[] clusters = SerialKMeans.calculate(documents,K ,vocIndex.size(), SEED);
    end=new Date();
    System.out.println("K: "+K+"; SEED: "+SEED);
    System.out.println("Execution Time: "+(end.getTime()-
                        start.getTime()));
    System.out.println(Arrays.stream(clusters)
                           .map (DocumentCluster::getDocumentCount)
                           .sorted (Comparator.reverseOrder())
      .map(Object::toString)
      .collect( Collectors.joining(", ", "Cluster sizes: ", "")));
  }
}