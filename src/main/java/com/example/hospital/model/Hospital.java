package com.example.hospital.model;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    public List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();
    //private ObjectMapper objectMapper;

    // Ініціалізація ObjectMapper в конструкторі
    public Hospital() {
        //this.objectMapper = new ObjectMapper();
    }
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    // Геттери і сеттери
    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
    public boolean isDoctorAvailable(Doctor doctor, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().equals(doctor) && appointment.getTime().equals(time)) {
                return false; // Лікар зайнятий на цей час
            }
        }
        return true; // Лікар доступний
    }
}
