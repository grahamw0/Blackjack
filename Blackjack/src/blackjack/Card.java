/**
 * 
 */
package blackjack;

/**
 * @author Ryan Godfrey, Will Graham
 *
 */
public class Card {
  public static final int LOW_ACE = 1; // Ace value of 1
  public static final int HIGH_ACE = 11; // Ace value of 11
  private int value; // Value of card
  private boolean isAce;

  public Card(String cardName) {
    this.isAce = false;
    parseCardName(cardName);
  }

  private void parseCardName(String cardName) {
    switch (cardName.substring(0)) {
      case "J":
      case "Q":
      case "K":
        this.value = 10;
        break;
      case "A":
        this.value = 1; // Default, will be switched in game logic if need be.
        isAce = true;
        break;
      default:
        this.value = Integer.valueOf(cardName);
        break;
    }
  }

  public void setAceHigh() {
    this.value = HIGH_ACE;
  }

  public void setAceLow() {
    this.value = LOW_ACE;
  }

  /**
   * @return the value
   */
  public int getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * @return the isAce
   */
  public boolean isAce() {
    return isAce;
  }



}
