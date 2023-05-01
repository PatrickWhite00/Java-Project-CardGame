public class Card
{
    //declaring variables
    private final int RANK, SUIT,COLOUR;

    // arrays for the cards ranks and suits
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] COLOURS={"Red","Black"};

    //constructor to pass the rank and the suit required as parameters
    public Card(int r, int s) {
        RANK = r;
        SUIT = s;
        if(s==1 || s==2)
            COLOUR=0;
        else
            COLOUR=1;
    }

    public int getRANK() {
        return RANK;
    }

    public int getSUIT() {
        return SUIT;
    }

    public int getCOLOUR()
    {
        return COLOUR;
    }

    public String toString()
    {
        return this.RANKS[RANK]+" of "+SUITS[SUIT];
    }

    //+" of "+COLOURS[COLOUR]
}
