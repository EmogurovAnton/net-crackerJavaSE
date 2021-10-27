package factories;

import buildings.officeBuilding.Office;
import buildings.officeBuilding.OfficeBuilding;
import buildings.officeBuilding.OfficeFloor;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

public class OfficeFactory implements BuildingFactory {
    @Override
    public Space createSpace(int square) {
        return new Office(square);
    }

    @Override
    public Space createSpace(int square, int roomCount) {
        return new Office(square, roomCount);
    }

    @Override
    public Floor createFloor(int spacesCount) {
        return new OfficeFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new OfficeFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spaceCount) {
        return new OfficeBuilding(floorsCount, spaceCount);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new OfficeBuilding(floors);
    }
}
