package buildings.dwelling;

import buildings.Floor;
import buildings.Space;
import exceptions.SpaceIndexOutOfBoundsException;

public class DwellingFloor extends Flat implements Floor, Cloneable {
    private int flatCount;
    private Space flats[];


    public DwellingFloor() {
    }

    ;

    public DwellingFloor(Space flats[]) {
        this.flats = flats;
    }

    public DwellingFloor(int flatCount) {
        this.flatCount = flatCount;
        this.flats = new Flat[this.flatCount];
    }


    public int getSpaceCount() {
        int floorFlatCount = 0;
        for (Space flat : flats) {
            floorFlatCount++;
        }
        return floorFlatCount;
    }

    public Space[] getSpaces() {
        return this.flats;
    }

    public int getFloorSpacesSquare() {
        int flatsSquareSum = 0;
        for (Space flat : flats) {
            flatsSquareSum += flat.getSquare();
        }
        return flatsSquareSum;
    }

    public int getFloorSpacesRoomCount() {
        int flatsRoomsCountSum = 0;
        for (Space flat : flats) {
            flatsRoomsCountSum += flat.getRoomCount();
        }

        return flatsRoomsCountSum;
    }

    public void showFloorInformation() {
        for (Space flat : flats) {
            if (flat != null) {
                String flatInformation = flat.getSquare() + " " + flat.getRoomCount();
                System.out.println(flatInformation);
            }
        }
    }

    public Space getSpaceByNumber(int flatNumber) throws SpaceIndexOutOfBoundsException {
        int flatIndex = flatNumber - 1;

        if (flatIndex >= flats.length || flatIndex < 0) {
            String errorMessage = "Space index is invalid";
            throw new SpaceIndexOutOfBoundsException(errorMessage);
        }
        return flats[flatIndex];
    }

    public void changeSpaceByNumber(int flatNumber, Space flat) throws SpaceIndexOutOfBoundsException {
        int flatIndex = flatNumber - 1;

        if (flatIndex > flats.length || flatIndex < 0) {
            String errorMessage = "Space index is invalid";
            throw new SpaceIndexOutOfBoundsException(errorMessage);
        }

        flats[flatIndex] = flat;
    }

    public void addSpaceByNumber(int futureFlatNumber, Space flat) throws SpaceIndexOutOfBoundsException {
        int futureFlatIndex = futureFlatNumber - 1;

        if (futureFlatNumber > this.flats.length) {
            Space[] flats = new Flat[futureFlatNumber];
            for (int i = 0; i < this.flats.length; i++) {
                flats[i] = this.flats[i];
            }
            flats[futureFlatIndex] = flat;
            this.flats = flats;

        } else if (this.flats[futureFlatIndex] == null) {
            this.flats[futureFlatIndex] = flat;
        }
    }

    public void deleteSpaceByNumber(int deletedFlatNumber) throws SpaceIndexOutOfBoundsException {
        int futureFlatDeletedIndex = deletedFlatNumber - 1;

        if (futureFlatDeletedIndex >= this.flats.length || futureFlatDeletedIndex < 0) {
            String errorMessage = "Space index is invalid";
            throw new SpaceIndexOutOfBoundsException(errorMessage);
        }

        this.flats[futureFlatDeletedIndex] = null;
    }

    public Space getBestSpace() {
        int maximumFlatSpace = this.flats[0].getSquare();
        int maximumFlatSpaceIndex = 0;

        for (int i = 0; i < this.flats.length; i++) {
            Space currentFlat = this.flats[i];
            if (currentFlat != null && currentFlat.getSquare() > maximumFlatSpace) {
                maximumFlatSpace = currentFlat.getSquare();
                maximumFlatSpaceIndex = i;
            }
        }
        return this.flats[maximumFlatSpaceIndex];
    }

    @Override
    public String toString() {
        String floorInfo = "DwellingFloor (" + this.getSpaceCount() + ", ";
        for (int i = 0; i < this.getSpaceCount(); i++) {
            String currentSpaceInfo = this.getSpaceByNumber(i + 1).toString() + ", ";
            floorInfo += currentSpaceInfo;
        }

        String resultFloorInfo = floorInfo.substring(0, floorInfo.length() - 2) + ")";

        return resultFloorInfo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        DwellingFloor dwellingFloor = (DwellingFloor) object;

        boolean spaceCountCheck = this.getSpaceCount() == dwellingFloor.getSpaceCount();
        boolean spacesCheck = true;

        for (int i = 0; i < this.getSpaceCount(); i++) {
            spacesCheck = this.getSpaceByNumber(i + 1).equals(dwellingFloor.getSpaceByNumber(i + 1));
            if (!spacesCheck) {
                break;
            }
        }

        return spaceCountCheck && spacesCheck;
    }

    @Override
    public int hashCode() {
        int hashCode = this.getSpaceCount();

        for (int i = 0; i < this.getSpaceCount(); i++) {
            int currentSpaceHash = this.getSpaceByNumber(i + 1).hashCode();
            hashCode = hashCode | currentSpaceHash;
        }
        return hashCode;
    }

    @Override
    public DwellingFloor clone() {
        return (DwellingFloor)super.clone();
    }
}

