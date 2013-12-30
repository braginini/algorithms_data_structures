package com.test.algorithms.datastructure;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Mike on 12/30/13.
 */
public class LightUndirectedGraph {

    //array index is vertex, each adj entry contains LinkedList of adjacent vertexes
    LinkedList<Integer>[] adj;

    private final int n;

    public LightUndirectedGraph(int n) {
        this.n = n;
        adj = new LinkedList[n];
        for (int i = 0; i < n; i++){
            adj[i] = new LinkedList<Integer>();
        }
    }

    public Collection<Integer> adjacent(int v) {
        return adj[v];
    }

    public boolean connected(int v1, int v2) {
        for (Integer v : adj[v1]) {
            if (v == v2)
                return true;
        }

        return false;
    }

    public int degree(int v) {
        return adj[v].size();

    }

    public void connect(int v1, int v2) {
        adj[v1].add(v2);
        adj[v2].add(v1);
    }

    public int size() {
        return n;
    }
}
