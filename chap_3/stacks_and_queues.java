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
        public T pop() {
            if (top == null) throw new EmptyStackException();
            
            T data = top.data;
            top = top.next;
            return data;
        }
        
        // push a new element to the stack
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
            return (top == null);
        }
        
        public void printStack() {
            if (top == null) throw new EmptyStackException();
            
            StackNode<T> p = top;
            while (p != null) {
                System.out.print(p.data + " -> ");
                p = p.next;
            }
            System.out.print("null");
            System.out.println();
        }
            
    }
    
    public static void main(String args[])
    {
        // 3.0: implementation of a stack
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

        res = st.pop();
        System.out.println("peek of top data: " + res);
        //st.printStack();
        
        System.out.println("The stack is empty: " + st.isEmpty());
    }
}
