package lesson_1.pool;

public class ThreadPoolMain {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(10);

        Runnable task1 = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " A " + i);
            }
        };
        Runnable task2 = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " B " + i);
            }
        };
        Runnable task3 = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " C " + i);
            }
        };
        Runnable task4 = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " D " + i);
            }
        };Runnable task5 = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " E " + i);
            }
        };

        threadPool.submit(task1);
        threadPool.submit(task2);
        threadPool.submit(task3);
        threadPool.submit(task4);
        threadPool.submit(task5);

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " F " + i);
        }


    }
}
