package main;

import buildings.*;
import buildings.dwelling.*;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.officeBuilding.*;

import java.io.*;


public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, IOException {

        Space firstFlat = new Flat(1, 3);
        Space secondFlat = new Flat(345, 1);
        Space thirdOffice = new Flat(500, 3);
        Space fourthOffice = new Office(34, 4);

        DwellingFloor first = new DwellingFloor(new Space[]{secondFlat, fourthOffice});
        DwellingFloor second = new DwellingFloor(new Space[]{thirdOffice});

        HotelFloor df = new HotelFloor(new Space[]{firstFlat, thirdOffice});
        Building building = new Dwelling(new Floor[]{first});


        System.out.println(building.toString());

        try (Reader in = new FileReader("test.txt")) {
            Building tb = Buildings.readBuilding(in);
            System.out.println(tb.toString());
        } catch (IOException exception) {
            System.out.println("Error");
        }




        /*try (FileOutputStream fos = new FileOutputStream("building.bin")){
            Buildings.outputBuilding(fBuilding, fos);
        }*/


        /*try(FileInputStream fis = new FileInputStream("building.bin")){
            Building testBuilding = Buildings.inputBuilding(fis);
            System.out.println(fBuilding.toString());
            System.out.println(testBuilding.toString());
        }*/





        /*HotelFloor secondFloor = new HotelFloor(new Space[]{fourthOffice, thirdOffice});
        HotelFloor hotelFloor = new HotelFloor(new Space[]{firstFlat, secondFlat});
        hotelFloor.setStarCount(2);
        secondFloor.setStarCount(3);

        //System.out.println(hotelFloor.toString());
        Hotel hotelBuilding = new Hotel(new Floor[]{secondFloor, hotelFloor, firstFloor});
        System.out.println(hotelBuilding.toString());*/






        /*try (FileOutputStream fos = new FileOutputStream("building.bin")) {
            Buildings.serializeBuilding(fBuilding, fos);
        } catch (IOException exception) {
            System.out.println("Error");
        }

        try (FileInputStream fis = new FileInputStream("building.bin")) {
            Building readBuilding = Buildings.deserializeBuilding(fis);
            System.out.println(readBuilding.toString());
        } catch (IOException exception) {
            System.out.println("Error");
        } catch (ClassNotFoundException exception) {
            System.out.println("Error");
        }*/









        /*System.out.println(fBuilding.toString());
        Floor test = fBuilding.clone();
        System.out.println(test.toString());
        System.out.println(fBuilding.equals(test));*/


        //System.out.println(firstFloor.equals(secondFloor));
        /*System.out.println(fBuilding.equals(sBuilding));
        System.out.println(fBuilding.hashCode());*/


        /*System.out.println(firstFlat.hashCode());
        System.out.println(thirdOffice.hashCode());
        System.out.println(firstFlat.equals(thirdOffice));*/



        /*try{
            OutputStream out = new DataOutputStream(new FileOutputStream("out.bin"));
            Buildings.outputBuilding(fBuilding, out);
        }
        catch (FileNotFoundException exception){
            System.out.println("File not found");
        }
        catch (IOException exception){
            System.out.println("Smth went wrong");
        }*/


    }
}
