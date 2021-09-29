package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MemberArchive {
    private final int SILVER_LIMIT = 25000;
    private final int GOLD_LIMIT = 75000;
    private final Random RANDOM_NUMBER = new Random();
    private HashMap<Integer, BonusMember> members = new HashMap<>();

    public int newMember(Personals persona, LocalDate enrolledDate) {
        int newMemberNo = findAvailableNo();
        members.put(newMemberNo, new BasicMember(newMemberNo, persona, enrolledDate));
        return newMemberNo;
    }


    private Integer findAvailableNo() {
        return RANDOM_NUMBER.ints().filter(x -> !members.containsKey(x)).findFirst().getAsInt();
    }

    public int findPoints(int memberNo, String password) {
        BonusMember member = members.get(memberNo);
        return (member == null || !member.okPassword(password)) ? -1 : member.getPoints();
    }

    public boolean registerPoints(Integer memberNo, int poeng) {
        BonusMember member = this.members.get(memberNo);
        if (member != null) {
            member.registerPoints(poeng);
            return true;
        } else return false;
    }

    public void checkMembers(LocalDate date) {
        members.forEach((integer, bonusMember) -> {
            if ((bonusMember instanceof SilverMember || bonusMember instanceof BasicMember) && bonusMember.findQualificationPoints(date) >= GOLD_LIMIT) {
                members.put(integer, new GoldMember(bonusMember.getMemberNo(),
                        bonusMember.getPersonals(),
                        bonusMember.getEnrolledDate(),
                        bonusMember.getPoints()));
            } else if (bonusMember instanceof BasicMember && bonusMember.findQualificationPoints(date) >= SILVER_LIMIT) {
                members.put(integer, new SilverMember(bonusMember.getMemberNo(),
                        bonusMember.getPersonals(),
                        bonusMember.getEnrolledDate(),
                        bonusMember.getPoints()));
            }
        });
    }

    public ObservableList<BonusMember> getObservableList() {
        return FXCollections.observableList(new ArrayList<>(members.values()));
    }

    public ArrayList<BonusMember> removeMember(ObservableList<BonusMember> bonusMembers) {
        ArrayList<BonusMember> deletedMembers = new ArrayList<>(bonusMembers.size());
        for (BonusMember bonusMember : bonusMembers) {
            deletedMembers.add(members.remove(bonusMember.getMemberNo()));
        }
        return deletedMembers;
    }
}
