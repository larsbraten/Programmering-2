import java.time.LocalDate;

public class GoldMember extends BonusMember {

  public GoldMember(int memberNo, Personals personals, LocalDate enrolledDate, int point) {
    super(memberNo, personals, enrolledDate, point);
  }

  /**
   * Metode som registrerer poeng
   *
   * @param points poeng
   */
  public void registerPoints(int points) {
    super.registerPoints((int) (points * factorGold));
  }
}