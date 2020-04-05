public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[])new Object[arr.length];
        for(int i= 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在 treeIndex的位置创建表示区间[l ... r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return ;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l)/2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid+1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }
    public E get(int index) {
        if(index<0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }
    private int leftChild(int index) {
        return 2 * index + 1;
    }
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // 返回区间[queryL, queryR] 的值
    public E query(int queryL, int queryR) {
        if(queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");
        return query(0, 0, data.length - 1, queryL, queryR);
    }
    private E query(int treeIndex, int left, int right, int queryL, int queryR) {
        if(queryL == left && queryR == right) {
            return tree[treeIndex];
        }
        int mid = left + (right - left)/2;
        if(queryR <= mid) {
            return query(leftChild(treeIndex), left, mid, queryL, queryR);
        }
        else if(mid < queryL) {
            return query(rightChild(treeIndex), mid+1, right, queryL, queryR);
        } else {
            return merger.merge(
                query(leftChild(treeIndex), left, mid, queryL, mid),
                query(rightChild(treeIndex), mid+1, right, mid+1, queryR)
            ) ;
        }
    }

    public void set(int index, E e) {
        if(index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // 在 treeIndex 为根的线段树中更新 index 的值为 e
    private void set(int treeIndex, int left, int right, int index, E e) {
        if(left == right) {
            tree[treeIndex] = e;
            return;
        }

        int mid = left + (right - left)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(index >= mid + 1) {
            set(rightTreeIndex, mid+1, right, index, e);
        } else {
            set(leftTreeIndex, left, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],  tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i=0; i<tree.length; i++) {
            if(tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');

        return res.toString();
    }
}
