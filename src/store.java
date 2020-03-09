
import java.util.*;

// Store class, models the store and possesses inventory, customer lists, and rental lists
// Also contains all the methods responsible for simulating store activity
public class store {
    private Vector<car> stock = new Vector<>();
    private Vector<car> rented = new Vector<>();
    private Vector<customer> init_customers = new Vector<>();
    private Vector<customer> customers = new Vector<>();
    private Vector<rental> rentals = new Vector<>();
    private Vector<rental> comp_rentals = new Vector<>();
    private Vector<rental> rental_archive = new Vector<>();

    // Factory pattern objects for instantiating cars and customers
    private carFactory factory = new carFactory();
    private customerFactory custFact = new customerFactory();

    // Customer name arrays
    String[] nameC = new String[]{"Carly", "Caroline", "Corrie", "Craig"};
    String[] nameB = new String[]{"Brad", "Blake", "Bernard", "Beatrix"};
    String[] nameR = new String[]{"Randy", "Ricky", "Riley", "Ralph"};

    // Location identifier, income in a day, and total income over time
    String location;
    private float income_per_day;
    private float total_money;

    // Constructor initializes store name and sets initial income and total money to 0
    public store(String title){
        location = title;
        income_per_day = 0;
        total_money = 0;
    }

    // Methods to print daily and total income
    public void print_daily_income(){
        System.out.println("Total Daily Income: " + "$" + income_per_day);
    }
    public void print_total_income(){
        System.out.println("Total Income: " + "$" + total_money);
    }

    // Method to print every car in inventory with its category and plate
    public void print_inventory(){
        System.out.println("--------------------------  Store Inventory  --------------------------\n");
        System.out.println("Total Cars in Inventory: " + stock.size());
        System.out.println("\nCar Type | License Plate\n");
        for(int i = 0; i < stock.size(); i++){
            System.out.println(stock.get(i).category+ "      -     " + stock.get(i).plate);
        }
    }

    public float get_total(){
        return total_money;
    }

    public float get_daily(){
        return income_per_day;
    }

    // Method to iterate through rental archive and categorize total rentals by customer type, printing totals of each type
    public void total_sales_breakdown(){
        int bus = 0;
        int cas = 0;
        int reg = 0;
        int total_rentals = rental_archive.size();

        for(int i = 0; i < total_rentals; i++){
            if(rental_archive.get(i).renter.type.equals("Business")){
                bus += 1;
            }
            else if(rental_archive.get(i).renter.type.equals("Regular")){
                reg += 1;
            }
            else{
                cas += 1;
            }
        }

        System.out.println("--------------------------  Total Sales Breakdown  --------------------------\n");
        System.out.println("Total Sales During Period: " + total_rentals);
        System.out.println("Total Business Customer Rentals: " + bus);
        System.out.println("Total Regular Customer Rentals: " + reg);
        System.out.println("Total Casual Customer Rentals: " + cas);
    }


    // Getter method to get the size of current inventory
    public int get_inventory_size(){
        return stock.size();
    }

    // Getter method to get the size of the customers vector after loading them in
    public int get_init_cust_size(){
        return init_customers.size();
    }

    // Method to print every rental that is active, with the renter name and type, and the license plate for each car rented
    public void print_rentals(){
        System.out.println("--------------------------  Active Rentals  --------------------------\n");
        System.out.println("Total Active Rentals: " + (25 - stock.size()));
        for(int i = 0; i < rentals.size(); i++){
                System.out.println("\nRenter Name: " +rentals.get(i).renter.name + " | Renter Type: " + rentals.get(i).renter.type);
                for(int j = 0; j < rentals.get(i).rental_car.length; j++){
                    System.out.println( "Rental Car Plate: "+rentals.get(i).rental_car[j].plate);
                }
        }
    }

    // Method to print every rental that has already been completed, with car, plate, renter, number of days, all add ons, and total fee
    public void print_comp_rentals() {
        System.out.println("--------------------------  Completed Rentals  --------------------------\n");
        System.out.println("Total Completed Sales: " + comp_rentals.size());
        for (int i = 0; i < comp_rentals.size(); i++) {
            System.out.println("\nRenter Name: " + comp_rentals.get(i).renter.name + " | Renter Type: " + comp_rentals.get(i).renter.type);
            for (int j = 0; j < comp_rentals.get(i).rental_car.length; j++) {
                System.out.println("Rental Car Plate: " + comp_rentals.get(i).rental_car[j].plate);
                System.out.println("Length of Rental: " + comp_rentals.get(i).days_rented + " days");
                System.out.println("Options Chosen: " + comp_rentals.get(i).num_car_seats + " Car Seats, " + comp_rentals.get(i).gps + " GPS, " + comp_rentals.get(i).sat_rad + " Satellite Radio");
                System.out.println("Total Fee: $" + comp_rentals.get(i).total_fee);
            }
        }
    }

    // Method responsible for returning all of the cars at the beginning of each day, and adding the cars back into the inventory vector
    // Also adds the rental slip to te completed rentals vector
    public void return_cars(){
        for(int i = 0; i < rentals.size(); i++){
            if(rentals.get(i).days_rented == 0){
                for(int j = 0; j < rentals.get(i).rental_car.length; j++){
                    for(int k = 0; k < rented.size(); k++){
                        if(rentals.get(i).rental_car[j].plate.equals(rented.get(k).plate)) {
                            car returned = rented.remove(k);
                            stock.add(returned);
                            break;
                        }
                    }
                    if(rented.size() == 0){
                        break;
                    }
                }
                rental completed = rentals.remove(i);
                comp_rentals.add(completed);
            }
        }
    }

    // Method responsible for subtracting from the days remaining on all active rentals, and wiping the selected customers vector so a new group
    // of random customers can be chosen the following day. Also wipes the completed rentals vector so that each day we can get the
    // rentals completed for that day as opposed to all the completed rentals ever.
    public void day_over(){
        total_money += income_per_day;
        income_per_day = 0;
        for(int i = 0; i < rentals.size(); i++){
            rentals.get(i).days_rented -= 1;
        }
        customers = new Vector<>();
        for(int i = 0; i < comp_rentals.size(); i++){
            rental_archive.add(comp_rentals.get(i));
        }
        comp_rentals = new Vector<>();
    }

    // Rent Method
    // Method responsible for renting a car to a customer
    public void rent(customer renter){
        int days;
        int cars;

        // Check the renter type and set the option values according to the random numbers
        if(renter.type.equals("Business")){
            days = 7;
            cars = 3;
        }
        else if(renter.type.equals("Regular")){
            Random num_cars = new Random();
            Random num_days = new Random();
            cars = num_cars.nextInt((renter.car_max - renter.car_min) + 1) + 1;
            days = num_days.nextInt((renter.rent_max - renter.rent_min)) + 1;
        }
        else{
            Random num_days = new Random();
            days = num_days.nextInt((renter.rent_max - renter.rent_min)) + 1;
            cars = 1;
        }

        // Add the selected cars to the rented vector
        car[] selected = new car[cars];
        for(int i = 0; i < selected.length; i++){
            if(stock.size() < cars){
                return;
            }

            car selected1 = stock.remove(0);
            rented.add(selected1);
            selected[i] = selected1;
        }


        // Generate random numbers for the options
        Random num_cs = new Random();
        Random num_gps = new Random();
        Random num_sat = new Random();

        int cs = num_cs.nextInt(5);
        int gps = num_gps.nextInt(2);
        int sat = num_sat.nextInt(2);

        // Send the rental to the observer so it can log the rental
        log_rental(renter, selected, days, cs, gps, sat);
    }


    // Method responsible for renting a car to each customer that comes into the store for the day
    public void start_day(){
        for(int i = 0; i < customers.size(); i++){
            if(stock.size() != 0){
                rent(customers.get(i));
            }
        }
    }

    // Method responsible for choosing a random pool of customers to enter the rental dealership each day
    public void get_daily_renters(){
        Random rand = new Random();
        int num_customers = rand.nextInt(init_customers.size()) + 1;

        Integer[] ints = {0,1,2,3,4,5,6,7,8,9,10,11};
        List<Integer> idxs = Arrays.asList(ints);

        Collections.shuffle(idxs);

        for(int i = 0; i < num_customers; i++){
            if(init_customers.get(idxs.get(i)).type.equals("Business") && stock.size() < 3){
                continue;
            }
            else{
                customers.add(init_customers.get(idxs.get(i)));
            }
        }
    }

    // Observer method to watch when a rent is made.
    // When a rent is made, add the money from the sale to the stores income, and subtract from the number of cars that renter can rent
    public void log_rental(customer renter, car[] rental_car, int days,int cs,int sat, int gps){
        rental rent_log =  new rental(renter,rental_car, days, cs, sat, gps);
        income_per_day += rent_log.total_fee;
        rentals.add(rent_log);
        renter.cars_rented += renter.car_max;
    }

    // Method responsible for instantiating all the cars in the inventory based on the number of cars per category
    public void load_inventory(int num_per_cat){
        String pre = "00";
        for(int i = 1; i <= num_per_cat; i++){
            String identifier = Integer.toString(i);
            String model = pre+identifier;
            car vehicle = factory.createCars("SUV", model);
            stock.add(vehicle);
        }
        for(int i = 1; i <= num_per_cat; i++){
            String identifier = Integer.toString(i);
            String model = pre+identifier;
            car vehicle = factory.createCars("LUX", model);
            stock.add(vehicle);
        }
        for(int i = 1; i <= num_per_cat; i++){
            String identifier = Integer.toString(i);
            String model = pre+identifier;
            car vehicle = factory.createCars("STD", model);
            stock.add(vehicle);
        }
        for(int i = 1; i <= num_per_cat; i++){
            String identifier = Integer.toString(i);
            String model = pre+identifier;
            car vehicle = factory.createCars("VAN", model);
            stock.add(vehicle);
        }
        for(int i = 1; i <= num_per_cat; i++){
            String identifier = Integer.toString(i);
            String model = pre+identifier;
            car vehicle = factory.createCars("ECO", model);
            stock.add(vehicle);
        }
    }

    // Method responsible for instantiating each customer prior to simulation
    public void load_customers(int num_per_cat){
        for(int i = 0; i < num_per_cat;i++){
            customer cust = custFact.createCustomers("Casual", nameC[i]);
            init_customers.add(cust);
        }
        for(int i = 0; i < num_per_cat;i++){
            customer cust = custFact.createCustomers("Business", nameB[i]);
            init_customers.add(cust);
        }
        for(int i = 0; i < num_per_cat;i++){
            customer cust = custFact.createCustomers("Regular", nameR[i]);
            init_customers.add(cust);
        }
    }
}
