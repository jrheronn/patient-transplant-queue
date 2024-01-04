# Heart Transplant Priority Queue

## Overview
This Java program implements a priority queue for managing the waiting list of patients in need of heart transplants. The priority queue is based on a binary heap, and patients are prioritized based on their UNOS status and age.

## Features
- **Priority Queue Operations:**
  - Inserting patient records from an input file.
  - Peeking at the highest priority patient.
  - Removing the highest priority patient.
  - Removing a patient based on their information.
  - Updating a patient's UNOS status and maintaining a history of changes.
  - Viewing the size of the priority queue.

- **UNOS Status:**
  - Patients are prioritized based on UNOS status, with ties broken by age.
  - Four UNOS status classifications: Status 1A, Status 1B, Status 2, and Status 7.

- **Patient Information:**
  - Each patient record includes details such as name, address, contact information, and date of birth.

- **Adaptive Priority Queue:**
  - The priority queue is adaptive, accommodating changes in UNOS status and maintaining a history of changes for each patient.

## Usage
1. **Compile:**
   - Compile the Java program using `javac`:
     ```bash
     javac YourProgramName.java
     ```

2. **Run:**
   - Run the program using `java`:
     ```bash
     java YourProgramName
     ```

3. **Follow the On-Screen Menu:**
   - Interact with the program through the on-screen menu to perform various operations.

4. **Input File Format:**
   - Ensure the input file (`inputFile.txt`) is formatted with patient information.

## File Structure
- `YourProgramName.java`: Main Java program file.
- `Patient.java`: Class file for representing patient information.
- `PatientComparator.java`: Comparator class for comparing patients based on UNOS status and age.

## License

## Author
- jrheronn

Feel free to customize this template to match the specifics of your program. Provide additional details, such as prerequisites, dependencies, or any specific instructions users need to follow.
