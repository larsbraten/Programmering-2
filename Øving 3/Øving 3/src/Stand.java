public class Stand extends Tribune {
    private int noSoldTickets = 0;

    public Stand(String tribuneName, int capacity, int price) {
        super(tribuneName, capacity, price);
    }
    @Override
    public int findNumberOfSoldTickets() {
        return noSoldTickets;
    }
    @Override
    public int findIncome() {
        return (noSoldTickets * getPrice());
    }
    @Override
    public Ticket[] buyTickets(int noTickets) {
        Ticket[] ticketList = new Ticket[noTickets];
        if((getCapacity() - noSoldTickets) >= noTickets) {
            for(int i = 0; i < noTickets; i++) {
                StandingTicket standingTicket = new StandingTicket(getTribuneName(), getPrice());
                ticketList[i] = standingTicket;
                noSoldTickets++;
            }
        } else {
            return null;
        }
        return ticketList;
    }

    @Override
    public Ticket[] buyTickets(String[] nameList) {
        return buyTickets(nameList.length);
    }
}
