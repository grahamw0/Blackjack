/**
 * 
 */
package blackjack;

/**
 * @author grahamw0
 *
 */
public class Player {
  private int money;
  private int wins;
  private int pushes;
  private int losses;
  private int currentBet;
  private Hand hand;

  public Player() {
    // TODO Auto-generated constructor stub
    this.money = 0;
    this.wins = 0;
    this.pushes = 0;
    this.losses = 0;
    this.currentBet = 0;
    this.hand = new Hand();
  }

  public Player(int dealersStartingAmount) {
    this.money = dealersStartingAmount;
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
   * @param wins the wins to set
   */
  public void setWins(int wins) {
    this.wins = wins;
  }

  /**
   * @return the pushes
   */
  public int getPushes() {
    return pushes;
  }

  /**
   * @param pushes the pushes to set
   */
  public void setPushes(int pushes) {
    this.pushes = pushes;
  }

  /**
   * @return the losses
   */
  public int getLosses() {
    return losses;
  }

  /**
   * @param losses the losses to set
   */
  public void setLosses(int losses) {
    this.losses = losses;
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
