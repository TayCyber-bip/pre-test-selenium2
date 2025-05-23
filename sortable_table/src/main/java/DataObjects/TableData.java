package DataObjects;

public class TableData {
    private String firstName;
    private String lastName;
    private String email;
    private String due;
    private String website;

    public TableData() {
    }

    public TableData(String firstName, String lastName, String email, String due, String website) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.due = due;
        this.website = website;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
