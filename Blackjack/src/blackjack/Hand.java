/**
 * 
 */
package blackjack;

import java.util.ArrayList;

/**
 * Class "Hand" 
 * @author Ryan Godfrey, Will Graham
 *
 */
public class Hand {
  private Card[] cards;
  private int total;
  private boolean bust;

  public Hand() {
    bust = false;
    this.cards = null; // Might throw error
    //calculateTotal();
    //this.bust = isBust(this.total);
    bust = false;
  }
  
  public Hand(Card[] cards) {
    bust = false;
    this.cards = cards; // Might throw error
    calculateTotal();
  }

  private void calculateTotal() {
    boolean hasAnAce = false;
    int softTotal = 0;
    int hardTotal = 0;

    for (Card card : cards) {
      if (card.isAce() && !hasAnAce) {
        hardTotal += Card.HIGH_ACE;
        softTotal += 1;
        hasAnAce = true;
      } else if (card.isAce() && hasAnAce) {
        hardTotal += 1;
        softTotal += 1;
      } else {
        hardTotal += card.getValue();
        softTotal += card.getValue();
      }
    }

    if (isBust(hardTotal)) {
      this.total = softTotal;
    } else {
      this.total = hardTotal;
    }
    
    this.bust = isBust(this.total);
  }

  private boolean isBust(int number) {
    if (number > 21) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * @return the cards
   */
  public Card[] getCards() {
    return cards;
  }

  /**
   * @param cards the cards to set
   */
  public void setCards(Card[] cards) {
    this.cards = cards;
  }

  /**
   * @return the total
   */
  public int getTotal() {
    return total;
  }

  /**
   * @param total the total to set
   */
  public void setTotal(int total) {
    this.total = total;
  }

  /**
   * @return the bust
   */
  public boolean isBust() {
    return bust;
  }

  /**
   * @param bust the bust to set
   */
  public void setBust(boolean bust) {
    this.bust = bust;
  }



}
