/*package com.example.hospital.service;
import com.example.hospital.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {
    @Test
    void testAddAppointmentSuccess() {
        Hospital hospital = new Hospital();
        DoctorService doctorService = new DoctorService(hospital);
        PatientService patientService = new PatientService(hospital);
        Doctor doctor = doctorService.createDoctor("John", "Doe", "Cardiology");
        Patient patient = patientService.createPatient("Alice", "Smith", 25 ,"Жіноча");
        hospital.addDoctor(doctor);

        AppointmentService service = new AppointmentService(hospital);
        Appointment appointment = service.addAppointment(patient, doctor, "2025-04-08T09:00");

        assertNotNull(appointment);
        assertEquals(1, hospital.getAppointments().size());
        assertEquals(doctor, appointment.getDoctor());
    }
    @Test
    void testAddAppointmentDoctorBusyThrowsException() {
        Hospital hospital = new Hospital();
        DoctorService doctorService = new DoctorService(hospital);
        PatientService patientService = new PatientService(hospital);

        // Створюємо лікаря та пацієнтів
        Doctor doctor = doctorService.createDoctor("John", "Doe", "Cardiology");
        Patient patient1 = patientService.createPatient("Alice", "Smith", 25, "Жіноча");
        Patient patient2 = patientService.createPatient("Bob", "Brown", 33, "Чоловіча");

        AppointmentService service = new AppointmentService(hospital);

        // Додаємо перший запис на прийом для лікаря
        service.addAppointment(patient1, doctor, "2025-04-08T09:00");

        // Перевірка, що викликається виключення, якщо лікар вже зайнятий
        Exception exception = assertThrows(IllegalStateException.class, () ->
                service.addAppointment(patient2, doctor, "2025-04-08T09:00")
        );

        // Перевірка повідомлення виключення
        assertTrue(exception.getMessage().contains("Лікар зайнятий"));
    }


    @Test
    void testGetAllAppointmentsAsStringThrowsWhenEmpty() {
        Hospital hospital = new Hospital();
        AppointmentService service = new AppointmentService(hospital);

        Exception exception = assertThrows(IllegalStateException.class, service::getAllAppointmentsAsString);
        assertTrue(exception.getMessage().contains("немає"));
    }

}*/
