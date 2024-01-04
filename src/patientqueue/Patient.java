package patientqueue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String city;
    private String county;
    private String state;
    private String zip;
    private String phone1;
    private String phone2;
    private String email;
    private String unosStatus;
    private Date dateListed;

    // Create a list to hold the UNOS Status history.
    private List<UNOSStatusChange> unosStatusChangeList;
    public Patient(String firstName, String lastName, Date dateOfBirth, String address,
                   String city, String county, String state, String zip, String phone1,
                   String phone2, String email, String unosStatus, Date dateListed) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.city = city;
        this.county = county;
        this.state = state;
        this.zip = zip;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.unosStatus = unosStatus;
        this.dateListed = dateListed;
        // Array list to hold changes to UNOS status for a patient.
        unosStatusChangeList = new ArrayList<>();
    }

    // Getters for data.
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getEmail() {
        return email;
    }

    public String getUnosStatus() {
        return unosStatus;
    }

    public Date getDateListed() {
        return dateListed;
    }

    public void setUnosStatus(String unosStatus) {
        this.unosStatus = unosStatus;
    }

    // Methods for UNOS status history.
    //Method to add a change to the UNOS Status Change List.
    public void addUNOSStatusChange(String unosStatus, Date date) {
        UNOSStatusChange unosChange = new UNOSStatusChange(unosStatus, date);
        unosStatusChangeList.add(unosChange);
    }

    // Method to return UNOS Status Change List.
    public List<UNOSStatusChange> getUnosStatusChangeList() {
        return unosStatusChangeList;
    }
}
