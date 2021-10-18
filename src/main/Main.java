package main;

import buildings.*;
import buildings.dwelling.*;
import buildings.officeBuilding.*;
import buildings.threads.*;

import java.io.*;
import java.util.Comparator;


public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, InterruptedException {

        Space firstFlat = new Flat(1, 3);
        Space secondFlat = new Flat(345, 1);
        Space thirdOffice = new Flat(500, 3);
        Space fourthOffice = new Office(34, 4);


        Space[] spaces = new Space[]{thirdOffice, fourthOffice, secondFlat, firstFlat};
        Space[] spaces1 = new Space[]{firstFlat};
        Space[] spaces2 = new Space[]{secondFlat, firstFlat};
        Space[] spaces3 = new Space[]{thirdOffice, fourthOffice, secondFlat};


        Floor first = new DwellingFloor(spaces);
        Floor second = new DwellingFloor(spaces1);
        Floor third = new DwellingFloor(spaces2);
        Floor fourth = new DwellingFloor(spaces3);

        Building test = new Dwelling(new Floor[]{first, fourth, third, second});


        Semaphore sem = new Semaphore();
        Thread sequentialRepairerThread = new Thread(new SequentalRepairer(first, sem));
        Thread sequentialCleanerThread = new Thread(new SequentalCleaner(first, sem));

        sequentialRepairerThread.start();
        sequentialCleanerThread.start();



        /*SpaceComparator spaceComparator = new SpaceComparator();
        FloorComparator floorComparator = new FloorComparator();

        System.out.println("Before");
        System.out.println(test.toString());
        System.out.println("After");
        Buildings.sortDecreasing(test.getFloors(), floorComparator);
        System.out.println(test.toString());

        System.out.println("Before");
        System.out.println(first.toString());
        System.out.println("After");
        Buildings.comparatorSortSpaces(first.getSpaces(), spaceComparator);
        System.out.println(first.toString());*/



/*        System.out.println("Before");
        System.out.println(first.toString());
        System.out.println("After");
        Buildings.sortRising(first.getSpaces());
        System.out.println(first.toString());*/

        /*System.out.println("Before");
        System.out.println(test.toString());
        System.out.println("After");
        Buildings.sortRising(test.getFloors());
        System.out.println(test.toString());*/






        /*OfficeFloor first = new OfficeFloor(new Space[]{secondFlat, fourthOffice, thirdOffice});
        DwellingFloor dw = new DwellingFloor(new Space[]{firstFlat, secondFlat, fourthOffice});

        OfficeBuilding building = new OfficeBuilding(new Floor[]{first, dw});
        Iterator iterator = building.getIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }*/





        /*DwellingFloor second = new DwellingFloor(new Space[]{thirdOffice});

        HotelFloor df = new HotelFloor(new Space[]{firstFlat, thirdOffice});
        Building building = new Dwelling(new Floor[]{first});


        System.out.println(building.toString());

        try (Reader in = new FileReader("test.txt")) {
            Building tb = Buildings.readBuilding(in);
            System.out.println(tb.toString());
        } catch (IOException exception) {
            System.out.println("Error");
        }*/




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
