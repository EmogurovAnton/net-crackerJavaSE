package buildings.threads;

import interfaces.Floor;
import interfaces.Space;

public class SequentalCleaner implements Runnable {
    private Floor floor;
    private Semaphore semaphore;

    public SequentalCleaner(Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
        for (int i = 0; i < this.floor.getSpaceCount(); i++) {
            Space currentSpace = this.floor.getSpaceByNumber(i + 1);
            int spaceSquare = currentSpace.getSquare();
            String message = "Cleaning room number " + i + " with total area " + spaceSquare + " square meters";
            semaphore.acquired(this);
            System.out.println(message);
            semaphore.release();

            if (Thread.currentThread().isInterrupted()) break;
        }
        System.out.println("Sequential cleaner has done its work!");
    }
}
