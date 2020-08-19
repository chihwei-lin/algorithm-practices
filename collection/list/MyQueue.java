package collection.list;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public class MyQueue<E> implements Iterable<E> {
    private MyQueue.Node<E> head;           // 私有类 Node 为 类中类
    private MyQueue.Node<E> last;
    private int size;

    public MyQueue(){

    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    // 回传 boolean 的原因？
    // Queue 新增原理为尾插法
    public boolean add(E var){
        if(this.size == 0){
            this.head = new Node(var, null, null);
            this.last = head;
        }else{
            this.last.next = new Node(var, this.last, null);
            this.last = this.last.next;
        }
        this.size++;
        return true;
    }

    public void remove(int index){
        MyQueue.Node<E> currNode = this.head;

        if(index > this.size)   return;

        // 移动至要删除的节点
        for (int i = 0; i < index-1; i++) {
            currNode = currNode.next;
        }

        // 分为四种情况 1. 前后节点到不为空 2. 前节点为空 后节点不为空 3. 前节点不为空 后节点为空 4. 前后节点都为空
        if(currNode.prev != null && currNode.next != null){
            currNode.prev.next = currNode.next;
            currNode.next.prev = currNode.prev;
        }
        else if(currNode.prev == null && currNode.next != null) {
            currNode.next.prev = null;
            this.head = currNode.next;
        }
        else if(currNode.prev != null && currNode.next == null) {
            currNode.prev.next = null;
            this.last = currNode.prev;
        }
        currNode = null;        // 把 currNode 里的数据清除
        this.size--;
    }

    public E poll(){
        MyQueue.Node<E> head = this.head;

        if(head == null) return null;

        // 移除第一个节点，情况有两种 1. head.next 为空 2. head.next 不为空
        if(this.head.next != null){  // head.next 不为空
            this.head = this.head.next;
            this.head.prev = null;
        }
        else        // head.next 为空
            this.head = null;
        this.size--;
        return head.item;
    }

    // poll 方法与 peek 方法区别在于 poll 方法会检查 queue 是否为空 而 peek 方法不会
    public E peek(){
        MyQueue.Node<E> head = this.head;

        // 移除第一个节点，情况有两种 1. head.next 为空 2. head.next 不为空
        if(this.head.next != null){  // head.next 不为空
            this.head = this.head.next;
            this.head.prev = null;
        }
        else        // head.next 为空
            this.head = null;
        this.size--;
        return head.item;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();

        if(this.head == null)   return null;

        for(MyQueue.Node<E> currNode = this.head;currNode != null; currNode = currNode.next){
            str.append(currNode.item).append(" ");
        }

        return str.toString();
    }

    @Override
    public Iterator<E> iterator() { // 实现迭代器（以私有类方式）
        return new Itr();           // 创建一个 Itr 类对象
    }

    private static class Node<E>{
        E item;
        Node<E> prev;
        Node<E> next;

        Node(){ }

        Node(E item){
            this();
            this.item = item;
        }

        public Node(E item,Node prev){
            this(item);
            this.prev = prev;
        }

        public Node(E item,Node prev, Node next){
            this(item, prev);
            this.next = next;
        }
    }

    private class Itr implements Iterator<E>{
        MyQueue.Node<E> cursor;

        Itr(){
            cursor = new Node(null, null, head);
        }

        @Override
        public boolean hasNext() {
            return cursor.next != null;
        }

        @Override
        public E next() {
            cursor = cursor.next;
            return cursor.item;
        }
    }
}
