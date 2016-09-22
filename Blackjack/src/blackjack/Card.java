package blackjack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is Class Card. It will initialize all fields that are affiliated with the individual card
 * that is found in a deck of playing cards. It will also be responsible for assigning the actual
 * value to the card itself.
 * 
 * @author Ryan Godfrey, Will Graham
 * @version 1.0 9/18/2016
 */

public class Card {
  public static final int LOW_ACE = 1;
  public static final int HIGH_ACE = 11;
  private int value; // Numerical value of card
  private boolean isAce; // is the card an Ace?

  /**
   * Constructor for class Card. It will initialize the boolean value of the Ace and assign the
   * value to all of the cards in the deck.
   * 
   * @param cardName the card's name, of the form "nS" where n is 2-9,J,Q,K,A; s is H,S,D,C
   */
  public Card(String cardName) {
    this.isAce = false;
    cardName = cardName.substring(0, cardName.length() - 1); // Disregards suit for value calc.
    parseCardName(cardName);
  }

  /**
   * The parseCardName method sets the value to the individual card.
   * 
   * @param cardName
   */
  private void parseCardName(String cardName) {
    switch (cardName) {
      case "J":
      case "Q":
      case "K":
        this.value = 10; // J,Q,K have value of 10.
        break;
      case "A":
        this.value = 1;
        isAce = true;
        break;
      default:
        this.value = Integer.valueOf(cardName);
        break;
    }
  }

  /**
   * The setAceHigh method will use the value of 11 for an Ace.
   */
  public void setAceHigh() {
    this.value = HIGH_ACE;
  }

  /**
   * The setAceLow method will use the value of 1 for an Ace.
   */
  public void setAceLow() {
    this.value = LOW_ACE;
  }

  /**
   * The getValue method will return the value.
   * 
   * @return the value
   */
  public int getValue() {
    return value;
  }

  /**
   * The setValue method will set the value.
   * 
   * @param value the value to set
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * The isAce method will return the boolean value of whether the card is an Ace or not an Ace.
   * 
   * @return the isAce
   */
  public boolean isAce() {
    return isAce;
  }

  /**
   * This validCardValue method will take in a String value of the card and check on if it is a
   * valid card or not. It will return true if int is valid and false if it is not valid
   * 
   * @param value
   * @return boolean value of whether the pattern is a match
   */
  public static boolean validCardValue(String value) {
    String regex = "([2-9]|10|[JQKA])[DHSC]"; // Regex pattern for valid card.
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(value);
    return matcher.matches();
  }
}
