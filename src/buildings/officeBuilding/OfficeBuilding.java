package buildings.officeBuilding;

import interfaces.Building;
import interfaces.Floor;
import interfaces.Iterator;
import interfaces.Space;
import buildings.dwelling.Flat;
import exceptions.FloorIndexOutOfBoundsException;

import java.io.Serializable;

public class OfficeBuilding implements Building, Cloneable, Serializable {
    int floorCount;
    int[] floorOffices;
    //MyLinkedList<OfficeFloor> officeBuildingFloors;
    Floor[] officeBuildingFloors;

    public OfficeBuilding() {
        this.floorCount = 0;
    }

    public OfficeBuilding(int officeBuildingFloorCount, int[] officeBuildingFloorOfficeCount) {
        this.floorCount = officeBuildingFloorCount;
        this.floorOffices = officeBuildingFloorOfficeCount;
        this.officeBuildingFloors = new OfficeFloor[this.floorCount];

        for (int i = 0; i < this.officeBuildingFloors.length; i++) {
            this.officeBuildingFloors[i] = new OfficeFloor(officeBuildingFloorOfficeCount[i]);
        }
    }

    public OfficeBuilding(Floor[] officeFloors) {
        this.officeBuildingFloors = officeFloors;
    }

    public int getBuildingFloorsCount() {
        int dwellingFloorCount = 0;

        for (Floor officeFloor : officeBuildingFloors) {
            dwellingFloorCount++;
        }
        return dwellingFloorCount;
    }

    public int getBuildingSpacesCount() {
        int dwellingFlatCount = 0;
        for (Floor officeFloor : officeBuildingFloors) {
            dwellingFlatCount += officeFloor.getSpaceCount();
        }
        return dwellingFlatCount;
    }

    public int getAllBuildingSpacesSquare() {
        int dwellingFlatsSquare = 0;
        for (Floor officeFloor : officeBuildingFloors) {
            dwellingFlatsSquare += officeFloor.getFloorSpacesSquare();
        }
        return dwellingFlatsSquare;
    }

    public int getAllBuildingSpacesRoomCount() {
        int dwellingRoomCount = 0;
        for (Floor officeFloor : officeBuildingFloors) {
            dwellingRoomCount += officeFloor.getFloorSpacesRoomCount();
        }
        return dwellingRoomCount;
    }

    public Floor[] getFloors() {
        return this.officeBuildingFloors;
    }

    public Floor getBuildingFloorByNumber(int number) {
        int dwellingFloorIndex = number - 1;

        if (dwellingFloorIndex >= this.officeBuildingFloors.length || dwellingFloorIndex < 0) {
            String exceptionMessage = "Floor index is invalid";
            throw new FloorIndexOutOfBoundsException(exceptionMessage);
        }


        return officeBuildingFloors[dwellingFloorIndex];
    }

    public void changeBuildingFloorByNumber(int number, Floor dwellingFloor) {
        int dwellingFloorIndex = number - 1;

        if (dwellingFloorIndex >= this.officeBuildingFloors.length || dwellingFloorIndex < 0) {
            String exceptionMessage = "Floor index is invalid";
            throw new FloorIndexOutOfBoundsException(exceptionMessage);
        }

        officeBuildingFloors[dwellingFloorIndex] = dwellingFloor;
    }

    public Space getSpaceInBuildingByNumber(int dwellingFloorNumber, int flatNumber) {
        return getBuildingFloorByNumber(dwellingFloorNumber).getSpaceByNumber(flatNumber);
    }

    public void changeSpaceInBuildingByNumber(int dwellingFloorNumber, int flatNumber, Space flat) {
        getBuildingFloorByNumber(dwellingFloorNumber).changeSpaceByNumber(flatNumber, flat);
    }

    public void addSpaceInBuildingByNumber(int dwellingFloorNumber, int flatNumber, Space flat) {
        getBuildingFloorByNumber(dwellingFloorNumber).addSpaceByNumber(flatNumber, flat);
    }

    public void deleteSpaceInBuildingByNumber(int dwellingFloorNumber, int flatNumber) {
        getBuildingFloorByNumber(dwellingFloorNumber).deleteSpaceByNumber(flatNumber);
    }


    public Space getBestSpace() {
        Space floorBestSpaceFlat = null;
        for (Floor officeFloor : officeBuildingFloors) {
            floorBestSpaceFlat = officeFloor.getBestSpace();
        }
        return floorBestSpaceFlat;
    }

    public Space[] getSortedSpacesBySquare() {
        Space[] allOffices = new Flat[getBuildingSpacesCount()];
        int allFlatsIndex = 0;

        for (int i = 0; i < officeBuildingFloors.length; i++) {
            int currentFloorFlatIndex = 0;
            for (int j = 0; j < officeBuildingFloors[i].getSpaceCount(); j++) {
                allOffices[allFlatsIndex] = officeBuildingFloors[i].getSpaceByNumber(currentFloorFlatIndex + 1);

                allFlatsIndex++;
                currentFloorFlatIndex++;
            }
        }

        sortFlatsBySquare(allOffices);
        return allOffices;
    }

    public void sortFlatsBySquare(Space[] flats) {
        for (int i = 0; i < flats.length - 1; i++) {
            for (int j = 0; j < flats.length - i - 1; j++) {
                if (flats[j].getSquare() < flats[j + 1].getSquare()) {
                    Space tempFlat = flats[j];
                    flats[j] = flats[j + 1];
                    flats[j + 1] = tempFlat;
                }
            }
        }
    }

    public void showBuildingInformation() {
        String flatInformation;
        for (int i = 0; i < this.officeBuildingFloors.length; i++) {
            String floorInformation = "Этаж № " + (i + 1);
            System.out.println(floorInformation);
            for (int j = 0; j < this.officeBuildingFloors[i].getSpaceCount(); j++) {
                flatInformation = officeBuildingFloors[i].getSpaceByNumber(j + 1).getSquare() + " ";
                flatInformation += officeBuildingFloors[i].getSpaceByNumber(j + 1).getRoomCount();
                System.out.println(flatInformation);
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        String buildingInfo = "OfficeBuilding (" + this.getBuildingFloorsCount() + ", ";

        for (int i = 0; i < this.getBuildingFloorsCount(); i++) {
            String currentFloorInfo = this.getBuildingFloorByNumber(i + 1).toString() + ", ";
            buildingInfo += currentFloorInfo;
        }

        String resultBuildingInfo = buildingInfo.substring(0, buildingInfo.length() - 2) + ")";

        return resultBuildingInfo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        OfficeBuilding officeBuilding = (OfficeBuilding) object;

        boolean floorCountCheck = this.getBuildingFloorsCount() == officeBuilding.getBuildingFloorsCount();

        if (!floorCountCheck) return false;

        boolean floorCheck = true;

        for (int i = 0; i < this.getBuildingFloorsCount(); i++) {
            Floor currentFloor = this.getBuildingFloorByNumber(i + 1);
            floorCheck = currentFloor.equals(officeBuilding.getBuildingFloorByNumber(i + 1));

            if (!floorCheck) {
                break;
            }
        }

        return floorCountCheck && floorCheck;
    }

    @Override
    public int hashCode() {
        int hashCode = this.getBuildingFloorsCount();

        for (int i = 0; i < this.getBuildingFloorsCount(); i++) {
            Floor currentFloor = this.getBuildingFloorByNumber(i + 1);
            hashCode = hashCode | currentFloor.hashCode();
        }
        return hashCode;
    }

    @Override
    public OfficeBuilding clone() throws CloneNotSupportedException {
        return (OfficeBuilding) super.clone();
    }

    @Override
    public Iterator getIterator() {
        return new FloorIterator();
    }

    private class FloorIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < officeBuildingFloors.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return officeBuildingFloors[index++];
        }
    }

    /*public OfficeBuilding(MyLinkedList<OfficeFloor> officeBuildingFloors) {
        this.officeBuildingFloors = officeBuildingFloors;
    }

    public int getAllOfficeBuildingFloors() {
        return this.officeBuildingFloors.size();
    }

    public int getAllOfficeBuildingOffices() {
        int allOfficesCount = 0;

        for (int i = 0; i < officeBuildingFloors.size(); i++) {
            allOfficesCount += officeBuildingFloors.get(i).getSpaceCount();
        }

        return allOfficesCount;
    }

    public int getAllOfficeBuildingRoomSquare() {
        int officeAllRoomSquare = 0;

        for (int i = 0; i < officeBuildingFloors.size(); i++) {
            officeAllRoomSquare += officeBuildingFloors.get(i).getFloorSpacesSquare();
        }
        return officeAllRoomSquare;
    }

    public int getAllOfficeBuildingRoomCount() {
        int officeAllRoomCount = 0;

        for (int i = 0; i < officeBuildingFloors.size(); i++) {
            officeAllRoomCount += officeBuildingFloors.get(i).getFloorSpacesRoomCount();
        }

        return officeAllRoomCount;
    }

    public OfficeFloor[] getOfficeBuildingFloorsAsArray() {
        OfficeFloor[] floorsInOfficeBuilding = new OfficeFloor[this.officeBuildingFloors.size()];

        for (int i = 0; i < officeBuildingFloors.size(); i++) {
            floorsInOfficeBuilding[i] = officeBuildingFloors.get(i);
        }

        return floorsInOfficeBuilding;
    }

    public OfficeFloor getOfficeBuildingFloorByNumber(int officeBuildingFloorNumber) {
        int officeFloorIndex = officeBuildingFloorNumber - 1;

        if (officeFloorIndex >= this.officeBuildingFloors.size() || officeFloorIndex < 0) {
            String exceptionMessage = "Floor index is invalid";
            throw new FloorIndexOutOfBoundsException(exceptionMessage);
        }

        return officeBuildingFloors.get(officeFloorIndex);
    }


    public void changeOfficeBuildingFloorByNumber(int officeBuildingFloorNumber, OfficeFloor newOfficeFloor) {
        int officeBuildingFloorIndex = officeBuildingFloorNumber - 1;

        if (officeBuildingFloorIndex >= this.officeBuildingFloors.size() || officeBuildingFloorIndex < 0) {
            String exceptionMessage = "Floor index is invalid";
            throw new FloorIndexOutOfBoundsException(exceptionMessage);
        }

        officeBuildingFloors.remove(officeBuildingFloorIndex);
        officeBuildingFloors.add(officeBuildingFloorIndex, newOfficeFloor);
    }

    public Space getOfficeInOfficeBuilding(int floorNumber, int officeNumber) {
        return getOfficeBuildingFloorByNumber(floorNumber).getSpaceByNumber(officeNumber);
    }

    public void changeOfficeInOfficeBuilding(int floorNumber, int officeNumber, Office newOffice) {
        getOfficeBuildingFloorByNumber(floorNumber).changeSpaceByNumber(officeNumber, newOffice);
    }

    public void addOfficeInOfficeBuilding(int floorNumber, int officeNumber, Office newOffice) {
        getOfficeBuildingFloorByNumber(floorNumber).addSpaceByNumber(officeNumber, newOffice);
    }

    public void removeOfficeInOfficeBuilding(int floorNumber, int officeNumber) {
        getOfficeBuildingFloorByNumber(floorNumber).deleteSpaceByNumber(officeNumber);
    }

    public Space getBestSpace() {
        Space bestSpaceOffice = officeBuildingFloors.get(0).getBestSpace();

        for (int i = 0; i < officeBuildingFloors.size(); i++) {
            if (bestSpaceOffice.getSquare() < officeBuildingFloors.get(i).getBestSpace().getSquare()) {
                bestSpaceOffice = officeBuildingFloors.get(i).getBestSpace();
            }
        }
        return bestSpaceOffice;
    }


    public Space[] getAllSortedOfficesBySquare() {
        Space[] officeBuildingOffices = new Office[getAllOfficeBuildingOffices()];
        int allOfficeIndex = 0;

        for (int i = 0; i < officeBuildingFloors.size(); i++) {
            for (int j = 0; j < officeBuildingFloors.get(i).getSpaceCount(); j++) {
                officeBuildingOffices[allOfficeIndex] = officeBuildingFloors.get(i).getSpaceByNumber(j + 1);
                allOfficeIndex++;
            }
        }
        sortOfficesBySquare(officeBuildingOffices);
        return officeBuildingOffices;
    }

    public MyList<Space> officesArrayToList(Space[] offices) {
        MyList<Space> officesList = new MyList<Space>();

        for (int i = 0; i < offices.length; i++) {
            officesList.add(i, offices[i]);
            officesList.get(i).showSpaceInformation();
        }

        return officesList;
    }

    public void sortOfficesBySquare(Space[] buildingOffices) {
        for (int i = 0; i < buildingOffices.length - 1; i++) {
            for (int j = 0; j < buildingOffices.length - i - 1; j++) {
                if (buildingOffices[j].getSquare() < buildingOffices[j + 1].getSquare()) {
                    Space tempOffice = buildingOffices[j];
                    buildingOffices[j] = buildingOffices[j + 1];
                    buildingOffices[j + 1] = tempOffice;
                }
            }
        }
    }*/

}
