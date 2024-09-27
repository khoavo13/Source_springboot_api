class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class vd {

    public static ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null; // Trả về null nếu danh sách rỗng
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;          // Di chuyển con trỏ chậm 1 bước
            fast = fast.next.next;    // Di chuyển con trỏ nhanh 2 bước
        }

        return slow; // Con trỏ chậm đang ở giữa danh sách
    }
    public static Integer find_max2(ListNode head) {
        if (head == null || head.next == null) {
            return null; 
        }

        Integer max1 = null;
        Integer max2 = null;

        ListNode current = head;
        while (current != null) {
            int value = current.value;

            if (max1 == null || value > max1) {
                max2 = max1; // Cập nhật max2 trước khi max1 thay đổi
                max1 = value;
            } else if (max1 != null && value < max1 && (max2 == null || value > max2)) {
                max2 = value;
            }

            current = current.next;
        }

        return max2;
    }
    public static Integer find_max22(ListNode head) {
        if (head == null || head.next == null) {
            return null; 
        }

        Integer firstMax = null;
        Integer secondMax = null;

        ListNode current = head;
        while (current != null) {
            int value = current.value;

            if (firstMax == null || value > firstMax) {
                secondMax = firstMax; 
                firstMax = value; 
            } else if (value < firstMax && (secondMax == null || value > secondMax)) {
                secondMax = value; // Cập nhật giá trị lớn thứ hai
            }

            current = current.next;
        }

        return secondMax;
    }


    public static void main(String[] args) {
        // Tạo danh sách liên kết đơn ví dụ: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode middle = findMiddle(head);
        System.out.println("Middle element: " + (middle != null ? middle.value : "List is empty"));
    }
}
