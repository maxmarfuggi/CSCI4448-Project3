
// Observer method
// Rental Class
// Class designed to model one rental, uses observer to watch for when a rental happens
// When it does, rental class is used to store the rental and update system states
public class rental {
    car[] rental_car;
    customer renter;

    int days_rented;
    float total_fee;
    int num_car_seats;
    int sat_rad;
    int gps;
    float gps_fee = 25;
    float sat_fee = 40;
    float cs_fee = 10;


    // Observer pattern method for logging a rental when one occurs
    // Takes in who is renting the car, the car they are renting, and the settings for additional options/days
    public rental(customer renter1, car[] rental_car1, int days,int cs1,int sat1, int gps1){
        renter = renter1;
        rental_car = rental_car1;
        num_car_seats = cs1;
        sat_rad = sat1;
        gps = gps1;
        days_rented = days;


        for(int i = 0; i < rental_car1.length; i++){
            total_fee += days_rented*rental_car1[i].price + num_car_seats*cs_fee + sat_rad*sat_fee + gps*gps_fee;
        }
    }
}
