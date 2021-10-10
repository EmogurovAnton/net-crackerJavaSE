package buildings.officeBuilding;

import buildings.Space;
import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.util.Objects;

public class Office implements Space, Cloneable, Serializable {
    private int square;
    private int roomCount;

    public Office() {
        this.roomCount = 1;
        this.square = 250;
    }

    public Office(int officeSquare) {
        this.roomCount = 1;
        this.square = officeSquare;
    }

    public Office(int officeSquare, int officeRoomCount) {
        this.roomCount = officeRoomCount;
        this.square = officeSquare;
    }

    public int getRoomCount() {
        return this.roomCount;
    }

    public void setRoomCount(int officeRoomCount) {

        if (officeRoomCount <= 0) {
            String exceptionMessage = "Room count cannot be negative or equal zero";
            throw new InvalidRoomsCountException(exceptionMessage);
        }

        this.roomCount = officeRoomCount;
    }

    public int getSquare() {
        return this.square;
    }

    public void setSquare(int officeSquare) {

        if (officeSquare <= 0) {
            String exceptionMessage = "Square cannot be negative or equal zero";
            throw new InvalidSpaceAreaException(exceptionMessage);
        }

        this.square = officeSquare;
    }

    public void showSpaceInformation() {
        String officeInformation = "Площадь: " + getSquare() + ", " + "количество комнат: " + getRoomCount();
        System.out.println(officeInformation);
    }

    @Override
    public String toString() {
        int roomCount = this.getRoomCount();
        double square = this.getSquare();
        String officeInfo = "Office" + " (" + roomCount + ", " + square + ")";

        return officeInfo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Office office = (Office) object;
        boolean squareCheck = this.getSquare() == office.getSquare();
        boolean roomCountCheck = this.getRoomCount() == office.getRoomCount();

        return squareCheck && roomCountCheck;
    }

    @Override
    public int hashCode() {
        int hashCode = this.getRoomCount() | this.getSquare();
        return hashCode;
    }

    @Override
    public Office clone() {
        try {
            return (Office) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new InternalError();
        }
    }

    @Override
    public int compareTo(Space o) {
        if (this.getSquare() == o.getSquare()) {
            return 0;
        } else if (this.getSquare() < o.getSquare()) {
            return -1;
        } else {
            return 1;
        }
    }
}
