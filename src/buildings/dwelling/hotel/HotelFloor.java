package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
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

        for(Space space : this.getSpaces()){
            hotelFloorInfo += space.toString() + ", ";
        }

        return hotelFloorInfo.substring(0, hotelFloorInfo.length() - 2) + ")";
    }
}
