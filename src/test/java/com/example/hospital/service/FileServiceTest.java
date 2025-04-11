/*package com.example.hospital.service;

import com.example.hospital.model.Doctor;
import com.example.hospital.model.Hospital;
import com.example.hospital.model.Patient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileServiceTest {

    @Test
    void importHospitalDataFromJson_shouldSuccessfullyLoadExistingFile() throws IOException {
        // Arrange
        Hospital original = new Hospital();
        original.addDoctor(new Doctor("Anna", "Ivanova", "Therapy"));
        original.addPatient(new Patient("Max", "Petrov", 28, "Male"));

        FileService fileService = new FileService();
        String filePath = "E:/Proga/Lab1Hospital/hospital_data.json";
        fileService.exportHospitalDataToJson(original, filePath, false, null, null);

        // Act
        Hospital imported = fileService.importHospitalDataFromJson(filePath);

        // Assert
        assertNotNull(imported);
        assertEquals(1, imported.getDoctors().size());
        assertEquals(1, imported.getPatients().size());

        // Clean up
        new File(filePath).delete();
    }

    @Test
    void exportHospitalDataToJson_shouldFailWithInvalidPath() {
        // Arrange
        Hospital hospital = new Hospital();
        hospital.addDoctor(new Doctor("Kate", "Miller", "Surgery"));
        hospital.addPatient(new Patient("Leo", "Smith", 35, "Male"));

        FileService fileService = new FileService();

        // Act & Assert
        assertThrows(IOException.class, () -> {
            fileService.exportHospitalDataToJson(hospital, "invalid_path:/invalid/test.json", false, null, null);
        });
    }

    @Test
    void importHospitalDataFromJson_shouldFailForMissingFile() {
        FileService fileService = new FileService();

        Exception exception = assertThrows(IOException.class, () ->
                fileService.importHospitalDataFromJson("missing_file_404.json")
        );

        assertTrue(exception.getMessage().contains("File not found"));
    }
}
*/
