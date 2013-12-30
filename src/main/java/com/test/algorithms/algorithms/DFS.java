package com.test.algorithms.algorithms;

import com.test.algorithms.datastructure.LightUndirectedGraph;

/**
 * Created by Mike on 12/30/13.
 */
public class DFS {

    LightUndirectedGraph graph;
    int v;
    boolean[] visited;
    int[] edgeTo;  //edgeTo[v] = previous vertex on path from s to v

    public DFS(LightUndirectedGraph graph, int v) {
        this.graph = graph;
        this.v = v;
        this.visited = new boolean[graph.size()];
        this.edgeTo = new int[graph.size()];
        dfs(v);
    }

    private void dfs(int v) {
        visited[v] = true;

        for (int i : graph.adjacent(v)) {
            if (!visited[i]) {
                dfs(v);
                edgeTo[i] = v;
            }
        }

    }
}
