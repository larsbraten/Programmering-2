import java.util.stream.IntStream;

public class VIP extends Sit {
    private String[][] spectator; //tilskuere: antall rader * antall plasser pr rad

    public VIP(String tribuneName, int capacity, int price, int noRows) {
        super(tribuneName, capacity, price, noRows);
        spectator = new String[noRows][getCapacity() / noRows];
    }

    @Override
    public Ticket[] buyTickets(int noTickets) {
        return null;
    }

    @Override
    public Ticket[] buyTickets(String[] nameList) {
        Ticket[] ticketList = new Ticket[nameList.length];
        if ((getCapacity() - findNumberOfSoldTickets()) >= nameList.length) {
            IntStream.range(0, nameList.length).forEach(i -> {
                int[] freeRow = findFreeSpot(nameList.length);
                assert freeRow != null;
                SittingTicket ticket = new SittingTicket(getTribuneName(), getPrice(), freeRow[0], freeRow[1]);
                ticketList[i] = ticket;
                spectator[freeRow[0]][freeRow[1]] = nameList[i];
            });
            return ticketList;
        }
        return null;
    }
    private int[] findFreeSpot(int noSpots) {
        int[]rowSpot = new int[2];
        int startingSpot = -1;
        int i = 0;
        while (i < spectator.length) {
            int counter = spectator[i].length; //Number of spots
            int j = 0;
            while (j < noSpots) {
                if (spectator[i][j] == null) {
                    counter --;
                    if(startingSpot == -1) {
                        startingSpot =j;
                        rowSpot[1] = startingSpot;
                    }
                }
                j++;
            }
            if(counter >= 0) {
                rowSpot[0] = i;
                return rowSpot;
            }
            i++;
        }
        return null;
    }

    @Override
    public int findNumberOfSoldTickets() {
        int sold = 0;
        for (String[] strings : spectator) {
            for (String string : strings) {
                if (string != null) {
                    sold++;
                }
            }
        }
        return sold;
    }

}
