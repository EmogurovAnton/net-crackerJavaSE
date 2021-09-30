package buildings;

public interface Space {
    public int getRoomCount();

    public void setRoomCount(int roomCount);

    public int getSquare();

    public void setSquare(int square);

    public void showSpaceInformation();

    public Space clone();
}
