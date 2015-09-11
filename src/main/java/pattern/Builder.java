package pattern;

import domain.Customer;

public class Builder {
    
    public static void main(String[] args) {
        Customer u = new Customer.CustomerBuilder().id(100).name("Terry").gender("Male").age(20).build();
        System.out.println(u);
    }
    
}
