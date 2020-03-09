// Simple factory pattern method for instantiating all of the cars by their  type
public class carFactory{
    public car createCars(String type, String model){
        if(type.equals("SUV")){
            car vehicle = new suv(model);
            return vehicle;
        }
        else if(type.equals("LUX")){
            car vehicle = new luxury(model);
            return vehicle;
        }
        else if(type.equals("STD")){
            car vehicle = new standard(model);
            return vehicle;
        }
        else if(type.equals("ECO")){
            car vehicle = new economy(model);
            return vehicle;
        }
        else if(type.equals("VAN")){
            car vehicle = new minivan(model);
            return vehicle;
        }
        return null;
    }
}
