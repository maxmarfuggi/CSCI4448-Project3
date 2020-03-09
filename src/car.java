// Abstract class to represent each car type
// Strategy pattern to help with the instantiation of cars through the type
abstract class car {
    String plate;
    String category;
    int days_left_on_rental;
    float price;
    Boolean status;

    public car(){
        status = false;
        category = "NA";
        days_left_on_rental = -1;
    }
}

// SUV type
class suv extends car {
    public suv(String model){
        category = "SUV";
        price = 150;
        plate = category + model;
    }
}

// Economy type
class economy extends car {
    public economy(String model){
        category = "ECO";
        price = 50;
        plate = category + model;
    }
}

// Luxury type
class luxury extends car {
    public luxury(String model){
        category = "LUX";
        price = 250;
        plate = category + model;
    }
}

// Minivan type
class minivan extends car{
    public minivan(String model){
        category = "VAN";
        price = 125;
        plate = category + model;
    }
}

// Standard type
class standard extends car{
    public standard(String model){
        category = "STD";
        price = 75;
        plate = category + model;
    }
}


