/**
 * 
 */
package blackjack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is Class Card.  It will initialize all fields that are 
 * affiliated with the individual card that is found in a deck
 * of playing cards.  It will also be responsible for assigning the 
 * actual value to the card itself.  
 * 
 * @author Ryan Godfrey, Will Graham
 * @version 1.0
 */

public class Card {
  public static final int LOW_ACE = 1; // Ace value of 1
  public static final int HIGH_ACE = 11; // Ace value of 11
  private int value; // Value of card
  private boolean isAce; // is the card an Ace?
  
  /**
   * Constructor for class Card. It will initialize the boolean
   * value of the Ace and assign the value to all of the 
   * cards in the deck.  
   * @param cardName
   */
  public Card(String cardName) {
    this.isAce = false; // Ace is initialized to false
    //parseCardName(cardName.substring(0, cardName.length()-2)); // Passes only the # component, disregards suit
    
    if(cardName.length() == 2) {
      parseCardName(cardName.substring(0, cardName.length()-1));
    }
    else {
      parseCardName(cardName.substring(0, cardName.length()-2));
    }
  }
  
  /**
   * The parseCardName method sets the 
   * value to the individual card.
   * @param cardName
   */
  private void parseCardName(String cardName) {
    switch (cardName) {
      case "J": // if the card is a Jack
      case "Q": // if the card is a Queen
      case "K": // if the card is a King
        this.value = 10; // All J,Q,K will be set to 10
        break;
      case "A": // if the card is an Ace
        this.value = 1; // Default, will be switched in game logic if need be
        isAce = true; // change boolean value to true
        break;
      default: // the default Integer value of the remaining cards
        this.value = Integer.valueOf(cardName); // take in the value of the card
        break;
    }
  }
  
  /**
   * The setAceHigh method will use the 
   * value of 11 for an Ace.
   */
  public void setAceHigh() {
    this.value = HIGH_ACE; // The value of an Ace is 11
  }

  /**
   * The setAceLow method will use the
   * value of 1 for an Ace.
   */
  public void setAceLow() {
    this.value = LOW_ACE; // The value of an Ace is 1
  }

  /**
   * The getValue method will return the value.
   * @return the value
   */
  public int getValue() {
    return value; // return value
  }

  /**
   * The setValue method will set the value.
   * @param value the value to set
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * The isAce method will return the 
   * boolean value of whether the card is 
   * an Ace or not an Ace.  
   * @return the isAce
   */
  public boolean isAce() {
    return isAce; // return boolean value.  
  }

  public static boolean validCardValue(String value) {
    String regex = "([2-9]|10|[JQKA])[DHSC]";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(value);
    return matcher.matches();
  }


}
