package buildings;

public class SynchronizedFloor implements Floor{
    private Floor floor;

    public SynchronizedFloor(Floor floor){
        try {
            this.floor = floor.clone();
        } catch (CloneNotSupportedException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public String toString() {
        return this.floor.toString();
    }

    @Override
    public int getSpaceCount() {
        return this.floor.getSpaceCount();
    }

    @Override
    public int getFloorSpacesSquare() {
        return this.floor.getFloorSpacesSquare();
    }

    @Override
    public int getFloorSpacesRoomCount() {
        return floor.getFloorSpacesRoomCount();
    }

    @Override
    public Space[] getSpaces() {
        return floor.getSpaces();
    }

    @Override
    public Space getSpaceByNumber(int spaceNumber) {
        return floor.getSpaceByNumber(spaceNumber);
    }

    @Override
    public void changeSpaceByNumber(int spaceNumber, Space space) {
        floor.changeSpaceByNumber(spaceNumber, space);
    }

    @Override
    public void addSpaceByNumber(int spaceNumber, Space space) {
        floor.addSpaceByNumber(spaceNumber, space);
    }

    @Override
    public void deleteSpaceByNumber(int spaceNumber) {
        floor.deleteSpaceByNumber(spaceNumber);
    }

    @Override
    public Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public void showFloorInformation() {
        floor.showFloorInformation();
    }

    @Override
    public Floor clone() throws CloneNotSupportedException {
        return floor.clone();
    }

    @Override
    public Iterator getIterator() {
        return floor.getIterator();
    }

    @Override
    public int compareTo(Floor o) {
        return floor.compareTo(o);
    }
}
