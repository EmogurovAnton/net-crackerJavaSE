package factories;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

public class DwellingFactory implements BuildingFactory {

    @Override
    public Space createSpace(int square) {
        return new Flat(square);
    }

    @Override
    public Space createSpace(int square, int roomCount) {
        return new Flat(square, roomCount);
    }

    @Override
    public Floor createFloor(int spacesCount) {
        return new DwellingFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new DwellingFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spaceCount) {
        return new Dwelling(floorsCount, spaceCount);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Dwelling(floors);
    }
}
