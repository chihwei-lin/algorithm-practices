package collection.list;

import java.util.LinkedList;

public class MyLinkedList<E> {
    private int size;
    private MyLinkedList.Node<E> head;
    private MyLinkedList.Node<E> tail;


    // 空置链表
    // clear up all elements in the linked list
    public void clear(){
        MyLinkedList.Node<E> head;          // MyLinkedList.Node is to clarify Node class is in MyLinkedList

        if(this.head == null)   return;

        for(head=this.head.next;head!=null;head=head.next){
            MyLinkedList.Node<E> prevNode = head.prev;
            prevNode.prev = null;
            prevNode.item = null;
            prevNode.next = null;
        }

        this.head = null;
        this.tail = null;

        this.size = 0;
    }

    // 判断链表是否为空
    // see is the linked list empty
    public boolean isEmpty(){
        return this.size == 0;
    }

    // 获取链表中元素的个数
    // get how many elements are in the linked list
    public int length(){
        return this.size;
    }

    public boolean add(E e) {
        if(this.head == null)
            this.head =  new MyLinkedList.Node<E>(null, e, null);
        else if(this.tail == null){
            this.tail = new MyLinkedList.Node<E>(this.head, e, null);
            this.head.next = this.tail;
        }
        else{
            MyLinkedList.Node<E> newNode = new MyLinkedList.Node<E>(this.tail, e, null);
            this.tail.next = newNode;
            this.tail = newNode;
        }

        this.size++;
        return true;
    }

    public void add(int index, E element){
        if(index == 0){
            MyLinkedList.Node<E> newNode = new MyLinkedList.Node<E>(null, element, this.head);
            this.head.prev = newNode;
            this.head = newNode;
            this.size++;
            return;
        }

        if(index == this.size){
            MyLinkedList.Node<E> newNode = new MyLinkedList.Node<E>(this.tail, element, null);
            this.tail.next = newNode;
            this.tail = newNode;
            this.size++;
            return;
        }

        MyLinkedList.Node<E> head = this.head;

        for(int i=0;i<index;i++)
            head = head.next;

        MyLinkedList.Node<E> newNode = new MyLinkedList.Node<E>(head.prev, element, head);
        head.prev.next = newNode;
        head.prev = newNode;

        this.size++;
    }

    public boolean remove(int i){
        if(i < 0 || i >= this.size)  return false;

        MyLinkedList.Node<E> head = this.head;

        for(int index=0;index<i;index++){
            head = head.next;
        }

        MyLinkedList.Node<E> prevNode = head.prev;
        MyLinkedList.Node<E> nextNode = head.next;

        if(prevNode == null && nextNode == null)
            this.head = null;
        else if(prevNode == null && nextNode != null){
            nextNode.prev = null;
            this.head = nextNode;
        }else if(prevNode != null && nextNode == null){
            prevNode.next = null;
            this.tail = prevNode;
        }else{      // 都不为空
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        this.size--;

        return true;
    }

    public E poll(){
        if(this.head == null)
            return null;
        MyLinkedList.Node<E> head = this.head;
        MyLinkedList.Node<E> nextNode = this.head.next;
        nextNode.prev = null;
        this.head = nextNode;

        return head.item;
    }

    /**
     *  pop()
     *  pop function is exactly the same as poll function
     *  however, poll will return null when the first element is null, pop won't
     *  @return E class object
     */
    public E pop(){
        MyLinkedList.Node<E> head = this.head;
        MyLinkedList.Node<E> nextNode = this.head.next;
        nextNode.prev = null;
        this.head = nextNode;

        return head.item;
    }

    public E get(int index){
        if(index > this.size)   return null;

        MyLinkedList.Node<E> head = this.head;

        for(int i=0;i<index;i++){
            head = head.next;
        }

        return head.item;
    }

    public void reverse(){
        MyLinkedList.Node<E> head = this.head;
        MyLinkedList.Node<E> prevNode;
        MyLinkedList.Node<E> tempNode;

        while(head != null){
            prevNode = head;
            head = head.next;

            // swap node.prev and node.next
            tempNode = prevNode.prev;
            prevNode.prev = prevNode.next;
            prevNode.next = tempNode;
        }

        // swap head and tail
        tempNode = this.head;
        this.head = this.tail;
        this.tail = tempNode;
    }

    public String toString(){
        String str = "";

        if(this.head == null)   return "";

        MyLinkedList.Node<E> head;
        for(head = this.head;head!=this.tail;head = head.next){
            str += head.item + " -> ";
        }

        str += head.item;

        return str;
    }

    /***
     *  custom Node class
     *  it implements data structure
     *
     */
    private static class Node<E> {
        E item;
        MyLinkedList.Node<E> next;
        MyLinkedList.Node<E> prev;

        Node(MyLinkedList.Node<E> prev, E element, MyLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
