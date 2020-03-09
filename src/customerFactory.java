// Simple factory pattern for instantiating customers by their given type
public class customerFactory {
    public customer createCustomers(String type1, String name){
        customer cust;
        switch (type1) {
            case "Casual": {
                cust = new casual(name);
                return cust;
            }
            case "Business": {
                cust = new business(name);
                return cust;
            }
            case "Regular": {
                cust = new regular(name);
                return cust;
            }
        }
        return null;
    }
}
