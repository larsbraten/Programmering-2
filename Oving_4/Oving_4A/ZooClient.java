import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ZooClient {

  public static void main(String[] args) throws ZooException {

    Zoo zoo = new Zoo("Kristiansand Dyrepark");

    Collection<Animal> animals = new ArrayList<Animal>();

    animals.add(new Crocodile("Crocodylus niloticus", 1001));
    animals.add(new Crocodile("Crocodylus niloticus", 1002));
    animals.add(new Crocodile("Crocodylus porosus", 1101));
    animals.add(new Crocodile("Crocodylus porosus", 1102));
    animals.add(new Pelican("Brown Pelican  ", 4001));
    animals.add(new Pelican("Dalmatian Pelican  ", 4101));

    animals.add(new Whale("Blue whale", 2001));
    animals.add(new Whale("Blue whale", 2002));
    animals.add(new Whale("Minke whale", 2101));
    animals.add(new Whale("Minke whale", 2102));
    animals.add(new Bat("Acerodon ", 3001));
    animals.add(new Bat("Cistugo  ", 3002));
    zoo.setAnimals(animals);
    //Oppgave 2A
    List<Object> flyers = zoo.getAnimals().stream().filter(a -> a instanceof Flyable)
        .collect(Collectors.toList());
    flyers.forEach(a -> {
      ((Flyable) a).fly();
      Animal obj = (Animal) a;

      System.out.println(obj.getName());
    });
    //Oppgave 2B
    List<Object> mammalJump = zoo.getAnimals().stream().filter(b -> b instanceof Mammal)
        .filter(b -> b instanceof Jumpable).collect(Collectors.toList());
    mammalJump.forEach(b -> {
      ((Jumpable) b).jump();
      Animal obj = (Animal) b;

      System.out.println(obj.getName());
    });
    //Oppgave 3A
    try {
      List<Object> walker = zoo.getAnimals().stream().filter(p -> p instanceof Walkable)
          .collect(Collectors.toList());
      walker.stream().forEach(p -> {
        ((Flyable) p).fly();
      });
    } catch (Exception e) {
      throw new ZooException(e.getMessage());
    } finally {
      System.out.println("Done catching");
    }
  }
}