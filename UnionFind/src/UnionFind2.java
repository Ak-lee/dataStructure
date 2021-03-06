// 我们的第二版 Union-Find
public class UnionFind2 implements UF {

    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];
        for(int i=0; i<parent.length; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找过程，查找元素 p 所对应的集合编号
    // O(h) 复杂度，h 为树的高度
    private int find(int p) {
        if(p<0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");
        while(p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    // 查看元素 p 和元素 q 是否所属一个集合
    // O(h) 复杂度，h 为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot) {
            return;
        }
        parent[pRoot] = qRoot;

        // parent[find(p)] = find(q);
    }
}
