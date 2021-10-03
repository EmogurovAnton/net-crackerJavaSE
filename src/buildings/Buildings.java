package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


public class Buildings {


    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);

        dos.writeInt(building.getBuildingFloorsCount());
        for (int i = 0; i < building.getBuildingFloorsCount(); i++) {
            Floor currentFloor = building.getBuildingFloorByNumber(i + 1);
            dos.writeInt(currentFloor.getSpaceCount());
            for (int j = 0; j < currentFloor.getSpaceCount(); j++) {
                Space currentSpace = currentFloor.getSpaceByNumber(j + 1);
                dos.writeInt(currentSpace.getSquare());
                dos.writeInt(currentSpace.getRoomCount());
            }
        }
        dos.close();
        out.close();
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream(in);

        int floorCount = dis.readInt();
        Floor[] floors = new Floor[floorCount];

        for (int i = 0; i < floorCount; i++) {
            int floorSpacesCount = dis.readInt();
            Space[] spaces = new Space[floorSpacesCount];
            for (int j = 0; j < floorSpacesCount; j++) {
                int spaceSquare = dis.readInt();
                int spaceRoomCount = dis.readInt();

                Space space = new Flat(spaceSquare, spaceRoomCount);
                spaces[j] = space;
            }
            floors[i] = new DwellingFloor(spaces);
        }

        dis.close();
        return new Dwelling(floors);
    }

   /* public static String inputBuilding(InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream(in);
        byte[] buff = dis.readAllBytes();

        String a = ByteBuffer.wrap(buff).getInt(3) + "";

        return a;
    }*/

    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(building);
        oos.close();
    }

    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        Building readBuilding = (Building) ois.readObject();
        return readBuilding;
    }




    /*public static String getBuildingInfo(Building building) {
        String buildingInfo = building.getBuildingFloorsCount() + " ";

        for (int i = 1; i <= building.getBuildingFloorsCount(); i++) {

            int currentFloorSpaceCount = building.getBuildingFloorByNumber(i).getSpaceCount();
            buildingInfo += currentFloorSpaceCount + " ";

            for (int j = 1; j <= building.getBuildingFloorByNumber(i).getSpaceCount(); j++) {
                int currentSpaceRoomCount = building.getBuildingFloorByNumber(i).getSpaceByNumber(j).getRoomCount();
                double currentSpaceSquare = building.getBuildingFloorByNumber(i).getSpaceByNumber(j).getSquare();

                buildingInfo += currentSpaceRoomCount + " " + currentSpaceSquare + " ";
            }
        }

        return buildingInfo;
    }*/





    /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);


        DataOutputStream dos = new DataOutputStream(out);

        try {
            dos.writeInt(building.getBuildingFloorsCount());
            for (int i = 0; i < building.getBuildingFloorsCount(); i++) {
                Floor currentFloor = building.getBuildingFloorByNumber(i + 1);
                dos.writeInt(currentFloor.getSpaceCount());
                for (int j = 0; j < currentFloor.getSpaceCount(); j++) {
                    Space currentSpace = currentFloor.getSpaceByNumber(j + 1);
                    dos.writeInt(currentSpace.getRoomCount());
                    dos.writeInt(currentSpace.getSquare());
                }
            }
            out.write(baos.toByteArray());
            baos.close();
            dos.close();
            out.close();
        } catch (IOException exception) {
            exception.getStackTrace();
        }*/

}
