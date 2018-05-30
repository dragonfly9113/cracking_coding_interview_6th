//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    public static class MyStack<T>
    {
        private class StackNode<T>
        {
            private T data;
            private StackNode<T> next;
            
            public StackNode(T data) { 
                this.data = data;
            }
        }
        
        private StackNode<T> top;
        
        // remove and return the top element of the stack
        // compute complexity: O(1)
        public T pop() {
            if (top == null) throw new EmptyStackException();
            
            T data = top.data;
            top = top.next;
            return data;
        }
        
        // push a new element to the stack
        // compute complexity: O(1)
        public void push(T data) {
            StackNode<T> node = new StackNode<T>(data);
            
            node.next = top;
            top = node;
        }
        
        // return (but not remove) the top element of the stack
        public T peek() {
            if (top == null) throw new EmptyStackException();
            
            return top.data;
        }
        
        public boolean isEmpty() {
            return top == null;
        }
        
        public void printStack() {
            if (top == null) throw new EmptyStackException();
            
            StackNode<T> p = top;
            System.out.print("Top -> ");
            while (p != null) {
                System.out.print(p.data + " -> ");
                p = p.next;
            }
            System.out.print("Bottom");
            System.out.println();
        }
            
    }
    
    // Implement a queue using singlely-linked list
    // Add and remove an item must happen at the opposite end of the list.
    // Should we use the head (of the list) to be first item or last item?
    // This choice is important because it will decide the effectness of remove() method!
    // Answer: it is better to choose the head as first item (oldest item). this choise will not affect add() but will make remove() much easier!
    public static class MyQueue<T> 
    {
        private class QueueNode<T>
        {
            private T data;
            private QueueNode<T> next;

            public QueueNode(T data) {
                this.data = data;
            }
        }

        private QueueNode<T> first;
        private QueueNode<T> last;
        
        // add an element to the queue
        public void add(T data) {
            QueueNode<T> t = new QueueNode<T>(data);
            
            if (last != null) last.next = t;
            last = t;
            
            if (first == null) first = last;
        }
            
        // remove and return the first element of the queue
        public T remove() {
            if (first == null) throw new NoSuchElementException();
            T data = first.data;

            first = first.next;
            if (first == null) last = first;
            
            return data;
        }
        
        // return the first element of the queue
        public T peek() {
            if (first == null) throw new NoSuchElementException();
            
            return first.data;
        }
        
        // check if a queue is empty
        public boolean isEmpty() {
            return first == null;
        }
        
        public void printQueue() {
            if (first == null) throw new NoSuchElementException();
            
            QueueNode<T> p = first;
            while (p != null) {
                System.out.print(p.data + " -> ");
                p = p.next;
            }
            System.out.print("null");
            System.out.println();
        }
    }
        
    @SuppressWarnings("serial")
    public static class FullStackException extends Exception
    {
        public FullStackException(String s) {
            super(s);
        }
    }
        
    public static void main(String args[])
    {
        // 3.2: Stack Min
        System.out.println("Stack min");

        MyStack<Integer> st = new MyStack<Integer>();
        st.push(3);
        st.push(2);
        st.push(1);
        st.printStack();
        
        int res = st.pop();
        System.out.println("pop out data: " + res);
        st.printStack();
        
        res = st.pop();
        System.out.println("pop out data: " + res);
        st.printStack();
        res = st.peek();
        System.out.println("peek of top data: " + res);
        st.printStack();
        
        System.out.println("The stack is empty: " + st.isEmpty());
    }
}
