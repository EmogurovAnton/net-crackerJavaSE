package buildings;

public interface BuildingFactory {
    public Space createSpace(int square);

    public Space createSpace(int square, int roomCount);

    public Floor createFloor(int spacesCount);

    public Floor createFloor(Space[] spaces);

    public Building createBuilding(int floorsCount, int[] spaceCount);

    public Building createBuilding(Floor[] floors);
}
