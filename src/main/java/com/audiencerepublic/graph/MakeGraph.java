package com.audiencerepublic.graph;

import java.util.*;

public class MakeGraph {

    public static Graph makeGraph(int n, int s) {
        if (s < n - 1 || s > n * (n - 1) / 2) {
            throw new IllegalArgumentException(
                    "Invalid number of edges S. It should be between " + (n - 1) + " and " + (n * (n - 1) / 2));
        }

        List<Integer> nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nodes.add(i);
        }

        Graph g;
        do {
            g = new Graph(nodes);
            RandomWalk(g, s);
        } while (!g.isConnected());

        return g;
    }

    static void RandomWalk(Graph graph, int numEdges) {
        List<Integer> nodes = graph.nodes;
        Map<Integer, Boolean> source = new HashMap<>();
        Map<Integer, Boolean> target = new HashMap<>();
        for (int node : nodes) {
            source.put(node, true);
        }

        Random rnd = new Random();
        int currentNode = nodes.get(rnd.nextInt(nodes.size()));
        source.remove(currentNode);
        target.put(currentNode, true);

        while (!source.isEmpty()) {
            int neighborNode = nodes.get(rnd.nextInt(nodes.size()));
            if (!target.containsKey(neighborNode)) {
                Edge edge = new Edge(currentNode, neighborNode, rnd.nextInt(numEdges) + 1);
                graph.addEdge(edge);
                source.remove(neighborNode);
                target.put(neighborNode, true);
            }
            currentNode = neighborNode;
        }

        graph.addRandomEdges(numEdges);
    }
}