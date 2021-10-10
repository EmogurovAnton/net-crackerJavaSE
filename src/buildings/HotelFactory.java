package buildings;

import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;

public class HotelFactory implements BuildingFactory{

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
        return new HotelFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new HotelFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spaceCount) {
        return new Hotel(floorsCount, spaceCount);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Hotel(floors);
    }
}
