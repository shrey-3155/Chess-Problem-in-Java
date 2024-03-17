/**
 * Author: Shrey Patel
 * Banner Id: B00960433, email id: sh644024@dal.ca
 * Assignment 1, The Chess problem
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//CLass that contains all other functions which returns boolean, some string or void and this function is called in the main class A1.java to
//further load board and perform other chess operations
public class FollowChess {

    // Defining an empty 2d array to store the board and further print it
    private char[][] chessBoarddata;
    // Variables for storing the length and width of the board and using them
    // through out the code wherever needed.
    public int boardwidth;
    public int boardlength;

    // Define a list that stores captured pieces by the white player
    private List<Character> pieceWhiteCaptured;

    // Define a list that stores captured pieces by the black player
    private List<Character> pieceBlackCaptured;

    // Initializes an instance of the object; constructor that takes the same name
    // as the main clas FollowChess and initializes three variables
    // One for storing the chess board in the form of 2D array and other two to
    // store the length and width of the board.
    public FollowChess() {

        chessBoarddata = new char[0][0];
        pieceWhiteCaptured = new ArrayList<>();
        pieceBlackCaptured = new ArrayList<>();

    }

    // Below is the loadBoard() function which takes boardFileDetails as input that
    // contains txt file for board configuration where capital letters denote black
    // pieces
    // and white pieces are in lower case. It returns true or false after checking
    // the input from the txt file
    public boolean loadBoard(BufferedReader boardFileDetails){

        // Print the file that contains the initial configuration of the chess board and
        // store into one string
        try {
            // Defining variable that store the board configuration. dataOfChess stores the
            // txt file data and chessBoardConfig
            // stores that data in chess format and is further used to print the data
            String dataOfChess = "";
            List<String> chessBoardConfig = new ArrayList<>();

            if(boardFileDetails == null){
                return false;
            }
            // This loop iterates over the file line by line and goes till the end of file
            // till it becomes null and gets all the data store in one variable
            dataOfChess = boardFileDetails.readLine();
            while (dataOfChess != null) {
                if (dataOfChess.trim().isEmpty()){return true;}
                chessBoardConfig.add(dataOfChess);
                dataOfChess = boardFileDetails.readLine();
            }

            if (chessBoardConfig == null || chessBoardConfig.isEmpty()) {
                return false;
            }
            boardwidth = chessBoardConfig.get(0).length();
            boardlength = chessBoardConfig.size();
            for (String row : chessBoardConfig) {
                if (row.length() != boardwidth) {
                    return false;
                }
            }
            // Checks whether the file is empty or not; if not empty then checks the
            // validity of the chess board.
            if (chessBoardConfig != null) {

                // Defining variables for counting rows, columns and king count of both white
                // and black pieces.
                // Assigning row count and column count equal to the board configuration size.

                int rowCount = chessBoardConfig.size();
                int colCount = chessBoardConfig.get(0).length();
                int WhiteKing = 0, BlackKing = 0;

                String validPieces = "kqnprbKQNPRB.";//to check whether the board pieces are within the defined range

                // The while loop is for checking whether the board configuration has both the
                // kings or not. If not then the board is not valid and returns false and does
                // not load the board.
                // Here the iterator is defined for iterating through all the elements of the
                // file one by one.
                Iterator<String> iterator = chessBoardConfig.iterator();
                while (iterator.hasNext()) {
                    String BoardElement = iterator.next();
                    if (BoardElement.contains("K")) {
                        BlackKing = BlackKing + 1;
                    }
                    if (BoardElement.contains("k")) {
                        WhiteKing = WhiteKing + 1;
                    }
                }
                

                // Here the validity of the board configuration is checked for all the test
                // cases in if condition
                if ((BlackKing == 1) && (WhiteKing == 1) && (rowCount > 0) && (colCount > 0)) {

                    // Then after a 2D array is defined and the whole data in ChessBoardConfig is
                    // store in the form of array in that variable. The two for loops iterate till
                    // the row count and column count of the txt file.
                    chessBoarddata = new char[rowCount][colCount];
                    for (int j = 0; j < rowCount; j++) {
                        String row = chessBoardConfig.get(j);
                        for (int p = 0; p < colCount; p++) {
                            chessBoarddata[j][p] = row.charAt(p);
                        }
                    }
                    for (int i = 0; i < boardlength; i++) {
                        for (int j = 0; j < boardwidth; j++) {
                            char quoted = chessBoarddata[i][j];
                            if(!validPieces.contains(String.valueOf(quoted))){
                               return false;
                                                           }
                        }
                    }
                    return true;
                }

            }
            return false;
        } catch (IOException e) {
        }

        return false;
    }

    // The printBoard function takes one input as what to print above the board and
    // does the functionality of showing the board in the console.
    public boolean printBoard(PrintWriter outstream) {
        // Here the logic to print the latest board configuration is defined which is
        // called after every valid move
        // Return true if the board is printed successfully, otherwise return false
        // The if loop here checks the chessBoard array whether it is null or not
        // After that the for loop
        if (chessBoarddata != null) {
            try{for (char[] RowofBoard : chessBoarddata) {
                outstream.print(new String(RowofBoard) + "\n");
            }
                // The flush class is used here to make sure that the entire board is printed
                // and no element is left
                outstream.flush();
                return true;}
            catch(Exception e){
                return false;
            }
        }
        return false;

    }

    // The applyMoveSequence function is the one which takes input from one file
    // which has four space separated
    // values. They are start row, start column, end row and end column. The
    // function here calls other function
    // in order to make the move after checking its validity.
    public boolean applyMoveSequence(BufferedReader userInput) {

        // Here the use of try catch makes sure that the exceptions are handled well if
        // there is any error.
        try {

            String testLine = null; // further used to store the whole file input line by line
            boolean isWhitePiece = true; // Variable for checking that the move is for white piece or not
            // Iterates through the whole file until the last line where it gets null
            while (userInput.ready()) {
                testLine = userInput.readLine();
                String[] move = testLine.split(" ");// splits the nth input line by space and stores in move array

                if ((move.length == 4))
                { // validation for checking whether input is valid or not

                    int startRow = boardlength - Character.getNumericValue(move[1].charAt(0));
                    int startCol = move[0].charAt(0) - 'a';
                    int endRow = boardlength - Character.getNumericValue(move[3].charAt(0));
                    int endCol = move[2].charAt(0) - 'a';

                    //Check whether the entered sequence is in bound or not.
                    if (startRow < 0 || startRow >= chessBoarddata.length || startCol < 0 || startCol >= chessBoarddata[0].length ||
                            endRow < 0 || endRow >= chessBoarddata.length || endCol < 0 || endCol >= chessBoarddata[0].length) {

                        return false;
                    }
                    // Storing the exact piece value in a new variable called piece
                    char piece = chessBoarddata[startRow][startCol];
                    int player;
                    if (Character.isLowerCase(chessBoarddata[startRow][startCol])) {
                        player = 0;
                    } else {
                        player = 1;
                    }

                    // Here the if condition checks whether the piece is in upper case ot not and is
                    // white piece or not
                    // This is to make sure that the white gets the first move then black piece and
                    // in alternate order
                    if ((isWhitePiece && Character.isLowerCase(piece)) || (!isWhitePiece && Character.isUpperCase(piece))) {
                        // Now isValidMove function is called with 4 parameters in order to check
                        // whether move is valid or not
                        // and if valid then does the move else returns false and message is displayed.
                            if (isValidMove(startRow, startCol, endRow, endCol)) {
                                if (Character.isLowerCase(piece)) {
                                    isWhitePiece = false;
                                } else {
                                    isWhitePiece = true;
                                }

                            } else {
                                return false; // Invalid move
                            }


                    } else {
                        return false; // Invalid input sequence, should be white first then black
                    }

                } else {
                    return false; // Invalid move format
                }
            }
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    // Here the validity of the move is checked which is called in the earlier
    // function
    // Takes four input parameters; first checks that the move requested is in the
    // boundary of the board
    // then if yes applies move for white and black move separately.
    private boolean isValidMove(int startRow, int startCol, int endRow, int endCol) {
        // Check if the move is within the board boundaries of the board of NxM size
        if (startRow < 0 || startRow >= chessBoarddata.length || startCol < 0 || startCol >= chessBoarddata[0].length ||
                endRow < 0 || endRow >= chessBoarddata.length || endCol < 0 || endCol >= chessBoarddata[0].length) {

            return false;
        }

        // Check if the piece at the starting position belongs to the current player and
        // whether it is Black or White by comparing the
        // string value as upper case or lower case.
        char piece = chessBoarddata[startRow][startCol];
        char playerColor = Character.isUpperCase(piece) ? 'B' : 'W';// If piece is in upper case then black piece else
                                                                    // white

        // After checking the color calls the individual color move function by having 5
        // parameters in form of piece itself and its location
        // in 2D array
        if ((playerColor == 'W' && !isValidWhiteMove(piece, startRow, startCol, endRow, endCol))) {
            return false;
        } else if ((playerColor == 'B' && !isValidBlackMove(piece, startRow, startCol, endRow, endCol))) {
            return false;
        }

        return true;
    }

    // This function is called in IsValidMove and it checks whether the input that
    // is provided for white move is valid or not
    // by checking some constraints and then calls make move function to make the
    // required move.
    private boolean isValidWhiteMove(char piece, int startRow, int startCol, int endRow, int endCol) {
        // Implemented logic for valid white piece moves for each of the pieces like
        // pawn, king, queen etc.

        String Color = String.valueOf('W');// Converted the string value to char for make move input for pawn

        // here it checks for all the pieces of the white player and accordingly calls
        // the make move function
        // if the move is valid then ir returns true.
        if (piece == 'k') {
            kingMove(piece, startRow, startCol, endRow, endCol);
            return true;
        } else if (piece == 'q') {
            QueenMove(piece, startRow, startCol, endRow, endCol);
            return true;
        } else if (piece == 'r') {
            RookMove(piece, startRow, startCol, endRow, endCol);
            return true;
        } else if (piece == 'b') {
            BishopMove(piece, startRow, startCol, endRow, endCol);
            return true;
        } else if (piece == 'n') {
            KnightMove(piece, startRow, startCol, endRow, endCol);
            return true;
        } else if (piece == 'p') {
            WhitePawnMove(Color, piece, startRow, startCol, endRow, endCol);
            return true;
        } else {
            return false;
        }

    }

    /*
    *  This function is called in IsValidMove and it checks whether the input that
    * is provided for black move is valid or not
    * by checking some constraints and then calls make move function to make the required move.
    * */
    private boolean isValidBlackMove(char piece, int startRow, int startCol, int endRow, int endCol) {
        // Implemented logic for valid white piece moves for each of the pieces like
        // pawn, king, queen etc.
        String Color = String.valueOf('B');

        // here it checks for all the pieces of the black player and accordingly calls
        // the make move function
        // if the move is valid then ir returns true.
        if (piece == 'K') {
            kingMove(piece, startRow, startCol, endRow, endCol);
            return true;
        } else if (piece == 'Q') {
            QueenMove(piece, startRow, startCol, endRow, endCol);
            return true;
        } else if (piece == 'R') {
            RookMove(piece, startRow, startCol, endRow, endCol);
            return true;
        } else if (piece == 'B') {
            BishopMove(piece, startRow, startCol, endRow, endCol);
            return true;
        } else if (piece == 'N') {
            KnightMove(piece, startRow, startCol, endRow, endCol);
            return true;
        } else if (piece == 'P') {
            BlackPawnMove(Color, piece, startRow, startCol, endRow, endCol);
            return true;
        } else {
            return false;
        }

    }

    // Checking the condition for king to move in the chess.
    // A king moves in all direction but only one step at a time.
    public boolean kingMove(char piece, int startRow, int startCol, int endRow, int endCol) {
        /*
         * king also can move diagonally and we can execute it by the row difference and
         * col difference as both of there
         * must be less than or equal to 1.
         */
        if ((Math.abs(endRow - startRow) <= 1 && Math.abs(endCol - startCol) <= 1)) {
            int stepRow = (Math.abs(endRow - startRow) == 0) ? 0 : ((endRow > startRow) ? 1 : -1);
            int stepCol = (Math.abs(endCol - startCol) == 0) ? 0 : ((endCol > startCol) ? 1 : -1);

            for (int i = 1; i < Math.max(Math.abs(endRow - startRow), Math.abs(endCol - startCol)); i++) {
                int checkRow = startRow + i * stepRow;
                int checkCol = startCol + i * stepCol;

                // Check if there is an obstacle in the path
                if (chessBoarddata[checkRow][checkCol] != '.') {
                    return false;
                }
            }
            makeMove(piece, startRow, startCol, endRow, endCol); // called make move to execute the move
        }
        // Here we check whether staring and ending row or starting col and ending col
        // are same or not.
        else if ((startRow == endRow) || (startCol == endCol)) {
            int stepRow = (Math.abs(endRow - startRow) == 0) ? 0 : ((endRow > startRow) ? 1 : -1);
            int stepCol = (Math.abs(endCol - startCol) == 0) ? 0 : ((endCol > startCol) ? 1 : -1);

            for (int i = 1; i < Math.max(Math.abs(endRow - startRow), Math.abs(endCol - startCol)); i++) {
                int checkRow = startRow + i * stepRow;
                int checkCol = startCol + i * stepCol;

                // Check if there is an obstacle in the path
                if (chessBoarddata[checkRow][checkCol] != '.') {
                    return false;
                }
            }
            makeMove(piece, startRow, startCol, endRow, endCol); // called make move to execute the move
        }
        return true;
    }

    /*
     * Checking the condition for queen to move in the chess.
     * A Queen moves in all direction. It can move as many squares as it likes left
     * or right horizontally,
     * or as many squares as it likes up or down vertically
     */
    public boolean QueenMove(char piece, int startRow, int startCol, int endRow, int endCol) {
        /*
         * Here the rows should be equal and column should be equal or
         * the absolute difference between rows and columns should be equal
         */
        if ((startRow == endRow) || (startCol == endCol)
                || (Math.abs(endRow - startRow) == Math.abs(endCol - startCol))) {
            int stepRow = (Math.abs(endRow - startRow) == 0) ? 0 : ((endRow > startRow) ? 1 : -1);
            int stepCol = (Math.abs(endCol - startCol) == 0) ? 0 : ((endCol > startCol) ? 1 : -1);

            for (int i = 1; i < Math.max(Math.abs(endRow - startRow), Math.abs(endCol - startCol)); i++) {
                int checkRow = startRow + i * stepRow;
                int checkCol = startCol + i * stepCol;

                // Check if there is an obstacle in the path
                if (chessBoarddata[checkRow][checkCol] != '.') {
                    return false;
                }
            }
            makeMove(piece, startRow, startCol, endRow, endCol); // called make move to execute the move
        }
        return true;
    }

    /*
     * Checking the condition for Rook to move in the chess.
     * A Rook moves in either vertical or horizontal direction. It can move as many
     * squares as it likes left or right
     * horizontally or as many squares as it likes up or down vertically
     */
    public boolean RookMove(char piece, int startRow, int startCol, int endRow, int endCol) {
        /*
         * here it checks whether the end row and start row are equal
         * or end column or start column are equal
         */
        if ((startRow == endRow) || (startCol == endCol)) {
            int stepRow = (Math.abs(endRow - startRow) == 0) ? 0 : ((endRow > startRow) ? 1 : -1);
            int stepCol = (Math.abs(endCol - startCol) == 0) ? 0 : ((endCol > startCol) ? 1 : -1);

            for (int i = 1; i < Math.max(Math.abs(endRow - startRow), Math.abs(endCol - startCol)); i++) {
                int checkRow = startRow + i * stepRow;
                int checkCol = startCol + i * stepCol;

                // Check if there is an obstacle in the path
                if (chessBoarddata[checkRow][checkCol] != '.') {
                    return false;
                }
            }
            makeMove(piece, startRow, startCol, endRow, endCol); // called make move to execute the move
        }
        return true;
    }

    /*
     * Checking the condition for Bishop to move in the chess.
     * A Bishop moves in all directions but horizontally only.
     */
    public boolean BishopMove(char piece, int startRow, int startCol, int endRow, int endCol) {
        /*
         * Here we have to check the absolute difference between the rows and columns
         * If both are equal of start and end position then it is a valid move
         */
        if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol)) {
            int stepRow = (Math.abs(endRow - startRow) == 0) ? 0 : ((endRow > startRow) ? 1 : -1);
            int stepCol = (Math.abs(endCol - startCol) == 0) ? 0 : ((endCol > startCol) ? 1 : -1);

            for (int i = 1; i < Math.max(Math.abs(endRow - startRow), Math.abs(endCol - startCol)); i++) {
                int checkRow = startRow + i * stepRow;
                int checkCol = startCol + i * stepCol;

                // Check if there is an obstacle in the path
                if (chessBoarddata[checkRow][checkCol] != '.') {
                    return false;
                }
            }
            makeMove(piece, startRow, startCol, endRow, endCol); // called make move to execute the move
        }
        return true;
    }

    /*
     * Checking the condition for Knight to move in the chess.
     * A Knight moves in all directions but horizontally only moves one square left
     * or right horizontally and then two
     * squares up or down vertically, OR it moves two squares left or right
     * horizontally and then one square up or down
     * vertically
     */
    public void KnightMove(char piece, int startRow, int startCol, int endRow, int endCol) {
        /*
         * Here we have to check if the row difference is 2 and col difference is 1 or
         * row difference is 1 and col difference is 2.
         */
        if ((Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 1)
                || (Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 2)) {
            makeMove(piece, startRow, startCol, endRow, endCol); // called make move to execute the move
        }
    }

    /*
     * Checking the condition for Pawn to move in the chess.
     * A Pawn moves forward just one square at a time. It attacks (or captures) each
     * square diagonally to the
     * left or right
     */
    public void BlackPawnMove(String Color, char piece, int startRow, int startCol, int endRow, int endCol) {

        // Valid non-capturing move (one step ahead)
        if (startRow < endRow && Math.abs(endRow - startRow) == 1 && Math.abs(startCol - endCol) == 0
                && chessBoarddata[endRow][endCol] == '.') {
            makeMove(piece, startRow, startCol, endRow, endCol); // called make move to execute the move
        }
        // Valid capturing move diagonally only if there is an opponent piece
        else if (startRow < endRow && Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 1
                && chessBoarddata[endRow][endCol] != '.' && Character.isLowerCase(chessBoarddata[endRow][endCol])) {
            makeMove(piece, startRow, startCol, endRow, endCol); // called make move to execute the move
        }

    }

    /*
     * Checking the condition for Pawn to move in the chess.
     * A Pawn moves forward just one square at a time. It attacks (or captures) each
     * square diagonally to the
     * left or right
     */
    public void WhitePawnMove(String Color, char piece, int startRow, int startCol, int endRow, int endCol) {
        // Valid non-capturing move (one step ahead)
        if (startRow > endRow && Math.abs(endRow - startRow) == 1 && Math.abs(startCol - endCol) == 0
                && chessBoarddata[endRow][endCol] == '.') {
            makeMove(piece, startRow, startCol, endRow, endCol); // called make move to execute the move
        }
        // Valid capturing move diagonally only if there is an opponent piece
        else if (startRow > endRow && Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 1
                && chessBoarddata[endRow][endCol] != '.' && Character.isUpperCase(chessBoarddata[endRow][endCol])) {
            makeMove(piece, startRow, startCol, endRow, endCol); // called make move to execute the move
        }

    }

    /*
     * here the logic for moving the piece is defined after checking all the
     * conditions for different types of pieces
     * This function makes sure that the position where the piece is moving is
     * replaced by the piece whether it is
     * opponent or blank dot.
     */
    private void makeMove(char piece, int startRow, int startCol, int endRow, int endCol) {
        // Implemented the logic to update the chessboard based on a valid move

        int whichPlayerCaptured;// This variable manages to make record of which player is captured which can be
                                // further used to store piece captured in list.
        if (Character.isLowerCase(chessBoarddata[endRow][endCol])) {
            whichPlayerCaptured = 1;

        } else {
            whichPlayerCaptured = 0;
        }

        // String capturedPiece; here the dot is replaced by the piece after the move.
        if (chessBoarddata[endRow][endCol] == '.') {
            chessBoarddata[startRow][startCol] = '.';
            chessBoarddata[endRow][endCol] = piece;
        } else {
            if (Character.isUpperCase(chessBoarddata[startRow][startCol]) != (Character.isUpperCase(chessBoarddata[endRow][endCol]))) {
                updateCaptureOrder(chessBoarddata[endRow][endCol], whichPlayerCaptured);// Update Capture order function to store the piece that is captured.
                chessBoarddata[startRow][startCol] = '.';
                chessBoarddata[endRow][endCol] = piece;
            }
        }

    }

    // This function is used to update the list that is defined for storing the
    // captured pieces for each of the player.
    private void updateCaptureOrder(char capturedPiece, int player) {
        if (player == 1) {
            // White player added when it is captured.
            pieceWhiteCaptured.add(capturedPiece);
            captureOrder(1);// Called captureOrder by passing player as binary
        } else if (player == 0) {
            // Black player is added when it is captured.
            pieceBlackCaptured.add(capturedPiece);
            captureOrder(0);
        }

    }

    // It just checks the player by 0 and 1 and returns the array list of captured
    // pieces accordingly.
    List<Character> captureOrder(int player) {
        if (player == 1) {
            // White player list returns.
            return pieceWhiteCaptured;
        } else if (player == 0) {
            // Black player list returns.
            return pieceBlackCaptured;
        }

        return null;
    }

    // This functions takes 0 or 1 as input for white and black king and checks
    // whether the king is in check or not.
    boolean inCheck(int player) {
        if(player == 0 || player == 1){
            // Below two parameters are defined to get the exact row and column of the king
            // of both the colors.
            int kingRow = 0;
            int kingColumn = 0;

            // Here we assign the value of king piece to a variable by checking whether
            // player is white or black
            // As we are checking for in check so we will reverse the scenario where white
            // player checks of capital K
            char kingSymbol = (player == 0) ? 'k' : 'K';
            for (int i = 0; i < boardlength; i++) {
                for (int j = 0; j < boardwidth; j++) {
                    // After iterating through all the rows and columns we get the exact location of
                    // the king.
                    if (chessBoarddata[i][j] == kingSymbol) {
                        kingRow = i;
                        kingColumn = j;
                        break;
                    }
                }
            }

            for (int i = 0; i < boardlength; i++) {
                for (int j = 0; j < chessBoarddata[0].length; j++) {
                    char piece = chessBoarddata[i][j];
                    // Check if the piece belongs to the opponent and can capture the king
                    if ((player == 0 && Character.isUpperCase(piece))) {
                        if (isValidBlackMoveForCheck(i, j, kingRow, kingColumn)) {
                            return true; // The white king is in check
                        }
                    }
                    if ((player == 1 && Character.isLowerCase(piece))) {
                        if (isValidWhiteMoveForCheck(i, j, kingRow, kingColumn)) {
                            return true; // The black king is in check
                        }

                    }
                }
            }
            return false;
        }

        return false;
    }

    /*
    * This function is made to make sure that while attacking there is no player in between of the same color
    * So it iterates over the whole path to make sure about this and returns true if the path is clear else false
    * */
    private boolean isPathBlocked(int endRow, int endCol, int kingRow, int kingColumn){
        int stepRow = (Math.abs(endRow - kingRow) == 0) ? 0 : ((kingRow > endRow) ? 1 : -1);
        int stepCol = (Math.abs(endCol - kingColumn) == 0) ? 0 : ((kingColumn > endCol) ? 1 : -1);

        //The loop iterates through all the dots that come in between the start position and the end position.
        for (int i = 1; i < Math.max(Math.abs(endRow - kingRow), Math.abs(endCol - kingColumn)); i++) {
            int checkRow = endRow + i * stepRow;
            int checkCol = endCol + i * stepCol;

            // Check if there is an obstacle in the path by checking that is there dot value in the path or not.
            if (chessBoarddata[checkRow][checkCol] != '.') {
                return false;
            }
        }
        return true;
    }

    /*
    * This function checks the move sequence for the white piece when the value of king is 1 and it is black king
    * It checks whether the move is valid for any of one piece in the next move such that it can attack the king.
    * */
    private boolean isValidWhiteMoveForCheck(int endRow, int endCol, int kingRow, int kingColumn) {
        char checkPiece = chessBoarddata[endRow][endCol];//Take the starting postion of the piece

            //Here the condition for every piece is checked by alphabets whether it has one move that can put the king in check
            if (checkPiece == 'k') {
                if ((Math.abs(kingRow - endRow) <= 1 && Math.abs(kingColumn - endCol) <= 1)) {
                    //To check whether the path is clear or not to attack the king and give check.
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;
                }
            } else if (checkPiece == 'q') {
                if ((kingRow == endRow) || (kingColumn == endCol) || (Math.abs(endRow - kingRow) == Math.abs(endCol - kingColumn))) {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;
                }

            } else if (checkPiece == 'r') {
                if ((kingRow == endRow) || (kingColumn == endCol)) {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }

            } else if (checkPiece == 'b') {
                if (Math.abs(kingRow - endRow) == Math.abs(kingColumn - endCol)) {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }
            } else if (checkPiece == 'n') {
                if ((Math.abs(kingRow - endRow) == 2 && Math.abs(kingColumn - endCol) == 1)
                        || (Math.abs(kingRow - endRow) == 1 && Math.abs(kingColumn - endCol) == 2)) {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }
            } else if (checkPiece == 'p') {
                if (endRow > kingRow && Math.abs(endRow - kingRow) == 1 && Math.abs(kingColumn - endCol) == 0
                        && chessBoarddata[kingRow][kingColumn] == '.') {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }
                // Valid capturing move diagonally only if there is an opponent piece
                else if (endRow > kingRow && Math.abs(endRow - kingRow) == 1 && Math.abs(kingColumn - endCol) == 1
                        && chessBoarddata[kingRow][kingColumn] != '.' && Character.isUpperCase(chessBoarddata[kingRow][kingColumn])) {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }

            }
        return false;
    }

    /*
     * This function checks the move sequence for the black piece when the value of king is 1 and it is white king
     * It checks whether the move is valid for any of one piece in the next move such that it can attack the king.
     * */
    private boolean isValidBlackMoveForCheck(int endRow, int endCol, int kingRow, int kingColumn) {
        char checkPiece = chessBoarddata[endRow][endCol];//Take the starting position of the piece

        //Here the condition for every piece is checked by alphabets whether it has one move that can put the king in check
        if (checkPiece == 'K') {
                if (Math.abs(kingRow - endRow) <= 1 && Math.abs(kingColumn - endCol) <= 1) {
                    //To check whether the path is cleared or not for the king to be in check.
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }
            } else if (checkPiece == 'Q') {
                if ((kingRow == endRow) || (kingColumn == endCol)
                        || (Math.abs(endRow - kingRow) == Math.abs(endCol - kingColumn))) {

                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;
                }

            } else if (checkPiece == 'R') {
                if ((kingRow == endRow) || (kingColumn == endCol)) {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }

            } else if (checkPiece == 'B') {
                if (Math.abs(kingRow - endRow) == Math.abs(kingColumn - endCol)) {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }
            } else if (checkPiece == 'N') {
                if ((Math.abs(kingRow - endRow) == 2 && Math.abs(kingColumn - endCol) == 1)
                        || (Math.abs(kingRow - endRow) == 1 && Math.abs(kingColumn - endCol) == 2)) {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }
            } else if (checkPiece == 'P') {
                if (endRow < kingRow && Math.abs(endRow - kingRow) == 1 && Math.abs(kingColumn - endCol) == 0
                        && chessBoarddata[kingRow][kingColumn] == '.') {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }
                // Valid capturing move diagonally only if there is an opponent piece
                else if (endRow < kingRow && Math.abs(endRow - kingRow) == 1 && Math.abs(kingColumn - endCol) == 1
                        && chessBoarddata[kingRow][kingColumn] != '.' && Character.isLowerCase(chessBoarddata[kingRow][kingColumn])) {
                    if(isPathBlocked(endRow, endCol, kingRow, kingColumn))
                    {
                        return true;
                    }
                    return false;                }

            }

        return false;
    }

    /*
    * This function is made to check whether there is atleast one valid move for every piece of the game
    * That move can be in any direction of the board. Also the same color piece cannot overlap each other.
    * */
    boolean pieceCanMove(String boardPosition) {
        if(boardPosition == null){
            return false;
        }
        String[] a1 = boardPosition.split(" "); // separate the value by space and store in array
        if(a1.length<2 || a1.length>2 ){
            return false;
        }
        int rowPiece = (boardlength - (Character.getNumericValue(a1[1].charAt(0))));//get the piece row in terms of integer
        int columnPiece = a1[0].charAt(0) - 'a';//get the piece column in terms of integer


        if(rowPiece<0||columnPiece<0||rowPiece>boardlength||columnPiece>boardwidth){
            return false;
        }

        char piece = chessBoarddata[rowPiece][columnPiece];
        int endRow = rowPiece;// assign piece row value to endRow
        int endColumn = columnPiece;// assign piece column value to endColumn

        if(piece == '.'){
            return false;
        }
        //Here it checks whether the piece is king, queen or anything else then accordingly checks horizontal, vertical and diagonal moves
        //If atleast one of the three cases is true then the piece move can be called as valid.
        if ((piece == 'K') || (piece == 'k')) {
            //King can move vertically, horizontally and diagonally so checking for all cases.
            if ((verticalMoveCheck(rowPiece, columnPiece, endRow, endColumn)) || (horizontalMoveCheck(rowPiece, columnPiece, endRow, endColumn)) || (canMoveDiagonally(rowPiece, columnPiece, endRow, endColumn))) {
                return true;
            } else {
                return false;
            }
        } else if ((piece == 'R') || (piece == 'r')) {
            //Rook moves in vertical or horizontal directions so checking for that.
            if ((verticalMoveCheck(rowPiece, columnPiece, endRow, endColumn)) || (horizontalMoveCheck(rowPiece, columnPiece, endRow, endColumn))) {
                return true;
            } else {
                return false;
            }
        } else if ((piece == 'B') || (piece == 'b')) {
            //Bishop moves diagonal only so checking for that.
            if ((canMoveDiagonally(rowPiece, columnPiece, endRow, endColumn))) {
                return true;
            } else {
                return false;
            }
        } else if ((piece == 'Q') || (piece == 'q')) {
            //Queen can move vertically, horizontally and diagonally so checking for all cases.
            if ((verticalMoveCheck(rowPiece, columnPiece, endRow, endColumn))
                    || (horizontalMoveCheck(rowPiece, columnPiece, endRow, endColumn))
                    || (canMoveDiagonally(rowPiece, columnPiece, endRow, endColumn))) {
                return true;
            } else {
                return false;
            }
        } else if ((piece == 'N') || (piece == 'n')) {
            //Knight can move in L shape so checking for all L shape move cases.
            if ((knighMoveCheck(rowPiece, columnPiece, endRow, endColumn))) {
                return true;
            } else {
                return false;
            }

        } else if ((piece == 'P') || (piece == 'p')) {
            if ((pawnMoveCondition(rowPiece, columnPiece, endRow, endColumn))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /*
    * Logic for checking whether pawn can move in at least one direction in either top to bottom or bottom to top direction
    * depending on the color of the piece.
    * */
    public boolean pawnMoveCondition(int rowPiece, int columnPiece, int endRow, int endColumn) {
        //Logic for white pawn
        if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
            //Checks by adding and subtracting 1 to rows and columns to check that whether it can move in just next space or not in all directions possible
            if (ValidMove(rowPiece, columnPiece, endRow - 1, endColumn + 1)) {
                if(Character.isUpperCase(chessBoarddata[endRow - 1][endColumn + 1])){
                    return true;
                }
                else{
                    return false;
                }

            }
            if (ValidMove(rowPiece, columnPiece, endRow - 1, endColumn - 1)) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow - 1, endColumn - 1)) {
                    if(Character.isUpperCase(chessBoarddata[endRow - 1][endColumn + 1])){
                        return true;
                    }
                    else{
                        return false;
                    }
                }

            }
            if (ValidMove(rowPiece, columnPiece, endRow - 1, endColumn)) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow - 1, endColumn)) {
                    return true;
                }
                else{
                    return false;
                }

            }

            //Logic for black pawn
        } else if (Character.isUpperCase(chessBoarddata[rowPiece][columnPiece])) {
            //Checks by adding or subtracting rows and columns to check that whether it can move in just next space or not in all directions possible
            if (ValidMove(rowPiece, columnPiece, endRow + 1, endColumn + 1)) {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow + 1, endColumn + 1)) {
                    if(Character.isLowerCase(chessBoarddata[endRow - 1][endColumn + 1])){
                        return true;
                    }
                }

            }
            if (ValidMove(rowPiece, columnPiece, endRow + 1, endColumn - 1)) {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow + 1, endColumn - 1)) {
                    if (validCaptureforwhite(rowPiece, columnPiece, endRow + 1, endColumn + 1)) {
                        if(Character.isLowerCase(chessBoarddata[endRow - 1][endRow + 1])){
                            return true;
                        }
                    }
                }
            }
            if (ValidMove(rowPiece, columnPiece, endRow + 1, endColumn)) {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow + 1, endColumn)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Logic for checking whether Knight can move in at least one direction in either top to bottom or bottom to top direction
     * depending on the color of the piece. The logic for L shape move is defined here
     * */
    public boolean knighMoveCheck(int rowPiece, int columnPiece, int endRow, int endColumn) {
        //Checks by adding and subtracting rows and columns to check that whether it can move in just next space or not in all directions possible
        if (ValidMove(rowPiece, columnPiece, endRow - 1, endColumn - 2)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow - 1, endColumn - 2)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow - 1, endColumn - 2)) {
                    return true;

                }
            }
        }
        //Checks by adding and subtracting rows and columns to check that whether it can move in just next space or not in all directions possible
        if (ValidMove(rowPiece, columnPiece, endRow - 1, endColumn + 2)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow - 1, endColumn + 2)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow - 1, endColumn + 2)) {
                    return true;

                }
            }
        }
        //Checks by adding and subtracting 1 or 2 rows and columns to check that whether it can move in just next space or not in all directions possible
        if (ValidMove(rowPiece, columnPiece, endRow + 1, endColumn - 2)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow + 1, endColumn - 2)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow + 1, endColumn - 2)) {
                    return true;

                }
            }
        }
        if (ValidMove(rowPiece, columnPiece, endRow + 1, endColumn + 2)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow + 1, endColumn + 2)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow + 1, endColumn + 2)) {
                    return true;

                }
            }
        }
        if (ValidMove(rowPiece, columnPiece, endRow - 2, endColumn - 1)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow - 2, endColumn - 1)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow - 2, endColumn - 1)) {
                    return true;

                }
            }
        }

        if (ValidMove(rowPiece, columnPiece, endRow - 2, endColumn + 1)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow - 2, endColumn + 1)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow - 2, endColumn + 1)) {
                    return true;

                }
            }
        }

        if (ValidMove(rowPiece, columnPiece, endRow + 2, endColumn - 1)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow + 2, endColumn - 1)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow + 2, endColumn - 1)) {
                    return true;

                }
            }
        }

        if (ValidMove(rowPiece, columnPiece, endRow + 2, endColumn + 1)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow + 2, endColumn + 1)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow + 2, endColumn + 1)) {
                    return true;

                }
            }
        }

        return false;
    }

    /*
     * Logic for checking whether piece can move in vertical direction for atleast one step in any of the directions.
     * */
    public boolean verticalMoveCheck(int rowPiece, int columnPiece, int endRow, int endColumn) {
        //Checks by adding and subtracting 1 to rows and columns to check that whether it can move in just next space or not in all directions possible
        if (ValidMove(rowPiece, columnPiece, endRow + 1, endColumn)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow + 1, endColumn)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow + 1, endColumn)) {
                    return true;

                }
            }
        }
        //Checks by adding and subtracting 1 to rows and columns to check that whether it can move in just next space or not in all directions possible

        if (ValidMove(rowPiece, columnPiece, endRow - 1, endColumn)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow - 1, endColumn)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow - 1, endColumn)) {
                    return true;

                }
            }
        }

        return false;
    }

    /*
     * Logic for checking whether piece can move in horizontal direction for atleast one step in any of the directions.
     * */
    public boolean horizontalMoveCheck(int rowPiece, int columnPiece, int endRow, int endColumn) {
        //Checks by adding and subtracting 1 to rows and columns to check that whether it can move in just next space or not in all directions possible
        if (ValidMove(rowPiece, columnPiece, endRow, endColumn + 1)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow, endColumn + 1)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow, endColumn + 1)) {
                    return true;

                }
            }
        }
        //Checks by adding and subtracting 1 to rows and columns to check that whether it can move in just next space or not in all directions possible
        if (ValidMove(rowPiece, columnPiece, endRow, endColumn - 1)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow, endColumn - 1)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow, endColumn - 1)) {
                    return true;

                }
            }
        }

        return false;
    }

    /*
     * Logic for checking whether piece can move in diagonal direction for atleast one step in any of the directions.
     * */
    public boolean canMoveDiagonally(int rowPiece, int columnPiece, int endRow, int endColumn) {
        //Checks by adding and subtracting 1 to rows and columns to check that whether it can move in just next space or not in all directions possible
        if (ValidMove(rowPiece, columnPiece, endRow + 1, endColumn + 1)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow + 1, endColumn + 1)) {
                    return true;
                }
            } else if (Character.isUpperCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow + 1, endColumn + 1)) {
                    return true;

                }
            }
        }
        if (ValidMove(rowPiece, columnPiece, endRow - 1, endColumn - 1)) {

            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow - 1, endColumn - 1)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow - 1, endColumn - 1)) {
                    return true;

                }
            }
        }

        if (ValidMove(rowPiece, columnPiece, endRow - 1, endColumn + 1)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow - 1, endColumn + 1)) {
                    return true;
                }
            } else if (Character.isUpperCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow - 1, endColumn + 1)) {
                    return true;

                }
            }
        }

        if (ValidMove(rowPiece, columnPiece, endRow + 1, endColumn - 1)) {
            if (Character.isLowerCase(chessBoarddata[rowPiece][columnPiece])) {
                if (validCaptureforBlack(rowPiece, columnPiece, endRow + 1, endColumn - 1)) {
                    return true;
                }
            } else {
                if (validCaptureforwhite(rowPiece, columnPiece, endRow + 1, endColumn - 1)) {
                    return true;

                }
            }
        }

        return false;
    }

    /*
    * Here the conditions are checked for piece whether there is blank space or is it opponent's piece
    * */
    public boolean validCaptureforBlack(int rowPiece, int columnPiece, int endRow, int endColumn) {
        //Here the piece is checked whether it is upper case or not and also whether there is a dot or not;
        //If yes then white piece has chance to move else cannot move
        if ((Character.isUpperCase(chessBoarddata[endRow][endColumn])) || (chessBoarddata[endRow][endColumn] == '.')) {
            return true;
        }
        return false;
    }

    /*
     * Here the conditions are checked for piece whether there is blank space or is it opponent's piece
     * */
    public boolean validCaptureforwhite(int rowPiece, int columnPiece, int endRow, int endColumn) {
        //Here the piece is checked whether it is lower case or not and also whether there is a dot or not;
        //If yes then black piece has chance to move else cannot move
        if (Character.isLowerCase(chessBoarddata[endRow][endColumn]) || (chessBoarddata[endRow][endColumn] == '.')) {
            return true;
        }
        return false;
    }

    private boolean ValidMove(int startRow, int startCol, int endRow, int endCol) {

        // Check if the move is within the board boundaries of the board of NxM size
        if (startRow < 0 || startRow >= boardlength || startCol < 0 || startCol >= boardwidth ||
                endRow < 0 || endRow >= boardlength || endCol < 0 || endCol >= boardwidth) {
            return false;
        }
        return true;
    }

}