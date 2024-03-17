import java.io.*;
import java.nio.Buffer;

public class A1 {
    public static void main(String[] args) {

        // Define a bufferreader variable which helps to read the data from the file of
        // board configuration
        BufferedReader boardFileDetails;
        BufferedReader userInput;
        // Give the file path as input to the bufferReader
        try {
            boardFileDetails = new BufferedReader(
                    new FileReader("src/Board_Configuration.txt"));
            // moveReader = new BufferedReader(new FileReader("src/Movesequence.txt"));
            userInput = new BufferedReader(
                    new FileReader("src/MoveSequenceInput.txt"));


            // Call the FollowChess constructor in the FollowChess.java file
            FollowChess chessGameFunctions = new FollowChess();

            if (chessGameFunctions.loadBoard(boardFileDetails)) {
                // Print the initial board
                System.out.println("Initial Board Configuration:");
                chessGameFunctions.printBoard(new PrintWriter(System.out));
                // Apply the move sequence
                if (chessGameFunctions.applyMoveSequence(userInput)) {
                    // Print the final board
                    System.out.println("\nFinal Board Configuration:");
                    chessGameFunctions.printBoard(new PrintWriter(System.out));

                } else {
                    System.out.println("Invalid move sequence. Board not updated.");
                    chessGameFunctions.printBoard(new PrintWriter(System.out));
                }

            } else {
                System.out.println("Invalid board configuration");
            }

        } catch (FileNotFoundException e) {
            return ;
        } catch (IOException e) {
            return ;
        }
    }
}