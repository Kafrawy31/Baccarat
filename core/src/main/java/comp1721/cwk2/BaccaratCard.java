package comp1721.cwk2;

// Implement the BaccaratCard class here
public class BaccaratCard extends Card {

    private Rank rank;
    private Suit suit;

    private int score = 0;


    public BaccaratCard(Rank r, Suit s) {
        super(r, s);
        this.rank = r;
        this.suit = s;
    }
}