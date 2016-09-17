/**
 * 
 */
package blackjack;

import dataFiles.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
  private ArrayList<Player> players; // Includes dealer as last player
  private ArrayList<String> outputArray;
  private File file = new File("src/dataFiles/test.txt"); // TODO: allow for file choices

  /**
   * @throws IOException
   * 
   */
  public Game() throws IOException {
    players = new ArrayList<>();
    outputArray = new ArrayList<>();
    play();
  }

  public void play() throws IOException {
    // Get 1st line- can't be in a loop, as its unique
    FileInputStream fis = new FileInputStream(file);
    BufferedReader br = new BufferedReader(new InputStreamReader(fis));

    String firstLine = br.readLine().substring(1); // Doesn't pick up the $ for easy String -> int
    String[] firstArray = firstLine.trim().split("\\s+");


    firstLineErrorCheck(firstArray);

    numberOfPlayers = Integer.valueOf(firstArray[1]); // # of players includes dealer

    for (int i = 0; i < numberOfPlayers; i++) {
      players.add(new Player());
    }

    // Ensures the dealer is always the last "player" in the list
    players.add(new Dealer(Integer.valueOf(firstArray[0])));
    numberOfPlayers++; // Accounts for dealer


    String gameLine = null;
    while ((gameLine = br.readLine()) != null) {
      String[] gameLineArray = gameLine.substring(1).split("\\[");

      if (gameLineArray.length > numberOfPlayers) {
        System.out.println("Error: Too many players");
      } else if (gameLineArray.length < numberOfPlayers) {
        System.out.println("Error: Too few players");
      } else {
        System.out.println("everything's cool, in terms of player numbers in this line");
        parseHands(gameLineArray);

      }

    }


    exit();
  }

  private void exit() throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    writer.append("\n");
    writer.append("\n");
    writer.append("OUTPUT:\n");
    for (String line : outputArray) {
      writer.append(line + "\n");
    }

    writer.close();
    System.exit(0);
  }

  private void firstLineErrorCheck(String[] array) throws IOException {
    // TODO: custom exceptions for these errors
    boolean firstLineError = false;
    if (Integer.valueOf(array[0]) < 1000) {
      System.out.println("ERROR: The dealer has started with not enough money.");
      outputArray.add("ERROR: The dealer has started with not enough money.");
      firstLineError = true;
    }
    if (Integer.valueOf(array[0]) > 100000) {
      System.out.println("ERROR: The dealer has started with too much money.");
      outputArray.add("ERROR: The dealer has started with too much money.");
      firstLineError = true;
    }
    if (Integer.valueOf(array[1]) < 1) {
      System.out.println("ERROR: Too few players.");
      outputArray.add("ERROR: Too few players.");
      firstLineError = true;
    }
    if (Integer.valueOf(array[1]) > 6) {
      System.out.println("ERROR: Too many players.");
      outputArray.add("ERROR: Too many players.");
      firstLineError = true;
    }
    if (firstLineError) {
      exit();
    } else {
      System.out.println("everything's cool");
      outputArray.add("everything's cool");
    }
  }

  private void parseHands(String[] array) {

  }

  /**
   * Compares a player's hand to the dealer's, updates each's money, then updates the appropriate
   * counter for each (win/lose/push).
   * 
   * @param player
   * @param dealer
   * @return
   */
  private void winLosePush(Player player, Dealer dealer) { // TODO check for busting, then apply
                                                           // appropriate wins, etc
    int playerTotal = player.getHand().getTotal();
    int dealerTotal = dealer.getHand().getTotal();


  }


}
