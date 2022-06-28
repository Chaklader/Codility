

// Innovit Test


public class ReverseList {
	

    private static int totalCapacity = 0;
    private static int visited = 0;

    private static ListNode<Integer> head;


    static class ListNode<T> {

        ListNode(T x) {
            value = x;
        }

        T value;
        ListNode<T> next;
    }

    public static ListNode<Integer> reverse(ListNode<Integer> head, int k) {

        head = reverseHelper(head, 3);
        return head;
    }

    public static ListNode<Integer> reverseHelper(ListNode<Integer> head, int k) {

        if (head == null) {
            return null;
        }


        int left = totalCapacity - visited;


        ListNode<Integer> current = head;
        ListNode<Integer> next = null;
        ListNode<Integer> prev = null;

        
        int count = 0;

        if (left < k) {
            
            return head;
        }

        while (count < k && current != null) {

            next = current.next;
            current.next = prev;

            prev = current;
            current = next;

            count++;
            visited++;
        }

        if (next != null) {
            head.next = reverseHelper(next, k);
        }

        return prev;
    }


    public static void push(int newData) {

        ListNode<Integer> newNode = new ListNode<Integer>(newData);

        newNode.next = head;

        head = newNode;

        totalCapacity++;
    }

    public static void printList(ListNode<Integer> node) {

        ListNode<Integer> temp = node;

        while (temp != null) {

            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {

        int[] arr = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        for (int value : arr) {

            push(value);
        }

        System.out.println("Given Linked List");
        printList(head);

        final ListNode<Integer> reversedList = reverse(head, 3);

        System.out.println("Reversed list");
        printList(reversedList);
    }


}