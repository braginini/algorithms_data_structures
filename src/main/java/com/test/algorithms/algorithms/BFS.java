package com.test.algorithms.algorithms;

import com.test.algorithms.datastructure.LightUndirectedGraph;

import java.util.*;

/**
 * Created by Mike on 12/30/13.
 */
public class BFS {

    LightUndirectedGraph graph;
    int v;
    boolean[] visited;
    Integer[] edgeTo;

    public BFS(LightUndirectedGraph graph, int v) {
        this.graph = graph;
        this.v = v;
        this.visited = new boolean[graph.size()];
        this.edgeTo = new Integer[v];

        bfs(v);
    }

    private void bfs(int v) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(v);

        while (!queue.isEmpty()){
            int currV = queue.poll();
            for (Integer adj : graph.adjacent(currV)) {
                if (!this.visited[adj]) {
                    queue.offer(adj);
                    this.edgeTo[adj] = currV;
                    this.visited[currV] = true;
                }
            }
        }
    }

    public Collection<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return Collections.emptyList();

        Stack<Integer> path = new Stack<Integer>();

        while (v != this.v) {
            path.push(v);
            v = edgeTo[v];
        }
        path.push(this.v);

        return path;
    }

    public boolean hasPathTo(int v) {
        return visited[v];
    }


}
