package lesson_1.pool;


import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool {

    private Deque<Runnable> tasks;

    private PoolWorker[] pool;

    public ThreadPool(int threadsCount) {
        this.tasks = new ConcurrentLinkedDeque<>();
        this.pool = new PoolWorker[threadsCount];

        for (int i = 0; i < this.pool.length; i++) {
            this.pool[i] = new PoolWorker();
            this.pool[i].start();
        }

    }

    public void submit(Runnable task) {
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }

    private class PoolWorker extends Thread {

        @Override
        public void run() {
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            throw new IllegalStateException();
                        }
                    }
                }
                Runnable task;
                synchronized (tasks) {
                    task = tasks.pollFirst();
                }
                if (task != null) {
                    task.run();
                } else {
                    throw  new IllegalStateException();
                }
            }
        }
    }
    // задать вопрос Сидикову, как написать реализацию случай, когда 1 из потоков прерывается.
    // спросить почему deque
    // есть ли разница при пробросе State or Argument Exception или просто потому что они оба непроверяемые
}
