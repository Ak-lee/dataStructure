
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head.val == val) {
            ListNode node = head;
            head = head.next;
            node.next = null;
        }
        if(head == null) return null;
        ListNode prevNode = head;
        ListNode curNode = head.next;
        while(curNode.val == val) {
            prevNode.next = curNode.next;
            curNode.next = null;
            curNode = prevNode.next;
        }
        return head;
    }
}