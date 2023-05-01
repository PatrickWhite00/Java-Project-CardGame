import java.util.Random;

public class Deck
{
    // declaring variables
    private static final int NUMCARDS = 52;
    private List<Card> cards=new List<>();
    private List<Card> handCards=new List<>();
    private int cardIndex;

    // creating the deck of 52 unique cards
    public Deck() {
        // loop for each card in the array to be a new card object
        // rank remainder of /13 to get range of 0 - 11 for ranks
        // suit rank /13  to get 0 - 3 range for suits
        for (int thisCard = 0; thisCard < NUMCARDS; thisCard++)
        {
            Card card = new Card(thisCard % 13, thisCard / 13);
            cards.add(card);
        }
        // points to last card in array
        this.cardIndex = NUMCARDS - 1;
        // shuffling deck using the shuffle method
       this.shuffle();
    }

    // shuffling the deck using the random java utility
    private void  shuffle()
    {
        Card temp;
        int index;
        //swap current card with randomly chosen card as pointer goes around the loop
        for (int i = this.cards.size()- 1; i > 0; i--)
        {
            index = getNextCard();
            temp=cards.get(index);
            cards.addAt(index,cards.get(i));
            cards.addAt(i,temp);

        }
    }


    boolean init[] = new boolean[52];
    Random r = new Random();
    int getNextCard(){
        int i = r.nextInt(52);
        while(init[i])
            i = r.nextInt(52);
        init[i] = true;
        return i;
    }

    public void printDeck()
    {
        for (int i=0;i< cards.size();i++)
        {
            System.out.println(cards.get(i)+"  "+i);
        }

    }

    public void handCards(int card)
    {

        for (cardIndex=0;cardIndex<card;cardIndex++)
        {
            handCards.add(cards.get(cardIndex));
        }
    }

    public String printCardsInHand()
    {
        String string="";
        for (int i=0;i<handCards.size();i++)
        {
            System.out.println((i+1)+" : "+handCards.get(i));
            string+=(i+1)+" : "+handCards.get(i)+"\n";
        }
        return string;
    }

    public void sortHandCard()
    {
        for (int i=0;i<handCards.size();i++)
        {
            int index=i;

            for (int j=i+1;j<handCards.size();j++)
            {
                if(handCards.get(j).getRANK()<handCards.get(index).getRANK())
                {
                    index=j;
                }
            }
            Card card1=handCards.get(index);
            handCards.addAt(index,handCards.get(i));
            handCards.addAt(i,card1);
        }
    }

    public int streak()
    {
        int longestStreak=1;
        int currentStreak=1;
        int currentStreakRank=handCards.get(0).getRANK();
        int currentStreakSuit=handCards.get(0).getSUIT();
        int currentStreakColor=handCards.get(0).getCOLOUR();
        boolean isSuitSame=true,isColourSame=true;

        for (int i=1;i<handCards.size();i++)
        {
            if(currentStreakRank == handCards.get(i).getRANK()-1)
            {
                currentStreakRank=handCards.get(i).getRANK();
                if(currentStreakSuit!=handCards.get(i).getSUIT())
                {
                    isSuitSame=false;
                }
                if(currentStreakColor!=handCards.get(i).getCOLOUR())
                {
                    isColourSame=false;
                }
                currentStreak++;
            }
            else
            {
                if(isSuitSame && currentStreak>1)
                {
                    currentStreak+=2;
                }

                if(isColourSame && currentStreak>1 && !isSuitSame)
                {
                    currentStreak+=1;
                }

                if(currentStreak >longestStreak)
                {
                    longestStreak=currentStreak;
                }

                currentStreakRank=handCards.get(i).getRANK();
                currentStreakColor=handCards.get(i).getCOLOUR();
                currentStreakSuit=handCards.get(i).getSUIT();

                isSuitSame=true;
                isColourSame=true;
                currentStreak=1;
            }
        }

        if (currentStreak> longestStreak) {
            longestStreak = currentStreak;

        }


        return longestStreak;

    }

    public void replaceCard(int index)
    {
        handCards.addAt(index,cards.get(cardIndex));
        cardIndex++;
    }

    public String getCardValue(int index)
    {
        return handCards.get(index).toString();
    }

}
