package patientqueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PatientQueueMenu {
    // Date format as month/day/year.
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static final PriorityQueue<Patient> priorityQueue = new PriorityQueue<>(100, new PatientComparator());

    // ANSI escape codes to change string color.
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int option;

        do {
            System.out.println("Patient Queue Menu:");
            System.out.println("1. Insert a file");
            System.out.println("2. Peek next patient");
            System.out.println("3. Get next patient");
            System.out.println("4. Remove a patient");
            System.out.println("5. Get queue size ");
            System.out.println("6. Update patient priority");
            System.out.println("7. Exit");

            System.out.print("Enter your option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    // Insert a file.
                    insertFile();
                    break;

                case 2:
                    // Peek next patient.
                    peekAtNextPatient();
                    break;

                case 3:
                    // Get next patient.
                    getNextPatient();
                    break;

                case 4:
                    // Remove a patient.
                    removePatient();
                    break;

                case 5:
                    // Get queue size.
                    printQueueSize();
                    break;

                case 6:
                    // Update patient priority.
                    updatePatientPriority();
                    break;

                case 7:
                    // Exit menu.
                    System.out.println("Exiting the menu.");
                    break;

                default:
                    System.out.println("Invalid option. Select a valid option of 1-7");
            }
        } while (option != 7);
    }

    private static void updatePatientPriority() {
        // Prompt the user for the patient data.
        System.out.println("Please enter the patient information to change UNOS status:");
        System.out.print("Please enter patient's first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Please enter the patient's last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Please enter patient’s data of birth: ");
        String dob = scanner.nextLine();
        Date dateOfBirth = parseDate(dob);
        System.out.println("Please enter the patient's address: " + ANSI_BLUE);
        String address = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's city: " + ANSI_BLUE);
        String city = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's county: " + ANSI_BLUE);
        String county = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's state: " + ANSI_BLUE);
        String state = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's zip code: " + ANSI_BLUE);
        String zip = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's phone number (1st Preference): " + ANSI_BLUE);
        String phone1 = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's phone number (2nd Preference): " + ANSI_BLUE);
        String phone2 = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's email address: " + ANSI_BLUE);
        String email = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please update the UNOS Status: " + ANSI_BLUE);
        String newUnosStatus = scanner.nextLine();
        System.out.println(ANSI_RESET);

        // Create a patient object with the provided data.
        Patient patient = new Patient(firstName, lastName, dateOfBirth, address, city, county, state, zip, phone1, phone2, email, null, null);

        // Check if the patient exists.
        if (priorityQueue.contains(patient)) {
            if (checkUNOSStatus(newUnosStatus)) {
                // If patient found, update the UNOS status and create new patient object.
                Patient updatedPatient = new Patient(firstName, lastName, dateOfBirth, address, city, county, state, zip, phone1, phone2, email, newUnosStatus, null);

                // Add UNOS Status change to the patient's UNOS change list.
                updatedPatient.addUNOSStatusChange(newUnosStatus, new Date());

                // Remove the old patient object.
                priorityQueue.remove(patient);

                updatedPatient.setUnosStatus(newUnosStatus);
                // Add new patient with updated UNOS status.
                priorityQueue.offer(updatedPatient);

                // Display updated patient information.
                System.out.println("The following patient detail has been updated: ");
                System.out.println("Patient's first name: " + updatedPatient.getFirstName());
                System.out.println("Patient's last name: " + updatedPatient.getLastName());
                System.out.println("Date of birth of the patient: " + dateFormat.format(updatedPatient.getDateOfBirth()));
                System.out.println("Address: " + updatedPatient.getAddress());
                System.out.println("City: " + updatedPatient.getCity());
                System.out.println("County: " + updatedPatient.getCounty());
                System.out.println("State: " + updatedPatient.getState());
                System.out.println("Zip code: " + updatedPatient.getZip());
                System.out.println("Phone Number (1st Preference): " + updatedPatient.getPhone1());
                System.out.println("Phone Number (2nd Preference): " + updatedPatient.getPhone2());
                System.out.println("Email Address: " + updatedPatient.getEmail());
                System.out.println("UNOS Status: " + updatedPatient.getUnosStatus());
                System.out.println("Date listed on " + ANSI_RED + updatedPatient.getUnosStatus() + ANSI_RESET + ": " + dateFormat.format(updatedPatient.getDateListed()));
                // Print patient's UNOS Status Change List.
                System.out.println("On" + "UNOS_status changed to " + updatedPatient.getUnosStatusChangeList());
                System.out.println();
            }
        } else {
            System.out.println("Patient not found in the queue.");
        }
    }

    private static boolean checkUNOSStatus(String newUnosStatus) {
        // Array of valid UNOS Statuses.
        String[] validUNOSStatuses = {"Status 1A", "Status 1B", "Status 2", "Status 7"};

        // Check if the new UNOS status is valid.
        for (String validUNOSStatus : validUNOSStatuses) {
            if (validUNOSStatus.equals(newUnosStatus)) {
                return true; // UNOS status is valid.
            }
        }
        // New UNOS status is invalid.
        return false;
    }

    private static void printQueueSize() {
        int queueSize = priorityQueue.size();
        System.out.println("Number of records in the database: " + queueSize);
    }

    private static void removePatient() {
        // Prompt the user for the patient data.
        System.out.println("Please enter the patient information to remove from the queue:");
        System.out.print("Please enter patient's first name: " + ANSI_BLUE);
        String firstName = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's last name: " + ANSI_BLUE);
        String lastName = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter patient’s data of birth: " + ANSI_BLUE);
        String dob = scanner.nextLine();
        Date dateOfBirth = parseDate(dob);

        System.out.println(ANSI_RESET + "Please enter the patient's address: " + ANSI_BLUE);
        String address = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's city: " + ANSI_BLUE);
        String city = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's county: " + ANSI_BLUE);
        String county = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's state: " + ANSI_BLUE);
        String state = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's zip code: " + ANSI_BLUE);
        String zip = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's phone number (1st Preference): " + ANSI_BLUE);
        String phone1 = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's phone number (2nd Preference): " + ANSI_BLUE);
        String phone2 = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the patient's email address: " + ANSI_BLUE);
        String email = scanner.nextLine();
        System.out.print(ANSI_RESET + "Please enter the UNOS Status: " + ANSI_BLUE);
        String unosStatus = scanner.nextLine();
        System.out.println(ANSI_RESET);

        // Create patient from provided data.
        Patient removedPatient = new Patient(firstName, lastName, dateOfBirth, address, city, county, state, zip, phone1, phone2, email, unosStatus, null);

        // Check if patient exist in the queue.
        boolean removed = priorityQueue.remove(removedPatient);

        if (removed) {
            System.out.println("The requested patient’s record has been removed from the queue.");
        } else {
            System.out.println("The requested patient’s record is not found.");
        }
    }

    private static Date parseDate(String dob) {
        try {
            return dateFormat.parse(dob);
        } catch (ParseException e) {
            System.out.println("Error parsing date.");
            return null;
        }
    }


    private static void getNextPatient() {
        if (priorityQueue.isEmpty()) {
            System.out.println("The priority queue is empty.");
        } else {
            // Get the next patient.
            Patient nextPatient = priorityQueue.poll();

            // Display patient information.
            System.out.println("The patient removed from the heap is as follows: ");
            System.out.println("Patient's first name: " + nextPatient.getFirstName());
            System.out.println("Patient's last name: " + nextPatient.getLastName());
            System.out.println("Date of birth of the patient: " + dateFormat.format(nextPatient.getDateOfBirth()));
            System.out.println("Address: " + nextPatient.getAddress());
            System.out.println("City: " + nextPatient.getCity());
            System.out.println("County: " + nextPatient.getCounty());
            System.out.println("State: " + nextPatient.getState());
            System.out.println("Zip code: " + nextPatient.getZip());
            System.out.println("Phone Number (1st Preference): " + nextPatient.getPhone1());
            System.out.println("Phone Number (2nd Preference): " + nextPatient.getPhone2());
            System.out.println("Email Address: " + nextPatient.getEmail());
            System.out.println("UNOS Status: " + nextPatient.getUnosStatus());
            System.out.println("Date listed on " + ANSI_RED + nextPatient.getUnosStatus() + ANSI_RESET + ": " + dateFormat.format(nextPatient.getDateListed()));
            System.out.println();
        }
    }

    private static void peekAtNextPatient() {
        if (priorityQueue.isEmpty()) {
            System.out.println("The priority queue is empty.");
        } else {
            // Peek at the next patient.
            Patient nextPatient = priorityQueue.peek();

            // Display patient information.
            System.out.println("The patient detail with the highest priority is as follow: ");
            System.out.println("Patient's first name: " + nextPatient.getFirstName());
            System.out.println("Patient's last name: " + nextPatient.getLastName());
            System.out.println("Date of birth of the patient: " + dateFormat.format(nextPatient.getDateOfBirth()));
            System.out.println("Address: " + nextPatient.getAddress());
            System.out.println("City: " + nextPatient.getCity());
            System.out.println("County: " + nextPatient.getCounty());
            System.out.println("State: " + nextPatient.getState());
            System.out.println("Zip code: " + nextPatient.getZip());
            System.out.println("Phone Number (1st Preference): " + nextPatient.getPhone1());
            System.out.println("Phone Number (2nd Preference): " + nextPatient.getPhone2());
            System.out.println("Email Address: " + nextPatient.getEmail());
            System.out.println("UNOS Status: " + nextPatient.getUnosStatus());
            System.out.println("Date listed on " + ANSI_RED + nextPatient.getUnosStatus() + ANSI_RESET + ": " + dateFormat.format(nextPatient.getDateListed()));
            System.out.println();
        }
    }

    private static void insertFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the input file to read: ");
        String inputFile = scanner.nextLine();

        try {
            File txtFile = new File(inputFile);
            Scanner fileScanner = new Scanner(txtFile);

            // Skip the first line of the txt file.
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(";");
                if (data.length == 13) {
                    String firstName = data[0];
                    String lastName = data[1];
                    String address = data[2];
                    String city = data[3];
                    String county = data[4];
                    String state = data[5];
                    String zip = data[6];
                    String phone1 = data[7];
                    String phone2 = data[8];
                    String email = data[9];
                    Date dateListed = dateFormat.parse(data[10]);
                    String unosStatus = data[11];
                    Date dateOfBirth = dateFormat.parse(data[12]);

                    // If patient UNOS status is empty.
                    if (unosStatus.isEmpty()) {
                        unosStatus = "Status 7";
                    }

                    Patient newPatient = new Patient(firstName, lastName, dateOfBirth, address, city, county,
                            state, zip, phone1, phone2, email, unosStatus, dateListed);

                    // Add patient to the queue.
                    priorityQueue.add(newPatient);
                }
            }
            fileScanner.close();
            System.out.println("Input file is read successfully.");
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("Error");
        }
    }
}