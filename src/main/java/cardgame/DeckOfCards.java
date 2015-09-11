package cardgame;

import java.util.Random;

/**
 * presenting a deck of cards(52)
 * @author reniaL
 */
public class DeckOfCards {
    
    private Card deck[];
    
    private int currentCard;
    
    private static final int NUM = 52;
    
    private Random rand;
    
    public DeckOfCards() {
        deck = new Card[NUM];
        currentCard = 0;
        rand = new Random();
        
        for (int i = 0; i < deck.length; i++) {
            deck[i] = new Card(i / 4, i % 4);
        }
    }
    
    public void Shuffle() {
        currentCard = 0;
        
        for (int first = 0; first < deck.length; first++) {
            int second = rand.nextInt(NUM);
            
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }
    
    public Card DealCard() {
        if (currentCard < deck.length) {
            return deck[currentCard++];
        } else {
            return null;
        }
    }
}