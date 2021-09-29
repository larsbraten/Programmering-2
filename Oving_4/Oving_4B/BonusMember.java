package com.company;

import java.time.LocalDate;
import java.time.Period;

public class BonusMember {
    protected final double FACTOR_SILVER = 1.2;
    protected final double FACTOR_GOLD = 1.5;
    private int memberNo;
    private LocalDate enrolledDate;
    private int point = 0;
    private Personals personal;

    /**
     * Every attribute should not be nullable. Non-compliant input should entail an
     * IllegalArgumentException to be thrown, as it's a fitting descriptive
     * exception usually thrown in these circumstances.
     *
     * @param memberNo     Membernumber.
     * @param personal     General information associated with given person.
     * @param enrolledDate Date of registry.
     */

    public BonusMember(int memberNo, Personals personal, LocalDate enrolledDate) {
        if (personal == null || enrolledDate == null) {
            throw new IllegalArgumentException("Variables is non-nullable");
        }
        this.memberNo = memberNo;
        this.personal = personal;
        this.enrolledDate = enrolledDate;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public Personals getPersonals() {
        return personal;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public int getPoints() {
        return point;
    }

    public int findQualificationPoints(LocalDate localDate) {
        return (Period.between(enrolledDate, localDate).getYears() < 1) ? point : 0;
    }

    public boolean okPassword(String password) {
        return personal.okPassword(password);
    }

    public void registerPoints(int point) {
        this.point = this.point + point;
    }

    public String getName() {
        return personal.getFirstname() + " " + personal.getSurname();
    }

    public String getStatus() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Membership ranking :\t" + getClass().getSimpleName() + "\n\n" +
                personal + "Member ID:\t\t\t" + memberNo + "\n" +
                "Enrollment date:\t\t" + getEnrolledDate() + "\n" +
                "Points:\t\t\t\t" + point + "\n\n";

    }
}
