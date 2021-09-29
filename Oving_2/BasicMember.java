import java.time.LocalDate;

public class BasicMember extends BonusMember {

  public BasicMember(int memberNo, Personals personals, LocalDate enrolledDate) {
    super(memberNo, personals, enrolledDate);
  }

  public BasicMember(int memberNo, Personals personals, LocalDate enrolledDate, int point) {
    super(memberNo, personals, enrolledDate, point);

  }
}
