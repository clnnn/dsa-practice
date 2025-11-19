package org.github.clnnn

data class GraphNode<T>(val id: String, val value: T, val neighbours: List<GraphNode<T>>)

fun <T> dfs(root: GraphNode<T>, visit: (GraphNode<T>) -> Unit) {
    val visited = HashSet<String>()
    val stack = ArrayDeque<GraphNode<T>>()

    stack.addLast(root)

    while (stack.isNotEmpty()) {
        val current = stack.removeLast()

        if (visited.contains(current.id)) {
            continue
        }

        visited.add(current.id)
        visit(current)

        // Add neighbours in reverse to maintain a more "natural" DFS order
        // if you were to print them, though not strictly necessary for correctness.
        for (neighbour in current.neighbours.asReversed()) {
            if (!visited.contains(neighbour.id)) {
                stack.addLast(neighbour)
            }
        }
    }
}

fun <T> bfs(root: GraphNode<T>, visit: (GraphNode<T>) -> Unit) {
    val visited = HashSet<String>()
    val queue = ArrayDeque<GraphNode<T>>()

    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()

        if (visited.contains(current.id)) {
            continue
        }

        visited.add(current.id)
        visit(current)

        for (neighbour in current.neighbours) {
            if (!visited.contains(neighbour.id)) {
                queue.addLast(neighbour)
            }
        }
    }
}