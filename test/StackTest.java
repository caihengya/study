package src.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackTest {

   /* 算法题1：使用栈实现队列的下列操作：
    push(x) -- 将一个元素放入队列的尾部。
    pop() -- 从队列首部移除元素。
    peek() -- 返回队列首部的元素。
    empty() -- 返回队列是否为空。*/

    /**
     * 解题思路：堆栈是FILO先进后出，队列是FIFO先进先出，要使用堆栈来实现队列的功能，可以采用2个堆栈的方式。
     * 堆栈A和堆栈B，当有元素要插入的时候，就往堆栈A里插入。
     * 当要移除元素的时候，先将堆栈A里的元素依次出栈放入到堆栈B中，再从堆栈B的顶部出数据。
     * 如此便基于2个堆栈实现了先进先出的原则了。
     */
    class MyQueue {

        private Stack<Integer> s1 = new Stack<>();
        private Stack<Integer> s2 = new Stack<>();
        private int fornt;


        /** Initialize your data structure here. */
        /** 在此初始化您的数据结构 */
        public MyQueue() {

        }

        /** Push element x to the back of queue. */
        /** 将元素x推到队列的后面。 */
        public void push(int x) {
            if(s1.empty()){
                fornt = x;
            }
            s1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        /** 从队列前面删除该元素并返回该元素 */
        public int pop() {
            if(s2.empty()){
                while(!s1.empty()){
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if(s2.empty()){
                return fornt;
            }
            return s2.peek();
        }

        /** Returns whether the queue is empty. */
        /** 返回队列是否为空。 */
        public boolean empty() {
            return s1.empty()&&s2.empty();
        }
    }

    //入栈的时间复杂度为O(1),出栈的时间复杂度为O(1)


    /*算法题2：使用队列来实现堆栈的下列操作：
    push(x) -- 元素 x 入栈
    pop() -- 移除栈顶元素
    top() -- 获取栈顶元素
    empty() -- 返回栈是否为空*/


    /**
     * 解题思路：由于需要使用FIFO的队列模拟出FILO的堆栈效果，因此需要使用2个队列来完成，
     * 队列A和队列B，当需要进行入栈操作的时候，直接往队列A中插入元素。
     * 当需要进行出栈操作的时候，先将队列A中的前n-1个元素依次出队移动到队列B中，
     * 这样队列A中剩下的最后一个元素其实就是我们所需要出栈的元素了，将这个元素出队即可。
     */
    class MyStack {

        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();
        int front;

        /** Initialize your data structure here. */
        public MyStack() {

        }

        /** Push element x onto stack. */
        public void push(int x) {
            q1.add(x);
            front = x;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            while(q1.size()>1){
                front = q1.remove();
                q2.add(front);
            }
            int val = q1.remove();
            Queue<Integer> temp = q2;
            q2 = q1;
            q1 = temp;
            return val;
        }

        /** Get the top element. */
        public int top() {
            return front;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q1.size()==0;
        }
    }

    //入栈的时间复杂度为O(1)，出栈的时间复杂度为O(n)

   /* 这道题其实还有另一个解法，只需要一个队列就可以做到模拟出堆栈，
   思路就是：当需要进行入栈操作的时候，先将新元素插入到队列的队尾中，
   再将这个队列中的其它元素依次出队，队列的特性当然是从队头出队了，
   但是出来的元素再让它们从队尾入队，这样依次进行，留下刚才插入的新元素不动，
   这个时候，这个新元素其实就被顶到了队头了，新元素入栈的动作就完成了。
   当需要进行出栈操作的时候，就直接将队列队头元素出队即是了。
   思路已经写出来了，代码的话就留给大家练习了哦。*/
}
