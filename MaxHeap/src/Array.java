public class Array<E> {
    private E[] data;
    private int size;   // size 不仅表示数组的大小，还表示数组的第一个为空元素的下标

    // 构造函数，传入数组的容量 capacity 构造 Array
    public Array(int capacity) {
        data =(E[]) new Object[capacity];
        size = 0;
    }

    // 五参数的构造函数，默认数组的容量 capacity=10
    public Array() {
        this(10);
    }

    public Array(E[] arr) {
        data = (E[])new Object[arr.length];
        for(int i = 0; i<arr.length; i ++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }
    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }
    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }
    // 在所有元素后添加一个新元素
    public void  addLast(E e) {
            add(size, e);
    }
    // 在所有元素前添加一个新元素
    public void addFirst(E e) {
        add(0, e);
    }
    // 在第 index 个位置插入一个新元素
    public void add(int index, E e) {
        if (index<0 && index>size)
            throw new IllegalArgumentException("Add failed. Require index>0 && index<=size.");
        if (size == data.length)
            resize(2*data.length);
        for (int i = size - 1; i>=index; i--)
            data[i+1] = data[i];
        data[index] = e;
        size ++;
    }

    // 修改 index 索引位置的元素为 e
    public void set(int index, E e) {
        if(index<0 && index>=size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }
    // 获取index索引位置的元素
    public E get(int index) {
        if(index<0 && index>=size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }
    public E getLast() {
        return get(size - 1);
    }
    public E getFirst() {
        return get(0);
    }

    // 查找数组中是否有元素 e
    public boolean contains(E e) {
        for(int i=0; i<size; i++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    // 从数组中删除 index 位置的元素，返回删除的元素
    public E remove(int index) {
        if(index<0 || index>=size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        E ret = data[index];
        for(int i=index+1; i<size; i++)
            data[i-1] = data[i];
        size --;
        data[size] = null;

        if(size == data.length / 4 && data.length/2 != 0) resize(data.length/2);

        return ret;
    }

    // 从数组中删除第1个元素，返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从数组中删除最后1个元素，返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除元素 e，如果元素 e 不存在那么什么都不做
    public void removeElement(E e) {
        int index = find(e);
        if(index != -1)
            remove(index);
    }

    // 查找数组中元素 e 所在的索引，如果不存在元素 e，则返回-1
    public int find(E e) {
        for(int i=0; i<size; i++) {
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }

    public void swap(int i, int j) {
        if(i<0 || i >= size || j<0 || j>= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() { // 定义该类在打印输出时的行为
        StringBuilder res  = new StringBuilder();
        res.append(String.format("Array: size=%d, capacity=%d\n", size, data.length));
        res.append('[');
        for(int i = 0; i<size; i ++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i=0; i<size; i ++)
            newData[i] = data[i];
        data = newData;
    }
}
