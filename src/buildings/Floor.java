package buildings;

public interface Floor extends Space{

    public int getSpaceCount();

    public int getFloorSpacesSquare();

    public int getFloorSpacesRoomCount();

    public Space[] getSpaces();

    public Space getSpaceByNumber(int spaceNumber);

    public void changeSpaceByNumber(int spaceNumber, Space space);

    public void addSpaceByNumber(int spaceNumber, Space space);

    public void deleteSpaceByNumber(int spaceNumber);

    public Space getBestSpace();

    public void showFloorInformation();

    public Floor clone();

    Iterator getIterator();
}
