package com.company;

import java.time.LocalDate;

public class SilverMember extends BonusMember {
    public SilverMember(int memberNo, Personals personal, LocalDate enrolledDate, int point) {
        super(memberNo, personal, enrolledDate);
        super.registerPoints(point);
    }

    @Override
    public void registerPoints(int point) {
        super.registerPoints((int) (point * FACTOR_SILVER));
    }
}
