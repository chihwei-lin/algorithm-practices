package collection.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Stack（子类） 继承于 Vector（父类）
 * Vector 有 AbstractList、List的接口
 * Vector 为线程安全，因此 Stack 也为线程安全
 * 底层为数组实现
 */

public class MyStack<E> implements Iterable<E> {
    private Object[] data;
    private int size;

    public MyStack(int capacity){
        this.data = new Object[capacity];
        this.size = 0;
    }

    public MyStack(){
        this(10);
    }

    public E push(E item){
        if(this.data.length == this.size)
            this.grow();

        this.data[this.size] = item;
        this.size++;

        return item;
    }

    private void grow(){
        Object[] newData = new Object[data.length * 2];
        newData = Arrays.copyOf(data, size);

        this.data = this.data;
    }

    public E pop(){
        int len = this.size;
        E element = this.peek();
        this.removeElement();

        return element;
    }

    private void removeElement(){
        int len = this.size;
        this.data[len-1] = null;

        this.size = len-1;
    }

    public E peek(){
        int len = this.size;

        return (E) this.data[len-1];
    }

    public String toString(){
        return Arrays.toString(data);
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E>{
        int cursor;

        public Itr(){
            this.cursor = 0;
        }

        public boolean hasNext(){
            return this.cursor != MyStack.this.size;
        }

        public E next(){
            int i = this.cursor;

            if(i >= MyStack.this.size){
                throw new NoSuchElementException();
            }else{
                this.cursor = i + 1;
                return (E) MyStack.this.data[i];
            }
        }
    }
}
