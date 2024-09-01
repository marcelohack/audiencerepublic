package com.audiencerepublic.graph;

import java.util.*;

public class Graph {
    List<Integer> nodes;
    List<Edge> edges;
    Map<Edge, Boolean> edgeMap;
    Map<Integer, List<Edge>> adjList;

    Graph(List<Integer> nodes) {
        this.nodes = nodes;
        this.edges = new ArrayList<>();
        this.edgeMap = new HashMap<>();
        this.adjList = new HashMap<>();
    }

    Graph(List<Integer> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
        this.edgeMap = new HashMap<>();
        this.adjList = new HashMap<>();
        edgesToAdjList(nodes.size());
    }

    // It's being used in JShell
    public List<Integer> getNodes() {
        return nodes;
    }

    void addEdge(Edge e) {
        if (edgeMap.containsKey(e)) {
            return;
        }
        edgeMap.put(e, true);
        edges.add(e);
    }

    void addRandomEdges(int numEdges) {
        Random rnd = new Random();

        while (edges.size() < numEdges) {
            int from = rnd.nextInt(nodes.size()) + 1;
            int to = rnd.nextInt(nodes.size()) + 1;

            if (from != to) {
                Edge e = new Edge(from, to, rnd.nextInt(numEdges) + 1);
                Edge reverseEdge = new Edge(to, from, 0);

                if (!edgeMap.containsKey(e) && !edgeMap.containsKey(reverseEdge)) {
                    addEdge(e);
                }
            }
        }
        // Must keep adjList up to date
        edgesToAdjList(nodes.size());
    }

    boolean isConnected() {
        Map<Integer, Boolean> visited = new HashMap<>();
        for (int node : nodes) {
            visited.put(node, false);
        }
        int startNode = nodes.get(0);
        dfs(startNode, visited);
        for (int node : nodes) {
            if (!visited.get(node)) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int node, Map<Integer, Boolean> visited) {
        visited.put(node, true);
        for (Edge neighbor : edges) {
            if (neighbor.from == node && !visited.get(neighbor.to)) {
                dfs(neighbor.to, visited);
            }
        }
    }

    private void edgesToAdjList(int n) {
        this.adjList = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adjList.put(i, new ArrayList<>());
        }
        for (Edge e : edges) {
            adjList.get(e.from).add(e);
        }
    }

    @Override
    public String toString() {
        // Use a TreeMap to ensure the nodes are sorted by the 'from' field
        Map<Integer, List<Edge>> graphMap = new TreeMap<>();

        // Initialize the graph map with an empty list for each node
        for (int node : nodes) {
            graphMap.put(node, new ArrayList<>());
        }

        // Populate the graph map with edges
        for (Edge e : edges) {
            graphMap.computeIfAbsent(e.from, k -> new ArrayList<>()).add(e);
        }

        // Ensure edges for each node are sorted by the 'to' field
        for (List<Edge> edgeList : graphMap.values()) {
            edgeList.sort(Comparator.comparingInt(e -> e.to));
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        // Build the string representation
        String ident = "";
        for (Map.Entry<Integer, List<Edge>> entry : graphMap.entrySet()) {
            sb.append(ident + " :").append(entry.getKey()).append(" [");

            for (Edge edge : entry.getValue()) {
                sb.append("(:").append(edge.to).append(" ").append(edge.weight).append("), ");
            }

            // Remove trailing comma and space, if any edges were added
            if (!entry.getValue().isEmpty()) {
                sb.setLength(sb.length() - 2); // Remove trailing ", "
            }

            sb.append("],\n");
            ident = " ";
        }

        // Remove trailing comma and space from the final string
        if (!graphMap.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove trailing ", "
        }

        sb.append(" }");
        return sb.toString();
    }
}
