package test;

import collection.list.MyLinkedList;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<String>();

        // isEmpty()
        System.out.printf("%-30s %-20s\n", "1. isEmpty():", list.isEmpty());

        // add(e)
        list.add("apple");
        list.add("orange");
        System.out.printf("%-30s %-20s\n", "2. add(apple, orange):", list.toString());

        // clear()
        list.clear();
        System.out.printf("%-30s %-20s\n", "3. clear(): ", list.toString());

        list.add("apple");
        list.add("orange");
        System.out.printf("%-30s %-20s\n", "4. add(apple, orange): ", list.toString());

        // add(index, e)
        list.add(0, "grape");
        System.out.printf("%-30s %-20s\n", "5. add(0, grape): ", list.toString());

        // get(index)
        System.out.printf("%-30s %-20s\n", "6. get(0):", list.get(0));

        // remove(index)
        list.remove(1);
        System.out.printf("%-30s %-20s\n", "7. remove()", list.toString());

        // length()
        System.out.printf("%-30s %-20s\n", "8. length()", list.length());

        // poll()
        System.out.printf("%-30s %-20s\n", "9. poll()", list.poll());
        System.out.printf("%-30s %-20s\n", "9. poll()", list.toString());

        // pop()
        //System.out.printf("%-30s %-20s\n", "10. pop()", list.pop());      // throw Exception

        // reverse()
        list.add("apple");
        list.reverse();
        System.out.printf("%-30s %-20s\n", "11. reverse()", list.toString());
    }
}
