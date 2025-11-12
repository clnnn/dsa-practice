package org.github.clnnn

class DisjointSetNode<T>(val value: T) {
    var parent: DisjointSetNode<T> = this
    var rank: Int = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DisjointSetNode<*>

        return value == other.value
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }
}

fun <T> find(node: DisjointSetNode<T>): DisjointSetNode<T> {
    if (node != node.parent) {
        node.parent = find(node.parent)  // Path compression
    }
    return node.parent
}

fun <T> union(first: DisjointSetNode<T>, second: DisjointSetNode<T>) {
    val firstRoot = find(first)
    val secondRoot = find(second)

    if (firstRoot == secondRoot) return

    when {
        firstRoot.rank < secondRoot.rank -> firstRoot.parent = secondRoot
        firstRoot.rank > secondRoot.rank -> secondRoot.parent = firstRoot
        else -> {
            secondRoot.parent = firstRoot
            firstRoot.rank++
        }
    }
}

fun <T> isConnected(first: DisjointSetNode<T>, second: DisjointSetNode<T>): Boolean {
    return find(first) === find(second)
}

