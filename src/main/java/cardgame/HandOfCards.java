package cardgame;

import java.util.ArrayList;

/**
 * A. a pair <br>
 * B. two pairs <br>
 * C. three of a kind <br>
 * D. a full house <br>
 * E. four of a kind <br>
 * F. a flush <br>
 * G. a straight
 */
public class HandOfCards {
    
    private static final int NUM = 5; // number of cards
    
    private Card myCards[];
    
    private TypeOfHand type; // type of this hand
    
    private int value; // value of this hand
    
    private ArrayList<Integer> unneeded; // array of index of the unneeded cards
    
    /**
     * Deal from deckOfCards 5 times to make a hand of cards, and sort
     */
    public HandOfCards(DeckOfCards deckOfCards) {
        myCards = new Card[NUM];
        unneeded = new ArrayList<Integer>();
        for (int i = 0; i < NUM; i++) {
            myCards[i] = deckOfCards.DealCard();
        }
        
        // sort and analyze the cards on hand
        sortCards();
        analyze();
    }
    
    /**
     * Analyze the cards on hand to determine the type and value
     */
    private void analyze() {
        unneeded.clear();
        if (checkPair()) { // type is PAIR, continue to check if it's double pair,
                           // three, four, or house
            if (checkThree()) {
                if (!checkFour()) {
                    checkHouse();
                }
            } else {
                checkDoublePair();
            }
        } else if (!checkStraight()) {
            if (!checkFlush()) { // NOTHING
                type = TypeOfHand.NOTHING;
                value = myCards[4].getValue();
                for (int i = 0; i < 5; i++) {
                    unneeded.add(i);
                }
            }
        }
    }
    
    private boolean checkPair() {
        for (int i = 1; i < NUM; i++) {
            if (myCards[i].getFace() == myCards[i - 1].getFace()) {
                type = TypeOfHand.PAIR;
                value = type.getType() * 52 + myCards[i].getValue();
                for (int j = 0; j < NUM; j++) {
                    if ((j != i) && (j != i - 1)) {
                        unneeded.add(j);
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    private boolean checkDoublePair() {
        int i = 1;
        while ((i < NUM) && (myCards[i].getFace() != myCards[i - 1].getFace())) {
            i++;
        }
        for (int j = i + 2; j < NUM; j++) {
            if (myCards[j].getFace() == myCards[j - 1].getFace()) {
                unneeded.clear();
                type = TypeOfHand.DOUBLE_PAIR;
                value = type.getType() * 52 + myCards[j].getValue();
                if (j == 3) { // AABBX
                    unneeded.add(4);
                } else {
                    if (i == 1) { // AAXBB
                        unneeded.add(2);
                    } else { // XAABB
                        unneeded.add(0);
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    private boolean checkThree() {
        for (int i = 2; i < NUM; i++) {
            if ((myCards[i].getFace() == myCards[i - 1].getFace())
                    && (myCards[i].getFace() == myCards[i - 2].getFace())) {
                unneeded.clear();
                type = TypeOfHand.THREE;
                value = type.getType() * 52 + myCards[i].getValue();
                for (int j = 0; j < NUM; j++) {
                    if ((j != i) && (j != i - 1) && (j != i - 2)) {
                        unneeded.add(j);
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    private boolean checkHouse() {
        if ((myCards[0].getFace() == myCards[1].getFace()) && (myCards[3].getFace() == myCards[4].getFace())
                && ((myCards[2].getFace() == myCards[0].getFace()) || (myCards[2].getFace() == myCards[4].getFace()))) {
            type = TypeOfHand.HOUSE;
            if (myCards[2].getFace() == myCards[0].getFace()) {
                value = type.getType() * 52 + myCards[2].getValue();
            } else {
                value = type.getType() * 52 + myCards[4].getValue();
            }
            unneeded.clear();
            return true;
        }
        return false;
    }
    
    private boolean checkFour() {
        for (int i = 3; i < NUM; i++) {
            if ((myCards[i].getFace() == myCards[i - 1].getFace())
                    && (myCards[i].getFace() == myCards[i - 2].getFace())
                    && (myCards[i].getFace() == myCards[i - 3].getFace())) {
                type = TypeOfHand.FOUR;
                value = type.getType() * 52 + myCards[i].getValue();
                unneeded.clear();
                return true;
            }
        }
        return false;
    }
    
    private boolean checkFlush() {
        int suit = myCards[0].getSuit();
        for (int i = 1; i < NUM; i++) {
            if (myCards[i].getSuit() != suit) {
                return false;
            }
        }
        type = TypeOfHand.FLUSH;
        value = type.getType() * 52 + myCards[4].getValue();
        unneeded.clear();
        return true;
    }
    
    private boolean checkStraight() {
        for (int i = 1; i < NUM; i++) {
            if ((myCards[i].getFace() - myCards[i - 1].getFace() != 1)) {
                return false;
            }
        }
        type = TypeOfHand.STRAIGHT;
        value = type.getType() * 52 + myCards[4].getValue();
        unneeded.clear();
        return true;
    }
    
    /**
     * Sort cards on hand, insertion sort
     */
    private void sortCards() {
        Card tempCard;
        for (int i = 0; i < NUM; i++) {
            for (int j = i; (j > 0) && (myCards[j].getValue() < myCards[j - 1].getValue()); j--) {
                tempCard = myCards[j];
                myCards[j] = myCards[j - 1];
                myCards[j - 1] = tempCard;
            }
        }
    }
    
    public void reDeal(DeckOfCards deckOfCards) {
        for (int i : unneeded) {
            myCards[i] = deckOfCards.DealCard();
        }
        
        // sort and analyze the cards on hand
        
        sortCards();
        analyze();
    }
    
    public void display() {
        for (int i = 0; i < NUM; i++) {
            System.out.print(myCards[i] + "\t");
        }
        System.out.println("\nType: " + type.getTypeName());
        System.out.println("Value: " + value);
        if (unneeded.size() == 0) {
            System.out.println("Unneeded: empty");
        } else {
            System.out.println("Unneeded: " + unneeded);
        }
    }
    
    public TypeOfHand getType() {
        return type;
    }
    
    public int getValue() {
        return value;
    }
    
    public ArrayList<Integer> getUnneeded() {
        return unneeded;
    }
    
    public Card[] getMyCards() {
        return myCards;
    }
    
    public void setMyCards(Card[] myCards) {
        this.myCards = myCards;
        
        sortCards();
        analyze();
    }
}