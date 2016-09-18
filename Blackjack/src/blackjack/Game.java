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
        output("Error: Too many players");
      } else if (gameLineArray.length < numberOfPlayers) {
        output("Error: Too few players");
      } else {
        output("everything's cool, in terms of player numbers in this line");
        boolean validHand = parseHands(gameLineArray);
        if (!validHand) {
          output("ERROR: Malformed hand.");
        } else {
          // Continue doin your thing
          for (int i = 0; i < (players.size() - 1); i++) {
            winLosePush(players.get(i), (Dealer) players.get(players.size() - 1));
          }
        }

      }

    }
    
    for(int i = 0; i < players.size() - 1; i++) {
      output("Player #" + (i + 1) + "'s money: $" + players.get(i).getMoney());
      output("Player #" + (i + 1) + "'s wins: " + players.get(i).getWins());
      output("Player #" + (i + 1) + "'s losses: " + players.get(i).getLosses());
      output("Player #" + (i + 1) + "'s pushes: " + players.get(i).getPushes());
      output("");
    }
    output("Dealer's remaining money: $" + players.get(players.size()-1).getMoney());
    output("Dealer's wins: " + players.get(players.size()-1).getWins());
    output("Dealer's losses: " + players.get(players.size()-1).getLosses());
    output("Dealer's pushes: " + players.get(players.size()-1).getPushes());

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
    if (firstLineError) {
      exit();
    } else {
      output("everything's cool");
    }
  }

  private boolean parseHands(String[] gameLineArray) {
    // TODO: Do it
    for (int i = 0; i < (gameLineArray.length - 1); i++) {
      String[] handBetArray = gameLineArray[i].trim().split("] ");
      players.get(i).setCurrentBet(Integer.valueOf(handBetArray[1].substring(1)));
      String[] cardStrings = handBetArray[0].split(", ");
      Card[] cardArray = new Card[cardStrings.length];
      for (int j = 0; j < cardArray.length; j++) {
        if (!Card.validCardValue(cardStrings[j])) {
          return false;
        } else {
          cardArray[j] = new Card(cardStrings[j]);
        }
      }
      players.get(i).setHand(new Hand(cardArray));
    }

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
    players.get(players.size()-1).setHand(new Hand(dealerCards));

    return true;
  }

  /**
   * Compares a player's hand to the dealer's, updates each's money, then updates the appropriate
   * counter for each (win/lose/push).
   * 
   * @param player
   * @param dealer
   * @return
   */
  private void winLosePush(Player player, Dealer dealer) {
    int playerTotal = player.getHand().getTotal();
    int dealerTotal = dealer.getHand().getTotal();

    if (player.getHand().isBust()) {
      player.lose();
      dealer.win(player.getCurrentBet());
    } else if (dealer.getHand().isBust()) {
      player.win();
      dealer.lose(player.getCurrentBet());
    } else if (playerTotal > dealerTotal) {
      player.win();
      dealer.lose(player.getCurrentBet());
    } else if (playerTotal < dealerTotal) {
      player.lose();
      dealer.win(player.getCurrentBet());
    } else {
      player.push();
      dealer.push();
    }

  }

  public void output(String line) {
    System.out.println(line);
    outputArray.add(line);
  }


}
