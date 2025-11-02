package org.github.clnnn

data class TreeNode<T>(
    val value: T,
    val left: TreeNode<T>? = null,
    val right: TreeNode<T>? = null
)

fun <T> preOrder(node: TreeNode<T>?, visit: (TreeNode<T>) -> Unit) {
    if (node == null) return

    visit(node)
    preOrder(node.left, visit)
    preOrder(node.right, visit)
}

fun <T> inOrder(node: TreeNode<T>?, visit: (TreeNode<T>) -> Unit) {
    if (node == null) return

    inOrder(node.left, visit)
    visit(node)
    inOrder(node.right, visit)
}

fun <T> postOrder(node: TreeNode<T>?, visit: (TreeNode<T>) -> Unit) {
    if (node == null) return

    postOrder(node.left, visit)
    postOrder(node.right, visit)
    visit(node)
}

