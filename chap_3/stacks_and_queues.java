//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    public class MyStack<T>
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
            if (this.top == null) throw new EmptyStackException();
            
            return this.top.data;
            
        }
        
        public void push() {
            
            
            
        }
    }
    
    
    
    
    public static void main(String args[])
    {
        System.out.println("Hello, World!");
    }
}
