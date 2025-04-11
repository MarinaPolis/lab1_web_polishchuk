package com.example.hospital.service;

import java.util.ArrayList;
import java.util.List;

import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.Hospital;
import com.example.hospital.model.Patient;

public class AppointmentService {

    private Hospital hospital;  // Лікарня, що містить лікарів та пацієнтів

    public AppointmentService(Hospital hospital) {
        this.hospital = hospital;
    }
    public Appointment addAppointment(Patient patient, Doctor doctor, String time) {
        if (patient == null || doctor == null || time == null || time.isBlank()) {
            throw new IllegalArgumentException("Пацієнт, лікар або час не можуть бути null або порожні");
        }

        if (!hospital.getPatients().contains(patient)) {
            throw new IllegalStateException("Пацієнт не знайдений у лікарні: " + patient.getFirstName() + " " + patient.getLastName());
        }

        if (!hospital.getDoctors().contains(doctor)) {
            throw new IllegalStateException("Лікар не знайдений у лікарні: " + doctor.getFirstName() + " " + doctor.getLastName());
        }

        if (!hospital.isDoctorAvailable(doctor, time)) {
            throw new IllegalStateException("Лікар зайнятий на зазначений час: " + time);
        }

        Appointment appointment = new Appointment(patient, doctor, time);
        hospital.addAppointment(appointment);
        return appointment;
    }
    public void removeAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Запис не може бути null");
        }

        if (!hospital.getAppointments().contains(appointment)) {
            throw new IllegalStateException("Запис на прийом не знайдений для пацієнта: " + appointment.toString());
        }
        hospital.removeAppointment(appointment);
    }
    public List<Appointment> getAllAppointmentsAsString() {
        List<Appointment> appointments = hospital.getAppointments();  // Отримуємо список записів на прийом з лікарні
        if (appointments == null || appointments.isEmpty()) {
            throw new IllegalStateException("Записів на прийом немає");  // Якщо список порожній, викидаємо виняток
        }
        return appointments;  // Повертаємо список рядків
    }

}
