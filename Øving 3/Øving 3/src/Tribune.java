public abstract class Tribune {


    private final String tribuneName;
    private final int capacity;
    private final int price;

    public Tribune(String tribuneName, int capacity, int price) {
        this.tribuneName = tribuneName;
        this.capacity = capacity;
        this.price = price;
    }

    public String getTribuneName() {
        return tribuneName;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() {
        return price;
    }
    abstract public int findNumberOfSoldTickets();

    public int findIncome() {
       return(findNumberOfSoldTickets() * getPrice());
    }
    abstract public Ticket[] buyTickets(int noTickets);

    abstract public Ticket[] buyTickets(String[] nameList);

    public String toString() {

        return "Name: " + getTribuneName() +"\nCapacity: " + getCapacity() + "\nNumber of sold tickets: " + findNumberOfSoldTickets() + "\nInntekt: " + findIncome();

    }
}
