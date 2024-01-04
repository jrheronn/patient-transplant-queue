package patientqueue;

import java.util.Date;

// Class to handle UNOS status changes in data.
public class UNOSStatusChange {

    // The new UNOS status.
    private String unosStatus;
    // The Data when UNOS status is changed.
    private Date date;

    public UNOSStatusChange(String unosStatus, Date date) {
        this.unosStatus = unosStatus;
        this.date = date;
    }
}