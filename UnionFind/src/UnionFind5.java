// 我们的第五版 Union-Find。在第四版的基础上添加了路径压缩操作

public class UnionFind5 implements UF {
    private int[] parent;
    private int[] rank;       // rank[i] 表示以 i 为根的集合所表示的树的层数

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for(int i = 0; i < size; i ++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找过程，查找元素 p 所对应的集合编号
    // O(h) 复杂度，h 为树的高度
    private int find(int p) {
        if(p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        while(p != parent[p]) {
            parent[p] = parent[parent[p]];
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

    // 合并元素p和元素q所属的集合
    // O(h) 复杂度，h 为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot)
            return;

        // 根据两个元素所在树的 rank 不同判断合并方向
        // 将 rank 低的集合合并到 rank 高的集合上
        if(rank[pRoot]<rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if(rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}


