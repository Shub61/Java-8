import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class LRUCache<T> {

    private final int capacity;
    private final Map<Integer, Node> hashMap;
    private final DoublyLinkedList internalQueue;
    private final Queue<Integer> queue = new LinkedList<>();

    private int size;

    public LRUCache(final int capacity){
        this.capacity = capacity;
        hashMap = new HashMap<>();
        internalQueue = new DoublyLinkedList();
    }

    public T get (final Integer key){
        Node node = hashMap.get(key);
        if(node == null) return null;
        return hashMap.get(key).value;
    }

    public void put (final Integer key, final T value){
        Node node =  hashMap.get(key);
        if(node != null) {
            node.value = value;
            //Move this key to front
            internalQueue.moveToFront(node);
        } else{
            Node newNode = new Node(key, value);
            if(size == capacity){
                // remove Least used
                Node rearNode = internalQueue.getRearNode();
                hashMap.remove(rearNode);
                internalQueue.removeLeastUsedFromRear();
                size--;
            }
            internalQueue.insertNewNodeAtFront(newNode);
            hashMap.put(key, newNode);
            size++;
        }
    }

    private class Node {
        private int key;
        private T value;
        private Node prev;
        private Node next;
        public Node(final int key, final T value){
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private class DoublyLinkedList {
        private Node rear;
        private Node front;
        public DoublyLinkedList(){
            rear = null;
            front = null;
        }

        public void moveToFront(Node node){
            if(node == front) return;

            if (node == rear){
                rear = rear.prev;
                rear.next = null;
            } else {
                node.next.next = node.next;
                node.next.prev = node.prev;
            }
            node.prev = null;
            node.next = front;
            front.prev = node;
            front = node;
        }

        public void removeLeastUsedFromRear(){
            if(rear == null) {
                return;
            }

            System.out.println("Deleting key: " + rear.key);
            if(front == rear) {
                front = rear = null;
            } else {
                rear = rear.prev;
                rear.next = null;
            }
        }

        public void insertNewNodeAtFront(Node node){
            if(rear == null){
                front = node;
                return;
            }
            node.next = front;
            front.prev = node;
            front = node;
        }

        public Node getRearNode(){
            return internalQueue.rear;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 5);
        lruCache.put(2, 6);
        lruCache.put(3, 7);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        lruCache.put(4, 9);

    }
}
