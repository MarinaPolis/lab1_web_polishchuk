package com.example.hospital.service;

import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.Patient;
import com.example.hospital.model.Hospital;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class FileService {

    private final ObjectMapper mapper = new ObjectMapper();

    // Експортуємо лікарів, пацієнтів і записи на прийом з об'єкта hospital
    public void exportHospitalDataToJson(Hospital hospital, String filePath, boolean choseSorting,
                                         Comparator<Doctor> doctorComparator,
                                         Comparator<Patient> patientComparator) throws IOException {
        // Отримуємо дані з об'єкта hospital
        List<Doctor> doctors = hospital.getDoctors();
        List<Patient> patients = hospital.getPatients();
        List<Appointment> appointments = hospital.getAppointments();

        if (choseSorting) {
            doctors.sort(doctorComparator);
        } else {
            patients.sort(patientComparator);
        }

        File file = new File(filePath);

        if (!file.exists()) {

            if (file.createNewFile()) {
                System.out.println("Файл створено: " + filePath);
            } else {
                System.out.println("Не вдалося створити файл.");
            }
        }


        mapper.writerWithDefaultPrettyPrinter().writeValue(file, hospital);
    }
    public Hospital importHospitalDataFromJson(String filePath) throws IOException {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("File not found: " + filePath);
        }


        Hospital importedHospital = mapper.readValue(file, Hospital.class);


        return importedHospital;
    }


}
