package com.audiencerepublic.graph;

import java.util.*;

public class PriorityQueueItem {
    int node;
    int distance;

    PriorityQueueItem(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

class PriorityQueueComparator implements Comparator<PriorityQueueItem> {
    public int compare(PriorityQueueItem i1, PriorityQueueItem i2) {
        return Integer.compare(i1.distance, i2.distance);
    }
}
