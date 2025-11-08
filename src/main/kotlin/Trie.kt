package org.github.clnnn

data class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isEndOfWord: Boolean = false
)

fun insert(root: TrieNode, word: String) {
    var node = root
    for (char in word) {
        node = node.children.getOrPut(char) { TrieNode() }
    }

    node.isEndOfWord = true
}

fun isWordPresent(root: TrieNode, word: String): Boolean {
    var node = root
    for (char in word) {
        val next = node.children[char] ?: return false
        node = next
    }

    return node.isEndOfWord
}

fun autoComplete(root: TrieNode, prefix: String): Array<String> {
    var node = root
    for (char in prefix) {
        val next = node.children[char] ?: return arrayOf()
        node = next
    }

    val results = mutableListOf<String>()
    collectWords(node, prefix, results)
    return results.toTypedArray()
}

fun collectWords(node: TrieNode, prefix: String, results: MutableList<String>) {
    if (node.isEndOfWord) {
        results.add(prefix)
    }

    for ((char, childNode) in node.children) {
        collectWords(childNode, prefix + char, results)
    }
}

