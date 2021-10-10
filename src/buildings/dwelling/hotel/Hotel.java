package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;

public class Hotel extends Dwelling {

    public Hotel(int hotelFloorCount, int[] hotelFloorSpaceCount) {
        super(hotelFloorCount, hotelFloorSpaceCount);
    }

    public Hotel(Floor[] hotelFloors) {
        super(hotelFloors);
    }

    public int getHotelStars() {
        int hotelStars = 1;
        for (int i = 0; i < super.getBuildingFloorsCount(); i++) {
            Floor floor = super.getBuildingFloorByNumber(i + 1);
            if (floor instanceof HotelFloor) {
                HotelFloor hotelFloor = (HotelFloor) floor;
                if (hotelFloor.getStarCount() > hotelStars) {
                    hotelStars = hotelFloor.getStarCount();
                }
            }

        }
        return hotelStars;
    }

    private double getCoeff(int floorStars) {
        double[] coeffs = new double[]{0.25, 0.5, 1.0, 1.25, 1.5};
        double finalCoeff = coeffs[floorStars - 1];

        return finalCoeff;
    }

    @Override
    public Space getBestSpace() {
        Space hotelBestSpace = null;
        double bestSpaceValue = 0.0;

        for (Floor floor : this.getFloors()) {
            if (floor instanceof HotelFloor) {
                HotelFloor hotelFloor = (HotelFloor) floor;
                int floorStars = hotelFloor.getStarCount();
                double floorCoeff = getCoeff(floorStars);
                double currentFloorSpaceValue = hotelFloor.getBestSpace().getSquare() * floorCoeff;

                if (currentFloorSpaceValue > bestSpaceValue) {
                    hotelBestSpace = hotelFloor.getBestSpace();
                    bestSpaceValue = currentFloorSpaceValue;
                }
            }
        }
        return hotelBestSpace;
    }

    @Override
    public String toString() {
        String hotelInfo = "Hotel (" + this.getHotelStars() + ", " + this.getBuildingFloorsCount() + ", ";

        for (int i = 0; i < this.getBuildingFloorsCount(); i++) {
            hotelInfo += getBuildingFloorByNumber(i + 1).toString() + ", ";
        }

        String resultHotelInfo = hotelInfo.substring(0, hotelInfo.length() - 2) + ")";

        return resultHotelInfo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Hotel hotel = (Hotel) object;

        boolean checkFloorCount = this.getBuildingFloorsCount() == hotel.getBuildingFloorsCount();
        if (!checkFloorCount) return false;
        boolean checkFloors = true;

        for (int i = 0; i < this.getBuildingFloorsCount(); i++) {
            HotelFloor currentFloor = (HotelFloor) this.getBuildingFloorByNumber(i + 1);
            HotelFloor hotelFloor = (HotelFloor) hotel.getBuildingFloorByNumber(i + 1);

            checkFloors = currentFloor.equals(hotelFloor);
            if (!checkFloors) {
                return false;
            }
        }

        return checkFloorCount && checkFloors;
    }

    @Override
    public int hashCode() {
        int floorCount = this.getBuildingFloorsCount();
        int hashCode = 0;

        for (int i = 0; i < this.getBuildingFloorsCount(); i++) {
            hashCode = floorCount | this.getBuildingFloorByNumber(i + 1).hashCode();
        }

        return hashCode;
    }
}
