package com.audiencerepublic.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class EccentricityTests {

        @Test
        void testEccentricity() {

                List<Integer> nodes = Arrays.asList(1, 2, 3, 4, 5);
                List<Edge> edges = Arrays.asList(
                                new Edge(1, 4, 2),
                                new Edge(2, 5, 2),
                                new Edge(3, 1, 1),
                                new Edge(3, 2, 4),
                                new Edge(4, 2, 4),
                                new Edge(4, 3, 2),
                                new Edge(5, 3, 7));

                Graph g = new Graph(nodes, edges);

                int ecc1 = Eccentricity.calculateEccentricity(g, 1);
                int ecc2 = Eccentricity.calculateEccentricity(g, 2);
                int ecc3 = Eccentricity.calculateEccentricity(g, 3);
                int ecc4 = Eccentricity.calculateEccentricity(g, 4);
                int ecc5 = Eccentricity.calculateEccentricity(g, 5);

                // Distances from node 1: {1=0, 2=6, 3=4, 4=2, 5=8} // 2(1->4) + 4(4->2) +
                // 2(2->5) = 8
                assertEquals(8, ecc1);
                // Distances from node 2: {1=10, 2=0, 3=9, 4=12, 5=2} // 2(2->5) + 7(5->3) +
                // 1(3->1) + 2(1->4) = 12
                assertEquals(12, ecc2);
                // Distances from node 3: {1=1, 2=4, 3=0, 4=3, 5=6} // 4(3->2) + 2(2->5) = 6
                assertEquals(6, ecc3);
                // Distances from node 4: {1=3, 2=4, 3=2, 4=0, 5=6} // 4(4->2) + 2(2->5) = 6
                assertEquals(6, ecc4);
                // Distances from node 5: {1=8, 2=11, 3=7, 4=10, 5=0} // 7(5->3) + 4(3->2) = 11
                assertEquals(11, ecc5);

                int diameter = Eccentricity.calculateDiameter(g);
                assertEquals(12, diameter); // The maximum eccentricity is 12
                int radius = Eccentricity.calculateRadius(g);
                assertEquals(6, radius); // The minimum eccentricity is 6
        }
}
