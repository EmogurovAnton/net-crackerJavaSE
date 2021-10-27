package buildings.dwelling.hotel;

import interfaces.Floor;
import interfaces.Space;
import buildings.dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor implements Floor {
    private final int starCount = 1;
    private int stars;


    public HotelFloor(Space[] hotelSpaces) {
        super(hotelSpaces);
        this.stars = starCount;
    }

    public HotelFloor(int spaceCount) {
        super(spaceCount);
        this.stars = starCount;
    }

    public int getStarCount() {
        return this.stars;
    }

    public void setStarCount(int starCount) {
        if (starCount > 0 && starCount < 6)
            this.stars = starCount;
    }

    @Override
    public String toString() {
        String hotelFloorInfo = "HotelFloor (" + this.getStarCount() + ", " + this.getSpaceCount() + ", ";

        for (Space space : this.getSpaces()) {
            hotelFloorInfo += space.toString() + ", ";
        }

        return hotelFloorInfo.substring(0, hotelFloorInfo.length() - 2) + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        HotelFloor hotelFloor = (HotelFloor) object;

        boolean checkSpaceCount = this.getSpaceCount() == hotelFloor.getSpaceCount();
        boolean checkStars = this.getStarCount() == hotelFloor.getStarCount();
        boolean checkSpaces = true;

        for (int i = 0; i < this.getSpaceCount(); i++) {
            checkSpaces = this.getSpaceByNumber(i + 1).equals(hotelFloor.getSpaceByNumber(i + 1));

            if (!checkSpaces) {
                break;
            }
        }

        return checkSpaceCount && checkStars && checkSpaces;
    }

    @Override
    public int hashCode() {
        int spaceCount = this.getSpaceCount();
        int starCount = this.getStarCount();
        int hashCode = spaceCount | starCount;

        for (int i = 0; i < this.getSpaceCount(); i++) {
            int currentSpaceHash = this.getSpaceByNumber(i + 1).hashCode();
            hashCode = hashCode | currentSpaceHash;
        }
        return hashCode;
    }

}
