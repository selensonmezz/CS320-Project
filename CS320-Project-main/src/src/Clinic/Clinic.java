package Clinic;

public class Clinic {
    private String clinicName;
    private String clinicAddress;
    private int clinicId;
    private String contactDetails;


    public Clinic(int clinicId, String clinicName, String clinicAddress, String contactDetails) {
        this.clinicId = clinicId;
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.contactDetails = contactDetails;
    }


    public int getClinicId() {return clinicId;}

    public String getClinicName() {
return clinicName;
    }

    public String getLocation() {
        return clinicAddress;
    }

    public String getContactDetails() {
return contactDetails;
    }

}
