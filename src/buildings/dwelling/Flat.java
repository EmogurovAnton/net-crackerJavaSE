package buildings.dwelling;

import buildings.Space;
import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;

import java.util.Objects;

public class Flat implements Space, Cloneable {

    private int square;
    private int roomCount;

    public Flat() {
        this.square = 50;
        this.roomCount = 2;
    }

    public Flat(int flatSquare) {

        this.square = flatSquare;
        this.roomCount = 2;
    }

    public Flat(int flatSquare, int flatRoomCount) {
        this.square = flatSquare;
        this.roomCount = flatRoomCount;
    }

    public int getRoomCount() {
        return this.roomCount;
    }

    public int getSquare() {
        return this.square;
    }

    public void setRoomCount(int flatRoomCount) {

        if (flatRoomCount <= 0) {
            String exceptionMessage = "Room count cannot be negative or equal zero";
            throw new InvalidRoomsCountException(exceptionMessage);
        }

        this.roomCount = flatRoomCount;
    }

    public void setSquare(int flatSquare) {
        if (flatSquare <= 0) {
            String exceptionMessage = "Square cannot be negative or equal zero";
            throw new InvalidSpaceAreaException(exceptionMessage);
        }
        this.square = flatSquare;
    }

    public void showSpaceInformation() {
        String flatInformation = getSquare() + " " + getRoomCount();
        System.out.println(flatInformation);
    }

    @Override
    public String toString() {
        int roomCount = this.getRoomCount();
        double square = this.getSquare();
        String flatInfo = "Flat" + " (" + roomCount + ", " + square + ")";

        return flatInfo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Flat flat = (Flat) object;
        boolean squareCheck = this.getSquare() == flat.getSquare();
        boolean roomCountCheck = this.getRoomCount() == flat.getRoomCount();

        return squareCheck && roomCountCheck;
    }

    @Override
    public int hashCode() {
        int hashCode = this.getRoomCount() | this.getSquare();
        return hashCode;
    }

    @Override
    public Flat clone()  {
        try {
            return (Flat) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new InternalError();
        }
    }
}
