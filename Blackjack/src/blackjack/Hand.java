/**
 * 
 */
package blackjack;

import java.util.ArrayList;

/**
 * @author grahamw0
 *
 */
public class Hand {
  private ArrayList<Card> cards;
  private int total;
  private boolean bust;
  
  public Hand(Card... cards) {
    this.cards = new ArrayList<>();
    for(Card card : cards) {
      this.cards.add(card);
    }
  }

  private void calculateTotal() {
    //TODO: Finish
  }
  
}
