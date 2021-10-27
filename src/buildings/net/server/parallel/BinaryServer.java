package buildings.net.server.parallel;

import interfaces.Building;
import buildings.Buildings;
import exceptions.BuildingUnderArrestException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BinaryServer {

    static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static class Server implements Runnable {
        private Socket clientSocket;

        public Server(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                DataOutputStream dos = new DataOutputStream(this.clientSocket.getOutputStream());
                DataInputStream dis = new DataInputStream(this.clientSocket.getInputStream());
                try {
                    while (!clientSocket.isClosed()) {
                        String buildingType = dis.readUTF();
                        System.out.println("Сервер получил тип здания " + buildingType);
                        Building building = Buildings.inputBuilding(dis);
                        System.out.println("Сервер получил здание " + building);
                        int buildingCost = buildingCost(building, buildingType);
                        System.out.println("Сервер рассчитал стоимость здания " + buildingCost);
                        dos.writeInt(buildingCost);
                        dos.flush();
                        System.out.println("Сервер отправил стоимость здания ");
                    }
                } catch (EOFException exception) {
                    exception.printStackTrace();
                }
                dos.close();
                dis.close();
                this.clientSocket.close();
                System.out.println("Сервер завершил свою работу");
            } catch (IOException | BuildingUnderArrestException exception) {
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
        ServerSocket server = new ServerSocket(4444);

        while (!server.isClosed()) {
            Socket client = server.accept();
            executor.execute(new Server(client));
        }
        executor.shutdown();
    }
}
