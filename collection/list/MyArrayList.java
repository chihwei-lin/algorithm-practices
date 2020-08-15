package collection.list;

import java.util.*;

public class MyArrayList<E> extends AbstractList<E> {
    private Object[] elementData;
    private int N;

    public MyArrayList() {
        // default size set to be 10
        this.elementData = new Object[10];
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.elementData = new Object[capacity];
            this.N = 0;
        } else
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    }

    @Override
    public int size() {
        return this.N;
    }

    @Override
    public boolean isEmpty() {
        return this.N == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.N; i++) {
            if (o.equals(this.elementData[i]))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private int cursor;

        public Itr() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < N;
        }

        @Override
        public E next() {
            return (E) elementData[cursor++];
        }
    }

    @Override
    public Object[] toArray() {
        return elementData;
    }

    @Override
    public boolean add(Object o) {
        if (this.N == this.elementData.length) {
            // 扩容
            Object[] temp = this.elementData;
            this.elementData = new Object[2 * this.N];

            // System.arraycopy(src, srcStartingPos, dest, destStartingPos, elemLen)
            System.arraycopy(temp, 0, this.elementData, 0, N);
        }

        this.elementData[N++] = o;

        return true;
    }

    public E get(int index) {
        if (index < N && N >= 0)
            return (E) elementData[index];
        else
            throw new ArrayIndexOutOfBoundsException(index + " is not a valid index");
    }

    @Override
    public boolean remove(Object o) {           //  remove the first valid element
        Object[] temp = this.elementData;

        int index = -1;
        for (int i = 0; i < this.N; i++) {
            if (o.equals(this.elementData[i])) {
                index = i;
                break;
            }
        }

        if (index == -1) return false;

        // copy first part and second part except for the removed element
        System.arraycopy(temp, 0, this.elementData, 0, index + 1);
        System.arraycopy(temp, index + 1, this.elementData, index, this.N - index);
        this.N--;

        if (N - 1 < this.elementData.length / 4) {        // 减少容量
            temp = this.elementData;
            System.arraycopy(temp, 0, this.elementData, 0, this.elementData.length / 2);
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E obj : collection)
            add(obj);
        return true;
    }

    @Override
    public void clear() {
        for(int i=0;i<this.N;i++)
            this.elementData[i] = null;
        this.N = 0;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return batchRemove(collection, true);
    }

    private boolean batchRemove(Collection<?> collection, boolean complement) {
        Objects.requireNonNull(collection);

        // 获取当前对象的所有元素
        final Object[] elementData = this.elementData;

        int r = 0, w = 0;

        boolean modified = false;
        try{
            for(;r<N;r++){
                if(collection.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
            }
        }finally{
            if(r != N){
                System.arraycopy(elementData, r, elementData, w, N - r);
                w += N - r;
            }

            if(w != N){
                for(int i=w;i<N;i++)
                    elementData[i] = null;
                modCount += N - w;
                N = w;
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return batchRemove(collection, false);
    }

    @Override
    public boolean containsAll(Collection collection) {
        for(Object obj : collection){
            if(!contains(obj))
                return false;
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return Arrays.copyOf(this.elementData, this.N);
    }

    @Override
    public String toString() {
        Object[] temp = new Object[N];

        System.arraycopy(this.elementData, 0, temp, 0, N);

        return Arrays.toString(temp);
    }
}
