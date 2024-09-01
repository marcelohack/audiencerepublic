package com.audiencerepublic.graph;

import java.util.*;

public class Eccentricity {

    public static final int INFINITY = Integer.MAX_VALUE;

    public static Map<Integer, Integer> dijkstra(Graph g, int node) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<PriorityQueueItem> pq = new PriorityQueue<>(new PriorityQueueComparator());

        for (int n : g.nodes) {
            distances.put(n, INFINITY);
        }
        distances.put(node, 0);
        pq.add(new PriorityQueueItem(node, 0));

        while (!pq.isEmpty()) {
            PriorityQueueItem current = pq.poll();

            for (Edge e : g.adjList.get(current.node)) {
                int newDist = current.distance + e.weight;
                if (newDist < distances.get(e.to)) {
                    distances.put(e.to, newDist);
                    pq.add(new PriorityQueueItem(e.to, newDist));
                }
            }
        }
        return distances;
    }

    public static int calculateEccentricity(Graph g, int node) {
        Map<Integer, Integer> distances = dijkstra(g, node);
        // Find the maximum distance from this node to any other node
        int maxDistance = 0;
        for (int dist : distances.values()) {
            if (dist > maxDistance && dist < INFINITY) {
                maxDistance = dist;
            }
        }
        return maxDistance;
    }

    public static Map<Integer, Integer> calculateEccentricities(Graph g) {
        Map<Integer, Integer> eccentricities = new HashMap<>();

        for (int node : g.nodes) {
            int maxDistance = calculateEccentricity(g, node);
            eccentricities.put(node, maxDistance);
        }

        return eccentricities;
    }

    public static int calculateRadius(Graph g) {
        Map<Integer, Integer> eccentricities = calculateEccentricities(g);
        int radius = INFINITY;

        for (int ecc : eccentricities.values()) {
            if (ecc < radius) {
                radius = ecc;
            }
        }

        return radius;
    }

    public static int calculateDiameter(Graph g) {
        Map<Integer, Integer> eccentricities = calculateEccentricities(g);
        int diameter = 0;

        for (int ecc : eccentricities.values()) {
            if (ecc > diameter) {
                diameter = ecc;
            }
        }

        return diameter;
    }
}
