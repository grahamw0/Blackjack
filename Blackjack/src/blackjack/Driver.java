/**
 * 
 */
package blackjack;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the driver of the blackjack statistics program. It holds the main method that initiates
 * the start of the program. It also houses a try/catch that will catch any IOExeption thrown by the
 * game.
 * 
 * @author Ryan Godfrey, Will Graham
 * @version 1.0 9/18/2016
 */
public class Driver {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    Game game = new Game(fileChoice());
  }

  private static File fileChoice() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Choose a file to run:");
    System.out.println("1- Working game");
    System.out.println("2- Incorrect Dealer starting amount");
    System.out.println("3- Incorrect player number declaration");
    System.out.println("4- Errors #2&3 combined");
    System.out.println("5- Incorrect number of hands in a round");
    System.out.println("6- Incorrect cards in hands");
    File file = null;

    do {
      System.out.print("Choice: ");
      int choice = scan.nextInt();
      switch (choice) {
        case 1:
          file = new File("src/dataFiles/correct.txt");
          break;
        case 2:
          file = new File("src/dataFiles/wrongDealerAmount.txt");
          break;
        case 3:
          file = new File("src/dataFiles/wrongPlayersDeclaration.txt");
          break;
        case 4:
          file = new File("src/dataFiles/firstLineTwoErrors.txt");
          break;
        case 5:
          file = new File("src/dataFiles/wrongNumberOfPlayers.txt");
          break;
        case 6:
          file = new File("src/dataFiles/malformedCards.txt");
          break;
        default:
          System.out.println("Incorrect choice. Try again!");
          System.out.println("");
      }
    } while (file == null);

    scan.close();
    return file;
  }

}
