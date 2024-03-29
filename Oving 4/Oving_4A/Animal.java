public abstract class Animal {

  public final String name;
  public final int animalID;

  public String getName() {
    return name;
  }

  public int getAnimalID() {
    return animalID;
  }

  public Animal(String name, int code) {
    this.name = name;
    this.animalID = code;


  }

  @Override
  public String toString() {
    return "Animal [name=" + name + ", code=" + animalID + "]";
  }
}