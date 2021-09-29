package com.company.deprecated;

import com.company.MemberArchive;
import com.company.Personals;
import java.time.LocalDate;

public class MyTestClass {
    public static void main(String... args){
        MemberArchive memberArchive = new MemberArchive();
        int memberNo = memberArchive.newMember(new Personals("Nordmann" , "Ola" , "test@mail.no" , "default") ,
                LocalDate.of(2005 , 2, 6));
        System.out.println(memberNo);
        System.out.println(memberArchive.registerPoints(memberNo , 75000));
        System.out.println(memberArchive.findPoints(memberNo , "default") + "\n");
        memberArchive.checkMembers(LocalDate.of(2005 , 3, 26));
        memberArchive.registerPoints(memberNo , 10000);
        System.out.println(memberArchive.findPoints(memberNo , "default"));
    }
}
