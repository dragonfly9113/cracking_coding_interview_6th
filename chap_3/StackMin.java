//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    // 3.2: Stack Min: add support to retrieve minimal element in O(1) time
    public static class MyStack<T extends Comparable<T>>
    {
        private class StackNode<T extends Comparable<T>>
        {
            private T data;
            // min: the miminal value of the substack with this node as top (inclusive)
            private T minData;
            private StackNode<T> next;
            
            public StackNode(T data) { 
                this.data = data;
            }
            
            public void updateMin(T oldMin) {
                minData = (data.compareTo(oldMin) < 0) ? data : oldMin;
            }
        }
        
        private StackNode<T> top;

        // push a new element to the stack
        public void push(T data) {
            StackNode<T> node = new StackNode<T>(data);
            // update the minimal element with this new node as top
            T oldMin = (top == null) ? data : top.minData;
            node.updateMin(oldMin);
            
            node.next = top;
            top = node;
        }
        
        // remove and return the top element of the stack
        public T pop() {
            if (top == null) throw new EmptyStackException();
            
            T data = top.data;
            top = top.next;
            return data;
        }
        
        // return (but not remove) the top element of the stack
        public T peek() {
            if (top == null) throw new EmptyStackException();
            
            return top.data;
        }
        
        // return the min
        public T min() {
            if (top == null) throw new EmptyStackException();
            
            T minData = top.minData;
            return minData;
        }
        
        public boolean isEmpty() {
            return top == null;
        }
        
        public void printStack() {
            if (top == null) throw new EmptyStackException();
            
            StackNode<T> p = top;
            System.out.print("Top ->");
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
        
    // 3.1: Use a single array to implement three stacks
    // assume the length of array is fixed at n and three stacks share the array equally
    // stack 1: a[0], a[3], a[6] ... a[n-3]
    // stack 2: a[1], a[4], a[7] ... a[n-2]
    // stack 3: a[2], a[5], a[8] ... a[n-1]
    // Since Java doesn't support array of generic type, here we assume the values are of int type.
    public static class MyThreeStacks
    {
        private int[] stackArr = new int[300];
        
        // array p is used to store current index for stack 0, 1 and 2
        private int[] p = new int[]{-3, -2, -1};
        
        public void push(int data, int i) {
            // move the index of stack i to the next slot
            p[i] += 3;
            stackArr[p[i]] = data;
        }
        
        public int pop(int i) {
            if (p[i] < 0) throw new EmptyStackException();
            
            int data = stackArr[p[i]];
            p[i] -= 3;
            return data;
        }

        public int peek(int i) {
            if (p[i] < 0) throw new EmptyStackException();
            
            return stackArr[p[i]];
        }
        
        public boolean isEmpty(int i) {
            return p[i] < 0;
        }
        
        public void printStack(int i) {
            if (p[i] < 0) throw new EmptyStackException();
            
            int idx = p[i];
            System.out.println("The stack #" + i + " is:");
            System.out.print("Top -- ");
            while (idx >= 0) {
                System.out.print(stackArr[idx] + " -- ");
                idx -= 3;
            }
            System.out.print("Bottom");
            System.out.println();
        }
    }

    // 3.1: Use a single array to implement three stack
    // assume the length of array is fixed at n and three stacks share the array equally
    // stack 1: [0, n/3)
    // stack 2: [n/3, 2n/3)
    // stack 3: [2n/3, n)
    public static class FixedMultiStack
    {
        private int numOfStacks = 3;
        private int stackCapacity;
        private int[] values;
        private int[] sizes;
        
        public FixedMultiStack(int stCapacity) {
            stackCapacity = stCapacity;
            values = new int[numOfStacks * stackCapacity];
            sizes = new int[numOfStacks];
        }
        
        public void push(int stackIndex, int data) throws FullStackException {
            if (isFull(stackIndex)) throw new FullStackException("Stack is full!");

            // save data into the according slot
            int offset = stackIndex * stackCapacity;
            values[offset + sizes[stackIndex]] = data;
            
            // increment this stack's size by one
            sizes[stackIndex]++;
        }

        public int pop(int stackIndex) {
            if (isEmpty(stackIndex)) throw new EmptyStackException();
            
            // decrement this stack's size by one
            sizes[stackIndex]--;
            
            // return the top item
            int offset = stackIndex * stackCapacity;
            return values[offset + sizes[stackIndex]];
        }
        
        public int peek(int stackIndex) {
            if (isEmpty(stackIndex)) throw new EmptyStackException();
            
            // just return the top item
            int offset = stackIndex * stackCapacity;
            return values[offset + sizes[stackIndex] - 1];
        }
        
        public void printStack(int stackIndex) {
            //if (isEmpty(stackIndex)) throw new EmptyStackException();
            
            int offset = stackIndex * stackCapacity;
            System.out.print("bottom -- ");
            for (int i = 0; i < sizes[stackIndex]; i++) {
                System.out.print(values[offset + i] + " -- ");
            }
            System.out.println("top");
        }
        
        public boolean isFull(int stackIndex) {
            return sizes[stackIndex] >= stackCapacity;
        }

        public boolean isEmpty(int stackIndex) {
            return sizes[stackIndex] <= 0;
        }
    }
    
    // 3.1: Use a looped array to implement multiple stacks in a more flexible way
    // Initially multiple stacks have same capacity
    // stack 1: [0, n/3)
    // stack 2: [n/3, 2n/3)
    // stack 3: [2n/3, n)
    // when one stack grows out of its capcity, it can grow over its neighbor if the space is available.
    // this way we can avoid some space is wasted on unpopulated stack
    public static class MultiStack
    {
        private int numOfStacks;
        private int totalCapacity;
        private int[] values;
        private stackInfo[] stacks;
        
        private class stackInfo
        {
            private int start;
            private int size;
            private int capacity;
            
            public stackInfo(int st, int sz, int cap) {
                this.start = st;
                this.size = sz;
                this.capacity = cap;
            }
            
            public int getTopIndex() {
                int topIndex = start + size;
                
                return (topIndex < totalCapacity) ? topIndex : topIndex - totalCapacity;
            }
            
            public boolean isFull() {
                return size == capacity;
            }
            
            public boolean isEmpty() {
                return size == 0;
            }
        }
        
        public MultiStack(int stackNum, int stackCap) {
            this.numOfStacks = stackNum;
            this.totalCapacity = stackNum * stackCap;
            this.values = new int[totalCapacity];
            this.stacks = new stackInfo[stackNum];
            
            for (int i = 0; i < stackNum; i++) {
                this.stacks[i] = new stackInfo(i * stackCap, 0, stackCap);
            }
        }

        public void push(int stIndex, int data) throws FullStackException {
            // if all stacks are at each one's capacity, throw exception
            if (areAllStacksFull()) throw new FullStackException("All stacks are full!");
            
            // if the target stack is full, need to expand into neighor stack
            if (stacks[stIndex].isFull()) {
                expand(stIndex);
            }
            
            // if the target stack still has capacity (or has just been expanded with one more slot), we can now push in the new data
            values[stacks[stIndex].getTopIndex()] = data;
            // increment its size
            stacks[stIndex].size++;
        }

        public int pop(int stIndex) {
            if (stacks[stIndex].isEmpty()) throw new EmptyStackException();
            
            // get the data at the top element
            int topIdx = stacks[stIndex].getTopIndex() - 1;
            topIdx = (topIdx < 0) ? totalCapacity + topIdx : topIdx;
            int data = values[topIdx];
            
            // clear the top slot
            values[topIdx] = 0;
            // update its stackInfo
            stacks[stIndex].size--;
            
            return data;
        }
        
        public int peek(int stIndex) {
            if (stacks[stIndex].isEmpty()) throw new EmptyStackException();
            
            // get the data at the top element
            int topIdx = stacks[stIndex].getTopIndex() - 1;
            topIdx = (topIdx < 0) ? totalCapacity + topIdx : topIdx;
            int data = values[topIdx];
            
            return data;
        }
        
        public boolean areAllStacksFull() {
            for (int i = 0; i < this.numOfStacks; i++) {
                if (this.stacks[i].size < this.stacks[i].capacity)
                    return false;
            }
            return true;
        }
        
        public void expand(int stIndex) {
            // the next stack to expand into; to the last stack, the next one is the first stack (looped back)
            int nextStack = (stIndex + 1) % numOfStacks;
            
            // if the next stack is also full, need to expand into the next to the next one, etc.
            if (stacks[nextStack].isFull())
                expand(nextStack);
            
            // the next stack still has capacity, shift all its contents to the right for one slot to make room for the stack to the left of it
            shift(nextStack);
            
            // after shifting the next stack, update the capacity of this stack (stIndex) so that new data can be pushed into it
            // its start and size remain unchanged
            stacks[stIndex].capacity++;
        }

        public void shift(int stIndex) {
            // move all data to the right for one slot
            int i, topIndex = stacks[stIndex].getTopIndex();
            for (i = 0; i < stacks[stIndex].size; i++) {
                // need to consider the case of topIndex-i or topIndex-i-1 is less than 0 (wrapping around case)
                int moveToIdx = topIndex - i;
                int moveFromIdx = topIndex - i - 1;
                moveToIdx = (moveToIdx < 0) ? totalCapacity + moveToIdx : moveToIdx;
                moveFromIdx = (moveFromIdx < 0) ? totalCapacity + moveFromIdx : moveFromIdx; 
                values[moveToIdx] = values[moveFromIdx];
            }
            
            // update its stackInfo; its size remains unchanged.
            stacks[stIndex].start++;
            stacks[stIndex].capacity--;
        }
        
        public void printAllStacks() {
            System.out.println("The values of all stacks are:");
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i] + " ");
            }
            System.out.println();
        }

        public void printAllStackInfos() {
            System.out.println("The stackInfo of all stacks are:");
            for (int i = 0; i < stacks.length; i++) {
                System.out.println("stacks[" + i + "].start... = " + stacks[i].start + " " + stacks[i].size + " " + stacks[i].capacity);
            }
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
        // 3.0: test MyStack
        /*
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
        */
        
        // 3.0: test MyQueue
        /*
        MyQueue<Integer> mq = new MyQueue<Integer>();
        
        mq.add(1);
        mq.add(2);
        mq.add(3);
        mq.printQueue();
        
        int res = mq.remove();
        System.out.println("removed the first element: " + res);
        mq.printQueue();
        res = mq.remove();
        System.out.println("removed the first element: " + res);
        mq.printQueue();
        res = mq.peek();
        System.out.println("peek the first element: " + res);
        mq.printQueue();
        
        System.out.println("The queue is empty: " + mq.isEmpty());
        */
        
        // 3.1: Three in one: implement three stacks using an array
        /*
        MyThreeStacks st = new MyThreeStacks();
        
        st.push(1, 0);
        st.push(2, 0);
        st.printStack(0);
        st.pop(0);
        st.printStack(0);
        System.out.println("The top of stack #0 is:" + st.peek(0));
        st.push(4, 1);
        st.push(5, 1);
        st.push(6, 1);
        st.printStack(1);
        System.out.println("The top of stack #1 is:" + st.peek(1));
        
        st.push(8, 2);
        st.push(9, 2);
        st.printStack(2);
        System.out.println("The top of stack #2 is:" + st.peek(2));
        */
        
        // 3.1: test FixedMultiStack class
        /*
        FixedMultiStack fms = new FixedMultiStack(100);
        
        try {
            fms.push(0, 1);
            fms.push(0, 2);
            fms.push(0, 3);
        }
        catch (FullStackException ex) {
            System.out.println(ex.getMessage());
        }
        fms.printStack(0);
        fms.printStack(1);
        fms.printStack(2);
        try {
            fms.push(1, 11);
            fms.push(1, 12);
            fms.push(1, 13);
        }
        catch (FullStackException ex) {
            System.out.println(ex.getMessage());
        }
        fms.printStack(0);
        fms.printStack(1);
        fms.printStack(2);
        try {
            fms.push(2, 111);
            fms.push(2, 222);
            fms.push(2, 333);
        }
        catch (FullStackException ex) {
            System.out.println(ex.getMessage());
        }
        fms.printStack(0);
        fms.printStack(1);
        fms.printStack(2);
        
        System.out.println("The poped out data is: " + fms.pop(2));
        fms.printStack(0);
        fms.printStack(1);
        fms.printStack(2);
        
        System.out.println("Peek stack 1: " + fms.peek(0));
        System.out.println("Peek stack 2: " + fms.peek(1));
        System.out.println("Peek stack 3: " + fms.peek(2));
        */
        
        // 3.1: test MultiStack class
        /*
        System.out.println("Test of MultiStack class");
        
        int stackNum = 3;
        int stackCap = 100;
        MultiStack ms = new MultiStack(stackNum, stackCap);
        
        System.out.println("In ms, number of stacks = " + ms.numOfStacks);
        //ms.printAllStacks();
        //ms.printAllStackInfos();
        
        try {
            for (int i = 0; i < 3; i++) {
                ms.push(0, i);
            }
            for (int i = 0; i < ms.stacks[1].capacity; i++) {
                ms.push(1, i);
            }
            for (int i = 0; i < ms.stacks[2].capacity; i++) {
                ms.push(2, i);
            }
            
            ms.push(1, 100);
            ms.push(1, 101);
            ms.push(1, 102);
        }
        catch (FullStackException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("pop stack #1: " + ms.pop(1));
        System.out.println("peek stack #1: " + ms.peek(1));
        
        System.out.println("pop stack #1: " + ms.pop(0));
        System.out.println("pop stack #1: " + ms.pop(0));
        System.out.println("pop stack #1: " + ms.pop(0));
        //System.out.println("pop stack #1: " + ms.pop(0));
        
        ms.printAllStacks();
        ms.printAllStackInfos();
        */
        
        // 3.2: test Stack Min
        System.out.println("test Stack Min");
        
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
