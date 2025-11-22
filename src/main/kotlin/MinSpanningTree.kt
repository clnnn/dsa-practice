package org.github.clnnn

data class Edge(val from: String, val to: String, val weight: Int)

data class Graph(val edges: List<Edge>) {
    fun vertices(): Set<String> {
        val vertices = mutableSetOf<String>()
        for (edge in edges) {
            vertices.add(edge.from)
            vertices.add(edge.to)
        }
        return vertices
    }
}

typealias MinSpanningTreeResult = Pair<List<Edge>, Int>

fun minSpanningTree(graph: Graph): MinSpanningTreeResult{
    val sortedEdges = graph.edges.sortedBy { it.weight }

    val vertices = graph.vertices()
    val disjointSetNode = vertices.associateWith { DisjointSetNode(it) }

    val mstEdges = mutableListOf<Edge>()
    var totalWeight = 0

    for ((from, to, weight) in sortedEdges) {
        val fromNode = disjointSetNode[from]!!
        val toNode = disjointSetNode[to]!!

        if (!isConnected(fromNode, toNode)) {
            union(fromNode, toNode)
            mstEdges.add(Edge(from, to, weight))
            totalWeight += weight

            // Early stopping if we already have enough edges
            if (mstEdges.size == vertices.size - 1) {
                break
            }
        }
    }

    return MinSpanningTreeResult(mstEdges, totalWeight)
}