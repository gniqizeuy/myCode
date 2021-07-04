public class ThreadStart {
    public static void main(String[] args) {
        Thread thread = new ChildThread();

        thread.start();

        System.out.printf("1. I'm %s.%n", Thread.currentThread().getName());
    }

}

class ChildThread extends Thread {
    @Override
    public void run() {
        System.out.printf("2. I'm %s.%n", Thread.currentThread().getName());
    }
}