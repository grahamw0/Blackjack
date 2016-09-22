/**
 * 
 */
package blackjack;

/**
 * This is class "Player". It will handle all logic that pertains to the individual player in each
 * hand.
 * 
 * @author Ryan Godfrey, Will Graham
 * @version 1.0 9/18/2016
 */

public class Player {
  protected int money;
  protected int wins;
  protected int pushes;
  protected int losses;
  private int currentBet; // Private because the inherited Dealer doesn't have their own bet.
  protected Hand hand;

  /**
   * The constructor for class "Player". It will initialize all fields upon creation.
   */
  public Player() {
    this.money = 0;
    this.wins = 0;
    this.pushes = 0;
    this.losses = 0;
    this.currentBet = 0;
    this.hand = new Hand();
  }

  /**
   * The win method will handle the proper amount of winnings plus increment the win total.
   */
  public void win() {
    money += currentBet * 2;
    wins++;
  }

  /**
   * The push method will increment the push total.
   */
  public void push() {
    pushes++;
  }

  /**
   * The lose method will handle the proper amount of lost and increment the losses total.
   */
  public void lose() {
    money -= currentBet;
    losses++;
  }

  /**
   * The getMoney method will return the money field.
   * 
   * @return the money
   */
  public int getMoney() {
    return money;
  }

  /**
   * The setMoney method will update the money field with the new variable money.
   * 
   * @param money the money to set
   */
  public void setMoney(int money) {
    this.money = money;
  }

  /**
   * The getWins method will return the wins field.
   * 
   * @return the wins
   */
  public int getWins() {
    return wins;
  }

  /**
   * The getPushes method will return the pushes field.
   * 
   * @return the pushes
   */
  public int getPushes() {
    return pushes;
  }

  /**
   * The getLosses method will return the losses field.
   * 
   * @return the losses
   */
  public int getLosses() {
    return losses;
  }

  /**
   * The getCurrentBet method will return the currentBet field.
   * 
   * @return the currentBet
   */
  public int getCurrentBet() {
    return currentBet;
  }

  /**
   * The setCurrentBet method will set the currentBet to itself.
   * 
   * @param currentBet the currentBet to set
   */
  public void setCurrentBet(int currentBet) {
    this.currentBet = currentBet;
  }

  /**
   * The getHand method will return the hand field.
   * 
   * @return the hand
   */
  public Hand getHand() {
    return hand;
  }

  /**
   * The setHand method will set the hand variable to the hand field.
   * 
   * @param hand the hand to set
   */
  public void setHand(Hand hand) {
    this.hand = hand;
  }

}
