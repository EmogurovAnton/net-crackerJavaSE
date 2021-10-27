package buildings;

import factories.DwellingFactory;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;
import java.util.Comparator;


public class Buildings {

    private static BuildingFactory buildingFactory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

    //Создание объекта с помощью фабрики
    public static Space createSpace(int square) {
        return buildingFactory.createSpace(square);
    }

    //Создание объекта с помощью рефлексии
    public static Space createSpace(int square, Class<? extends Space> spaceClass) {
        try {
            return spaceClass.getConstructor(Integer.TYPE).newInstance(square);
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
    }

    //Создание объекта с помощью фабрики
    public static Space createSpace(int square, int roomCount) {
        return buildingFactory.createSpace(square, roomCount);
    }

    //Создание объекта с помощью рефлексии
    public static Space createSpace(int square, int roomCount, Class<? extends Space> spaceClass) {
        try {
            return spaceClass.getConstructor(Integer.TYPE, Integer.TYPE).newInstance(square, roomCount);
        } catch (Exception exception) {
            throw new IllegalArgumentException();
        }
    }

    //Создание объекта с помощью фабрики
    public static Floor createFloor(int spacesCount) {
        return buildingFactory.createFloor(spacesCount);
    }

    //Создание объекта с помощтю рефлексии
    public static Floor createFloor(int spaceCount, Class<? extends Floor> floorClass) {
        try {
            return floorClass.getConstructor(Integer.TYPE).newInstance(spaceCount);
        } catch (Exception exception) {
            throw new IllegalArgumentException();
        }
    }

    //Создание объекта с помощью фабрики
    public static Floor createFloor(Space...spaces) {
        return buildingFactory.createFloor(spaces);
    }

    //Создание объекта с помощью рефлексии
    public static Floor createFloor(Class<? extends Floor> floorClass, Space...spaces) {
        try {
            return floorClass.getConstructor(Space[].class).newInstance(spaces);
        } catch (Exception exception) {
            throw new IllegalArgumentException();
        }
    }

    //Создание объекта с помощью фабрики
    public static Building createBuilding(int floorsCount, int...spaceCount) {
        return buildingFactory.createBuilding(floorsCount, spaceCount);
    }

    //Создание объекта с помощью рефлексии
    public static Building createBuilding(int floorsCount, Class<? extends Building> buildingClass, int...spaceCount) {
        try {
            return buildingClass.getConstructor(Integer.TYPE, int[].class).newInstance(floorsCount, spaceCount);
        } catch (Exception exception) {
            throw new IllegalArgumentException();
        }
    }

    //Создание объекта с помощью фабрики
    public static Building createBuilding(Floor...floors) {
        return buildingFactory.createBuilding(floors);
    }

    //Создание объекта с помощью рефлексии
    public static Building createBuilding(Class<? extends Building> buildingClass, Floor...floors) {
        try {
            return buildingClass.getConstructor(Floor[].class).newInstance(floors);
        } catch (Exception exception) {
            throw new IllegalArgumentException();
        }
    }


    //Запись здания в байтовый поток
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
    }


    //Чтение здания из байтового потока (фабрика)
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

                spaces[j] = createSpace(spaceSquare, spaceRoomCount);
            }
            floors[i] = createFloor(spaces);
        }
        return createBuilding(floors);
    }

    //Чтение здания из байтового потока (рефлексия)
    public static Building inputBuilding(InputStream in,
                                         Class<? extends Building> buildingClass,
                                         Class<? extends Floor> floorClass,
                                         Class<? extends Space> spaceClass) throws IOException {
        DataInputStream dis = new DataInputStream(in);

        int floorCount = dis.readInt();
        Floor[] floors = new Floor[floorCount];

        for (int i = 0; i < floorCount; i++) {
            int floorSpacesCount = dis.readInt();
            Space[] spaces = new Space[floorSpacesCount];

            for (int j = 0; j < floorSpacesCount; j++) {
                int spaceSquare = dis.readInt();
                int spaceRoomCount = dis.readInt();

                spaces[j] = createSpace(spaceSquare, spaceRoomCount, spaceClass);
            }
            floors[i] = createFloor(floorClass, spaces);
        }
        return createBuilding(buildingClass, floors);
    }

    //Запись здания в символьный поток
    public static void writeBuilding(Building building, Writer out) throws IOException {
        int floorsCount = building.getBuildingFloorsCount();
        out.write(Integer.toString(floorsCount));
        out.write(" ");

        for (int i = 0; i < building.getBuildingFloorsCount(); i++) {
            Floor currentFloor = building.getBuildingFloorByNumber(i + 1);
            out.write(Integer.toString(currentFloor.getSpaceCount()));
            out.write(" ");
            for (int j = 0; j < currentFloor.getSpaceCount(); j++) {
                Space currentSpace = currentFloor.getSpaceByNumber(j + 1);
                int square = currentSpace.getSquare();
                int roomCount = currentSpace.getRoomCount();
                out.write(Integer.toString(square));
                out.write(" ");
                out.write(Integer.toString(roomCount));
                out.write(" ");
            }
        }
    }

    //Чтение здание из символьного потока (фабрика)
    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        st.nextToken();
        int floorCount = (int) st.nval;
        Floor[] floors = new Floor[floorCount];

        for (int i = 0; i < floorCount; i++) {
            st.nextToken();
            int currentFloorSpaceCount = (int) st.nval;
            Space[] spaces = new Space[currentFloorSpaceCount];
            for (int j = 0; j < currentFloorSpaceCount; j++) {
                st.nextToken();
                int square = (int) st.nval;
                st.nextToken();
                int roomCount = (int) st.nval;
                spaces[j] = createSpace(square, roomCount);
            }
            floors[i] = createFloor(spaces);
        }
        return createBuilding(floors);
    }

    //Чтение здание из символьного потока (рефлексия)
    public static Building readBuilding(Reader in,
                                        Class<? extends Building> buildingClass,
                                        Class<? extends Floor> floorClass,
                                        Class<? extends Space> spaceClass) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        st.nextToken();
        int floorCount = (int) st.nval;
        Floor[] floors = new Floor[floorCount];

        for (int i = 0; i < floorCount; i++) {
            st.nextToken();
            int currentFloorSpaceCount = (int) st.nval;
            Space[] spaces = new Space[currentFloorSpaceCount];
            for (int j = 0; j < currentFloorSpaceCount; j++) {
                st.nextToken();
                int square = (int) st.nval;
                st.nextToken();
                int roomCount = (int) st.nval;
                spaces[j] = createSpace(square, roomCount, spaceClass);
            }
            floors[i] = createFloor(floorClass, spaces);
        }
        return createBuilding(buildingClass, floors);
    }

    //Сериализация здания
    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(building);
        oos.close();
    }

    //Десериализация здания
    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        Building readBuilding = (Building) ois.readObject();
        return readBuilding;
    }


    //Сортировка по возрастанию с Comparable <T>
    public static <T extends Comparable<T>> void sortRising(T[] objects) {
        for (int i = 0; i < objects.length - 1; i++) {
            for (int j = 0; j < objects.length - i - 1; j++) {
                if (objects[j].compareTo(objects[j + 1]) > 0) {
                    T tempObject = objects[j];
                    objects[j] = objects[j + 1];
                    objects[j + 1] = tempObject;
                }
            }
        }
    }

    //Сортировка с критерием через Comparator <T>
    public static <T> void sortDecreasing(T[] objects, Comparator<T> comparator) {
        for (int i = 0; i < objects.length - 1; i++) {
            for (int j = 0; j < objects.length - i - 1; j++) {
                if (comparator.compare(objects[j], objects[j + 1]) > 0) {
                    T tempObject = objects[j];
                    objects[j] = objects[j + 1];
                    objects[j + 1] = tempObject;
                }
            }
        }
    }

    //Созданание этажа, безопасного с точки зрения многопоточности
    public static Floor synchronizedFloor(Floor floor) {
        return new SynchronizedFloor(floor);
    }
}
