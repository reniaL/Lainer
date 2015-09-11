package cardgame;

public class CardGameTest {
    
    public static void main(String[] args) {
        DeckOfCards deckOfCards = new DeckOfCards();
        deckOfCards.Shuffle();
        HandOfCards hand = new HandOfCards(deckOfCards);
        System.out.println("Before redeal:");
        hand.display();
        
        hand.reDeal(deckOfCards);
        System.out.println("\nAfter redeal:");
        hand.display();
    }
}