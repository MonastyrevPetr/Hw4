public class Main {
    static Object mon = new Object();
    static volatile char ch = 'A';

    public static void main(String[] args) {
        new Thread(() -> {

            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (ch != 'A') {
                            mon.wait();
                        }
                        System.out.print(ch);
                        ch='B';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }).start();

        new Thread(() -> {

            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (ch != 'B') {
                            mon.wait();
                        }
                        System.out.print(ch);
                        ch='C';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }).start();

        new Thread(() -> {

            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (ch != 'C') {
                            mon.wait();
                        }
                        System.out.print(ch);
                        ch='A';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }).start();
    }
}
