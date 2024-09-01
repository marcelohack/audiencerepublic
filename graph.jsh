import com.audiencerepublic.graph.*;

int n = Integer.parseInt(System.getProperty("N", "4"));
int s = Integer.parseInt(System.getProperty("S", "4"));
System.out.println("------------------------------------------------------------------------------");
System.out.println("------------------------------------------------------------------------------");
System.out.printf("Number of nodes: %d, Number of edges: %d%n", n, s);

var g = MakeGraph.makeGraph(n, s);
System.out.println(g);

System.out.println("------------------------------------------------------------------------------");

int radius = Eccentricity.calculateRadius(g);
int diameter = Eccentricity.calculateDiameter(g);
System.out.println("Radius: " + radius);
System.out.println("Diameter: " + diameter);

System.out.println("------------------------------------------------------------------------------");
Random rnd = new Random();
int start = g.getNodes().get(rnd.nextInt(g.getNodes().size()));
int end = g.getNodes().get(rnd.nextInt(g.getNodes().size()));

System.out.printf("Start: %d, End: %d%n", start, end);

List<Integer> path = ShortestPath.shortestPath(g, start, end);
System.out.println("Shortest path:");
System.out.println(path);
System.out.println("------------------------------------------------------------------------------");

int node = g.getNodes().get(rnd.nextInt(g.getNodes().size()));
int ecc = Eccentricity.calculateEccentricity(g, node);
System.out.println("Eccentricity for node " + node + ": " + ecc);

System.out.println("------------------------------------------------------------------------------");
System.out.println("------------------------------------------------------------------------------");

/exit