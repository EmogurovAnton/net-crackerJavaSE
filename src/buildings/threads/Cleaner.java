package buildings.threads;

import interfaces.Floor;
import interfaces.Space;

public class Cleaner extends Thread {
    private Floor floor;

    public Cleaner(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.floor.getSpaceCount(); i++) {
            Space currentSpace = this.floor.getSpaceByNumber(i + 1);
            int spaceSquare = currentSpace.getSquare();
            String message = "Cleaning room number " + i + " with total area " + spaceSquare + " square meters";
            System.out.println(message);

            if (Thread.currentThread().isInterrupted()) {
                break;
            }

        }
        System.out.println("Cleaner has done its work!");
    }
}
