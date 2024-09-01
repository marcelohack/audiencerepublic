package com.audiencerepublic.graph;

import java.util.*;

public class Edge {
    int from, to, weight;

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Edge edge = (Edge) obj;
        return from == edge.from && to == edge.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return String.format("%d->%d (weight %d)", from, to, weight);
    }
}
