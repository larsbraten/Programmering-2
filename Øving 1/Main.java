import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Deck myDeck = new Deck();
        myDeck.makeDeck();
        ArrayList list = new ArrayList<>(myDeck.assign(51));
        System.out.println("Skriv ut fullstendig liste " +list);
        System.out.println("\n" +"Alle sparkort: ");
        myDeck.spades(list);
        System.out.println("\n" + "Alle hjerter: " + "\n" +
                myDeck.hearts(list) + "\n" + "Kortfarger: " + "\n" +
                myDeck.suite(list) + "\n" + "Kortverdier: " + "\n" +
                myDeck.value(list) + "\n" + "Finnes spar dame i listen? \n" +
                myDeck.queenOfSpades(list) + "\n" + "Er lista en poker flush? " + "\n" +
                myDeck.pokerFlush(list) + "\n");
    }
}