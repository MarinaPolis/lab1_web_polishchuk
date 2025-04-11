package com.example.hospital.model;

import java.util.Objects;

public class Patient {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    public Patient() {}
    public Patient(String firstName, String lastName, int age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    // Геттери і сеттери
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return age == patient.age &&
                Objects.equals(firstName, patient.firstName) &&
                Objects.equals(lastName, patient.lastName) &&
                Objects.equals(gender, patient.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, gender);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " - Вік: " + age + ", Стать: " + gender;
    }
}
