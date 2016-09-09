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
	  bust = false;
    this.cards = new ArrayList<>();
    for(Card card : cards) {
      this.cards.add(card);
    }
    calculateTotal();
    this.bust = isBust(this.total);
  }

  private void calculateTotal() {
	  boolean hasAnAce = false;
	  int softTotal = 0;
	  int hardTotal = 0;
	  
	  for(Card card : cards) {
		  if (card.isAce() && !hasAnAce) {
			  hardTotal += Card.HIGH_ACE;
			  softTotal += 1;
			  hasAnAce = true;
		  }
		  else if (card.isAce() && hasAnAce) {
			  hardTotal += 1;
			  softTotal += 1;
		  }
		  else {
			  hardTotal += card.getValue();
			  softTotal += card.getValue();
		  }
	  }
	  
	  if(isBust(hardTotal)) {
		  this.total = softTotal;
	  }
	  else {
		  this.total = hardTotal;
	  }
	  
  }
  
  private boolean isBust(int number) {
	  if (number > 21) {
		  return true;
	  }
	  else {
		  return false;
	  }
  }
  
}
