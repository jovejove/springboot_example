package util;

import java.util.ArrayDeque;

/**
 * @author: ljj
 * @date: 2022/5/6
 * @description:
 */
public class ArrayDequeDemo {

    public static void main(String[] args) {
        /**
         * Deque 接口的可调整大小的数组实现。
         * 数组双端队列没有容量限制；它们会根据需要增长以支持使用。
         * 它们不是线程安全的；在没有外部同步的情况下，它们不支持多线程并发访问。
         * 禁止使用空元素。
         * 这个类在作为栈使用时可能比 Stack 快，作为队列使用时比 LinkedList 快。
         */
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        arrayDeque.add(1);
        arrayDeque.offer(2);
        System.out.println(arrayDeque.peek());

    }
}
