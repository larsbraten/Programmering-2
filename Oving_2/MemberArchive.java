import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class MemberArchive {

  private static final int silverLimit = 25000;
  private static final int goldLimit = 75000;

  private ArrayList<BonusMember> memberList;

  /**
   * Arraylist over medlemmer
   *
   * @param memberList medlemsliste
   */
  public void setMemberList(ArrayList<BonusMember> memberList) {
    this.memberList = memberList;
  }

  {
    memberList = new ArrayList<>();
  }

  /**
   * Metode som finner brukerens poeng
   *
   * @param memberNo medlemsnummer
   * @param password brukerens passord
   * @return int
   */
  public int findPoints(int memberNo, String password) {
    return memberList.stream().filter(
        bonusMember -> (bonusMember.getMemberNo() == memberNo) && (bonusMember
            .okPassword(password))).findFirst().map(BonusMember::getPoints).orElse(-1);
  }

  /**
   * Metode som sjekker om en bruker eksisterer.
   *
   * @param memberNo medlemsnummer
   * @return Boolean
   */
  private boolean memberExists(int memberNo) {
    return (memberList.stream().anyMatch(m -> m.getMemberNo() == memberNo));
  }

  /**
   * Metode som finner medlem  basert på medlemsnummer
   *
   * @param memberNo medlemsnummer
   * @return Bonusmember
   */
  private BonusMember findMember(int memberNo) {
    return (memberList.stream().filter(m -> m.getMemberNo() == memberNo).findFirst().get());
  }

  /**
   * @param memberNo Medlemsnummer
   * @param points   poeng
   * @return boolean
   */
  public boolean registerPoints(int memberNo, int points) {
    if (memberExists(memberNo)) {
      BonusMember memberObj = findMember(memberNo);
      memberObj.registerPoints(points);
      return true;
    }
    return false;
  }

  /**
   * Metode som registrerer ny bruker.
   *
   * @param p            personen som skal legges inn
   * @param enrolledDate enrolled data
   */
  public void newMember(Personals p, LocalDate enrolledDate) {
    BasicMember newMember = new BasicMember(findAvailableNo(), p, enrolledDate, 0);
    memberList.add(newMember);
  }

  /**
   * Metode som oppgraderer kvalifiserende brukere.
   *
   * @return boolean
   */
  public boolean checkMembers() {
    boolean upgrade = false;
    int i = 0;
    while (i < memberList.size()) {
      if (memberList.get(i) instanceof BasicMember) {
        BasicMember member1 = (BasicMember) memberList.get(i);
        if (member1.getPoints() >= goldLimit) {
          GoldMember newMember = new GoldMember(member1.getMemberNo(), member1.getPersonals(),
              member1.getEnrolledDate(), member1.getPoints());
          memberList.set(i, newMember);
          upgrade = true;
        } else if (member1.getPoints() >= silverLimit) {
          SilverMember newMember = new SilverMember(member1.getMemberNo(), member1.getPersonals(),
              member1.getEnrolledDate(), member1.getPoints());
          memberList.set(i, newMember);
          upgrade = true;
        }

      } else if (memberList.get(i) instanceof SilverMember) {
        SilverMember member1 = (SilverMember) memberList.get(i);
        if (member1.getPoints() >= goldLimit) {
          GoldMember newMember = new GoldMember(member1.getMemberNo(), member1.getPersonals(),
              member1.getEnrolledDate(), member1.getPoints());
          memberList.set(i, newMember);
          upgrade = true;
        }
      }
      i++;
    }
    return upgrade;
  }

  /**
   * Metode som finner ledige medlemsnummer.
   *
   * @return int
   */
  int findAvailableNo() {
    Random ran = new Random();
    int memberNo = ran.nextInt(1000000);
    boolean change = true;
    while (change) {
      boolean availableNumber = true;
      for (BonusMember bonusMember : memberList) {
        if (bonusMember.getMemberNo() == memberNo) {
          availableNumber = false;
          break;
        }
      }
      if (!availableNumber) {
        memberNo = ran.nextInt(1000000);
      } else {
        change = false;
      }
    }
    return memberNo;
  }
}