import java.util.Arrays;

public class Sit extends Tribune {

    private final int[] noBusy;

    public Sit(String tribuneName, int capacity, int price, int noRows) {
        super(tribuneName, capacity, price);
        noBusy = new int[noRows];


    }


    @Override
    public int findNumberOfSoldTickets() {
        int sum = Arrays.stream(noBusy).sum();
        return sum;
    }

    private int findFreeSpot(int noTickets) {
        int kapPrRad = getCapacity() / noBusy.length;

        if (noTickets > getCapacity()) {
            return -1;
        }

        for (int i = 0; i < noBusy.length; i++) {
            if ((noBusy[i] < kapPrRad) && (kapPrRad - noBusy[i]) >= noTickets) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Ticket[] buyTickets(int noTickets) {
        Ticket[] ticketList = new Ticket[noTickets];
        int free = findFreeSpot(noTickets);
        if ((free != -1) && ((getCapacity() - findNumberOfSoldTickets()) >= noTickets)) {
            for (int i = 0; i < noTickets; i++) {
                int freeRow = findFreeSpot(noTickets);
                noBusy[freeRow]++;
                SittingTicket ticket = new SittingTicket(getTribuneName(), getPrice(), noBusy[freeRow],
                        freeRow);
                ticketList[i] = ticket;
            }
        } else {
            return null;
        }
        return ticketList;
    }

    @Override
    public Ticket[] buyTickets(String[] navneliste) {
        return buyTickets(navneliste.length);
    }
}