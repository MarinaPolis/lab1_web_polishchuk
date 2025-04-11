package com.example.hospital.model;

public class Appointment {
    private Patient patient;
    private Doctor doctor;  // Лікар, який приймає пацієнта
    private String time;    // Час прийому
    public Appointment() {}
    // Конструктор
    public Appointment(Patient patient, Doctor doctor, String time) {
        this.patient = patient;
        this.doctor = doctor;
        this.time = time;
    }

    // Геттери та сеттери
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Пацієнт: " + patient.getFirstName() + " " + patient.getLastName() +
                ", Лікар: " + doctor.getFirstName() + " " + doctor.getLastName() +
                ", Час: " + time;
    }
}
