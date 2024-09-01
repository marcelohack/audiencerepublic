package com.audiencerepublic.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GraphTests {

        @Test
        void testGraphCreation() {
                List<Integer> nodes = Arrays.asList(1, 2, 3, 4, 5);
                List<Edge> edges = Arrays.asList(
                                new Edge(1, 2, 1),
                                new Edge(1, 3, 2),
                                new Edge(2, 3, 3),
                                new Edge(2, 4, 4),
                                new Edge(3, 4, 5),
                                new Edge(4, 5, 6));

                Graph g = new Graph(nodes, edges);

                assertEquals(nodes, g.getNodes());
                assertEquals(5, g.getNodes().size());

                assertEquals(edges, g.edges);
                assertEquals(6, g.edges.size());
        }

        @Test
        void testConnectedGraphs() {

                List<Integer> nodes = Arrays.asList(1, 2, 3, 4, 5);
                List<Edge> edges = Arrays.asList(
                                new Edge(1, 2, 1),
                                new Edge(1, 3, 2),
                                new Edge(2, 3, 3),
                                new Edge(2, 4, 4),
                                new Edge(3, 4, 5),
                                new Edge(4, 5, 6));

                Graph g = new Graph(nodes, edges);
                // Node 1 can reach all other nodes either directly or indirectly (via
                // intermediate nodes).
                // Node 2 can also reach all other nodes (e.g., Node 2 → Node 3, Node 2 → Node
                // 4, Node 2 → Node 5).
                // Node 3 can reach Node 4 and Node 5, and so on.

                assertEquals(true, g.isConnected());

                nodes = Arrays.asList(1, 2, 3, 4);
                edges = Arrays.asList(
                                new Edge(1, 2, 2),
                                new Edge(1, 3, 1),
                                new Edge(3, 4, 3),
                                new Edge(4, 1, 2));

                // Even though Node 2 has no outgoing edges, it is still reachable from Node 1.
                // The graph forms a cycle involving Node 1, Node 3, and Node 4.
                // Because there is a path between every pair of nodes, the graph is strongly
                // connected.

                g = new Graph(nodes, edges);
                assertEquals(true, g.isConnected());
        }

        @Test
        void testNotConnectedGraphs() {

                List<Integer> nodes = Arrays.asList(1, 2, 3, 4);

                List<Edge> edges = Arrays.asList(
                                new Edge(2, 1, 3),
                                new Edge(2, 4, 2),
                                new Edge(4, 1, 1),
                                new Edge(4, 3, 1));

                Graph g = new Graph(nodes, edges);

                // Node 1 and Node 3 are unable to reach any other nodes, the graph is not
                // strongly connected.
                assertEquals(false, g.isConnected());

                nodes = Arrays.asList(1, 2, 3, 4, 5);
                edges = Arrays.asList(
                                new Edge(1, 2, 6),
                                new Edge(1, 4, 3),
                                new Edge(2, 4, 5),
                                new Edge(3, 1, 5),
                                new Edge(4, 3, 7),
                                new Edge(5, 1, 3),
                                new Edge(5, 4, 4));

                // Node 3 can be reached from Node 1 (through Node 4), but there is no path from
                // Node 3 to Node 5 or Node 2.
                // Node 5 and Node 3 are effectively disconnected from each other, with no path
                // connecting them directly or indirectly via other nodes.
                g = new Graph(nodes, edges);
                assertEquals(false, g.isConnected());
        }

}
