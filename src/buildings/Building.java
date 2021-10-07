package buildings;

public interface Building extends Floor {
    public int getBuildingFloorsCount();

    public int getBuildingSpacesCount();

    public int getAllBuildingSpacesSquare();

    public int getAllBuildingSpacesRoomCount();

    public Floor[] getFloors();

    public Floor getBuildingFloorByNumber(int floorNumber);

    public void changeBuildingFloorByNumber(int floorNumber, Floor floor);

    public Space getSpaceInBuildingByNumber(int floorNumber, int spaceNumber);

    public void changeSpaceInBuildingByNumber(int floorNumber, int spaceNumber, Space space);

    public void addSpaceInBuildingByNumber(int floorNumber, int spaceNumber, Space space);

    public void deleteSpaceInBuildingByNumber(int floorNumber, int spaceNumber);

    public Space getBestSpace();

    public Space[] getSortedSpacesBySquare();

    public void showBuildingInformation();

    public Building clone();

    Iterator getIterator();
}
