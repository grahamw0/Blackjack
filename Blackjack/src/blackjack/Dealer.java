package blackjack;

/**
 * This is class "Dealer" and it extends class Player. It will create a Dealer object and initiate
 * the starting amount of the dealer's purse upon creation.
 *
 * @author Will Graham, Ryan Godfrey
 * @version 1.0 9/18/2016
 */
public class Dealer extends Player {

  /**
   * Constructor for class Dealer. It will create an instance of dealer and initialize the dealer's
   * starting amount.
   * 
   * @param dealersStartingAmount the starting amount of the dealer
   */
  public Dealer(int dealersStartingAmount) {
    super();
    this.money = dealersStartingAmount; // Initializes the dealer's starting amount.
  }

  /**
   * The win method will update the dealer's amount of money, and increment the win total.
   * 
   * @param amount the amount of money the dealer has
   */
  public void win(int amount) {
    wins++;
    money += amount;
  }

  /**
   * The lose method will update the dealer's amount of money, and decrement the win total.
   * 
   * @param amount the amount of money the dealer has
   */
  public void lose(int amount) {
    losses++;
    money -= (amount * 2);
  }

  /**
   * The push method will increment the push total.
   */
  public void push() {
    pushes++;
  }
}
