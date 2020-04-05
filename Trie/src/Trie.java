import java.util.TreeMap;

public class Trie {
    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    // 获得 Trie 中存储的单词数量
    public int getSize() {
        return size;
    }

    // 向 Trie 中添加一个新的单词 word
    public void add(String word) {
        Node cur = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null) {
                root.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if(!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

    // 查询单词 word 是否在 Trie 中
    public boolean contains(String word) {
        Node cur = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // 在 Trie 中查询是否有单词以 prefix 开头
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for(int i=0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
