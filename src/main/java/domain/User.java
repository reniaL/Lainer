package domain;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import domain.Customer.CustomerBuilder;

/**
 * @author reniaL
 */
public class User {
    
    protected Log log = LogFactory.getLog(this.getClass());
    
    private int id;
    
    private String name;
    
    private String gender;
    
    private int age;
    
    private Date birthday;
    
    private Date posttime;
    
    private Customer customer;
    
    public User() {
    }
    
    public User(String name, String gender, int age) {
        log.info("creating a user: " + name);
        setName(name);
        setGender(gender);
        setAge(age);
        this.customer = new CustomerBuilder().id(5).age(11).name("cc").build();
        System.out.println(customer);
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
        log.info("getting age");
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public Date getPosttime() {
        return posttime;
    }
    
    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
}
