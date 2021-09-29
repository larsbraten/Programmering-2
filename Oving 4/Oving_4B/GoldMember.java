package com.company;

import java.time.LocalDate;

public class GoldMember extends BonusMember {
    public GoldMember(int memberNo, Personals personal, LocalDate enrolledDate, int points) {
        super(memberNo, personal, enrolledDate);
        super.registerPoints(points);
    }

    @Override
    public void registerPoints(int points) {
        super.registerPoints((int) (points * FACTOR_GOLD));
    }
}
