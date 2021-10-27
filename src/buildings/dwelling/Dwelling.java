package buildings.dwelling;

import interfaces.Building;
import interfaces.Floor;
import interfaces.Iterator;
import interfaces.Space;
import exceptions.FloorIndexOutOfBoundsException;

import java.io.Serializable;

public class Dwelling implements Building, Cloneable, Serializable {

    private int dwellingFloorCount;
    private int[] dwellingFloorFlatCount;
    private Floor[] dwellingFloors;

    public Dwelling() {
    }

    ;

    public Dwelling(int dwellingFloorCount, int[] dwellingFloorFlatCount) {
        this.dwellingFloorCount = dwellingFloorCount;
        this.dwellingFloorFlatCount = dwellingFloorFlatCount;
        dwellingFloors = new DwellingFloor[dwellingFloorCount];

        for (int i = 0; i < dwellingFloors.length; i++) {
            dwellingFloors[i] = new DwellingFloor(dwellingFloorFlatCount[i]);
        }
    }

    public Dwelling(Floor[] dwellingFloors) {
        this.dwellingFloors = dwellingFloors;
    }


    public int getBuildingFloorsCount() {
        int dwellingFloorCount = 0;

        for (Floor dwellingFloor : dwellingFloors) {
            dwellingFloorCount++;
        }
        return dwellingFloorCount;
    }

    public int getBuildingSpacesCount() {
        int dwellingFlatCount = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            dwellingFlatCount += dwellingFloor.getSpaceCount();
        }
        return dwellingFlatCount;
    }

    public int getAllBuildingSpacesSquare() {
        int dwellingFlatsSquare = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            dwellingFlatsSquare += dwellingFloor.getFloorSpacesSquare();
        }
        return dwellingFlatsSquare;
    }

    public int getAllBuildingSpacesRoomCount() {
        int dwellingRoomCount = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            dwellingRoomCount += dwellingFloor.getFloorSpacesRoomCount();
        }
        return dwellingRoomCount;
    }

    public Floor[] getFloors() {
        return this.dwellingFloors;
    }

    public Floor getBuildingFloorByNumber(int number) {
        int dwellingFloorIndex = number - 1;

        if (dwellingFloorIndex >= this.dwellingFloors.length || dwellingFloorIndex < 0) {
            String exceptionMessage = "Floor index is invalid";
            throw new FloorIndexOutOfBoundsException(exceptionMessage);
        }


        return dwellingFloors[dwellingFloorIndex];
    }

    public void changeBuildingFloorByNumber(int number, Floor dwellingFloor) {
        int dwellingFloorIndex = number - 1;

        if (dwellingFloorIndex >= this.dwellingFloors.length || dwellingFloorIndex < 0) {
            String exceptionMessage = "Floor index is invalid";
            throw new FloorIndexOutOfBoundsException(exceptionMessage);
        }

        dwellingFloors[dwellingFloorIndex] = dwellingFloor;
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
        for (Floor dwellingFloor : dwellingFloors) {
            floorBestSpaceFlat = dwellingFloor.getBestSpace();
        }
        return floorBestSpaceFlat;
    }

    public Space[] getSortedSpacesBySquare() {
        Space[] allDwellingFlats = new Flat[getBuildingSpacesCount()];
        int allFlatsIndex = 0;

        for (int i = 0; i < dwellingFloors.length; i++) {
            int currentFloorFlatIndex = 0;
            for (int j = 0; j < dwellingFloors[i].getSpaceCount(); j++) {
                allDwellingFlats[allFlatsIndex] = dwellingFloors[i].getSpaceByNumber(currentFloorFlatIndex + 1);

                allFlatsIndex++;
                currentFloorFlatIndex++;
            }
        }

        sortFlatsBySquare(allDwellingFlats);
        return allDwellingFlats;
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
        for (int i = 0; i < this.dwellingFloors.length; i++) {
            String floorInformation = "Этаж № " + (i + 1);
            System.out.println(floorInformation);
            for (int j = 0; j < this.dwellingFloors[i].getSpaceCount(); j++) {
                flatInformation = dwellingFloors[i].getSpaceByNumber(j + 1).getSquare() + " " + dwellingFloors[i].getSpaceByNumber(j + 1).getRoomCount();
                System.out.println(flatInformation);
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        String buildingInfo = "Dwelling (" + this.getBuildingFloorsCount() + ", ";

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

        Dwelling dwelling = (Dwelling) object;

        boolean floorCountCheck = this.getBuildingFloorsCount() == dwelling.getBuildingFloorsCount();

        if(!floorCountCheck){
            return false;
        }

        boolean floorCheck = true;

        for (int i = 0; i < this.getBuildingFloorsCount(); i++) {
            Floor currentFloor = this.getBuildingFloorByNumber(i + 1);
            floorCheck = currentFloor.equals(dwelling.getBuildingFloorByNumber(i + 1));

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
    public Dwelling clone() throws CloneNotSupportedException{
        return (Dwelling) super.clone();
    }

    @Override
    public Iterator getIterator() {
        return new FloorIterator();
    }

    private class FloorIterator implements Iterator{
        int index;

        @Override
        public boolean hasNext() {
            if(index < dwellingFloors.length){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return dwellingFloors[index++];
        }
    }

    /*public void showDwellingFlatsInformation(Space[] flats) {
        for (Space flat : flats) {
            String flatInformation = flat.getSquare() + " " + flat.getRoomCount();
            System.out.println(flatInformation);
        }
    }*/

}
