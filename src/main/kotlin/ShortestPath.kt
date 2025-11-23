package org.github.clnnn

fun bellmanFord(graph: Graph, source: String): List<String>? {
    val vertices = graph.vertices()
    val distances = mutableMapOf<String, Int>().withDefault { Int.MAX_VALUE }
    distances[source] = 0

    // Relax edges (n-1) times
    repeat(vertices.size - 1) {
        for ((from, to, weight) in graph.edges) {
            val fromDist = distances[from]!!
            if (fromDist == Int.MAX_VALUE) continue

            // Relaxation step - update distance if a shorter path is found
            val newDist = fromDist + weight
            if (newDist < distances[to]!!) {
                distances[to] = newDist
            }
        }
    }

    // Check for negative-weight cycles
    for ((from, to, weight) in graph.edges) {
        val fromDist = distances[from] ?: Int.MAX_VALUE
        if (fromDist != Int.MAX_VALUE && fromDist + weight < distances[to]!!) {
            return null // Negative-weight cycle detected
        }
    }

    return vertices.filter { distances.containsKey(it) }
}