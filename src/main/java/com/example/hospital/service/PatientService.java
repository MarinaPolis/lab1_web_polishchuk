package com.example.hospital.service;

import java.util.ArrayList;
import java.util.List;

import com.example.hospital.model.Doctor;
import com.example.hospital.model.Patient;
import com.example.hospital.model.Appointment;
import com.example.hospital.model.Hospital;

public class PatientService {
    private Hospital hospital;

    public PatientService(Hospital hospital) {
        this.hospital = hospital;
    }

    public Patient createPatient(String firstName, String lastName, int age, String gender) {
        Patient patient = new Patient(firstName, lastName, age, gender);
        hospital.addPatient(patient);
        return patient;
    }


    public void deletePatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Пацієнт не може бути null");
        }

        if (!hospital.getPatients().contains(patient)) {
            throw new IllegalStateException("Пацієнт не знайдений у лікарні");
        }
        hospital.getAppointments().removeIf(appointment -> appointment.getPatient().equals(patient));
        hospital.removePatient(patient);
    }
    public void updatePatient(Patient patient, String newFirstName, String newLastName, Integer newAge, String newGender) {
        if (!hospital.getPatients().contains(patient)) {
            throw new IllegalArgumentException("Пацієнта не знайдено в лікарні");
        }
        if (newFirstName != null && !newFirstName.isBlank()) patient.setFirstName(newFirstName);
        if (newLastName != null && !newLastName.isBlank()) patient.setLastName(newLastName);
        if (newAge != null && newAge > 0) patient.setAge(newAge);
        if (newGender != null && !newGender.isBlank()) patient.setGender(newGender);
        for (Appointment appointment : hospital.getAppointments()) {
            if (appointment.getPatient().equals(patient)) {
                appointment.setPatient(patient);  // оновлюємо посилання на оновленого пацієнта
            }
        }
    }
    public List<String> getAllPatientsAsString() {
        List<Patient> patients = hospital.getPatients();  // Отримуємо список пацієнтів з лікарні
        if (patients == null || patients.isEmpty()) {
            throw new IllegalStateException("Пацієнтів немає");// Якщо список порожній, повертаємо порожній список
        }
        List<String> patientsStrings = new ArrayList<>();


        for (Patient patient : patients) {
            patientsStrings.add(patient.toString());
        }

        return patientsStrings;
    }

}
