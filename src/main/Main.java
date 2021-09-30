package main;

import buildings.*;
import buildings.dwelling.*;
import buildings.officeBuilding.*;
import myList.MyList;

import java.io.*;


public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, IOException {

        Space firstFlat = new Flat(1143, 3);
        Space secondFlat = new Flat(34, 1);
        Space thirdOffice = new Office(231, 12);
        Space fourthOffice = new Office(1444, 4);


        Floor firstFloor = new DwellingFloor(new Space[]{firstFlat, fourthOffice});
        Floor secondFloor = new OfficeFloor(new Space[]{secondFlat, thirdOffice});

        Building fBuilding = new OfficeBuilding(new Floor[]{firstFloor, secondFloor});
        Building sBuilding = new Dwelling(new Floor[]{secondFloor});

        try (FileOutputStream fos = new FileOutputStream("test.txt")) {
            Buildings.outputBuilding(fBuilding, fos);
        } catch (IOException exception) {
            System.out.println("Error");
        }







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
