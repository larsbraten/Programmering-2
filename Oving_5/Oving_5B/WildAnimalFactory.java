import java.time.LocalDate;

public class WildAnimalFactory {
    private static WildAnimalFactory instance;

    private WildAnimalFactory() {
    }

    public ScandinavianWildAnimal newMaleBear(LocalDate arrivalDate, String name, LocalDate dateOfBirth,
                                              String address) {
        return new MaleIndividual("Bjørn", "Ursus arctos", "Ursidae", arrivalDate, name, dateOfBirth, true, address);
    }

    public ScandinavianWildAnimal newFemaleWolf(LocalDate arrivalDate, String name, LocalDate dateOfBirth,
                                                String address, int noLitters) {
        return new FemaleIndividual("Ulv", "Canis lupus", "Canidae", arrivalDate, name, dateOfBirth, true, address,
                noLitters);
    }

    public ScandinavianWildAnimal newFemaleBear(LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address, int noLitters) {
        return new FemaleIndividual("Bjørn", "Ursus arctos", "Ursidae", arrivalDate, name, dateOfBirth, true, address, noLitters);

    }

    public ScandinavianWildAnimal newMaleWolf(LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address) {
        return new MaleIndividual("Ulv", "Canis Lupus", "Canidae", arrivalDate, name, dateOfBirth, true, address);
    }


    public static WildAnimalFactory getInstance() {
        if (instance == null)
            instance = new WildAnimalFactory();

        return instance;
    }
}

