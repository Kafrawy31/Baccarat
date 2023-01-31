package comp1721.cwk2;

// Implement the Shoe class here


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import comp1721.cwk2.Card.Rank;
import comp1721.cwk2.Card.Suit;

public class Shoe {

    private final List<Card> shoe = new ArrayList<>();


    public Shoe(int decks) {
        // Clear shoe if not empty
        if (!shoe.isEmpty()) {
            shoe.clear();
        }

        if (decks == 8 || decks == 6) {
            // Fill shoe with given number of decks
            for (int i = 0; i < decks; i++) {
                for (Rank rank : Rank.values()) {
                    for (Suit suit : Suit.values()) {
                        shoe.add(new BaccaratCard(rank, suit));
                    }
                }
            }
            shuffle();
        } else {
            throw new CardException("Deck value should be either 6 or 8");
        }
    }
    public Card deal() {
        if (shoe.isEmpty()) {
            throw new IllegalStateException("Shoe is empty");
        }
        return shoe.remove(0);
    }

    public void shuffle(){

        // Shuffle shoe
        Collections.shuffle(shoe);
    }

    public int size(){
        return shoe.size();
    }

    public boolean isEmpty(){
        return shoe.isEmpty();
    }
}
