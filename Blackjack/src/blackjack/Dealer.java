/**
 * 
 */
package blackjack;

/**
 * 
 *
 * @author Will
 */
public class Dealer extends Player {
  
  
  
  /**
   * 
   */
  public Dealer(int dealersStartingAmount) {
    super();
    this.money = dealersStartingAmount;
  }
  
  public void win(int amount) {
    wins++;
    money += amount;
  }
  
  public void lose(int amount) {
    losses++;
    money -= (amount * 2);
  }
  
}
