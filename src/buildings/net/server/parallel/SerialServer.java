package buildings.net.server.parallel;

import interfaces.Building;
import exceptions.BuildingUnderArrestException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerialServer {
    static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static class Server implements Runnable {
        private Socket client;

        public Server(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream ois = new ObjectInputStream(this.client.getInputStream());
                DataInputStream dis = new DataInputStream(this.client.getInputStream());
                DataOutputStream dos = new DataOutputStream(this.client.getOutputStream());

                try {
                    while (!this.client.isClosed()) {
                        String buildingType = dis.readUTF();
                        System.out.println("Сервер получил тип здания: " + buildingType);
                        Building building = (Building) ois.readObject();
                        System.out.println("Сервер получил здание: " + building);
                        int buildingCost = buildingCost(building, buildingType);
                        System.out.println("Сервер рассчитал стоимость здания: " + buildingCost);
                        dos.writeInt(buildingCost);
                        System.out.println("Сервер отправил стоимость здания ");
                    }
                } catch (EOFException exception) {
                    System.out.println(exception.getStackTrace());
                }
                ois.close();
                dis.close();
                dos.close();
                this.client.close();
                System.out.println("Сервер завершил свою работу");
            } catch (IOException | ClassNotFoundException | BuildingUnderArrestException exception) {
                exception.printStackTrace();
            }
        }
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

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4444);

        while (!serverSocket.isClosed()) {
            Socket client = serverSocket.accept();
            executor.execute(new Server(client));
        }
        serverSocket.close();
        executor.shutdown();
    }
}
