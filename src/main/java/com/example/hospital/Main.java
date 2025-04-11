package com.example.hospital;

import java.util.Scanner;
import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.Patient;
import com.example.hospital.model.Hospital;
import com.example.hospital.service.FileService;
import com.example.hospital.service.AppointmentService;
import com.example.hospital.service.DoctorService;
import com.example.hospital.service.PatientService;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

public class Main {
    public static void printAllDoctors(DoctorService doctorService) {
        System.out.println("Список лікарів:");
        try {
            for (String doctorInfo : doctorService.getAllDoctorsAsString()) {
                System.out.println(doctorInfo);
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void printAllPatients(PatientService patientService) {
        System.out.println("\nСписок пацієнтів:");
        try {
            for (String patientInfo : patientService.getAllPatientsAsString()) {
                System.out.println(patientInfo);
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void exportHospitalData(FileService fileService, Hospital hospital, String filePath) {
        Comparator<Doctor> doctorComparator = Comparator.comparing(Doctor::getFirstName);
        Comparator<Patient> patientComparator = Comparator.comparing(Patient::getFirstName);
        try {
            fileService.exportHospitalDataToJson(hospital, filePath, true, doctorComparator, patientComparator);
            System.out.println("Дані успішно експортовано до файлу: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка при експорті даних: " + e.getMessage());
        }
    }
    public static void importAndDisplayHospitalData(FileService fileService,String filePath) {
        try {
            Hospital importedHospital = fileService.importHospitalDataFromJson(filePath);

            System.out.println("\nІмпортовані лікарі:");
            for (Doctor doctor : importedHospital.getDoctors()) {
                System.out.println(doctor);
            }

            System.out.println("\nІмпортовані пацієнти:");
            for (Patient patient : importedHospital.getPatients()) {
                System.out.println(patient);
            }

            System.out.println("\nІмпортовані записи на прийом:");
            for (Appointment appointment : importedHospital.getAppointments()) {
                System.out.println(appointment);
            }

        } catch (IOException e) {
            System.out.println("Помилка при імпорті даних: " + e.getMessage());
        }
    }
    public static Patient  parsePatientData(PatientService patientService, String patientData, Boolean choise_input) {;

        String[] data = patientData.split(",\\s*");

        if (data.length != 3) {
            throw new IllegalArgumentException("Невірний формат даних пацієнта: " + patientData);
        }

        String[] nameParts = data[0].split("\\s+");
        if (nameParts.length != 2) {
            throw new IllegalArgumentException("Невірний формат імені пацієнта: " + data[0]);
        }
        String firstName = nameParts[0];
        String lastName = nameParts[1];


        int age;
        try {
            age = Integer.parseInt(data[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Невірний формат віку пацієнта: " + data[1]);
        }
        String gender = data[2];
        if(choise_input) {
            try {
                Patient patient = patientService.createPatient(firstName, lastName, age, gender);
                return patient;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
        else{
            try {
                Patient patient = new Patient(firstName, lastName, age, gender);
                patientService.deletePatient(patient);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }

        return null;
    }

    public static Doctor parseDoctorData(DoctorService doctorService, String doctorData, Boolean choise_input) {

        String[] data = doctorData.split(", ");

        if (data.length != 2) {
            throw new IllegalArgumentException("Невірний формат даних лікаря: " + doctorData);
        }

        String[] nameParts = data[0].split(" ");
        if (nameParts.length != 2) {
            throw new IllegalArgumentException("Невірний формат імені лікаря: " + data[0]);
        }
        String firstName = nameParts[0];
        String lastName = nameParts[1];

        String specialization = data[1];
        if (choise_input) {
            try {
                Doctor doctor = doctorService.createDoctor(firstName, lastName, specialization);
                return doctor;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
        else{
            try {
                Doctor doctor = new Doctor(firstName, lastName, specialization);
                doctorService.deleteDoctor(doctor);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }

        return null;
    }
    public static void addAppointmentFromConsole(AppointmentService appointmentService, String appointmentData) {

        String[] parts = appointmentData.split(", ");

        if (parts.length != 7) {
            System.out.println("Невірний формат вводу. Очікується 7 частин.");
            return;
        }


        String patientFirstName = parts[0].split(" ")[0];  // Ім'я пацієнта
        String patientLastName = parts[0].split(" ")[1];   // Прізвище пацієнта
        int patientAge = Integer.parseInt(parts[1]);       // Вік пацієнта
        String patientGender = parts[2];                    // Стать пацієнта


        Patient patient = new Patient(patientFirstName, patientLastName, patientAge, patientGender);

        String doctorFirstName = parts[4].split(" ")[0];    // Ім'я лікаря
        String doctorLastName = parts[4].split(" ")[1];     // Прізвище лікаря
        String doctorSpecialization = parts[5];             // Спеціалізація лікаря

        Doctor doctor = new Doctor(doctorFirstName, doctorLastName, doctorSpecialization);


        String appointmentTime = parts[6];  // Час прийому

        try {
            appointmentService.addAppointment(patient, doctor, appointmentTime);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        DoctorService doctorService = new DoctorService(hospital);
        PatientService patientService = new PatientService(hospital);
        AppointmentService appointmentService = new AppointmentService(hospital);
        FileService fileService = new FileService();
        printAllDoctors(doctorService);
        printAllPatients(patientService);


        System.out.println("Додавання 2 лікарів");
        Doctor doctor1 = doctorService.createDoctor("Іван", "Іванов", "Терапевт");
        Doctor doctor2 = doctorService.createDoctor("Марія", "Петренко", "Хірург");

        printAllDoctors(doctorService);

        System.out.println("Оновлення даних лікаря");
        doctorService.updateDoctor(doctor1, "Іван", "Сидоренко", "Педіатр");

        printAllDoctors(doctorService);

        boolean isAvailable = doctorService.checkDoctorAvailability(doctor1, "2025-04-07 10:00");
        System.out.println("Чи доступний лікар: " + isAvailable);

        System.out.println("Додавання 2 нових пацієнтів");

        Patient patient1 = patientService.createPatient("Олександр", "Коваль", 30, "Чоловіча");
        Patient patient2 = patientService.createPatient("Анна", "Шевченко", 25, "Жіноча");

        printAllPatients(patientService);

        appointmentService.addAppointment(patient1, doctor1, "2025-04-07 10:00");

        System.out.println("Видалення пацієнта");

        patientService.deletePatient(patient1);

        printAllPatients(patientService);

        System.out.println("Видалення лікаря");

        doctorService.deleteDoctor(doctor2);
        printAllDoctors(doctorService);
        String filePath = "E:/Proga/Lab1Hospital/hospital_data.json";
        Scanner scanner = new Scanner(System.in);
        exportHospitalData(fileService, hospital, filePath);

        importAndDisplayHospitalData(fileService,filePath);
        //Олександр Коваль, Хірург
        // Анна Шевченко, 25, Жіноча
        // Анна Шевченко, 25, Жіноча, Олександр Коваль, Хірург, 2025-04-07 10:00
        //Олександр Коваль, 30, Чоловіча, Олександр Коваль, Хірург, 2025-04-07 10:00
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати лікаря");
            System.out.println("2. Додати пацієнта");
            System.out.println("3. Додати запис на прийом");
            System.out.println("4. Видалити лікаря");
            System.out.println("5. Видалити пацієнта");
            System.out.println("6. Перевірити доступність лікаря");
            System.out.println("7. Показати список лікарів");
            System.out.println("8. Показати список пацієнтів");
            System.out.println("9. Вийти");
            System.out.print("Оберіть опцію: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Читаємо залишок рядка

            String input;

            switch (option) {
                case 1:
                    System.out.println("Введіть дані у форматі: Ім'я Лікаря Прізвище лікаря, Спеціалізація Лікаря");
                    input = scanner.nextLine();
                    parseDoctorData(doctorService,input, true);
                    break;
                case 2:
                    System.out.println("Введіть дані у форматі: Ім'я Пацієнта, Прізвище Пацієнта, Вік Пацієнта, Стать Пацієнта");
                    input = scanner.nextLine();
                    parsePatientData(patientService, input, true);
                    break;
                case 3:
                    System.out.println("Введіть дані у форматі: Ім'я Пацієнта, Прізвище Пацієнта, Вік Пацієнта, Стать Пацієнта, Ім'я Лікаря, Спеціалізація Лікаря, Час Прийому");
                    input = scanner.nextLine();
                    addAppointmentFromConsole(appointmentService, input);
                    break;
                case 4:
                    input = scanner.nextLine();
                    parseDoctorData(doctorService,input, false);
                    break;
                case 5:
                    System.out.println("Введіть дані у форматі: Ім'я Пацієнта, Прізвище Пацієнта, Вік Пацієнта, Стать Пацієнта, Ім'я Лікаря, Спеціалізація Лікаря, Час Прийому");
                    input = scanner.nextLine();
                    parsePatientData(patientService, input, false);
                    break;
                case 6:
                    try {
                        isAvailable = doctorService.checkDoctorAvailability(doctor1, "2025-04-07 10:00");
                        System.out.println("Чи доступний лікар: " + isAvailable);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка: Невірний формат дати або часу.");
                    } catch (Exception e) {
                        System.out.println("Сталася непередбачувана помилка: " + e.getMessage());
                    }
                    break;
                case 7:
                    printAllDoctors(doctorService);
                    break;
                case 8:
                    printAllPatients(patientService);
                    break;
                case 9:
                    System.out.println("Вихід...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невірна опція. Спробуйте ще раз.");
                    break;
            }

        }

    }
}

