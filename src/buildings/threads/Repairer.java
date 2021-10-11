package buildings.threads;

import buildings.Floor;
import buildings.Space;

public class Repairer extends Thread {

    private Floor floor;

    public Repairer(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.floor.getSpaceCount(); i++) {
            Space currentSpace = this.floor.getSpaceByNumber(i + 1);
            int spaceSquare = currentSpace.getSquare();
            String message = "Repairing space number " + i + " with total area " + spaceSquare + " square meters";
            System.out.println(message);

            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
        System.out.println("Repairer has done its work!");
    }
}
