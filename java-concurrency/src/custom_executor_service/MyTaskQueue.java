package custom_executor_service;

import java.util.Queue;

public class MyTaskQueue {
    private int sizeLimit;
    private Queue<Task> taskQueue;

    public MyTaskQueue(int queueSize) {
        this.sizeLimit = queueSize;
        this.
    }

    synchronized boolean put(MyTask task) {
        if (_queue.size() < sizeLimit) {
            return _queue.add(task);
        }
        return false;
    }

    synchronized MyTask get() {
        if (!_queue.isEmpty()) {
            return _queue.remove(0);
        }
        return null;
    }

    synchronized boolean isEmpty() {
        return _queue.isEmpty();
    }

    synchronized boolean isFull() {
        return _queue.size() == sizeLimit;
    }
}
Footer