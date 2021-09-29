import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BonusMember {

  private final int memberNo;
  private final Personals personals;
  private final LocalDate enrolledDate;
  int points = 0;
  static final double factorSilver = 1.20;
  static final double factorGold = 1.50;

  /**
   * @param memberNo     Medlemsnummer
   * @param personals    Personlig info
   * @param enrolledDate Datoen brukeren ble opprettet
   * @param point        poeng
   */
  public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate, int point) {
    this.memberNo = memberNo;
    this.personals = personals;
    this.enrolledDate = enrolledDate;
    this.points = point;
  }

  /**
   * @param memberNo     Medlemsnummer
   * @param personals    Personlig info
   * @param enrolledDate Datoen brukeren ble opprettet
   */
  public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate) {
    this.memberNo = memberNo;
    this.personals = personals;
    this.enrolledDate = enrolledDate;
  }

  public int getMemberNo() {
    return memberNo;
  }

  public Personals getPersonals() {
    return personals;
  }

  public LocalDate getEnrolledDate() {
    return enrolledDate;
  }

  public int getPoints() {
    return points;
  }

  /**
   * @param testDato testdato
   * @return long
   */
  public int findQualificationPoints(LocalDate testDato) {
    if (ChronoUnit.DAYS.between(enrolledDate, testDato) <= 365) {
      return points;
    }
    return 0;
  }

  /**
   * Metode som sjekker passord
   *
   * @param password passord
   * @return Boolean
   */
  public boolean okPassword(String password) {
    return personals.okPassword(password);
  }

  /**
   * metode som registrerer poeng.
   *
   * @param point poeng
   */
  public void registerPoints(int point) {
    this.points = this.points + point;
  }

  @Override
  public String toString() {
    return this.getClass() +
        "{" + "\n memberNo" +
        memberNo +
        "\n personals" +
        personals +
        "\n enrolledDate" +
        enrolledDate +
        "\n points" +
        points +
        "}";
  }
}

