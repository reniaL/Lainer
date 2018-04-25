package domain;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("customerFilter")
public class Customer {
    
    private int id;
    private String name;
    private String gender;
    private int age;
    
    public static class CustomerBuilder {
        
        private int id;
        private String name;
        private String gender;
        private int age;
        
        public CustomerBuilder id(int id) {
            this.id = id;
            return this;
        }
        
        public CustomerBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        public CustomerBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }
        
        public CustomerBuilder age(int age) {
            this.age = age;
            return this;
        }
        
        public Customer build() {
            return new Customer(this);
        }
    }
    
    private Customer(CustomerBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.gender = builder.gender;
        this.age = builder.age;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String toString() {
        return String.format("%d: %s, %s, %d", id, name, gender, age);
    }
}
