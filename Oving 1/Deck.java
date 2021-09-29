
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {

  ArrayList<Card> cards;

  public Deck() {
    cards = new ArrayList<>();
  }

  /* Oppgave A */
  public void makeDeck() {
    for (int j = 1; j < 14; j++) {
      cards.add(new Card('S', j));
      cards.add(new Card('H', j));
      cards.add(new Card('D', j));
      cards.add(new Card('C', j));
    }
  }

  /* Oppgave B */
  public ArrayList<Card> assign(int n) {
    Random rand = new Random();
    ArrayList<Card> randCards = new ArrayList<>();
    IntStream.range(0, n).mapToObj(i -> cards.get(rand.nextInt(cards.size()))).forEach(c -> {
      randCards.add(c);
      cards.remove(c);
    });
    return new ArrayList<>(randCards);
  }

  /* Oppgave C */
  public void spades(Collection<Card> list) {
    (list.stream()
        .filter(c -> c.getSuit() == 'S'))
        .forEach(x -> System.out.print(x + "  " + "\b"));
  }

  /* Oppgave D */
  public List<Card> hearts(Collection<Card> list) {
    return list.stream()
        .filter(c -> c.getSuit() == 'H')
        .collect(Collectors.toList());
  }

  /* Oppgave E */
  public List suite(Collection<Card> list) {
    return list.stream()
        .map(Card::getSuit)
        .collect(Collectors.toList());
  }

  /* Oppgave F */
  public int value(Collection<Card> list) {
    return list.stream()
        .map(Card::getFace)
        .reduce(Integer::sum).get();
    //.reduce((a,b)-> a+b).get();
  }

  /* Oppgave G */
  public boolean queenOfSpades(Collection<Card> list) {
    return list.stream()
        .anyMatch(c -> c.getSuit() == 'S' && c.getFace() == 12);
  }

  /* Oppgave H */
  public boolean pokerFlush(Collection<Card> list) {
    Map<Character, Long> c = list.stream()
        .map(Card::getSuit)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    return c.values().stream().anyMatch(t -> t >= 5);
  }
}