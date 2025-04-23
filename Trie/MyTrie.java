package Trie;

class TrieNode {
    // 26个字母 直接用index 存 字母
    //Trie 本身不一定需要存每个节点的字母，因为位置（index）已经隐含了字母；但为了调试、可读性，也可以存一个 char val 字段当标识。
    TrieNode[] children = new TrieNode[26];

    // end 标记
    boolean isEndOfWord = false;

    // 可选
    char var;
}

public class MyTrie {
    private TrieNode root;
    
    // constructor
    public MyTrie() {
        root = new TrieNode();
    }

    // insertion
    public void insert(String word) {
        TrieNode node = root;
        // 只是插入 遍历
        for (char c : word.toCharArray()) {
            // 计算单词索引
            int i = c - 'a';
            if (node.children[i] == null) {
                // 如果没有就新建 
                node.children[i] = new TrieNode();
            }
            // 往下走
            node = node.children[i];
        }
        node.isEndOfWord = true;
    }

    // 查找整个单词是否存在
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null)
                return false;
            node = node.children[i];
        }
        return node.isEndOfWord;
    }

    // 查找是否存在这个前缀
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null)
                return false;
            node = node.children[i];
        }
        return true;
    }
}
