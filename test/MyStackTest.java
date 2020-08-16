package test;

import collection.list.MyStack;

import java.util.Stack;

public class MyStackTest {

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();

        // isEmpty()
        System.out.printf("%-20s %-30s\n", "isEmpty()", stack.isEmpty());

        // push()
        stack.push("apple");
        System.out.printf("%-20s %-30s\n","push()", stack.toString());

        // pop()
        System.out.printf("%-20s %-30s\n", "pop()", stack.pop());
        System.out.printf("%-20s %-30s\n", "pop()", stack.toString());

        // push()
        stack.push("orange");
        stack.push("banana");
        stack.push("watermelon");
        System.out.printf("%-20s %-30s\n", "push()", stack.toString());

        // peek()
        System.out.printf("%-20s %-30s\n", "peek()", stack.peek());

        // foreach()
        String str = "";
        for( String s : stack){
            str += s + " ";
        }
        System.out.printf("%-20s %-30s\n", "foreach()", str);
    }
}
