
# Audience Republic Developer Test
https://bitbucket.org/audiencerepublic/developer-test/wiki/clojure-2

## Assumptions
- I assumed that the random weight range is between 1 and the number of edges in the graph.

## Issues
- I had some issues generating the random connected graph, sometimes the graph is not fully connected (some nodes are not reachable from other nodes) which can cause shortest path algorithm to return an empty path.
- I couldn't find a way to make JShell support live reload of the classes.

## Instructions
### Run Graph Traversal
```console
clj -M clojure/graph_traversal.clj
```

### Run Weighted Graph
```console
clj -M clojure/weighted_graph.clj
```

### Build jar
./mvnw clean package

### Graph Tests

Graph tests should be run using the wrapper script `./graph.sh` which will run the JShell `./graph.jsh` script with the graph jar file in its classpath.

```console
./graph.sh -N 6 -S 8
# or ./graph.sh // default values are N=4 and S=4
```

### Run JShell
```console
jshell --class-path target/graph-1.0-SNAPSHOT.jar
```
> For more information on how to test the components in shell check the ./graph.jsh script

### Run JShell Interactive Graph
To run the interactive graph in JShell, you need to build the classpath and run JShell with the classpath and the target classes directory.
```console
mvn dependency:build-classpath -DincludeTypes=jar -Dmdep.outputFile=.cp.txt
jshell --class-path `cat .cp.txt`:target/classes
// or run the bash script ./interactive_graph.sh
```

