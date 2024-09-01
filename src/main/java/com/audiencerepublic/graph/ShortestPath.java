package com.audiencerepublic.graph;

import java.util.*;

public class ShortestPath {

    public static final int INFINITY = Integer.MAX_VALUE;

    public static Map<Integer, Integer> dijkstra(Graph g, int start, int end) {
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        PriorityQueue<PriorityQueueItem> pq = new PriorityQueue<>(new PriorityQueueComparator());

        for (int node : g.nodes) {
            distances.put(node, INFINITY);
            predecessors.put(node, -1);
        }
        distances.put(start, 0);
        pq.add(new PriorityQueueItem(start, 0));

        while (!pq.isEmpty()) {
            PriorityQueueItem current = pq.poll();
            if (current.node == end) {
                break;
            }

            for (Edge e : g.adjList.get(current.node)) {
                int newDist = current.distance + e.weight;
                if (newDist < distances.get(e.to)) {
                    distances.put(e.to, newDist);
                    predecessors.put(e.to, current.node);
                    pq.add(new PriorityQueueItem(e.to, newDist));
                }
            }
        }

        return predecessors;
    }

    static List<Integer> reconstructPath(Map<Integer, Integer> predecessors, int start, int end) {
        List<Integer> path = new ArrayList<>();
        for (int at = end; at != -1; at = predecessors.get(at)) {
            path.add(0, at);
        }
        if (!path.isEmpty() && path.get(0) == start) {
            return path;
        }
        return Collections.emptyList();
    }

    public static List<Integer> shortestPath(Graph g, int start, int end) {
        Map<Integer, Integer> predecessors = dijkstra(g, start, end);
        return reconstructPath(predecessors, start, end);
    }
}
