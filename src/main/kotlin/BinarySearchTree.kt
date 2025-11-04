package org.github.clnnn

fun search(node: TreeNode<Int>?, searchValue: Int): TreeNode<Int>? {
    if (node == null) return null

    return when {
        searchValue < node.value -> search(node.left, searchValue)
        searchValue > node.value -> search(node.right, searchValue)
        else -> node
    }
}

fun insert(node: TreeNode<Int>?, newValue: Int): TreeNode<Int> {
    if (node == null) {
        return TreeNode(newValue)
    }

    when {
        newValue < node.value -> node.left = insert(node.left, newValue)
        newValue > node.value -> node.right = insert(node.right, newValue)
    }

    return node
}

fun remove(node: TreeNode<Int>?, value: Int): TreeNode<Int>? {
    if (node == null) return null

    when {
        value < node.value -> node.left = remove(node.left, value)
        value > node.value -> node.right = remove(node.right, value)
        else -> when {
            // Leaf node or single child cases
            node.left == null -> node.right
            node.right == null -> node.left

            // Two children: replace with successor
            else -> node.apply {
                this.value = findMin(right)!!.value
                right = remove(right, this.value)
            }
        }
    }

    return node
}

fun findMin(node: TreeNode<Int>?): TreeNode<Int>? {
    var current = node
    while (current?.left != null) {
        current = current.left
    }
    return current
}

