package buildings.officeBuilding;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Flat;
import exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;

public class OfficeFloor extends Office implements Floor, Cloneable, Serializable {
    private int floorOfficeCount;
    private Space[] offices;


    //MyList<Space> offices;

    public OfficeFloor() {
        this.floorOfficeCount = 0;
    }

    public OfficeFloor(Space[] offices) {
        this.offices = offices;
    }

    public OfficeFloor(int floorOfficeCount, Space[] offices) {
        this.floorOfficeCount = floorOfficeCount;
        this.offices = offices;
    }

    public int getSpaceCount() {
        int floorFlatCount = 0;
        for (Space office : offices) {
            floorFlatCount++;
        }
        return floorFlatCount;
    }

    public Space[] getSpaces() {
        return this.offices;
    }

    public int getFloorSpacesSquare() {
        int flatsSquareSum = 0;
        for (Space office : offices) {
            flatsSquareSum += office.getSquare();
        }
        return flatsSquareSum;
    }

    public int getFloorSpacesRoomCount() {
        int flatsRoomsCountSum = 0;
        for (Space office : offices) {
            flatsRoomsCountSum += office.getRoomCount();
        }

        return flatsRoomsCountSum;
    }

    public void showFloorInformation() {
        for (Space office : offices) {
            if (office != null) {
                String flatInformation = office.getSquare() + " " + office.getRoomCount();
                System.out.println(flatInformation);
            }
        }
    }

    public Space getSpaceByNumber(int flatNumber) throws SpaceIndexOutOfBoundsException {
        int flatIndex = flatNumber - 1;

        if (flatIndex >= offices.length || flatIndex < 0) {
            String errorMessage = "Space index is invalid";
            throw new SpaceIndexOutOfBoundsException(errorMessage);
        }
        return offices[flatIndex];
    }

    public void changeSpaceByNumber(int flatNumber, Space flat) throws SpaceIndexOutOfBoundsException {
        int flatIndex = flatNumber - 1;

        if (flatIndex > offices.length || flatIndex < 0) {
            String errorMessage = "Space index is invalid";
            throw new SpaceIndexOutOfBoundsException(errorMessage);
        }

        offices[flatIndex] = flat;
    }

    public void addSpaceByNumber(int futureFlatNumber, Space flat) throws SpaceIndexOutOfBoundsException {
        int futureFlatIndex = futureFlatNumber - 1;

        if (futureFlatNumber > this.offices.length) {
            Space[] flats = new Flat[futureFlatNumber];
            for (int i = 0; i < this.offices.length; i++) {
                flats[i] = this.offices[i];
            }
            flats[futureFlatIndex] = flat;
            this.offices = flats;

        } else if (this.offices[futureFlatIndex] == null) {
            this.offices[futureFlatIndex] = flat;
        }
    }

    public void deleteSpaceByNumber(int deletedFlatNumber) throws SpaceIndexOutOfBoundsException {
        int futureFlatDeletedIndex = deletedFlatNumber - 1;

        if (futureFlatDeletedIndex >= this.offices.length || futureFlatDeletedIndex < 0) {
            String errorMessage = "Space index is invalid";
            throw new SpaceIndexOutOfBoundsException(errorMessage);
        }

        this.offices[futureFlatDeletedIndex] = null;
    }

    public Space getBestSpace() {
        int maximumFlatSpace = this.offices[0].getSquare();
        int maximumFlatSpaceIndex = 0;

        for (int i = 0; i < this.offices.length; i++) {
            Space currentFlat = this.offices[i];
            if (currentFlat != null && currentFlat.getSquare() > maximumFlatSpace) {
                maximumFlatSpace = currentFlat.getSquare();
                maximumFlatSpaceIndex = i;
            }
        }
        return this.offices[maximumFlatSpaceIndex];
    }

    @Override
    public String toString() {
        String floorInfo = "OfficeFloor (" + this.getSpaceCount() + ", ";
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

        OfficeFloor officeFloor = (OfficeFloor) object;
        boolean spaceCountCheck = this.getSpaceCount() == officeFloor.getSpaceCount();
        boolean spaceCheck = true;

        for (int i = 0; i < this.getSpaceCount(); i++) {
            spaceCheck = this.getSpaceByNumber(i + 1).equals(officeFloor.getSpaceByNumber(i + 1));
            if (!spaceCheck) {
                break;
            }
        }

        return spaceCountCheck && spaceCheck;
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
    public OfficeFloor clone() {
        return (OfficeFloor) super.clone();
    }

    /*public int getSpaceCount() {
        return this.offices.size();
    }

    public Space[] getSpaces() {
        Space[] floorOffices = new Office[offices.size()];

        for (int i = 0; i < offices.size(); i++) {
            floorOffices[i] = offices.get(i);
        }

        return floorOffices;
    }

    public int getFloorSpacesSquare() {
        int floorSquareSum = 0;

        for (int i = 0; i < offices.size(); i++) {
            floorSquareSum += offices.get(i).getSquare();
        }

        return floorSquareSum;
    }

    public int getFloorSpacesRoomCount() {
        int roomCountSum = 0;
        for (int i = 0; i < offices.size(); i++) {
            roomCountSum += offices.get(i).getRoomCount();
        }

        return roomCountSum;
    }

    public Space getSpaceByNumber(int officeNumber) {
        int index = officeNumber - 1;

        if (index >= this.offices.size() || index < 0) {
            String exceptionMessage = "Space index is invalid";
            throw new SpaceIndexOutOfBoundsException(exceptionMessage);
        }

        return this.offices.get(index);
    }

    public void changeSpaceByNumber(int officeNumber, Space office) {
        int index = officeNumber - 1;

        if (index >= this.offices.size() || index < 0) {
            String exceptionMessage = "Space index is invalid";
            throw new SpaceIndexOutOfBoundsException(exceptionMessage);
        }

        this.offices.get(index).setSquare(office.getSquare());
        this.offices.get(index).setRoomCount(office.getRoomCount());
    }

    public void addSpaceByNumber(int officeNumber, Space office) {
        int index = officeNumber - 1;

        if (index > this.offices.size() || index < 0) {
            String exceptionMessage = "Space index is invalid";
            throw new SpaceIndexOutOfBoundsException(exceptionMessage);
        }

        this.offices.add(index, office);
    }

    public void deleteSpaceByNumber(int officeNumber) {
        int index = officeNumber - 1;

        if (index >= this.offices.size() || index < 0) {
            String exceptionMessage = "Space index is invalid";
            throw new SpaceIndexOutOfBoundsException(exceptionMessage);
        }

        this.offices.remove(index);
    }

    public void showFloorInformation() {
        for (int i = 0; i < this.offices.size(); i++) {
            String floorInformation = offices.get(i).getSquare() + " " + offices.get(i).getRoomCount();
            System.out.println(floorInformation);
        }
    }

    public Space getBestSpace() {
        Space bestSpaceOffice = this.offices.get(0);

        for (int i = 1; i < this.offices.size(); i++) {
            if (this.offices.get(i).getSquare() > bestSpaceOffice.getSquare()) {
                bestSpaceOffice = this.offices.get(i);
            }
        }
        return bestSpaceOffice;
    }

    */

}
