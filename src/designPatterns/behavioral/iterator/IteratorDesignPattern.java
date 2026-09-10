package designPatterns.behavioral.iterator;

interface MyIterator<T> {
    boolean hasNext();
    T next();
}

interface Aggregate<T> {
    MyIterator<T> createIterator();
}

class MyCollection<T> implements Aggregate<T> {
    private final T[] items;
    private int size;
    private int capacity;

    public MyCollection(int capacity) {
        this.capacity = capacity;
        this.items = (T[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public MyIterator<T> createIterator() {
        return new MyCollectionIterator();
    }

    public void add(T item) {
        if (size < capacity) {
            items[size++] = item;
        }
    }

    private class MyCollectionIterator implements MyIterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            return items[currentIndex++];
        }
    }
}

public class IteratorDesignPattern {
    static void main() {
        MyCollection<String> collection = new MyCollection<>(5);
        collection.add("Item 1");
        collection.add("Item 2");
        collection.add("Item 3");
        MyIterator<String> iterator = collection.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        collection.add("Item 4");
        collection.add("Item 5");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
