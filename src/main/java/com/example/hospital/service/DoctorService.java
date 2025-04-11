package com.example.hospital.service;

import java.util.ArrayList;
import java.util.List;

import com.example.hospital.model.Doctor;
import com.example.hospital.model.Hospital;

public class DoctorService {
    private Hospital hospital;

    public DoctorService(Hospital hospital) {
        this.hospital = hospital;
    }

    public Doctor createDoctor(String firstName, String lastName, String specialization) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Ім'я лікаря не може бути порожнім");
        }

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Прізвище лікаря не може бути порожнім");
        }

        if (specialization == null || specialization.isBlank()) {
            throw new IllegalArgumentException("Спеціалізація лікаря не може бути порожньою");
        }

        Doctor doctor = new Doctor(firstName, lastName, specialization);
        hospital.addDoctor(doctor);
        return doctor;
    }


    public boolean checkDoctorAvailability(Doctor doctor, String time) {
        if (doctor == null) {
            throw new IllegalArgumentException("Лікар не може бути null");
        }

        if (time == null || time.isBlank()) {
            throw new IllegalArgumentException("Час не може бути порожнім або null");
        }

        if (!hospital.getDoctors().contains(doctor)) {
            throw new IllegalArgumentException("Лікар не знайдений у лікарні");
        }

        return hospital.isDoctorAvailable(doctor,time);
    }


    public void deleteDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Лікар не може бути null");
        }

        if (!hospital.getDoctors().contains(doctor)) {
            throw new IllegalStateException("Лікар не знайдений у лікарні");
        }
        hospital.getAppointments().removeIf(appointment -> appointment.getDoctor() == doctor);
        hospital.removeDoctor(doctor);
    }
    public void updateDoctor(Doctor doctor, String newFirstName, String newLastName, String newSpecialization) {
        if (!hospital.getDoctors().contains(doctor)) {
            throw new IllegalArgumentException("Лікаря не знайдено в лікарні");
        }
        if (newFirstName != null && !newFirstName.isBlank()) doctor.setFirstName(newFirstName);
        if (newLastName != null && !newLastName.isBlank()) doctor.setLastName(newLastName);
        if (newSpecialization != null && !newSpecialization.isBlank()) doctor.setSpecialization(newSpecialization);
    }
    public List<String> getAllDoctorsAsString() {
        List<Doctor> doctors = hospital.getDoctors();  // Отримуємо список лікарів з лікарні

        if (doctors == null || doctors.isEmpty()) {
            throw new IllegalStateException("Лікарів немає");// Якщо список порожній, повертаємо порожній список
        }
        List<String> doctorStrings = new ArrayList<>();

        for (Doctor doctor : doctors) {
            doctorStrings.add(doctor.toString());
        }

        return doctorStrings;
    }
}
