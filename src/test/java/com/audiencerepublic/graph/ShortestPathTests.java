package com.audiencerepublic.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ShortestPathTests {

    @Test
    void testShortestPath() {

        List<Integer> nodes = Arrays.asList(1, 2, 3, 4);
        List<Edge> edges = Arrays.asList(
                new Edge(1, 3, 1),
                new Edge(1, 4, 3),
                new Edge(3, 2, 3),
                new Edge(4, 3, 1));

        Graph g = new Graph(nodes, edges);

        List<Integer> path = ShortestPath.shortestPath(g, 4, 3);
        assertEquals(Arrays.asList(4, 3), path);
        // There is a direct edge from Node 4 to Node 3 with a weight of 1.

        nodes = Arrays.asList(1, 2, 3, 4, 5);
        edges = Arrays.asList(
                new Edge(1, 5, 1),
                new Edge(2, 1, 3),
                new Edge(2, 4, 4),
                new Edge(3, 1, 7),
                new Edge(4, 1, 5),
                new Edge(5, 2, 3),
                new Edge(5, 3, 6));

        g = new Graph(nodes, edges);
        // The shortest path from Node 4 to Node 3 is '4 → 1 → 5 → 3' with a total weight of 12.
        // Alternative path: 4 → 1 → 5 → 2 → 3 // Total weight: 16
        path = ShortestPath.shortestPath(g, 4, 3);
        assertEquals(Arrays.asList(4, 1, 5, 3), path);

        nodes = Arrays.asList(1, 2, 3, 4, 5);
        edges = Arrays.asList(
                new Edge(1, 2, 5),
                new Edge(1, 3, 4),
                new Edge(1, 4, 4),
                new Edge(2, 5, 4),
                new Edge(3, 4, 3),
                new Edge(4, 5, 7),
                new Edge(5, 3, 7));

        g = new Graph(nodes, edges);

        // Since there's no sequence of edges that leads from Node 3 to Node 2, there is no valid path between these two nodes. Therefore, the shortest path is indeed an empty list [].
        // This indicates that the graph is not fully connected, at least in terms of finding a path from Node 3 to Node 2.
        path = ShortestPath.shortestPath(g, 3, 2);
        assertEquals(Arrays.asList(), path);
    }

}
