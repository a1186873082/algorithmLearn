package com.lc.Text.leetCode;

/**
 * 前缀树
 */
public class Trie {
    public static class TrieNode {
        private boolean isLeaf;
        private TrieNode[] children;
        private char val;

        public TrieNode() {
            this.isLeaf = false;
            children = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode();
                cur.children[idx].val = c;
            }
            cur = cur.children[idx];
        }
        cur.isLeaf = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) {
                return false;
            }
            cur = cur.children[idx];
        }
        return cur.isLeaf == true;
    }

    public boolean startsWith(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int idx = c- 'a';
            if(cur.children[idx] == null){
                return false;
            }
            cur = cur.children[idx];
        }
        return true;
    }
}
