// Max Marfuggi
// CSCI 4448

// Main method for simulation of rental dealership

public class main {
    public static void main(String[] args){
        // Number of cars per category and number of customers per type
        int car_cat_size = 5;
        int cust_cat_size = 4;

        // Unit test object
        MyUnitTest test = new MyUnitTest();

        // Create store
        store store1 = new store("Boulder");

        // Junit tests to check for correct customer and inventory loading
        test.inventory_size(car_cat_size);
        System.out.println("Stock loading successful...\n");

        test.customer_size(cust_cat_size);
        System.out.println("Customer loading successful...\n");

        // Loading customers and car inventory
        store1.load_inventory(car_cat_size);
        store1.load_customers(cust_cat_size);

        // Checking total money sum

        System.out.println("Rental system check is successful...\n");
        System.out.println("\n");

        // Main simulation loop
        // Store will first return all of the cars, then get a random pool of customers, then rent to all those customers
        // Then the methods will print all of the active and completed rentals, along with the income per day
        // Then call day over to decrement rental days
        for(int i = 0; i < 35; i++){
            System.out.println("\n\n--------------------------  Day " + (i+1) +" --------------------------\n");

            store1.return_cars();
            store1.get_daily_renters();
            store1.start_day();
            store1.print_comp_rentals();
            store1.print_rentals();
            store1.print_inventory();
            store1.print_daily_income();
            store1.day_over();
        }

        // Print total sales breakdown and total income
        store1.total_sales_breakdown();
        store1.print_total_income();
    }
}
