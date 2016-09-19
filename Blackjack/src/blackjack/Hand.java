/**
 * 
 */
package blackjack;

import java.util.ArrayList;

/**
 * This is class "Hand".  It will control the logic
 * of what is entailed in a hand of blackjack. 
 *   
 * @author Ryan Godfrey, Will Graham
 * @version 1.0		9/18/2016
 *
 */
public class Hand {
  private Card[] cards; // Array of Card called cards
  private int total; // The total of all cards in a hand.
  private boolean bust; // Boolean value of whether the hand is bust or not.  
  
  /**
   * The constructor for class Hand. It will set bust to false
   * and initialize cards to null.  
   */

  public Hand() {
    bust = false; // bust is initialized to false.
    this.cards = null; // Cards set equal to null.  
    //calculateTotal();
    //this.bust = isBust(this.total);
    bust = false;
  }
  
  /**
   * Overloaded constructor for class Hand.  Set's bust to false
   * and initializes cards to itself.  
   * @param cards
   */
  public Hand(Card[] cards) {
    bust = false; // bust is initialized to false
    this.cards = cards; // Might throw error
    calculateTotal(); // Calls on the calculateTotal() method.  
  }

  /**
   * The calculateTotal method holds the logic on how
   * much each card is worth from an integer standpoint.  
   */
  private void calculateTotal() {
    boolean hasAnAce = false; // Set hasAnAce to false.  If the hand has an Ace this will be changed to true.  
    int softTotal = 0; // Create an int called softTotal and set it to equal 0.
    int hardTotal = 0; // Create an int called hardTotal and set it to equal 0.  
    // Create a for each loop that iterates through the cards.   
    for (Card card : cards) {
      if (card.isAce() && !hasAnAce) { // If the card is an Ace and hasAnAce is true
        hardTotal += Card.HIGH_ACE; // The hardTotal 's Ace is worth 11.
        softTotal += 1; // The softTotal 's Ace is worth 1.
        hasAnAce = true; // Set hasAnAce to true.  
      } else if (card.isAce() && hasAnAce) { // If the card is not an Ace and hasAcAce is false
        hardTotal += 1; // The Ace is worth 1. 
        softTotal += 1; // The Ace is worth 1.
      } else {
        hardTotal += card.getValue(); // Get the value of the hardTotal.
        softTotal += card.getValue(); // Get the value of the softTotal. 
      }
    }
    
    if (isBust(hardTotal)) { // If the hardTotal is a bust
      this.total = softTotal; // use the softTotal
    } else { // If it is not a bust 
      this.total = hardTotal; // use the hardTotal
    }
    
    this.bust = isBust(this.total); // Which ever total is used check whether it is a bust or not.  
  }
  
  /**
   * The isBust method checks to see if the 
   * total of a hand is greater than 21. 
   * @param number
   * @return boolean value.  
   */
  private boolean isBust(int number) {
    if (number > 21) { // if the variable is greater than 21
      return true; // return true
    } else {
      return false; // If the variable is less than 21 return false.  
    }
  }

  /**
   * The getCards method returns the Array of Card called cards. 
   * @return the cards
   */
  public Card[] getCards() {
    return cards; // Return the Array called cards. 
  }

  /**
   * The setCards method sets the variable cards to 
   * the Array called cards.
   * @param cards the cards to set
   */
  public void setCards(Card[] cards) {
    this.cards = cards; // Sets the variable cards to the Array of Card called cards
  }

  /**
   * The getTotal method returns the integer field called total.  
   * @return the total
   */
  public int getTotal() {
    return total; // Return the total field. 
  }

  /**
   * The setTotal method sets the variable total to 
   * the field called total.
   * @param total the total to set
   */
  public void setTotal(int total) {
    this.total = total; // Sets the variable total to the int field called total. 
  }

  /**
   * The isBust method returns a boolean value
   * of whether the hand is bust or not
   * @return the bust
   */
  public boolean isBust() {
    return bust; // return bust
  }

  /**
   * The setBust method sets the bust field
   * to true or false.  
   * @param bust the bust to set
   */
  public void setBust(boolean bust) {
    this.bust = bust; // Set the variable bust to the bust field .
  }

}
