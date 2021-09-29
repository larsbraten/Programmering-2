package com.company;

public class Personals {
    private String surname;
    private String firstname;
    private String ePostadr;
    private String password;

    /**
     * Every attribute should not be nullable. Non-compliant input should entail an
     * IllegalArgumentException to be thrown, as it's a fitting descriptive
     * exception usually thrown in these circumstances.
     *
     * @param surname   Surname of person.
     * @param firstname Firstname of person.
     * @param ePostadr  E-mail belonging to person.
     * @param password  Password associated to person.
     */

    public Personals(String surname, String firstname, String ePostadr, String password) {
        if (surname == null || surname.isBlank() || firstname == null || firstname.isBlank()
                || ePostadr == null || ePostadr.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Et eller flere konstruktrør argumenter er null og/eller blanke.");
        } else {
            this.surname = surname;
            this.firstname = firstname;
            this.ePostadr = ePostadr;
            this.password = password;
        }
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getePostadr() {
        return ePostadr;
    }

    public boolean okPassword(String password) {
        return password.equalsIgnoreCase(this.password);
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (oldPassword == null || newPassword == null) {
            return false;
        }
        if (!password.equalsIgnoreCase(oldPassword.trim())) {
            return false;
        } else {
            password = newPassword.trim();
            return true;
        }
    }

    @Override
    public String toString() {
        return "Firstname:\t\t\t" + firstname + "\n" +
                "Surname:\t\t\t\t" + surname + "\n" +
                "Email:\t\t\t\t" + ePostadr + "\n";
    }
}
