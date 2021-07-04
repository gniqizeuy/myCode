public class ThreadStart1 {

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadTask());

        thread.start();

        System.out.printf("1. I'm %s.%n", Thread.currentThread().getName());
    }
}


class ThreadTask implements Runnable {

    @Override
    public void run() {
        System.out.printf("2. I'm %s.%n", Thread.currentThread().getName());
    }
}