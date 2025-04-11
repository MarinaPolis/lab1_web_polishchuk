/*package com.example.hospital.service;
import com.example.hospital.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {
    @Test
    void testDeleteNonExistentPatientThrowsException() {
        Hospital hospital = new Hospital();
        PatientService service = new PatientService(hospital);
        Patient fakePatient = new Patient("Test", "Patient", 30, "Male");

        Exception exception = assertThrows(IllegalStateException.class, () ->
                service.deletePatient(fakePatient)
        );

        assertTrue(exception.getMessage().contains("не знайдений"));
    }
    @Test
    void testUpdateNonExistentPatientThrowsException() {
        Hospital hospital = new Hospital();
        PatientService service = new PatientService(hospital);
        Patient fakePatient = new Patient("Anna", "Unknown", 25, "Female");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                service.updatePatient(fakePatient, "NewName", null, null, null)
        );

        assertTrue(exception.getMessage().contains("не знайдено"));
    }
    @Test
    void testGetAllPatientsAsStringThrowsWhenEmpty() {
        Hospital hospital = new Hospital();
        PatientService service = new PatientService(hospital);

        Exception exception = assertThrows(IllegalStateException.class, service::getAllPatientsAsString);
        assertTrue(exception.getMessage().contains("немає"));
    }

}*/
