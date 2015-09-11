package cardgame;

/**
 * Object presenting one card
 * @author reniaL
 */
public class Card {
    
    private int face;
    
    private int suit;
    
    private static final String faces[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    
    private static final String suits[] = { "Diamonds", "Clubs", "Hearts", "Spades" };
    
    public Card(int cardFace, int cardSuit) {
        face = cardFace;
        suit = cardSuit;
    }
    
    public int getFace() {
        return face;
    }
    
    public int getSuit() {
        return suit;
    }
    
    /**
     * Get the "value" of this card
     */
    public int getValue() {
        return face * 4 + suit;
    }
    
    @Override
    public String toString() {
        return faces[face] + " of " + suits[suit];
    }
}