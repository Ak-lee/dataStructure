// linkedListStack 相比 ArrayStack，虽然插入和删除的不会发生内存大小的resize
// 但是new一个空间是一个比较慢的操作， 所以综合起来，linkedListStack 和 ArrayStack 的区别不大，是同一个数量级的。


public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> list;
    public LinkedListStack() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.size;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();


        for (int i=0; i<5; i ++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
