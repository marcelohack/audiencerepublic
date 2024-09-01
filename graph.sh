
#!/bin/bash

num_args=$#
n=4
s=4

if [ $num_args -ne 0 ] && [ $num_args -ne 4 ]; then
    echo "Usage: './graph.sh -N 5 -S 6' or './graph.sh' // default values are -N ${n} -S ${s}"
    exit 1
fi

if [ $num_args == 4 ]; then
    n=$2
    s=$4
fi

jshell --class-path target/graph-1.0-SNAPSHOT.jar -v -R-DN=$n -R-DS=$s ./graph.jsh