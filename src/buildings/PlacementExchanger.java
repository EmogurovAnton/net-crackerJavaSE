package buildings;

import exceptions.UnexchangeableFloorsException;
import exceptions.UnexchangeableSpacesException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

public class PlacementExchanger {

    public static boolean isSpaceExchange(Space firstSpace, Space secondSpace) {
        boolean spacesSquareCheck = firstSpace.getSquare() == secondSpace.getSquare();
        boolean spacesRoomCountCheck = firstSpace.getRoomCount() == secondSpace.getRoomCount();

        return spacesSquareCheck && spacesRoomCountCheck;
    }

    public static boolean isFloorExchange(Floor firstFloor, Floor secondFloor) {
        boolean floorsSquareCheck = firstFloor.getFloorSpacesSquare() == secondFloor.getFloorSpacesSquare();
        boolean floorsRoomCountCheck = firstFloor.getFloorSpacesRoomCount() == secondFloor.getFloorSpacesRoomCount();

        return floorsSquareCheck && floorsRoomCountCheck;
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) {
        Space firstSpace = floor1.getSpaceByNumber(index1);
        Space secondSpace = floor2.getSpaceByNumber(index2);

        if (!isSpaceExchange(firstSpace, secondSpace)) {
            String exceptionMessage = "Spaces exchange is impossible";
            throw new UnexchangeableSpacesException(exceptionMessage);
        }

        String successMessage = "Spaces exchange is possible";
        System.out.println(successMessage);
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) {
        Floor firstFloor = building1.getBuildingFloorByNumber(index1);
        Floor secondFloor = building2.getBuildingFloorByNumber(index2);

        if (!isFloorExchange(firstFloor, secondFloor)) {
            String exceptionMessage = "Floors exchange is impossible";
            throw new UnexchangeableFloorsException(exceptionMessage);
        }

        String successMessage = "Floors exchange is possible";
        System.out.println(successMessage);
    }
}
