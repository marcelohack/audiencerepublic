import com.audiencerepublic.graph.*;

int n = Integer.parseInt(System.getProperty("N", "4"));
int s = Integer.parseInt(System.getProperty("S", "4"));
System.out.println("------------------------------------------------------------------------------");
System.out.println("------------------------------------------------------------------------------");
System.out.printf("Number of nodes: %d, Number of edges: %d%n", n, s);

var g = MakeGraph.makeGraph(n, s);
System.out.println(g);

