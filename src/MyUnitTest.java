import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit test class
public class MyUnitTest {
    // Test that car loading function loads the correct number of cars
    @Test
    public void inventory_size(int size) {
        int tot_inv = size * 5;

        store store1 = new store("test");
        store1.load_inventory(size);

        assertEquals(store1.get_inventory_size(), tot_inv);
    }

    //Test the customer loading function loads the correct number of customers
    @Test
    public void customer_size(int size){
        int tot_cust = size*3;

        store store1 = new store("test");
        store1.load_customers(size);

        assertEquals(store1.get_init_cust_size(), tot_cust);
    }

}
