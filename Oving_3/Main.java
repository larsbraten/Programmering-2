import java.util.Arrays;
import java.util.Comparator;

import static java.lang.System.out;


public class Main {

    public static void main(String[] args) {
        Stand standingTribune1 = new Stand("Standing1", 100, 500);
        Stand standingTribune2 = new Stand("Standing2", 74, 400);
        Sit sittingTribune = new Sit("Sitting1", 500, 10, 10);
        VIP vipTribune = new VIP("Vipspot", 10000, 30000, 5);
        Tribune[] tribuneList = {standingTribune1, standingTribune2, sittingTribune, vipTribune};

        //Methods which buys tickets
        String[] test = {"Lars", "Karl", "Henrik"};
        //StandingTribune1
        Ticket[] ticketListStand = standingTribune1.buyTickets(5);
        for (Ticket b : ticketListStand) {
            out.println(b.toString());
        }
        //StandingTribune2
        Ticket[] ticketListStand2 = standingTribune2.buyTickets(test);
        for (Ticket b : ticketListStand2) {
            out.println(b.toString());
        }

        //SittingTribune
        Ticket[] sittingList = sittingTribune.buyTickets(5);
        for (Ticket b : sittingList) {
            out.println(b.toString());
        }
        //VIP-tribune
        Ticket[] vipList1 = vipTribune.buyTickets(test);
        for (Ticket b : vipList1) {
            out.println(b.toString());
        }
        for (Tribune tribune : tribuneList) {
            out.println(tribune.toString());
        }

        //Oppgave E:
        //1: Sortering vha en metode i klassen Arrays:

    }

    private static Tribune[] sorter(Tribune[] trib) {
        Arrays.sort(trib, Comparator.comparingInt(tribune -> tribune.findIncome()));
        return trib;
    }
}


