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
    
    // 3.4: Implement a queue using two stacks
    public static class MyQueueWithStack<T> 
    {
        private Stack<T> inStack, outStack;
        private boolean inMode;
        
        public MyQueueWithStack() {
            inStack = new Stack<T>();
            outStack = new Stack<T>();
            inMode = true;
        }
        
        // queue operation can only happen on inStack
        public void queue(T value) {
            // move all data to inStack if they are not yet
            if (!inMode) {
                moveData();
                inMode = true;
            }
            
            inStack.push(value);
        }

        // dequeue operation can only happen on outStack
        public T dequeue() {
            // move all data to outStack if they are not yet
            if (inMode) {
                moveData();
                inMode = false;
            }

            if (outStack.empty()) throw new EmptyStackException();
            
            return outStack.pop();
        }

        public void moveData() {
            Stack<T> source = inMode ? inStack : outStack;
            Stack<T> target = inMode ? outStack : inStack;
            
            while (!source.empty()) {
                target.push(source.pop());
            }
        }
        
        public void printQueue() {
            System.out.println(inMode ? inStack : outStack);
        }
    }
    
    // 3.4: a better way
    // The above MyQueueWithStack class has a weak point: if a user do queue(), dequeue() aternatively, data will be moved between two stacks unnecessarily.
    // In this case, for either queue() or dequeue(), time complexity will be O(N). This will be bad if N is large.
    // In the following class, we will try to avoid unnecessary data-moving between two stacks.
    // Time complexity: O(1) for both queue() and dequeue()
    public static class MyQueueWithStack1<T>
    {
        private Stack<T> stackNewestOnTop, stackOldestOnTop;
        
        public MyQueueWithStack1() {
            stackNewestOnTop = new Stack<T>();
            stackOldestOnTop = new Stack<T>();
        }
        
        // queue operation: push to stackNewestOnTop since the newest data is on top in this stack
        public void queue(T value) {
            stackNewestOnTop.push(value);
        }

        // dequeue operation: pop from stackOldestOnTop since the oldest data (to be dequeued) is on top in this stack
        public T dequeue() {
            if (isEmpty()) throw new EmptyStackException();

            // if stackOldestOnTop is empty, move data from stackNewestOnTop to stackOldestOnTop
            if (stackOldestOnTop.empty()) {
                moveData(stackNewestOnTop, stackOldestOnTop);
            }
            
            return stackOldestOnTop.pop();
        }

        public void moveData(Stack<T> src, Stack<T> dst) {
            while (!src.empty()) {
                dst.push(src.pop());
            }
        }
        
        public boolean isEmpty() {
            return (stackNewestOnTop.empty() && stackOldestOnTop.empty());
        }
        
        public void printQueue() {
            System.out.println("stackNewestOnTop: " + stackNewestOnTop);
            System.out.println("stackOldestOnTop: " + stackOldestOnTop); 
        }
    }    
    
    // 3.5: Sort stack with smallest element on top: Rudimentary version
    //Input : [34, 3, 31, 98, 92, 23]
    //Output: [98, 92, 34, 31, 23, 3]
    // time: O(N^2)
    public static class SortStack
    {
        private static class Result
        {
            int value;
            Stack<Integer> stack;
            
            public Result() {
            }
            
            public Result(Stack<Integer> stack) {
                this.stack = stack;
            }
        }
            
        public static Stack<Integer> sort(Stack<Integer> stack) {
            Stack<Integer> sorted = new Stack<Integer>();
            
            Result res = new Result(stack);
            
            while (!res.stack.empty()) {
                res = max(res.stack);
                sorted.push(res.value);
            }

            return sorted;
        }

        // returns:
        // 1. the max value in the current stack
        // 2. a new stack after removing the max value
        public static Result max(Stack<Integer> stack) {
            Stack<Integer> tmp = new Stack<Integer>();
            Result res = new Result();
            
            // go through stack to find out the max value
            int maxValue = Integer.MIN_VALUE;
            while (!stack.empty()) {
                int value = stack.pop();
                maxValue = (value > maxValue) ? value : maxValue;
                tmp.push(value);
            }
            
            // go through tmp to remove the current max element from the stack
            boolean maxIgnored = false;
            while (!tmp.empty()) {
                int value = tmp.pop();
                if (!maxIgnored && value == maxValue) {
                    maxIgnored = true;
                    continue;
                }
                
                stack.push(value);
            }
            
            res.value = maxValue;
            res.stack = stack;
            
            return res;
        }
    }

    // 3.5: Sort stack with smallest element on top: similar to the rudimentary version but try to use recursion
    //Input : [34, 3, 31, 98, 92, 23]
    //Output: [98, 92, 34, 31, 23, 3]
    public static class SortStack1
    {
        public static Stack<Integer> sort(Stack<Integer> stack) {
            Stack<Integer> sorted = new Stack<Integer>();
            sortHelper(stack, sorted);

            return sorted;
        }

        public static void sortHelper(Stack<Integer> stack, Stack<Integer> sorted) {
            if (stack.empty()) return;

            Stack<Integer> tmp = new Stack<Integer>();
            
            // go through stack to find out the max value
            int maxValue = Integer.MIN_VALUE;
            while (!stack.empty()) {
                int value = stack.pop();
                maxValue = (value > maxValue) ? value : maxValue;
                tmp.push(value);
            }
            
            // go through tmp to remove the current max element from the stack
            boolean maxIgnored = false;
            while (!tmp.empty()) {
                int value = tmp.pop();
                if (!maxIgnored && value == maxValue) {
                    maxIgnored = true;
                    continue;
                }
                
                stack.push(value);
            }
            
            sorted.push(maxValue);
            sortHelper(stack, sorted);
            
            return;
        }
    }
    
    // 3.5: Actually we can do this with only one extra stack
    //Input : [34, 3, 31, 98, 92, 23]
    //Output: [98, 92, 34, 31, 23, 3]
    //time: O(N^2)
    public static class SortStack2
    {
        public static Stack<Integer> sort(Stack<Integer> stack) {
            Stack<Integer> sorted = new Stack<Integer>();

            while (!stack.empty()) {
                int value = stack.pop();
                
                if (sorted.empty() || value <= sorted.peek()) {
                    sorted.push(value);
                    continue;
                }

                // now let's handle the trick case: value > sorted.peek()
                while (!sorted.empty() && value > sorted.peek()) {
                    stack.push(sorted.pop());
                }
                
                sorted.push(value);
            }

            return sorted;
        }
    }
    
    // 3.6: Animal Shelter
    // put all animals (cats or dogs) in one linked list
    // Time complexity: dequeueAny() is always O(1), dequeueDog() or dequeueCat() can be O(N) in the worst case
    public static class AnimalShelter
    {
        private LinkedList<Animal> animalQueue = null;
        
        public AnimalShelter() {
            animalQueue = new LinkedList<Animal>();
        }
        
        public void enqueue(Animal a) {
            animalQueue.add(a);
        }

        public Animal dequeueAny() {
            if (animalQueue.isEmpty()) throw new NoSuchElementException();
            
            return animalQueue.removeFirst();
        }
        
        public Dog dequeueDog() {
            if (animalQueue.isEmpty()) throw new NoSuchElementException();

            Iterator<Animal> it = animalQueue.iterator();
            
            while (it.hasNext()) {
                Animal a = it.next();
                
                if (a instanceof Dog) {
                    it.remove();
                    return (Dog)a;
                }
            }
            
            return null;
        }

        public Cat dequeueCat() {
            if (animalQueue.isEmpty()) throw new NoSuchElementException();

            Iterator<Animal> it = animalQueue.iterator();
            
            while (it.hasNext()) {
                Animal a = it.next();
                
                if (a instanceof Cat) {
                    it.remove();
                    return (Cat)a;
                }
            }
            
            return null;
        }
        
        public void printQueue() {
            Iterator<Animal> it = animalQueue.iterator();
            
            System.out.println("Current animal queue:");
            while (it.hasNext()) {
                System.out.print(it.next().getName() + " ");
            }
            System.out.println();
        }
    }
    
    // 3.6: Animal Shelter: use two queues instead of one
    // Time complexity: all O(1)
    public static class AnimalShelter1
    {
        private int order;
        private LinkedList<Dog> dogQueue = null;
        private LinkedList<Cat> catQueue = null;
        
        public AnimalShelter1() {
            order = 0;
            dogQueue = new LinkedList<Dog>();
            catQueue = new LinkedList<Cat>();
        }
        
        public void enqueue(Animal a) {
            this.order++;
            a.order = this.order;
            
            if (a instanceof Dog) {
                dogQueue.add((Dog)a);
            } else {
                catQueue.add((Cat)a);
            }
        }

        public Animal dequeueAny() {
            if (dogQueue.isEmpty() && catQueue.isEmpty()) throw new NoSuchElementException();
            
            if (dogQueue.isEmpty()) {
                return dequeueCat();
            } else if (catQueue.isEmpty()) {
                return dequeueDog();
            }
            
            Dog d = dogQueue.peek();
            Cat c = catQueue.peek();
            if (d.getOrder() < c.getOrder()) {
                return dequeueDog();
            } else {
                return dequeueCat();
            }
        }
        
        public Dog dequeueDog() {
            if (dogQueue.isEmpty()) throw new NoSuchElementException();
            
            return dogQueue.remove();
        }

        public Cat dequeueCat() {
            if (catQueue.isEmpty()) throw new NoSuchElementException();
            
            return catQueue.remove();
        }

        public void printQueue() {
            Iterator<Dog> itd = dogQueue.iterator();
            Iterator<Cat> itc = catQueue.iterator();
            
            System.out.print("Current dog queue: ");
            while (itd.hasNext()) {
                Dog d = itd.next();
                System.out.print(d.getName() + " " + d.getOrder() + " -> ");
            }
            System.out.println("null");
            
            System.out.print("Current cat queue: ");
            while (itc.hasNext()) {
                Cat c = itc.next();
                System.out.print(c.getName() + " " + c.getOrder() + " -> ");
            }
            System.out.println("null");
        }
        
    }
    
    abstract static class Animal
    {
        private String name;
        private int order;
        
        public Animal(String n) { name = n; }
        
        public String getName() {
            return name;
        }
        
        public int getOrder() {
            return order;
        }
    }
    
    public static class Dog extends Animal
    {
        public Dog(String n) { super(n); }
    }
    
    public static class Cat extends Animal
    {
        public Cat(String n) { super(n); }
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
        // 3.6: Animal Shelter
        System.out.println("3.6: Animal Shelter");
        
        AnimalShelter1 as = new AnimalShelter1();
        as.enqueue(new Dog("dog1"));
        as.enqueue(new Cat("cat1"));
        as.enqueue(new Cat("cat2"));
        as.enqueue(new Cat("cat3"));
        as.enqueue(new Dog("dog2"));
        as.enqueue(new Dog("dog3"));
        as.printQueue();
        System.out.println();
        
        System.out.println("dequeueAny(): " + as.dequeueAny().getName());
        as.printQueue();
        System.out.println("dequeueAny(): " + as.dequeueAny().getName());
        as.printQueue();
        System.out.println();

        System.out.println("dequeueDog(): " + as.dequeueDog().getName());
        as.printQueue();
        System.out.println("dequeueDog(): " + as.dequeueDog().getName());
        as.printQueue();
        System.out.println();

        System.out.println("dequeueCat(): " + as.dequeueCat().getName());
        as.printQueue();
        System.out.println("dequeueCat(): " + as.dequeueCat().getName());
        as.printQueue();

    }
}
