package com.audiencerepublic.graph;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class App {
    public static void main(String[] args) {

        int n = 5;
        int s = 7;

        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
            if (args.length > 1) {
                s = Integer.parseInt(args[1]);
            } else {
                s = n;
            }
        }

        if (s < n - 1 || s > n * (n - 1) / 2) {
            throw new IllegalArgumentException(
                    "Invalid number of edges S. It should be between " + (n - 1) + " and " + (n * (n - 1) / 2));
        }

        System.out.println();
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();
        System.out.printf("Number of nodes: %d, Number of edges: %d%n", n, s);
        Graph g = MakeGraph.makeGraph(n, s);
        System.out.println("Graph:");
        System.out.println(g);

        System.out.println();
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        Random rnd = new Random();
        int start = g.nodes.get(rnd.nextInt(g.nodes.size()));
        int end = g.nodes.get(rnd.nextInt(g.nodes.size()));

        System.out.printf("Start: %d, End: %d%n", start, end);

        List<Integer> path = ShortestPath.shortestPath(g, start, end);
        System.out.println("Shortest path:");
        System.out.println(path);
        System.out.println();
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        // Calculate and print eccentricities
        Map<Integer, Integer> eccentricities = Eccentricity.calculateEccentricities(g);
        System.out.println("Eccentricities:");
        for (Map.Entry<Integer, Integer> entry : eccentricities.entrySet()) {
            System.out.println("Node " + entry.getKey() + ": " + entry.getValue());
        }

        // Generate a random node and calculate its eccentricity
        int node = g.nodes.get(rnd.nextInt(g.nodes.size()));
        int ecc = Eccentricity.calculateEccentricity(g, node);
        System.out.println("Eccentricity for node " + node + ": " + ecc);

        // Calculate and print the radius and diameter of the graph
        int radius = Eccentricity.calculateRadius(g);
        int diameter = Eccentricity.calculateDiameter(g);
        System.out.println("Radius: " + radius);
        System.out.println("Diameter: " + diameter);
        System.out.println();
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();
    }
}