package com.BankApi.Entity;

import com.BankApi.Role;

import java.util.Objects;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class User {
    long id;
    String phone;
    String password;
    String firstName;
    String lastName;
    Role role;


    public User(String phone,String password, String firstName, String lastName,
                 Role role) {
        this.phone = phone;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(long id,String phone,String password, String firstName, String lastName,
                  Role role) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && phone.equals(user.phone) && password.equals(user.password) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, password, firstName, lastName, role);

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}