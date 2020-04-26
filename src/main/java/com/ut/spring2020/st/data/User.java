package com.ut.spring2020.st.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-26, Sun
 * webautomation
 **/

public class User {
    private String name;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String email;

    public User() {
        this.name = "";
        this.address = "";
        this.city = "";
        this.state = "";
        this.phone = "";
        this.email = "";
    }

    public User(String name, String address, String city, String state, String phone, String email) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.email = email;
    }

    public static User createTestUser() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
        Date date = new Date();
        String strDate = dateFormat.format(date).toString();
        User user = new User();
        user.setName("TestUser-"+strDate);
        Random rand = new Random();
        // Generate random integers in range 0 to 5000
        int rand_int1 = rand.nextInt(5000);
        user.setAddress(rand_int1 + ",Test Street");
        user.setCity("Austin");
        user.setState("TX");
        user.setPhone("123-456-7890");
        user.setEmail("lorum@ipsum.com");
        return user;
    }

    public static User updateTestUser(User user){
        user.setName("Edited-"+user.getName());
        user.setAddress("Edited-"+user.getAddress());
        user.setCity("Edited-"+user.getCity());
        user.setState("Edited-"+user.getState());
        user.setPhone("999-888-7777");
        user.setEmail("edited@user.com");
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
