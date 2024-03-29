class Personals {

  private final String surname;
  private final String firstname;
  private final String ePostadr;
  private String password;

  /**
   * KonstruktÃ¸r: Alle data mÃ¥ oppgis: fornavn, etternavn, ePostadr, passord Ingen av dataene kan
   * vÃ¦re null eller blanke strenger.
   */
  public Personals(String firstname, String surname, String ePostadr, String password) {
    if (firstname == null || surname == null || ePostadr == null || password == null ||
        firstname.trim().equals("") || surname.trim().equals("") ||
        ePostadr.trim().equals("") || password.trim().equals("")) {
      throw new IllegalArgumentException(
          "Ett eller flere konstruktørargumenter er null og/eller blanke.");
    }
    this.firstname = firstname.trim();
    this.surname = surname.trim();
    this.ePostadr = ePostadr.trim();
    this.password = password.trim();
  }

  public String getFirstname() {
    return firstname;
  }

  public String getSurname() {
    return surname;
  }

  public String getEPostadr() {
    return ePostadr;
  }

  /**
   * Metoden returnerer true dersom passordet er korrekt. Passordkontrollen skiller ikke mellom
   * store og små bokstaver.
   */
  public boolean okPassword(String password) {
    return this.password.equalsIgnoreCase(password);
  }
  /**
   * Metoden setter nytt passord, dersom det er forskjellig fra
   * det gamle. To passord betraktes som like dersom det kun er
   * forskjeller i store/smÃ¥ bokstaver.
   *
   * Metoden returnerer true dersom passordet ble endret, ellers false.
   */
  /**
   * Metode som endrer passord til en bruker.
   *
   * @param oldPassword Gammelt passord
   * @param newPassword Nytt passord
   * @return
   */
  public boolean changePassword(String oldPassword, String newPassword) {
    if (oldPassword == null || newPassword == null) {
      return false;
    }
    if (!password.equalsIgnoreCase(oldPassword.trim())) {
      return false;
    } else {
      password = newPassword.trim();
      return true;
    }
  }
} 