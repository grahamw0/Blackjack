package blackjack;

/**
 * This is class "Hand". It will control the logic of what is entailed in a hand of blackjack.
 * 
 * @author Ryan Godfrey, Will Graham
 * @version 1.0 9/18/2016
 *
 */
public class Hand {
  private Card[] cards;
  private int total;
  private boolean bust; // Quick way to get whether a hand is bust or not.

  /**
   * The constructor for class Hand. It will set bust to false and initialize cards to null.
   */

  public Hand() {
    bust = false;
    this.cards = null;
    bust = false;
  }

  /**
   * Overloaded constructor for class Hand. Set's bust to false and initializes cards to itself.
   * 
   * @param cards
   */
  public Hand(Card[] cards) {
    bust = false;
    this.cards = cards;
    calculateTotal();
  }

  /**
   * The calculateTotal method holds the logic on how much each card is worth from an integer
   * standpoint.
   */
  private void calculateTotal() {
    boolean hasAnAce = false;
    int softTotal = 0; // Assumes any aces will be counted as only 1.
    int hardTotal = 0; // The first ace will be 11, all others must be 1.
    for (Card card : cards) {
      if (card.isAce() && !hasAnAce) { // If the card is an ane and there isn't already one.
        hardTotal += Card.HIGH_ACE; // The first ace for hardTotal is always 11.
        softTotal += 1;
        hasAnAce = true;
      } else if (card.isAce() && hasAnAce) { // A second or more ace- both totals get a 1.
        hardTotal += 1;
        softTotal += 1;
      } else {
        hardTotal += card.getValue();
        softTotal += card.getValue();
      }
    }

    if (isBust(hardTotal)) { // hardTotal always higher- if its a bust, the lower must be used.
      this.total = softTotal;
    } else {
      this.total = hardTotal;
    }

    this.bust = isBust(this.total); // Which ever total is used check whether it is a bust or not.
  }

  /**
   * The isBust method checks to see if the total of a hand is greater than 21.
   * 
   * @param number number to check
   * @return whether the number is a bust or not
   */
  private boolean isBust(int number) {
    if (number > 21) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * The getCards method returns the Array of Card called cards.
   * 
   * @return the cards
   */
  public Card[] getCards() {
    return cards;
  }

  /**
   * The setCards method sets the variable cards to the Array called cards.
   * 
   * @param cards the cards to set
   */
  public void setCards(Card[] cards) {
    this.cards = cards;
  }

  /**
   * The getTotal method returns the integer field called total.
   * 
   * @return the total
   */
  public int getTotal() {
    return total;
  }

  /**
   * The setTotal method sets the variable total to the field called total.
   * 
   * @param total the total to set
   */
  public void setTotal(int total) {
    this.total = total;
  }

  /**
   * The isBust method returns a boolean value of whether the hand is bust or not
   * 
   * @return the bust
   */
  public boolean isBust() {
    return bust;
  }

  /**
   * The setBust method sets the bust field to true or false.
   * 
   * @param bust the bust to set
   */
  public void setBust(boolean bust) {
    this.bust = bust;
  }

}
