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
 * This is class "Game". It will house the methods that will be in control of the game logic.
 *
 * @author Ryan Godfrey, Will Graham
 * @version 1.0 9/18/2016
 */
public class Game {
  private int numberOfPlayers; // The number of players in an individual hand.
  private ArrayList<Player> players; // The ArralyList of players which includes the dealer as last
                                     // player.
  private ArrayList<String> outputArray; // The ArrayList that will be in charge of the output.
  private File file;

  /**
   * This is the constructor for class "Game". It will initialize the ArrayList called players, and
   * initialize the ArrayList called outputArray.
   * 
   * @throws IOException
   */
  public Game(File file) throws IOException {
    players = new ArrayList<>(); // Initializes the ArrayList called players.
    outputArray = new ArrayList<>(); // Initializes the ArrayList called outputArray.
    this.file = file; // Allows for multiple files
    play(); // calls the "Play" method
  }

  /**
   * The play method will initiate the reading of the text file.
   * 
   * @throws IOException
   */
  public void play() throws IOException {
    // Get 1st line- can't be in a loop, as its unique
    FileInputStream fis = new FileInputStream(file); // Creates a new FileInputStream called fis
    BufferedReader br = new BufferedReader(new InputStreamReader(fis)); // creates a new
                                                                        // BufferedReader called br
    String firstLine = br.readLine().substring(1); // Doesn't pick up the $ for easy String -> int
    String[] firstArray = firstLine.trim().split("\\s+"); // Trims and splits the firstArray on all
                                                          // white spaces.
    // Checks the first line of the text file which is the dealer's money and # of players.
    firstLineErrorCheck(firstArray);
    // The number of players will be index #1 of the firstArray. It also includes the dealer.
    numberOfPlayers = Integer.valueOf(firstArray[1]); // # of players includes dealer
    // Create a for loop that adds all of the players plus the dealer to the ArrayList called
    // players.
    for (int i = 0; i < numberOfPlayers; i++) {
      players.add(new Player()); // Add the next player to the ArrayList called players.
    }
    // Ensures the dealer is always the last "player" in the list
    players.add(new Dealer(Integer.valueOf(firstArray[0]))); // Create the new Dealer along with the
                                                             // starting purse value.
    numberOfPlayers++; // Accounts for dealer

    String gameLine = null; // Create a String called gameLine.
    // while the current line being read is not null and is not a "----------------" and is not an
    // empty String...
    while ((gameLine = br.readLine()) != null && !(gameLine.equals("----------------"))
        && !(gameLine.equals(""))) {
      // Create and Array of String called gamelineArray and start on the 2nd char. Split the Array
      // on "["
      String[] gameLineArray = gameLine.substring(1).split("\\[");
      // If the Array's length is greater than the number of players output an error.
      if (gameLineArray.length > numberOfPlayers) {
        output("Error: Too many players"); // Too many players.
        // If the Array's length is less than the number of players output an error.
      } else if (gameLineArray.length < numberOfPlayers) {
        output("Error: Too few players"); // Too few players.
        // Reassure that everything is okay if there is no error.
      } else {
        output("everything's cool, in terms of player numbers in this line"); // Everything is okay.
        boolean validHand = parseHands(gameLineArray); // Check if the hand is valid or not.
        // If the hand is not valid output an error.
        if (!validHand) {
          output("ERROR: Malformed hand."); // Hand is not valid.
        } else {
          // Create a for loop that gets the players and the dealer and checks for winLosePush.
          for (int i = 0; i < (players.size() - 1); i++) {
            winLosePush(players.get(i), (Dealer) players.get(players.size() - 1)); // Dealer
          }
        }
      }
    }
    // Create a for loop that obtains all of the player's statistics.
    for (int i = 0; i < players.size() - 1; i++) {
      output("Player #" + (i + 1) + "'s money: $" + players.get(i).getMoney()); // Get the money for
                                                                                // player i.
      output("Player #" + (i + 1) + "'s wins: " + players.get(i).getWins()); // Get the win total
                                                                             // for player i.
      output("Player #" + (i + 1) + "'s losses: " + players.get(i).getLosses()); // Get the loss
                                                                                 // total for player
                                                                                 // i.
      output("Player #" + (i + 1) + "'s pushes: " + players.get(i).getPushes()); // Get the push
                                                                                 // total for player
                                                                                 // i.
      output(""); // print an empty line.
    }
    // Now get the dealer's statistics. The dealer is at players.size()-1
    output("Dealer's remaining money: $" + players.get(players.size() - 1).getMoney()); // Get the
                                                                                        // money for
                                                                                        // the
                                                                                        // dealer.
    output("Dealer's wins: " + players.get(players.size() - 1).getWins()); // Get the win total for
                                                                           // the dealer.
    output("Dealer's losses: " + players.get(players.size() - 1).getLosses()); // Get the loss total
                                                                               // for the dealer.
    output("Dealer's pushes: " + players.get(players.size() - 1).getPushes()); // Get the push total
                                                                               // for the dealer.

    exit(); // Call on the method exit().
  }

  /**
   * The exit method will write the gathered statistics to the text file and append the information
   * after the already existing log file of the hands played.
   * 
   * @throws IOException
   */
  private void exit() throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); // Create a new
                                                                            // BufferedWriter called
                                                                            // writer.
    writer.append("\n"); // new line
    writer.append("\n"); // new line
    writer.append("----------------"); // Create a separation line for cosmetic purposes.
    writer.append("\n"); // new line
    writer.append("OUTPUT:\n"); // Print the following text.
    // Create a for each loop to write the information gathered in the outputArray.
    for (String line : outputArray) {
      writer.append(line + "\n"); // Write each array and jump to the next line.
    }

    writer.close(); // When finished close the writer.
    System.exit(0); // Then exit and terminate the program.
  }

  /**
   * The firstLineErrorCheck method checks the very first line of the log file. The very first line
   * should be the amount that the dealer starts with, and the total number of players. The dealer
   * is included in the number of players.
   * 
   * @param array
   * @throws IOException
   */
  private void firstLineErrorCheck(String[] array) throws IOException {
    // TODO: custom exceptions for these errors
    boolean firstLineError = false; // Create a boolean called firstLineError and set it to false.
    if (Integer.valueOf(array[0]) < 1000) { // If the dealer's money is less than $1000 print an
                                            // error message.
      output("ERROR: The dealer has started with not enough money.");
      firstLineError = true; // Set boolean to true.
    }
    if (Integer.valueOf(array[0]) > 100000) { // If the dealer's money is greater than $100,000
                                              // print an error message.
      output("ERROR: The dealer has started with too much money.");
      firstLineError = true; // Set boolean to true.
    }
    if (Integer.valueOf(array[1]) < 1) { // If the amount of players is less than 1 print an error
                                         // message.
      output("ERROR: Too few players.");
      firstLineError = true; // Set boolean to true.
    }
    if (Integer.valueOf(array[1]) > 6) { // If the amount of players is greater than 6 print an
                                         // error message.
      output("ERROR: Too many players.");
      firstLineError = true; // Set boolean to false.
    }
    if (firstLineError) { // If the boolean of firstLineError is true then call on the exit method.
      exit();
    } else { // If there is no error then print everything's cool message for the first line.
      output("everything's cool");
    }
  }

  /**
   * The parseHands method will check to see if the hands are valid. It will parse through and split
   * the log file up to be able to deal with the hands one by one.
   * 
   * @param gameLineArray
   * @return (false if the dealer or any of the player's hands are invalid. true if both hands are
   *         valid.
   */

  private boolean parseHands(String[] gameLineArray) {
    // Create a for loop that iterates through a line of the log file.
    for (int i = 0; i < (gameLineArray.length - 1); i++) {
      String[] handBetArray = gameLineArray[i].trim().split("] "); // Split on all "] " which
                                                                   // separates hand and bet.
      players.get(i).setCurrentBet(Integer.valueOf(handBetArray[1].substring(1))); // Get the value
                                                                                   // of the bet
                                                                                   // ignore "$"
      String[] cardStrings = handBetArray[0].split(", "); // Split the cards on a "," to separate
                                                          // the cards per player.
      Card[] cardArray = new Card[cardStrings.length]; // Create a new Card array called cardArray.
      // Create a for loop to add the cards to the array.
      for (int j = 0; j < cardArray.length; j++) {
        if (!Card.validCardValue(cardStrings[j])) { // Check that all cards are valid entries. If
                                                    // invalid return false.
          return false;
        } else {
          cardArray[j] = new Card(cardStrings[j]); // If Player cards are valid then set the cards
                                                   // to the cardArray.
        }
      }
      players.get(i).setHand(new Hand(cardArray)); // Set the cards to be a hand.
    }
    // Do the same as above for the dealer. Create a new String array called cardStringDealer.
    String[] cardStringsDealer =
        gameLineArray[gameLineArray.length - 1].replaceAll("\\]", "").split(", "); // Get dealer
                                                                                   // hand and split
                                                                                   // the cards.
    Card[] dealerCards = new Card[cardStringsDealer.length]; // Create a new Card Array called
                                                             // dealerCards.
    // Create a for loop that iterates through the dealer's cards.
    for (int i = 0; i < dealerCards.length; i++) {
      if (!Card.validCardValue(cardStringsDealer[i])) { // Check that all cards are valid entries.
                                                        // If invalid return false.
        return false;
      } else {
        dealerCards[i] = new Card(cardStringsDealer[i]); // If dealer cards are valid then set the
                                                         // cards to the dealerCards.
      }
    }
    players.get(players.size() - 1).setHand(new Hand(dealerCards)); // Set the cards to be the
                                                                    // dealer's hand.

    return true; // If all cards are valid then return true.
  }

  /**
   * The winLosePush method compares a player's hand to the dealer's, updates each individual's
   * money, then updates the appropriate counter for each (win/lose/push).
   * 
   * @param player
   * @param dealer
   */
  private void winLosePush(Player player, Dealer dealer) {
    int playerTotal = player.getHand().getTotal(); // Get player hand and total and set it to
                                                   // playerTotal.
    int dealerTotal = dealer.getHand().getTotal(); // Get dealer hand and total and set it to
                                                   // dealerTotal.

    if (player.getHand().isBust()) { // Is the player's hand is a bust?
      player.lose(); // If so then the player loses.
      dealer.win(player.getCurrentBet()); // The dealer wins and collects the players current bet.
    } else if (dealer.getHand().isBust()) { // Is the dealer's hand a bust?
      player.win(); // If so then the player wins.
      dealer.lose(player.getCurrentBet()); // The player wins and collects the winnings.
    } else if (playerTotal > dealerTotal) { // Is the player's card total is greater than the
                                            // dealer's card total?
      player.win(); // The player wins.
      dealer.lose(player.getCurrentBet()); // The dealer loses and player collects winnings.
    } else if (playerTotal < dealerTotal) { // Is the players's card total less than the dealer's
                                            // card total?
      player.lose(); // The player loses.
      dealer.win(player.getCurrentBet()); // The dealer wins and collects the winnings.
    } else { // If none of the above happens then the outcome must be a push.
      player.push(); // Player's hand is a push.
      dealer.push(); // Dealer's hand is a push.
    }

  }

  /**
   * The output method condenses printing to console for easy access/debugging and adding to array
   * of Strings to be printed to file.
   * 
   * @param line String to be printed in console and text file output
   */
  public void output(String line) {
    System.out.println(line); // Print to the Console.
    outputArray.add(line); // Add the line to the outputArray.
  }

}
