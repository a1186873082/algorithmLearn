package com.lc.Text.leetCode;

public class Trie {
    class TrieNode {
        public boolean isLeaf;
        public TrieNode[] children;
        public char val;

        public TrieNode() {
            this.isLeaf = false;
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String str) {
        TrieNode trieNode = root;
        for (char c : str.toCharArray()) {
            int idx = c - 'a';
            if(trieNode.children[idx] == null) {
                trieNode.children[idx] = new TrieNode();
                trieNode.children[idx].val = c;
            }
            trieNode = trieNode.children[idx];
        }
        trieNode.isLeaf = true;
    }

    public boolean search(String str) {
        TrieNode trieNode = root;
        for (char c : str.toCharArray()) {
            int idx = c - 'a';
            if (trieNode.children[idx] == null) {
                return false;
            }
            trieNode = trieNode.children[idx];
        }
        return true;
    }

    public boolean equals(String str) {
        TrieNode trieNode = root;
        for (char c : str.toCharArray()) {
            int idx = c - 'a';
            if (trieNode.children[idx] == null) {
                return false;
            }
            trieNode = trieNode.children[idx];
        }
        return trieNode.isLeaf == true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("potijac");
//        trie.insert("potij");
//        trie.insert("potiff");
//        trie.insert("aotijac");
//        trie.insert("fffijac");
        trie.insert("potitt");


//        System.out.println(trie.search("123"));
        System.out.println(trie.equals("potijac"));

    }
}