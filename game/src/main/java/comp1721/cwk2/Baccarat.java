package comp1721.cwk2;


import java.util.Scanner;

public class Baccarat {

    private int playerWin = 0;
    private int bankerWin = 0;
    private int tie = 0;
    private int round = 1;
    private String result;
    private Shoe gameShoe;
    private BaccaratHand playerHand;
    private BaccaratHand bankerHand;

    int playerScore = 0;
    int bankerScore = 0;

    public Baccarat() {
        gameShoe = new Shoe(8);
        playerHand = new BaccaratHand();
        bankerHand = new BaccaratHand();
    }


    public void autoSimulate() {
        //deals cards for both player and banker
        while (gameShoe.size()>=6){
            System.out.println("ROUND  "+round);
            playerHand.add(gameShoe.deal());
            bankerHand.add(gameShoe.deal());
            playerHand.add(gameShoe.deal());
            bankerHand.add(gameShoe.deal());

            //calculates the values of the bankers and players hands
            playerScore = playerHand.value();
            bankerScore = bankerHand.value();

            // runs find winner function in order to determine who wins the round as long as values > 5
            if (playerScore > 5 && bankerScore > 5){
                findWinner();

            }



            //does player need a third card
            if (playerScore <= 5) {
                System.out.println("Player: " + playerHand.toString() + " = " + playerScore);
                System.out.println("Banker: " + bankerHand.toString() + " = " + bankerScore);

                System.out.println("Dealing third card to player...");
                Card playerThirdCard = gameShoe.deal();
                playerHand.add(playerThirdCard);
                int thirdCardValue = playerThirdCard.value();

                if (thirdCardValue == 9) {
                    thirdCardValue = -1;
                } else if (thirdCardValue == 8) {
                    thirdCardValue = -2;
                }

                thirdCardValue = thirdCardValue / 2 + 3;
                //calculates third card value
                if (bankerScore <= thirdCardValue) {

                    System.out.println("Dealing third card to banker...");
                    bankerHand.add(gameShoe.deal());
                    bankerScore = bankerHand.value();
                }

                playerScore = playerHand.value();
                findWinner();

            } else {
                //determines if banker needs a third card
                if (bankerScore <= 5) {
                    System.out.println("Player: " + playerHand.toString() + " = " + playerScore);
                    System.out.println("Banker: " + bankerHand.toString() + " = " + bankerScore);

                    System.out.println("Dealing third card to banker...");
                    bankerHand.add(gameShoe.deal());
                    bankerScore = bankerHand.value();
                    findWinner();

                }

            }
            playerHand.discard();
            bankerHand.discard();
            //clears hand and adds round.
            round++;

        }
        printResult(bankerWin,playerWin,tie,--round);


    }



    public void humanSimulate() {

        Scanner input = new Scanner(System.in);
        //when player wants to play another game
        char choice ='y';

        while (choice != 'n'){
            System.out.println("ROUND  "+round);

            playerHand.add(gameShoe.deal());
            bankerHand.add(gameShoe.deal());
            playerHand.add(gameShoe.deal());
            bankerHand.add(gameShoe.deal());


            playerScore = playerHand.value();
            bankerScore = bankerHand.value();


            if (playerScore > 5 && bankerScore > 5){
                findWinner();

            }



            //does player need a third card
            if (playerScore <= 5) {
                System.out.println("Player: " + playerHand.toString() + " = " + playerScore);
                System.out.println("Banker: " + bankerHand.toString() + " = " + bankerScore);

                System.out.println("Dealing third card to player...");
                Card playerThirdCard = gameShoe.deal();
                playerHand.add(playerThirdCard);
                int thirdCardValue = playerThirdCard.value();

                if (thirdCardValue == 9) {
                    thirdCardValue = -1;
                } else if (thirdCardValue == 8) {
                    thirdCardValue = -2;
                }

                thirdCardValue = thirdCardValue / 2 + 3;

                if (bankerScore <= thirdCardValue) {

                    System.out.println("Dealing third card to banker...");
                    bankerHand.add(gameShoe.deal());
                    bankerScore = bankerHand.value();
                }

                playerScore = playerHand.value();
                findWinner();

            } else {
                if (bankerScore <= 5) {
                    System.out.println("Player: " + playerHand.toString() + " = " + playerScore);
                    System.out.println("Banker: " + bankerHand.toString() + " = " + bankerScore);

                    System.out.println("Dealing third card to banker...");
                    bankerHand.add(gameShoe.deal());
                    bankerScore = bankerHand.value();
                    findWinner();

                }

            }

            if(gameShoe.size()>=6){
                System.out.print("Another round? (y/n): ");
                choice = input.next().charAt(0);
                playerHand.discard();
                bankerHand.discard();
                round++;

            }else {
                break;
            }



        }
        printResult(bankerWin,playerWin,tie,--round);

    }




    public void findWinner(){
        //checks if scores are equal
        if (playerScore == bankerScore) {
            System.out.println("Player: " + playerHand.toString() + " = " + playerScore);
            System.out.println("Banker: " + bankerHand.toString() + " = " + bankerScore);

            tie++;

            System.out.println("TIE");
        //checks if player won
        } else if (playerScore > bankerScore) {
            System.out.println("Player: " + playerHand.toString() + " = " + playerScore);
            System.out.println("Banker: " + bankerHand.toString() + " = " + bankerScore);
            playerWin++;
            System.out.println("Player Win!");

        }
        //if the two above are not true then banker wins.
        else{
            System.out.println("Player: " + playerHand.toString() + " = " + playerScore);
            System.out.println("Banker: " + bankerHand.toString() + " = " + bankerScore);
            bankerWin++;
            System.out.println("Banker Win!");

        }

    }




    public void printResult(int bankerWin, int playerWin, int tie, int rounds){
    //overview of the game session.
        System.out.println(rounds + " rounds played");
        System.out.println(playerWin + " player wins");
        System.out.println(bankerWin + " banker wins");
        System.out.println(tie + " ties");
    }


    public static void main(String[] args) {



        Baccarat currentGame = new Baccarat();
        //this is for human interaction option
        if(args.length>0){
            currentGame.humanSimulate();
        }
        //this is for without human interaction mod
        //now playing without human interaction
        else{
            currentGame.autoSimulate();
        }
    }

}