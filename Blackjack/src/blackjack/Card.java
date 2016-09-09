/**
 * 
 */
package blackjack;

/**
 * @author grahamw0
 *
 */
public class Card {
  private final int LOW_ACE = 1; // Ace value of 1  
  private final int HIGH_ACE = 11; // Ace value of 11
  private int value; // Value of card

  public Card(String cardName) {
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
        this.value = 11; // Default, will be switched in game logic if need be.
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



}
