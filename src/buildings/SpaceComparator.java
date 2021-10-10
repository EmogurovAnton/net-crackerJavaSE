package buildings;
import java.util.Comparator;

public class SpaceComparator implements Comparator<Space> {

    @Override
    public int compare(Space first, Space second) {
        if (first.getRoomCount() == second.getRoomCount()) {
            return 0;
        } else if (first.getRoomCount() < second.getRoomCount()) {
            return 1;
        } else return -1;
    }
}
