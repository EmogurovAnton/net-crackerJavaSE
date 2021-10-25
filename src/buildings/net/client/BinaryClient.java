package buildings.net.client;

import buildings.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class BinaryClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket client = null;
        String buildingInfoPath = "src/buildings/net/BuildingInformation";
        String buildingTypePath = "src/buildings/net/BuildingType";
        String buildingCost = "src/buildings/net/BuildingCost.txt";

        try {
            client = new Socket("localhost", 4444);
            System.out.println("Клиент начал соединение");
        } catch (UnknownHostException exception) {
            System.err.println("Don't know about 'localhost'");
            System.exit(1);
        }

        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        FileOutputStream fos = new FileOutputStream(buildingCost);
        PrintStream writeCostToFile = new PrintStream(fos);
        Scanner typeReader = new Scanner(new File(buildingTypePath));
        Reader dataReader = new FileReader(buildingInfoPath);

        while (typeReader.hasNext() && !client.isOutputShutdown()) {
            String buildingType = typeReader.next();
            switch (buildingType) {
                case "Dwelling":
                    Buildings.setBuildingFactory(new DwellingFactory());
                    break;
                case "OfficeBuilding":
                    Buildings.setBuildingFactory(new OfficeFactory());
                    break;
                case "Hotel":
                    Buildings.setBuildingFactory(new HotelFactory());
                    break;
            }
            dos.writeUTF(buildingType);
            System.out.println("Клиент отправил тип здания: " + buildingType);
            Building building = Buildings.readBuilding(dataReader);
            Buildings.outputBuilding(building, dos);
            System.out.println("Клиент отправил здание: " + building);
            dos.flush();
            Thread.sleep(1000);
            int estimatedCost = dis.readInt();
            fos.write(estimatedCost);
            System.out.println("Клиент получил стоимость здания " + estimatedCost);

        }
        dis.close();
        dos.close();
        fos.close();
        writeCostToFile.close();
        client.close();
        System.out.println("Клиент завершил работу");
    }
}
