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
        Space secondFlat = new Flat(34, 1);
        Space thirdOffice = new Office(1, 12);
        Space fourthOffice = new Office(34, 4);


        Floor firstFloor = new DwellingFloor(new Space[]{firstFlat, fourthOffice});


        //Building fBuilding = new OfficeBuilding(new Floor[]{firstFloor, secondFloor});

        HotelFloor secondFloor = new HotelFloor(new Space[]{fourthOffice, thirdOffice});
        HotelFloor hotelFloor = new HotelFloor(new Space[]{firstFlat, secondFlat});
        hotelFloor.setStarCount(2);
        secondFloor.setStarCount(3);

        //System.out.println(hotelFloor.toString());
        Hotel hotelBuilding = new Hotel(new Floor[]{secondFloor, hotelFloor, firstFloor});
        System.out.println(hotelBuilding.toString());






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
