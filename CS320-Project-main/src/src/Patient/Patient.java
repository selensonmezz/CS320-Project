package Patient;

public class Patient {
    private int patient_id;
    private String first_name;
    private String last_name;
    private String phone_num;

    public Patient(int patient_id, String first_name, String last_name, String phone_num) {
        this.patient_id = patient_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_num = phone_num;
    }

    public int getPatientId() {
        return patient_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getPhoneNum() {
        return phone_num;
    }

    public void setPhoneNum(String updatedPhoneNumber) {
    }
}

