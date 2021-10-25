package exceptions;

public class BuildingUnderArrestException extends Exception{

    public BuildingUnderArrestException(String message){
        System.err.println(message);
    }
}
