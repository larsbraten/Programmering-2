package com.company;

import java.time.LocalDate;

public class BasicMember extends BonusMember {

    public BasicMember(int memberNo, Personals personal, LocalDate enrolledDate) {
        super(memberNo, personal, enrolledDate);
    }
}
