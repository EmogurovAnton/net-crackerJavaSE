package buildings.threads;

import buildings.Floor;
import buildings.Space;

public class SequentalRepairer implements Runnable {
    private Floor floor;
    private Semaphore semaphore;

    public SequentalRepairer(Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
        for (int i = 0; i < this.floor.getSpaceCount(); i++) {
            Space currentSpace = this.floor.getSpaceByNumber(i + 1);
            int spaceSquare = currentSpace.getSquare();
            String message = "Repairing space number " + i + " with total area " + spaceSquare + " square meters";
            semaphore.acquired(this);
            System.out.println(message);
            semaphore.release();

            if (Thread.currentThread().isInterrupted()) break;
        }
        System.out.println("Sequential repairer has done its work!");
    }
}
