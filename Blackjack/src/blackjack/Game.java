/**
 * 
 */
package blackjack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 *
 * @author Ryan Godfrey, Will Graham
 */
public class Game {
  private int numberOfPlayers;
  private ArrayList<Player> players;

  /**
   * @throws IOException
   * 
   */
  public Game() throws IOException {
    players = new ArrayList<>();
    play();
  }

  public void play() throws IOException {
    // Get 1st line- can't be in a loop, as its unique
    File file = new File("test.txt"); // TODO: allow for file choices
    FileInputStream fis = new FileInputStream(file);
    BufferedReader br = new BufferedReader(new InputStreamReader(fis));

    String firstLine = br.readLine().substring(1); // Doesn't pick up the $ for easy String -> int
    String[] firstArray = firstLine.trim().split("\\s+");

    // TODO: custom exceptions for these errors
    boolean firstLineError = false;
    if (Integer.valueOf(firstArray[0]) < 1000) {
      System.out.println("ERROR: The dealer has started with not enough money.");
      firstLineError = true;
    }
    if (Integer.valueOf(firstArray[0]) > 100000) {
      System.out.println("ERROR: The dealer has started with too much money.");
      firstLineError = true;
    }
    if (Integer.valueOf(firstArray[1]) < 1) {
      System.out.println("ERROR: Too few players.");
      firstLineError = true;
    }
    if (Integer.valueOf(firstArray[1]) > 6) {
      System.out.println("ERROR: Too many players.");
      firstLineError = true;
    }
    if (firstLineError) {
      return;
    }

    Player dealer = new Player(Integer.valueOf(firstArray[0]));
    numberOfPlayers = Integer.valueOf(firstArray[0]);

    for (int i = 0; i < numberOfPlayers; i++) {
      players.add(new Player());
    }
    players.add(dealer); // Ensures the dealer is always the last "player" in the list



  }


}
