public class ContactInfo {
    // Attributes
    private String phoneNumber;
    private String emailAddress;

    // Constructor
    public ContactInfo(String phoneNumber, String emailAddress) {
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Methods
    public void updateContactInfo(String phone, String email) {
        // To be implemented
    }

    // Getters and setters can be added as needed
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
