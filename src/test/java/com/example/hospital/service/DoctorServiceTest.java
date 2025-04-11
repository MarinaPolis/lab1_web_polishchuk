/*package com.example.hospital.service;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.Hospital;
import com.example.hospital.service.DoctorService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoctorServiceTest {
    @Test
    void testCreateDoctorWithEmptyFirstNameThrowsException() {
        Hospital hospital = new Hospital();
        DoctorService doctorService = new DoctorService(hospital);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                doctorService.createDoctor("", "Shevchenko", "Cardiology")
        );

        assertTrue(exception.getMessage().contains("Ім'я"));
    }
    @Test
    void testDeleteNonExistentDoctorThrowsException() {
        Hospital hospital = new Hospital();
        DoctorService doctorService = new DoctorService(hospital);
        Doctor doctor = new Doctor("Ivan", "Ivanov", "Therapist");

        Exception exception = assertThrows(IllegalStateException.class, () ->
                doctorService.deleteDoctor(doctor)
        );

        assertTrue(exception.getMessage().contains("не знайдений"));
    }
    @Test
    void testCheckDoctorAvailabilityWhenDoctorNotInHospitalThrowsException() {
        Hospital hospital = new Hospital();
        DoctorService doctorService = new DoctorService(hospital);
        Doctor doctor = new Doctor("Anna", "Petrova", "Surgeon");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                doctorService.checkDoctorAvailability(doctor, "2025-04-09T10:00")
        );

        assertTrue(exception.getMessage().contains("не знайдений"));
    }

}*/
