package buildings.threads;

public class Semaphore {
    private Object lock;
    private boolean isRepaired;


    public Semaphore() {
        this.lock = new Object();
        this.isRepaired = false;
    }

    public void acquired(Runnable thread) {
        synchronized (this.lock) {
            if ((isRepaired && (thread instanceof SequentalRepairer)) ||
                    (!isRepaired && (thread instanceof SequentalCleaner))) {
                try {
                    this.lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void release() {
        synchronized (this.lock) {
            this.isRepaired = !this.isRepaired;
            this.lock.notify();
        }
    }

}
