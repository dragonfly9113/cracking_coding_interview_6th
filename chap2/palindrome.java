//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

// 2.6 Palindrome: Implement a function to check if a linked list is a palindrome

class Rextester
{  
    static class Linked_list
    {
        private Node head;
        private int nodeCount;
        
        Linked_list() {
            head = null;
            nodeCount = 0;
        }
        
        static class Node
        {
            private int data;
            private Node next;
            
            Node() { data = -1; next = null; }
            Node(int d) { data = d; next = null; }
        }
        
        // Insert a new Node at front of the list.
        // Time complexity: O(1)
        public void push(int new_data)
        {
            Node new_node = new Node(new_data);
            
            new_node.next = head;
            head = new_node;
            nodeCount++;
        }

        // Append a new Node at the end of the list.
        // Time complexity: O(N)
        public void append(int new_data)
        {
            Node new_node = new Node(new_data);
            
            if (head == null) {
                head = new_node;
                nodeCount++;
                return;
            }
            
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            
            temp.next = new_node;
            nodeCount++;
        }
        
        // Insert a new Node after the given Node
        // Assume prev_node is either null or a valid Node in the list
        // Time complexity: O(1)
        public void insertAfter(Node prev_node, int new_data)
        {
            if (prev_node == null) return;
            
            Node new_node = new Node(new_data);

            new_node.next = prev_node.next;
            prev_node.next = new_node;
            nodeCount++;
        }
        
        // Given a key, deletes the first occurance of key in the linked list
        public void deleteNode(int key)
        {
            // empty list, abort deletion
            if (head == null) return;
            
            // the first occurance of key is the head, delete the head
            if (head.data == key) {
                head = head.next;
                nodeCount--;
                return;
            }
                        
            Node temp = head;
            while (temp.next != null && temp.next.data != key)
                temp = temp.next;
            
            // no occurance of key found, abort deletion
            if (temp.next == null) return;
            
            // found the first occurance of key, delete the according node
            temp.next = temp.next.next;
            nodeCount--;
        }
        
        // Return the kth node
        public Node getNode(int k)
        {
            if (nodeCount == 0 || k < 0 || k >= nodeCount) return null;
            
            int count = 0;
            Node temp = head;
            while (count < k) {
                temp = temp.next;
                count++;
            }

            return temp;
        }
        
        // Remove duplicate node(s)
        // Time complexity: O(n)
        public void deleteDups() 
        {
            HashSet<Integer> set = new HashSet<Integer>();
            
            Node prev = null;
            Node temp = head;
            while (temp != null) {
                // if this node has duplicate value, delete this node
                if (set.contains(temp.data)) {
                    prev.next = temp.next;
                    nodeCount--;
                }
                // if this node has unique value, add this value to set for recording purpose
                else {
                    set.add(temp.data);
                    prev = temp;
                }
                temp = temp.next;
            }
        }
        
        // Return the Kth node from the last node
        public Node kthFromLast(int k)
        {     
            if (nodeCount == 0 || k < 0 || k >= nodeCount) return null;
            
            Node n1 = head, n2 = head;
            
            // n2's starting point is kth ahead of n1
            for (int i = 0; i < k; i++)
                n2 = n2.next;

            // move n1 and n2 together until n2 refers to the last node
            while (n2.next != null) {
                n1 = n1.next;
                n2 = n2.next;
            }

            // return n1 which refers to the kth from n2 (the last node)
            return n1;
        }
            
        // Delete middle node: implement an algorithm to delete a node in the middle
        // (i.e. any node but the first and last node, no necessarily the exact middle) of a singly linked list, given only access to that node.
        // Example:
        // input: the node c from the linked list a->b->c->d->e->f
        // output: nothing is returned, but the new list looks like: a->b->d->e->f
        public void deleteMiddleNode(Node n)
        {
            // if there is no middle node, abort deletion
            if (nodeCount <= 2) return;
            
            Node prev = head;
            Node temp = head.next; // never delete the first node
            while (temp.next != null) { // never delete the last node
                // if this node is the matching node, delete it
                if (temp == n) {
                    prev.next = temp.next;
                    nodeCount--;
                    return;
                }
                // not a matching node, move on to next node
                else {
                    prev = temp;
                    temp = temp.next;
                }
            }
        }
        
        // 2.4 Partition: write code to partition a linked list around a value x, such that all nodes less than x come before all nodes
        // greater than or equal to x. If x is contained in the list, the values of x only need to be after the elements less than x.
        // The partition element x can appear anywhere in the "right partition"; it does not need to appear between the left and right partition.
        // Example:
        // input: 3 5 8 5 10 2 1 (partition = 5)
        // output: 3 1 2 10 5 5 8
        public void partitionList(int pivot)
        {
            // two dummy heads: head1 is for the left partition and head2 is for the right
            Node p1 = new Node(-1);
            Node p2 = new Node(-1);
            Node head1 = p1, head2 = p2;
            
            Node temp = head;
            while (temp != null) {
                
                if (temp.data < pivot) {
                    Node n = new Node(temp.data);
                    p1.next = n;
                    p1 = p1.next;
                }
                else {
                    Node n = new Node(temp.data);
                    p2.next = n;
                    p2 = p2.next;
                }
                temp = temp.next;
            }
            
            // concatenate two lists together
            p1.next = head2.next;    // skip the dummy head of list2
            head = head1.next;    // skip the dummy head of list1
        }

        // try not to create new nodes
        public void partitionList_1(int pivot)
        {
            // two dummy heads: head1 is for the left partition and head2 is for the right
            Node p1 = new Node(-1);
            Node p2 = new Node(-1);
            Node head1 = p1, head2 = p2;
            
            Node temp = head;
            while (temp != null) {
                
                if (temp.data < pivot) {
                    p1.next = temp;
                    p1 = p1.next;
                }
                else {
                    p2.next = temp;
                    p2 = p2.next;
                }
                temp = temp.next;
            }
            
            p1.next = null;
            p2.next = null;
            
            // concatenate two lists together
            p1.next = head2.next;    // skip the dummy head of list2
            head = head1.next;    // skip the dummy head of list1
        }
        
        // 2.5: sum list
        public static Linked_list sumList(Linked_list list1, Linked_list list2)
        {
            Node p1 = list1.head, p2 = list2.head;
            
            Linked_list list = new Linked_list();
            Node p = new Node(-1); // serve as a dummy head
            list.head = p;
            int carry = 0;
            
            while (p1 != null && p2 != null) {
                Node sumNode = null;
                int sum = p1.data + p2.data + carry;
                if (sum >= 10) {
                    sumNode = new Node(sum % 10);
                    carry = 1;
                } else {
                    sumNode = new Node(sum);
                    carry = 0;
                }
                p.next = sumNode;
                p1 = p1.next; p2 = p2.next; p = p.next;
            }
            
            if (p1 == null && p2 == null) {
                if (carry == 1) {
                    Node sumNode = new Node(carry);
                    p.next = sumNode;
                }
                list.head = list.head.next;
                return list;
            }
            Node sumNode = null;
            if (p1 == null) {
                int sum = p2.data + carry;
                sumNode = new Node(sum);
                sumNode.next = p2.next;
            } else { // p2 == null
                int sum = p1.data + carry;
                sumNode = new Node(sum);
                sumNode.next = p1.next;
            }
            p.next = sumNode;
            list.head = list.head.next;
            return list;
        }

        // 2.5: sum list: recursive version
        public static Node addList(Node p1, Node p2, int carry)
        {
            if (p1 == null && p2 == null && carry == 0) return null;
            
            Node result = new Node();
            int value = carry;
            
            if (p1 != null)
                value += p1.data;
            
            if (p2 != null)
                value += p2.data;
            
            result.data = value % 10;
            
            // if there are more digits to add, call addList() again
            if (p1 != null || p2 != null) {
                Node more = addList(p1 == null ? null : p1.next,
                                    p2 == null ? null : p2.next,
                                    value >= 10 ? 1 : 0);
                result.next = more;
            }
            
            return result;
        }

        // 2.5: sum list: follow-up: now the most significant digit is stored in the first node
        // Input: 5->8->9 means 589
        //        6->7->8 means 678
        // Output: 1->2->6->7 means 1267
        //
        // Input: 5->8->9 means 589
        //        6->7 means 67
        // Output: 6->5->6 means 656
        // In Java, function parameters are passed in by value (no pass-by-reference mechnism), therefore in order to
        // return two values, we can use the trick as below: use a wrapper class.
        static class PartialSum
        {
            public Node node = null;
            public int carry = 0;
        }
        
        public static Linked_list addListFollowup(Linked_list l1, Linked_list l2)
        {
            // To make the task easier, we make sure that the two lists be of the same length by padding zeros in the shorter one
            if (l1.getCount() != l2.getCount())
                padZeros(l1, l2);
            
            PartialSum res = addListHelper(l1.head, l2.head);
            
            Linked_list list = new Linked_list();
            list.head = res.node;
                
            return list;
        }
    
        static void padZeros(Linked_list l1, Linked_list l2)
        {
            int len1 = l1.getCount();
            int len2 = l2.getCount();
            
            int diff = Math.abs(len1 - len2);
            Linked_list listToPad = (len1 < len2) ? l1 : l2;
            Node head = listToPad.head;
            
            for (int i = 0; i < diff; i++) {
                Node zero = new Node(0);
                zero.next = head;
                head = zero;
            }
            listToPad.head = head;
        }
    
        static PartialSum addListHelper(Node p1, Node p2)
        {
            if (p1 == null && p2 == null) {
                PartialSum res = new PartialSum();
                res.node = null;
                res.carry = 0;
                return res;
            }
            
            PartialSum res = addListHelper(p1.next, p2.next);
            
            int value = res.carry;
            
            value += p1.data;
            value += p2.data;
            
            PartialSum helperRes = new PartialSum();
            helperRes.carry = value >= 10 ? 1 : 0;
            
            Node node = new Node(value % 10);
            node.next = res.node;
            helperRes.node = node;
                        
            return helperRes;
        }

        // 2.6 Palindrome: Implement a function to check if a linked list is a palindrome
        public boolean isPalindrome()
        {
            if (this.head == null) return false;
            
            Linked_list revList = this.copyLink();
            
            revList.reverseLinkInPlace();
                       
            return this.equalList(revList);
        }

        // 2.6 Palindrome: another version which uses reverseLink() instead of reverseLinkInPlace()
        public boolean isPalindrome_1()
        {
            if (this.head == null) return false;
            
            Linked_list revList = this.reverseLink();
            //System.out.println("current list's count = " + this.getCount());
            //System.out.println("revList's count = " + revList.getCount());
            
            return this.equalList(revList);
        }
        
        // 2.6 Palindrome: how about using a stack?
        public boolean isPalindrome_2()
        {
            if (this.head == null) return false;
            Node p = this.head;
            
            // push all nodes into a stack
            Stack<Integer> stack = new Stack<Integer>();
            while (p != null) {
                stack.push(p.data);
                p = p.next;
            }
            
            // pop from stack one by one and compare with the list
            p = this.head;
            while (p != null) {
                if (p.data != stack.pop()) return false;
                p = p.next;
            }
            
            return true;
        }
        
        // 2.6 Palindrome: still an iterative version using a stack
        // We don't need to compare the whole list with a reversed version of itself
        // It is enough to just compare the reversed version of the first half with the second half
        // But need to take care of the middle node in the case of odd number of nodes
        // Assume that we don't know the number of nodes
        public boolean isPalindrome_3()
        {
            if (this.head == null) return false;
            
            Stack<Integer> stack = new Stack<Integer>();
            Node slow = this.head, fast = this.head;
            
            // fast will move twice as fast as slow, so when fast reaches the end of the list, slow should be in the middle
            // at the same time, push the first half into a stack
            while (slow != null && fast != null && fast.next != null) {
                stack.push(slow.data);
                slow = slow.next;
                fast = fast.next.next;
            }

            // handle the case when there is odd number of nodes in the list
            if (fast != null) slow = slow.next;
            
            // Now the first hald is in the stack, we can then compare it with the second half
            while (slow != null) {
                int top = stack.pop().intValue();
                    
                if (slow.data != top) return false;
                slow = slow.next;
            }

            return true;
        }
        
        // 2.6 Palindrome: recursive version
        // Assume we know the length of the list
        public boolean isPalindrome_4()
        {
            int length = this.getCount();
            System.out.println("isPalindrome_4(): length = " + length);
            Node p = this.head;
            
            PalindromeResult res = isPalindromeHelper(p, length);
            
            return res.isPal;
        }
        
        // A wrapper class to help isPalindrom() recursive version
        class PalindromeResult
        {
            public boolean isPal;
            public Node backNode;
        }
            
        // The real recursive function
        PalindromeResult isPalindromeHelper(Node frontNode, int length)
        {
            if (length == 0 || length == 1) {
                PalindromeResult res = new PalindromeResult();
                res.isPal = true;
                res.backNode = (length == 0) ? frontNode : frontNode.next;
                return res;
            }
            
            PalindromeResult res = isPalindromeHelper(frontNode.next, length - 2);
            
            if (res.isPal == false) return res;
            
            // compare according frontNode and backNode
            if (frontNode.data == res.backNode.data) {
                res.isPal = true;
                res.backNode = res.backNode.next;
            } else {
                res.isPal = false;
                // res.backNode doesn't matter any more
            }
            
            return res;
        }
        
        // check if two lists are the same
        public boolean equalList(Linked_list list)
        {
            if (list.getCount() != this.nodeCount) return false;
            
            Node p1 = head, p2 = list.head;
            while (p1 != null) {
                
                if (p1.data != p2.data) return false;
                
                p1 = p1.next;
                p2 = p2.next;
            }

            return true;
        }
        
        // returns a copy of this link
        public Linked_list copyLink()
        {
            Linked_list newLink = new Linked_list();
            if (this.head == null) return newLink;
            
            Node dummyHead = new Node(-1);
            Node p1 = this.head, p2 = dummyHead;
            while (p1 != null) {
                Node n = new Node(p1.data);
                p2.next = n;
                p2 = p2.next;
                p1 = p1.next;
                newLink.nodeCount++;
            }
       
            p2.next = null;
            newLink.head = dummyHead.next;
            
            return newLink;
        }
        
        // Reverse the link: in-place reverse (no new list is created)
        public void reverseLinkInPlace()
        {
            Node c = this.head, p = null;
            
            while (c != null) {
                Node t = c;
                c = c.next;
                t.next = p;
                p = t;
            }
            this.head = p;
        }
        
        // returns a new link in reverse order
        public Linked_list reverseLink()
        {
            Linked_list revList = new Linked_list();
            if (this.head == null) return revList;
            
            Node p1 = this.head, p2 = null;
            while (p1 != null) {
                Node n = new Node(p1.data);
                n.next = p2;
                p1 = p1.next;
                p2 = n;
                revList.nodeCount++;
            }
                
            revList.head = p2;
            return revList;
        }
        
        // Return the size of the list by traversing through the list
        // Time complexity: O(n)
        public int getSize()
        {
            Node temp = head;
            int size = 0;
            
            while (temp != null) {
                size++;
                temp = temp.next;
            }
            
            return size;
        }
        
        // Do the same thing as getSize() but in O(1) time
        public int getCount()
        {
            return nodeCount;
        }
        
        public void buildList(int[] a)
        {
            for (int i = a.length - 1; i >= 0; i--) {
                push(a[i]);
            }
        }
        
        // Print out the list
        public void printList()
        {
            Node temp = this.head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            
            System.out.println();
        }
    }
    
    public static void main(String args[])
    {
        Linked_list llist = new Linked_list();
        int[] a = {1, 2, 3, 4, 5, 6};
        llist.buildList(a);
        llist.printList();
        
        llist.push(0);
        llist.printList();
        
        llist.append(7);
        llist.printList();
        
        llist.insertAfter(llist.head, 4);
        llist.append(4);
        llist.push(4);
        llist.printList();
        
        llist.deleteNode(6);
        llist.printList();
        System.out.println("getCount() = " + llist.getCount());
        
        /* 2.2
        int k = 5;
        Linked_list.Node n = llist.kthFromLast(k);
        if (n != null)
            System.out.println("The " + k + "th node from the last is: " + n.data);
        else
            System.out.println("The " + k + "th node from the last is an invalid node.");
        */
        
        // 2.3 delete specified middle node
        /*
        Linked_list.Node n = llist.getNode(2);
        llist.deleteMiddleNode(n);
        llist.printList();
        System.out.println("getCount() = " + llist.getCount());
        */
        
        // 2.4 Partition: write code to partition a linked list around a value x
        // input: 3 5 8 5 10 2 1 (partition = 5)
        /*
        System.out.println();
        System.out.println("A new list l2: ");
        Linked_list l2 = new Linked_list();
        int[] b = {3, 5, 8, 5, 10, 2, 1};
        l2.buildList(b);
        l2.printList();
        System.out.println("getCount() = " + l2.getCount());
        System.out.println("After partitionList(): ");
        l2.partitionList_1(8);
        l2.printList();
        System.out.println("getCount() = " + l2.getCount());
        */
        
        // 2.5 Sum list:
        /*
        System.out.println();
        System.out.println("Test 2.5 sum list");
        Linked_list list1 = new Linked_list();
        Linked_list list2 = new Linked_list();
        //int[] b1 = {2, 1, 6};
        //int[] b2 = {5, 2, 5};
        int[] b1 = {6, 1, 7, 2};
        int[] b2 = {2, 9, 5};
        list1.buildList(b1);
        list2.buildList(b2);
        System.out.println("list1 is: ");
        list1.printList();
        System.out.println("list2 is: ");
        list2.printList();
     
        //Linked_list result = Linked_list.sumList(list1, list2);
        //Linked_list result = new Linked_list();
        //result.head = Linked_list.addList(list1.head, list2.head, 0);
        Linked_list result = Linked_list.addListFollowup(list1, list2);
        
        System.out.println("The sum result is: ");
        result.printList();
        */
        
        // 2.6 Palindrome: Implement a function to check if a linked list is a palindrome
        System.out.println();
        System.out.println("Test 2.6: palindrom list");
        Linked_list list1 = new Linked_list();
        Linked_list list2 = new Linked_list();
        int[] b1 = {1, 2, 2, 1, 0};
        int[] b2 = {1, 2, 3, 4};
        list1.buildList(b1);
        list2.buildList(b2);
        System.out.println("list1 is: ");
        list1.printList();
        System.out.println("list2 is: ");
        list2.printList();
        System.out.println("list1 and list2 are equal: " + list1.equalList(list2));
        
        System.out.println("list1 is palindrome: " + list1.isPalindrome_4());
        System.out.println("list2 is palindrome: " + list2.isPalindrome_4());
    }
}
