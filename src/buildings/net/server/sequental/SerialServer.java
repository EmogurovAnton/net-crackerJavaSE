package buildings.net.server.sequental;

import buildings.Building;
import buildings.Buildings;
import exceptions.BuildingUnderArrestException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class SerialServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(4444);
        Socket clientSocket = serverSocket.accept();
        System.out.println("Сервер успешно запустился и установил соединение");

        DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

        try {
            while (!clientSocket.isClosed()) {
                String buildingType = dis.readUTF();
                System.out.println("Сервер получил тип здания " + buildingType);
                Building building = (Building) ois.readObject();
                int buildingCost = buildingCost(building, buildingType);
                dos.writeInt(buildingCost);
                dos.flush();
                System.out.println("Сервер отправил на клиент стоимость здания " + buildingCost);
            }
        } catch (EOFException | BuildingUnderArrestException | SocketException exception) {
            System.out.println(exception.getStackTrace());
        }
        dos.close();
        ois.close();
        dis.close();
        clientSocket.close();
        serverSocket.close();
        System.out.println("Сервер завершил свою работу ");
    }


    private static int buildingCost(Building building, String buildingType) throws BuildingUnderArrestException {
        if (isArrested()) {
            throw new BuildingUnderArrestException("Здание находится под арестом");
        }

        int buildingCost = 0;
        int buildingArea = building.getAllBuildingSpacesSquare();

        switch (buildingType) {
            case "Dwelling":
                buildingCost = buildingArea * 1000;
                break;

            case "OfficeBuilding":
                buildingCost = buildingArea * 1500;
                break;

            case "Hotel":
                buildingCost = buildingArea * 2000;
                break;
        }

        return buildingCost;
    }

    private static boolean isArrested() {
        Random rnd = new Random();
        int arrestedValue = rnd.nextInt(11) + 1;
        return arrestedValue == 0;
    }
}

