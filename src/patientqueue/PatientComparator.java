package patientqueue;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class PatientComparator implements Comparator<Patient> {
    @Override
    public int compare(Patient o1, Patient o2) {
        // Compare patient UNOS status.
        int unosComparison = compareUNOSStatus(o1.getUnosStatus(), o2.getUnosStatus());

        if (unosComparison != 0) {
            return unosComparison;
        }
        // In a tie in UNOS status, compare age.
        return compareAge(o1.getDateOfBirth(), o2.getDateOfBirth());
    }

    private int compareAge(Date dateOfBirth1, Date dateOfBirth2) {
        // Compare patients' ages.
        int age1 = calculateAge(dateOfBirth1);
        int age2 = calculateAge(dateOfBirth2);

        // Younger patient receives higher priority.
        return Long.compare(age1, age2);
    }

    private int calculateAge(Date dateOfBirth) {
        Calendar dob = Calendar.getInstance();
        dob.setTime(dateOfBirth);
        Calendar todaysDate = Calendar.getInstance();

        // Calculate patient's age.
        int age = todaysDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        // Check if patient's birthday has occurred for the year.
        if (todaysDate.get(Calendar.MONTH) < dob.get(Calendar.MONTH) ||
                todaysDate.get(Calendar.MONTH) == dob.get(Calendar.MONTH) &&
                        todaysDate.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }

    private int compareUNOSStatus(String unosStatus1, String unosStatus2) {
        // Compare UNOS statuses based on priority.
        if (unosStatus1.equals(unosStatus2)) {
            return 0;
        } else if (unosStatus1.equals("Status 1A")) {
            return -1;
        } else if (unosStatus2.equals("Status 1A")) {
            return 1;
        } else if (unosStatus1.equals("Status 1B")) {
            return -1;
        } else if (unosStatus2.equals("Status 1B")) {
            return 1;
        } else if (unosStatus1.equals("Status 2")) {
            return -1;
        } else {
            return 1;
        }
    }
}