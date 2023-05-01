import java.io.IOException;
import java.util.Scanner;

public class Main
{
    static List<HighScores> highScoresList=new List<>();
    public static void main(String args[])
    {
        mainMenu();

    }

    public static void mainMenu()
    {
        Scanner input=new Scanner(System.in);
        System.out.println("\t\tSTREAK\t\t");
        System.out.println("-----------------------------");
        while (true)
        {
            System.out.print("1. Single player game\n2. 2-player game\n3. View Hi-Score table\n9. Exit\nEnter choice > ");

            String selection=input.next();
            if(selection.equals("1"))
            {
                singlePlayer();
            }
            else if(selection.equals("2"))
            {
                twoPlayer();
            }
            else if(selection.equals("3"))
            {
                highScore();

            }
            else if(selection.equals("9"))
            {
                System.exit(0);
            }
            else
            {
                System.out.println("Please select correct choice.\n");
            }
        }
    }

    public static void singlePlayer()
    {
        Scanner input=new Scanner(System.in);
        Deck deck=new Deck();
        int numberOfCards;
        while (true)
        {
            System.out.print("\nHow many cards do you want to play with(5-10)? >");
            String s=input.next();
            try {
                numberOfCards=Integer.parseInt(s);
                if(numberOfCards>=5&&numberOfCards<=10)
                {
                    break;
                }
                else
                {
                    System.out.println("Please select number of cards between 5 to 10.");
                }
            }catch (Exception e)
            {
                System.out.println("Please enter integer number.");
            }
        }

        System.out.print("\nEnter player name > ");
        String playerName=input.next();

        deck.handCards(numberOfCards);
        deck.sortHandCard();

        boolean exit=true;
        int count=1;
        String history;

        String replay[]=new String[numberOfCards];
        List<String> list=new List<>();

        while (exit && count<=numberOfCards)
        {
            history="";
            System.out.println("\n"+playerName);
            history+=playerName+"\n";
            history+=deck.printCardsInHand()+"\n";
            System.out.println("\nMax Streak Value is "+deck.streak()+"\n");
            while (true)
            {
                System.out.print(count+" of "+numberOfCards+" : Chose card to change or x to exit > ");
                String s=input.next();
                if(s.equalsIgnoreCase("x"))
                {
                    exit=false;
                    break;
                }
                try {
                    int cardNumber=Integer.parseInt(s);
                    if(cardNumber>0&& cardNumber<=numberOfCards)
                    {
                        history+="Selection was "+deck.getCardValue(cardNumber-1);
                        deck.replaceCard(cardNumber-1);
                        break;
                    }
                }catch (Exception e)
                {
                    System.out.println("\nPlease enter an integer.\n");
                }
            }
            replay[count-1]=history;
            list.add(history);
            count++;
        }



        System.out.println("\n*******************\nStreak value is "+deck.streak()+"\n*******************\n");

        highScoresList.add(new HighScores(deck.streak(),playerName));

        System.out.print("See replay?(y/n) > ");
        String choice=input.next();
        System.out.println();

        if(choice.equalsIgnoreCase("y"))
        {
            System.out.println("REPLAY\n------\n");
            for (int i=0;i<list.size();i++)
            {
                System.out.println(list.get(i));
                if(list.size()>i+1)
                {
                    System.out.print("(more....) >");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }



        //mainMenu();

    }

    public static void twoPlayer()
    {
        Scanner input=new Scanner(System.in);

        int numberOfCards;
        while (true)
        {
            System.out.print("\nHow many cards do you want to play with(5-10)? >");
            String s=input.next();
            try {
                numberOfCards=Integer.parseInt(s);
                if(numberOfCards>=5&&numberOfCards<=10)
                {
                    break;
                }
                else
                {
                    System.out.println("Please select number of cards between 5 to 10.");
                }
            }catch (Exception e)
            {
                System.out.println("Please enter integer number.");
            }
        }

        System.out.print("\nEnter player 1 name > ");
        String playerOneName=input.next();

        System.out.print("Enter player 2 name > ");
        String playerTwoName=input.next();
        int round=1;

        int playerOneMatchScore=0,playerTwoMatchScore=0;

        while (round<=3)
        {
            Deck player1Deck=new Deck();
            Deck player2Deck=new Deck();
            System.out.println("\n*******************\nRound "+round+" of 3"+"\n*******************\n");
            gamePlay(numberOfCards,player1Deck,playerOneName);
            gamePlay(numberOfCards,player2Deck,playerTwoName);

            playerOneMatchScore+=player1Deck.streak();
            playerTwoMatchScore+=player2Deck.streak();

            System.out.println("\nEND OF ROUND "+round);

            System.out.println("\n"+playerOneName+" "+player1Deck.streak()+" "+playerTwoName+" "+player2Deck.streak()+"\n");

            System.out.println("MATCH SCORE\n"+playerOneName+" "+playerOneMatchScore+" "+playerTwoName+" "+playerTwoMatchScore+"");

            round++;

        }

    }

    public static void gamePlay(int numberOfCards,Deck deck,String playerName)
    {
        Scanner input=new Scanner(System.in);
        boolean exit=true;
        int count=1;

        deck.handCards(numberOfCards);

        while (exit && count<=numberOfCards)
        {
            System.out.println("\n"+playerName);
            deck.printCardsInHand();
            System.out.println("\nMax Streak Value is "+deck.streak()+"\n");
            while (true)
            {
                System.out.print(count+" of "+numberOfCards+" : Chose card to change or x to exit > ");
                String s=input.next();
                if(s.equalsIgnoreCase("x"))
                {
                    exit=false;
                    break;
                }
                try {
                    int cardNumber=Integer.parseInt(s);
                    if(cardNumber>0&& cardNumber<=numberOfCards)
                    {
                        deck.replaceCard(cardNumber-1);
                        count++;
                        break;
                    }
                    else
                    {
                        System.out.println("\nPlease select correct card.\n");
                    }
                }catch (Exception e)
                {
                    System.out.println("\nPlease enter an integer.\n");
                }
            }
        }

        System.out.println("\n*******************\nStreak value is "+deck.streak()+"\n*******************\n");
        highScoresList.add(new HighScores(deck.streak(),playerName));
    }


    public static void highScore()
    {
        sortHighScoreList();
        System.out.println("\nHIGH SCORES\n---------------\n");
        for (int i=0;i<highScoresList.size();i++)
        {
            System.out.println(highScoresList.get(i));
            if(i==4)
            {
                break;
            }
        }
        System.out.println();
    }


    public static void sortHighScoreList()
    {
        for (int i=0;i<highScoresList.size();i++)
        {
            for (int j=i+1;j<highScoresList.size();j++)
            {
                if(highScoresList.get(i).getScore()< highScoresList.get(j).getScore())
                {
                    HighScores highScores=highScoresList.get(i);
                    highScoresList.addAt(i,highScoresList.get(j));
                    highScoresList.addAt(j,highScores);
                }
            }
        }
    }
}
