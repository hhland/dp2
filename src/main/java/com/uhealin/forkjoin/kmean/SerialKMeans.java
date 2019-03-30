package com.uhealin.forkjoin.kmean;

import java.util.Random;

public class SerialKMeans {

	public static DocumentCluster[] calculate(Document[] documents,
            int clusterCount, int vocSize, int seed) {
DocumentCluster[] clusters = new DocumentCluster[clusterCount];

Random random = new Random(seed);
for (int i = 0; i < clusterCount; i++) {

clusters[i] = new DocumentCluster(vocSize);
clusters[i].initialize(random);
}
//然后，重复指派和更新阶段，直到所有文档对应的簇都不再变化为止。最后，返回描述了文档最终组织情况的簇数组，如下述代码所示：

boolean change = true;

int numSteps = 0;
while (change) {
change = assignment(clusters, documents);
update(clusters);
numSteps++;
}
System.out.println("Number of steps: "+numSteps);
return clusters;
}
//指派阶段的工作在assignment()方法中实现。该方法接收Document对象数组和DocumentCluster对象数组作为参数。对于每个文档，该方法都计算其与所有簇之间的欧氏距离，并且将该文档指派到距离最短的簇。该方法返回一个布尔值，该值表明从当前位置到下一位置是否有一个或多个文档改变了为其指派的簇。如以下代码所示：

private static boolean assignment(DocumentCluster[] clusters, Document[]
documents) {

boolean change = false;

for (DocumentCluster cluster : clusters) {
cluster.clearClusters();
}

int numChanges = 0;
for (Document document : documents) {
double distance = Double.MAX_VALUE;
DocumentCluster selectedCluster = null;
for (DocumentCluster cluster : clusters) {
double curDistance = DistanceMeasurer.euclideanDistance
               (document.getData(), cluster.getCentroid());
if (curDistance < distance) {
 distance = curDistance;
 selectedCluster = cluster;
}
}
selectedCluster.addDocument(document);
boolean result = document.setCluster(selectedCluster);
if (result)
numChanges++;
}
System.out.println("Number of Changes: " + numChanges);
return numChanges > 0;
}
//更新阶段在update()方法中实现。该方法接收带有簇信息的DocumentCluster对象数组作为参数，并且直接重新计算每个簇的质心。

private static void update(DocumentCluster[] clusters) {
for (DocumentCluster cluster : clusters) {
cluster.calculateCentroid();
}
}
}
