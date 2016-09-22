/**
 * 
 */
package blackjack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This is class "Game". It will house the methods that will be in control of the game logic.
 *
 * @author Ryan Godfrey, Will Graham
 * @version 1.0 9/18/2016
 */
public class Game {
  private int numberOfPlayers; // The number of players in an individual hand.
  private ArrayList<Player> players; // All players, dealer is last.
  private ArrayList<String> outputArray; // Holds all messages for output.
  private File file;

  /**
   * This is the constructor for class "Game". It will initialize the ArrayList called players, and
   * initialize the ArrayList called outputArray, and start the file processing.
   * 
   * @throws IOException
   */
  public Game(File file) throws IOException {
    players = new ArrayList<>();
    outputArray = new ArrayList<>();
    this.file = file;
    play();
  }

  /**
   * The play method will initiate the reading of the text file.
   * 
   * @throws IOException
   */
  public void play() throws IOException {
    // Get 1st line- can't be in a loop, as its unique
    FileInputStream fis = new FileInputStream(file);
    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    String firstLine = br.readLine().substring(1); // Doesn't pick up the $ for easy String -> int
    String[] firstArray = firstLine.trim().split("\\s+");

    // Checks the first line of the text file which is the dealer's money and # of players.
    firstLineErrorCheck(firstArray);

    // The number of non-dealer players will be index #1 of the firstArray.
    numberOfPlayers = Integer.valueOf(firstArray[1]);
    for (int i = 0; i < numberOfPlayers; i++) {
      players.add(new Player()); // Add the next player to the ArrayList called players.
    }
    // Ensures the dealer is always the last "player" in the list
    players.add(new Dealer(Integer.valueOf(firstArray[0]))); // Create the new Dealer along with the
                                                             // starting purse value.
    numberOfPlayers++; // Accounts for dealer

    String gameLine = null; // Current game line, file is over when multiple "-" is hit
    while ((gameLine = br.readLine()) != null && !(gameLine.equals("----------------"))
        && !(gameLine.equals(""))) {
      String[] gameLineArray = gameLine.substring(1).split("\\[");
      // If the Array's length is greater than the number of players output an error.
      if (gameLineArray.length > numberOfPlayers) {
        output("Error: Too many players"); // Too many players.
        // If the Array's length is less than the number of players output an error.
      } else if (gameLineArray.length < numberOfPlayers) {
        output("Error: Too few players"); // Too few players.
      } else {
        boolean validHand = parseHands(gameLineArray); // Check if the hand is valid or not.
        // If the hand is not valid output an error.
        if (!validHand) {
          output("ERROR: Malformed hand.");
        } else {
          // Create a for loop that gets the players and the dealer and checks for winLosePush.
          for (int i = 0; i < (players.size() - 1); i++) {
            winLosePush(players.get(i), (Dealer) players.get(players.size() - 1));
          }
        }
      }
    }
    // Create a for loop that obtains all of the player's statistics.
    for (int i = 0; i < players.size() - 1; i++) {
      output("Player #" + (i + 1) + "'s money: $" + players.get(i).getMoney());
      output("Player #" + (i + 1) + "'s wins: " + players.get(i).getWins());
      output("Player #" + (i + 1) + "'s losses: " + players.get(i).getLosses());
      output("Player #" + (i + 1) + "'s pushes: " + players.get(i).getPushes());
      output("");
    }
    // Now get the dealer's statistics. The dealer is at players.size()-1
    output("Dealer's remaining money: $" + players.get(players.size() - 1).getMoney());
    output("Dealer's wins: " + players.get(players.size() - 1).getWins());
    output("Dealer's losses: " + players.get(players.size() - 1).getLosses());
    output("Dealer's pushes: " + players.get(players.size() - 1).getPushes());

    exit();
  }

  /**
   * Method will append any errors and the gathered statistics to the already existing log file of
   * the hands played, then gracefully exit.
   * 
   * @throws IOException
   */
  private void exit() throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    writer.append("\n");
    writer.append("\n");
    writer.append("----------------"); // Delimiter between hands and output
    writer.append("\n");
    writer.append("OUTPUT:\n");
    for (String line : outputArray) {
      writer.append(line + "\n");
    }

    writer.close();
    System.exit(0);
  }

  /**
   * The firstLineErrorCheck method checks the very first line of the log file. The very first line
   * should be the amount that the dealer starts with, and the total number of players. The dealer
   * is included in the number of players.
   * 
   * @param array Holds, in order, the dealer's starting money and then expected number of players
   * @throws IOException
   */
  private void firstLineErrorCheck(String[] array) throws IOException {
    boolean firstLineError = false;
    if (Integer.valueOf(array[0]) < 1000) {
      output("ERROR: The dealer has started with not enough money.");
      firstLineError = true;
    }
    if (Integer.valueOf(array[0]) > 100000) {
      output("ERROR: The dealer has started with too much money.");
      firstLineError = true;
    }
    if (Integer.valueOf(array[1]) < 1) {
      output("ERROR: Too few players.");
      firstLineError = true;
    }
    if (Integer.valueOf(array[1]) > 6) {
      output("ERROR: Too many players.");
      firstLineError = true;
    }
    if (firstLineError) { // First line errors are un-recoverable.
      exit();
    }
  }

  /**
   * The parseHands method will check to see if the hands are valid, and if they are, assign them to
   * their corresponding player.
   * 
   * @param gameLineArray Each entry contains a hand and a bet (for all players except the dealer)
   * @return The existence of an error in any hand. If true, the line should be skipped.
   */

  private boolean parseHands(String[] gameLineArray) {
    for (int i = 0; i < (gameLineArray.length - 1); i++) {
      String[] handBetArray = gameLineArray[i].trim().split("] "); // Separate hand and bet.
      players.get(i).setCurrentBet(Integer.valueOf(handBetArray[1].substring(1))); // Ignore $
      String[] cardStrings = handBetArray[0].split(", "); // String[] representing all cards
      Card[] cardArray = new Card[cardStrings.length]; // Create a new Card array called cardArray.

      for (int j = 0; j < cardArray.length; j++) {
        if (!Card.validCardValue(cardStrings[j])) {
          return false;
        } else {
          cardArray[j] = new Card(cardStrings[j]);
        }
      }
      players.get(i).setHand(new Hand(cardArray)); // Set the cards to be a hand.
    }

    // Dealer must seperate as he has no bet.
    String[] cardStringsDealer =
        gameLineArray[gameLineArray.length - 1].replaceAll("\\]", "").split(", ");
    Card[] dealerCards = new Card[cardStringsDealer.length];
    for (int i = 0; i < dealerCards.length; i++) {
      if (!Card.validCardValue(cardStringsDealer[i])) {
        return false;
      } else {
        dealerCards[i] = new Card(cardStringsDealer[i]);
      }
    }
    players.get(players.size() - 1).setHand(new Hand(dealerCards));

    return true; // If all cards are valid then return true.
  }

  /**
   * The winLosePush method compares a player's hand to the dealer's, updates each individual's
   * money, then updates the appropriate counter for each (win/lose/push).
   * 
   * @param player The non-dealer player
   * @param dealer The dealer
   */
  private void winLosePush(Player player, Dealer dealer) {
    int playerTotal = player.getHand().getTotal();
    int dealerTotal = dealer.getHand().getTotal();

    if (player.getHand().isBust()) {
      player.lose();
      dealer.win(player.getCurrentBet()); // The dealer collects the players current bet.
    } else if (dealer.getHand().isBust()) {
      player.win();
      dealer.lose(player.getCurrentBet());
    } else if (playerTotal > dealerTotal) {
      player.win();
      dealer.lose(player.getCurrentBet());
    } else if (playerTotal < dealerTotal) {
      player.lose();
      dealer.win(player.getCurrentBet());
    } else { // If neither win or lose, both push.
      player.push();
      dealer.push();
    }

  }

  /**
   * The output method condenses printing to console for easy access/debugging and adding to array
   * of Strings to be printed to file.
   * 
   * @param line String to be printed in console and text file output
   */
  public void output(String line) {
    System.out.println(line);
    outputArray.add(line); // Add the line to the outputArray.
  }

}
