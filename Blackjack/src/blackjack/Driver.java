/**
 * 
 */
package blackjack;

import java.io.IOException;

/**
 * This is the driver of the blackjack statistics program.  
 * It holds the main method that initiates the start of the
 * program.  It also houses a try/catch that will catch any 
 * IOExeption thrown by the game.  
 * 
 * @author Ryan Godfrey, Will Graham
 * @version 1.0 	9/18/2016
 */
public class Driver {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
      Game game = new Game();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
