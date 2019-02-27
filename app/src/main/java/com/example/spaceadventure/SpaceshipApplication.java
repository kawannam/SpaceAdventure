package com.example.spaceadventure;

public class SpaceshipApplication {

    int id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String email;
    String phoneNumber;
    String postalCode;
    String password;

    public SpaceshipApplication(int id, String email, String phoneNumber, String postalCode, String password) {

        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.password = password;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", password='" + password + '\'';
    }
}
