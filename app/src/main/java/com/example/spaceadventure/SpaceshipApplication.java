package com.example.spaceadventure;

public class SpaceshipApplication {

    int getId() {
        return id;
    }

    private int id;

    String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String phoneNumber;
    private String postalCode;
    private String password;

    public SpaceshipApplication(int id, String email, String phoneNumber, String postalCode, String password) {

        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.password = password;
    }

    @Override
    public String toString() {
        return  "ID = " + id +
                "\t|\tEmail = " + email +
                "\t|\tPhone Number = " + phoneNumber +
                "\t|\tPostal Code = " + postalCode;
    }
}
