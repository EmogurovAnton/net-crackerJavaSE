package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;

public class Hotel extends Dwelling {

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
}
