
// Customer  class responsible for modeling each type of customer
// Holds the max and min rental lengths as well as the max and min number of cars that can be rented
abstract class customer {
    String type;
    String name;
    int cars_rented;
    int car_max;
    int car_min;
    int rent_min;
    int rent_max;
}

// Business customer derived class
class business extends customer {
    public business(String name1){
        type = "Business";
        name = name1;
        rent_min = 7;
        rent_max = 7;
        cars_rented = 0;
        car_max = 3;
    }
}

// Casual customer derived class
class casual extends customer{
    public casual(String name1){
        type = "Casual";
        name = name1;
        rent_min = 1;
        rent_max = 3;
        cars_rented = 0;
        car_max = 1;
    }
}


// Regular customer derived class
class regular extends customer {
    public regular(String name1){
        type = "Regular";
        name = name1;
        rent_min = 3;
        rent_max = 5;
        cars_rented = 0;
        car_max = 3;
        car_min = 1;
    }
}


