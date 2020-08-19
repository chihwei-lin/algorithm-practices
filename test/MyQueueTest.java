package test;

import collection.list.MyQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyQueueTest {
    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();

        // add()
        queue.add("apple");
        queue.add("orange");
        System.out.printf("%-20s %-30s\n", "add()", queue.toString());

        // isEmpty()
        System.out.printf("%-20s %-30s\n", "isEmpty()", queue.isEmpty());

        // size()
        System.out.printf("%-20s %-30s\n", "size()", queue.size());

        // remove()
        queue.remove(1);
        System.out.printf("%-20s %-30s\n", "remove()", queue.toString());

        // poll()
        queue.poll();
        System.out.printf("%-20s %-30s\n", "poll()", queue.toString());

        // foreach()
        queue.add("pear");
        queue.add("strawberry");
        for(String s:queue){
            System.out.println(s);
        }
    }
}
