
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * @author Lars-Håvard Bråten
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        WildAnimalFactory scandinavian = WildAnimalFactory.getInstance();

        ScandinavianWildAnimal RobinVold = scandinavian.newFemaleWolf(LocalDate.of(2015, 4, 29), "Ulla", LocalDate.of(2015, 2, 26),
                "Innhegning 2", 2);

        log.info("Saved new female wolf");

        ScandinavianWildAnimal KarlLabrador = scandinavian.newMaleWolf(LocalDate.of(2015, 4, 29), "Karl", LocalDate.of(2015, 2, 26),
                "Innhegning 2");

        log.info("Saved new male wolf");

        ScandinavianWildAnimal Eivind = scandinavian.newMaleBear(LocalDate.of(1997, 4, 4), "Eivind", LocalDate.of(2017, 5, 3),
                "Innhegning 2");

        log.info("Saved new male bear");

        ScandinavianWildAnimal Arvid = scandinavian.newFemaleBear(LocalDate.of(1992, 6, 4), "Arvid", LocalDate.of(2019, 4, 3),
                "Innhegning 2", 4);

        log.info("Saved new female bear");


        /*Antar Logger er singleton design da jeg tror den henter en global instans av loggeren.*/

    }
}
