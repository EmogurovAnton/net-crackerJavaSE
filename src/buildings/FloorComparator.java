package buildings;
import interfaces.Floor;

import java.util.Comparator;

public class FloorComparator implements Comparator<Floor> {

    @Override
    public int compare(Floor first, Floor second) {
        if (first.getFloorSpacesSquare() == second.getFloorSpacesSquare()) {
            return 0;
        } else if (first.getFloorSpacesSquare() < second.getFloorSpacesSquare()) {
            return 1;
        } else {
            return -1;
        }
    }
}
