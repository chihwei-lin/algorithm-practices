package test;

import collection.list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<String> myal = new MyArrayList<String>(3);
        // isEmpty() - empty ArrayList
        System.out.println("isEmpty(): "+myal.isEmpty());

        // add()
        myal.add("apple");
        System.out.println("ArrayList: " + myal.toString());

        // get()
        System.out.println("ArrayList: " + myal.get(0));

        // addAll()
        String[] fruits = {"banana", "orange", "pear","strawberry", "blueberry", "berry"};
        myal.addAll(Arrays.asList(fruits));
        System.out.println("ArrayList: " + myal.toString());

        // isEmpty() - non empty ArrayList
        System.out.println("isEmpty(): "+myal.isEmpty());

        // removeAll()
        String[] remove = {"pear", "berry"};
        myal.removeAll(Arrays.asList(remove));
        System.out.println("ArrayList: " + myal.toString());

        // retainAll()
        String[] intersect = {"banana", "orange"};
        myal.retainAll(Arrays.asList(intersect));
        System.out.println("ArrayList: " + myal.toString());

        // contains()
        System.out.println("contains(): "+myal.contains("orange"));

        // containAll()
        System.out.println("containAll(): "+myal.containsAll(Arrays.asList(intersect)));

        // remove()
        myal.remove("orange");
        System.out.println("ArrayList: " + myal.toString());

        // clear()
        myal.clear();;
        System.out.println("ArrayList: " + myal.toString());

        // foreach()
        for(String a:myal){
            System.out.println(a);
        }
    }
}
