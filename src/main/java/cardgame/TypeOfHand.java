package cardgame;

/**
 * enumeration of type of a hand of cards
 * @author reniaL
 */
public enum TypeOfHand {
    // declare constants of enum type
    NOTHING(0, "Nothing"), PAIR(1, "A Pair"), THREE(2, "Three Of A Kind"), DOUBLE_PAIR(3, "Two Pairs"), HOUSE(4,
            "House"), FOUR(5, "Four Of A Kind"), FLUSH(6, "Flush"), STRAIGHT(7, "Straight");
    
    private final int type;
    
    private final String typeName;
    
    TypeOfHand(int theType, String theTypeName) {
        type = theType;
        typeName = theTypeName;
    }
    
    public int getType() {
        return type;
    }
    
    public String getTypeName() {
        return typeName;
    }
}