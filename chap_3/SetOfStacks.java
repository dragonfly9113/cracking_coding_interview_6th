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
            System.out.print("Top -> ");
            while (p != null) {
                System.out.print(p.data + " -> ");
                p = p.next;
            }
            System.out.print("Bottom");
            System.out.println();

            // also print out min values
            p = top;
            System.out.print("Min -> ");
            while (p != null) {
                System.out.print(p.minData + " -> ");
                p = p.next;
            }
            System.out.print("Bottom");
            System.out.println();
        }
    }
    
    // 3.2 Stack Min: 2nd version which might save some space
    @SuppressWarnings("serial")
    public static class StackWithMin extends Stack<Integer>
    {
        // use another stack to keep min values
        private Stack<Integer> s2;
        
        public StackWithMin() {
            s2 = new Stack<Integer>();
        }

        public void push(int value) {
            // value is the new min value, save it in s2
            if (value <= this.min())
                s2.push(value);
            
            super.push(value);
        }

        public Integer pop() {
            // if the top value is the current min value, pop it from s2
            if (super.peek() == this.min())
                s2.pop();
            
            return super.pop();
        }
        
        public Integer min() {
            if (s2.isEmpty())
                return Integer.MAX_VALUE;
            
            return s2.peek();
        }
        
        public void printStack() {
            if (this.empty()) throw new EmptyStackException();
            System.out.println("current stack:" + this);
            System.out.println("s2:" + s2);
            System.out.println("current min: " + this.min());
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
     
    // 3.3: Stack of Plates
    // Implement a new data structure SetOfStacks that mimics stacks of plates. SetofStacks should be composed of several stacks and should
    // create a new stack once the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single
    // stack (that is, pop() should return the same values as it would if there were just a single stack).
    public static class SetOfStacks
    {
        private ArrayList<Stack<Integer>> stacks;
        private int stackSize;
        private int currStackIdx;
        
        public SetOfStacks(int stackSize) {
            this.stackSize = stackSize;
            currStackIdx = 0;
            stacks = new ArrayList<Stack<Integer>>();
            Stack<Integer> stack = new Stack<Integer>();
            stacks.add(stack);
        }
        
        public void push(int value) {
            // if the current stack is not full, just push the data
            if (!isStackFull(currStackIdx)) {
                stacks.get(currStackIdx).push(value);
            } 
            else {  // if the current stack is full, need to create a new stack
                Stack<Integer> stack = new Stack<Integer>();
                stacks.add(stack);
                currStackIdx++;
                
                // push the data to the new stack
                stacks.get(currStackIdx).push(value);
            }
        }
        
        public int pop() {
            // if the set if empty, just throw exception
            if (isSetEmpty()) throw new EmptyStackException();

            int value = stacks.get(currStackIdx).pop();
            // now if the set if empty, just return the value
            if (isSetEmpty())
                return value;
                
            // if the current stack is empty, remove it and update currStackIdx;
            if (isStackEmpty(currStackIdx)) {
                stacks.remove(currStackIdx);
                currStackIdx--;
            }
            
            return value;
        }
            
        public boolean isStackEmpty(int index) {
            return stacks.get(index).empty();
        }
        
        public boolean isStackFull(int index) {
            return stacks.get(index).size() == stackSize;
        }
        
        public boolean isSetEmpty() {
            return (currStackIdx == 0 && isStackEmpty(currStackIdx));
        }
        
        public void printStacks() {
            int numOfStacks = stacks.size();
            System.out.println("Number of stacks: " + numOfStacks + " current stack index: " + currStackIdx);
            for (int i = 0; i < numOfStacks; i++) {
                System.out.println(stacks.get(i));
            }
        }
    }
    
    // 3.3: Stack of Plates: same idea as the first version but in a simpler form
    public static class SetOfStacks2
    {
        @SuppressWarnings("serial")
        private class StackWithCap extends Stack<Integer>
        {
            private int capacity;
            
            public StackWithCap(int capacity) {
                this.capacity = capacity;
            }
            
            public boolean isFull() {
                return this.size() == capacity;
            }
        }
        
        private ArrayList<StackWithCap> stacks;
        private int capacity;
        
        public SetOfStacks2(int capacity) {
            stacks = new ArrayList<StackWithCap>();
            this.capacity = capacity;
        }
        
        public void push(int value) {
            StackWithCap last = getLastStack();
            
            if (last != null && !last.isFull())
                last.push(value);
            else {
                StackWithCap stack = new StackWithCap(capacity);
                stack.push(value);
                stacks.add(stack);
            }
        }

        public int pop() {
            StackWithCap last = getLastStack();
            if (last == null) throw new EmptyStackException();
            
            int value = last.pop();
            if (last.empty()) {
                stacks.remove(last);
            }
            
            return value;
        }
        
        public StackWithCap getLastStack() {
            return stacks.isEmpty() ? null : stacks.get(stacks.size() - 1);
        }
        
        public void printStacks() {
            int numOfStacks = stacks.size();
            System.out.println("Number of stacks: " + numOfStacks);
            for (int i = 0; i < numOfStacks; i++) {
                System.out.println(stacks.get(i));
            }
        }
    }

    // 3.3: Stack of Plates: follw up
    // Implement a function popAt(int index) which performs a pop oeration on a specific substack
    public static class SetOfStacks3
    {
        @SuppressWarnings("serial")
        private class StackWithCap extends Stack<Integer>
        {
            private int capacity;
            
            public StackWithCap(int capacity) {
                this.capacity = capacity;
            }
            
            public boolean isFull() {
                return this.size() == capacity;
            }
        }
        
        private ArrayList<StackWithCap> stacks;
        private int capacity;
        
        public SetOfStacks3(int capacity) {
            stacks = new ArrayList<StackWithCap>();
            this.capacity = capacity;
        }
        
        public void push(int value) {
            StackWithCap last = getLastStack();
            
            if (last != null && !last.isFull())
                last.push(value);
            else {
                StackWithCap stack = new StackWithCap(capacity);
                stack.push(value);
                stacks.add(stack);
            }
        }

        public int pop() {
            StackWithCap last = getLastStack();
            if (last == null) throw new EmptyStackException();
            
            int value = last.pop();
            if (last.empty()) {
                stacks.remove(last);
            }
            
            return value;
        }
        
        public int popAt(int index) {
            StackWithCap last = getLastStack();
            if (last == null) throw new EmptyStackException();
            if (index < 0 || index >= stacks.size()) throw new IndexOutOfBoundsException();

            int value;
            if (index == stacks.size() - 1) {
                value = pop();
                return value;
            }
                
            StackWithCap stack = stacks.get(index);
            value = stack.pop();
            
            rollOver(index);
            return value;
        }

        public void rollOver(int index) {
            if (index == stacks.size() - 1) return;

            StackWithCap stack = stacks.get(index);
            StackWithCap next = stacks.get(index + 1);
            int tmp = next.remove(0);
            stack.push(tmp);

            if (next.empty()) {
                stacks.remove(next);
                return;
            }
            
            rollOver(index + 1);
        }
        
        public StackWithCap getLastStack() {
            return stacks.isEmpty() ? null : stacks.get(stacks.size() - 1);
        }
        
        public void printStacks() {
            int numOfStacks = stacks.size();
            System.out.println("Number of stacks: " + numOfStacks);
            for (int i = 0; i < numOfStacks; i++) {
                System.out.println(stacks.get(i));
            }
        }
    }
    
    // 3.3: Stack of Plates: implement a function popAt(int index) which performs a pop oeration on a specific substack
    // SetOfStacks3 uses Stack.remove() method which is actually inherited from Vector class. This is not allowed since Stack.remove() is not
    // a feature of stack. We should only use push() and pop() methods.
    // Rewrite it by only using push() and pop() methods
    public static class SetOfStacks4
    {
        @SuppressWarnings("serial")
        private class StackWithCap extends Stack<Integer>
        {
            private int capacity;
            
            public StackWithCap(int capacity) {
                this.capacity = capacity;
            }
            
            public boolean isFull() {
                return this.size() == capacity;
            }
        }
        
        private ArrayList<StackWithCap> stacks;
        private int capacity;
        
        public SetOfStacks4(int capacity) {
            stacks = new ArrayList<StackWithCap>();
            this.capacity = capacity;
        }
        
        public void push(int value) {
            StackWithCap last = getLastStack();
            
            if (last != null && !last.isFull())
                last.push(value);
            else {
                StackWithCap stack = new StackWithCap(capacity);
                stack.push(value);
                stacks.add(stack);
            }
        }

        public int pop() {
            StackWithCap last = getLastStack();
            if (last == null) throw new EmptyStackException();
            
            int value = last.pop();
            if (last.empty()) {
                stacks.remove(last);
            }
            
            return value;
        }
        
        public int popAt(int index) {
            StackWithCap last = getLastStack();
            if (last == null) throw new EmptyStackException();
            if (index < 0 || index >= stacks.size()) throw new IndexOutOfBoundsException();

            int value;
            if (index == stacks.size() - 1) {
                value = pop();
                return value;
            }
                
            StackWithCap stack = stacks.get(index);
            value = stack.pop();
            
            rollOver(index);
            return value;
        }

        public void rollOver(int index) {
            if (index == stacks.size() - 1) return;

            StackWithCap stack = stacks.get(index);
            StackWithCap next = stacks.get(index + 1);
            //int tmp = next.remove(0);
            
            stack.push(tmp);

            if (next.empty()) {
                stacks.remove(next);
                return;
            }
            
            rollOver(index + 1);
        }
        
        public StackWithCap getLastStack() {
            return stacks.isEmpty() ? null : stacks.get(stacks.size() - 1);
        }
        
        public void printStacks() {
            int numOfStacks = stacks.size();
            System.out.println("Number of stacks: " + numOfStacks);
            for (int i = 0; i < numOfStacks; i++) {
                System.out.println(stacks.get(i));
            }
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
        System.out.println("test Stack of Plates (SetOfStacks class)");
        
        int stackSize = 10;
        //SetOfStacks ss = new SetOfStacks(stackSize);
        //SetOfStacks2 ss = new SetOfStacks2(stackSize);
        SetOfStacks3 ss = new SetOfStacks3(stackSize);
        
        for (int i = 0; i < stackSize * 2; i++)
            ss.push(i);
        
        ss.push(20);
        ss.push(21);
        ss.printStacks();
        
        int index = 0;
        System.out.println("pop out at stack# " + index + ": " + ss.popAt(index));
        ss.printStacks();

        System.out.println("pop out at stack# " + index + ": " + ss.popAt(index));
        ss.printStacks();
    }
}
