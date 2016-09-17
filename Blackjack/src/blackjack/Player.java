/**
 * 
 */
package blackjack;

/**
 * @author Ryan Godfrey, Will Graham
 *
 */
public class Player {
  protected int money;
  protected int wins;
  protected int pushes;
  protected int losses;
  private int currentBet;  // Dealer won't ever have his own bet
  protected Hand hand;

  public Player() {
    // TODO Auto-generated constructor stub
    this.money = 0;
    this.wins = 0;
    this.pushes = 0;
    this.losses = 0;
    this.currentBet = 0;
    this.hand = new Hand();
  }

  public Player() {
    this.money = 0;
    this.wins = 0;
    this.pushes = 0;
    this.losses = 0;
    this.hand = new Hand();
  }

  public void win() {
    money += currentBet * 2;
    wins++;
  }

  public void push() {
    pushes++;
  }

  public void lose() {
    money -= currentBet;
    losses++;
  }



  /**
   * @return the money
   */
  public int getMoney() {
    return money;
  }

  /**
   * @param money the money to set
   */
  public void setMoney(int money) {
    this.money = money;
  }

  /**
   * @return the wins
   */
  public int getWins() {
    return wins;
  }


  /**
   * @return the pushes
   */
  public int getPushes() {
    return pushes;
  }

  /**
   * @return the losses
   */
  public int getLosses() {
    return losses;
  }

  /**
   * @return the currentBet
   */
  public int getCurrentBet() {
    return currentBet;
  }

  /**
   * @param currentBet the currentBet to set
   */
  public void setCurrentBet(int currentBet) {
    this.currentBet = currentBet;
  }

  /**
   * @return the hand
   */
  public Hand getHand() {
    return hand;
  }

  /**
   * @param hand the hand to set
   */
  public void setHand(Hand hand) {
    this.hand = hand;
  }

}
