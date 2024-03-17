import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.StringReader;

class ApplyMoveSequenceTest {
    @Test
    void nullStream() {
        FollowChess game = new FollowChess();

        assertFalse( game.applyMoveSequence( null ), "null move sequence stream" );
    }

    @Test
    void emptyStream() {
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "K.\n.k\n" )) ), "basic board" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "" )) ), "empty move stream" );
    }

    @Test
    void startTopEdge() {
        String startBoard = "..X..\n.....\n.....\n.....\nkK...\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "P") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 1 a 2\nc 5 c 4\n" )) ), "move pawn" );

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "R") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 1 a 2\nc 5 c 4\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "N") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 1 a 2\nc 5 b 3\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "B") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 1 a 2\nc 5 d 4\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "Q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 1 a 2\nc 5 c 4\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("K", ".").replace("X", "K") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 1 a 2\nc 5 c 4\n" )) ), "move king" );
    }

    @Test
    void startBottomEdge() {
        String startBoard = "kK...\n.....\n.....\n.....\n..X..\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 c 2\n" )) ), "move pawn" );

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 c 2\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 b 3\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 d 2\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 c 2\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("k", ".").replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 c 2\n" )) ), "move king" );
    }

    @Test
    void startRightEdge() {
        String startBoard = "kK...\n.....\n....X\n.....\n.....\n";
        FollowChess game = new FollowChess();

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "R") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\ne 3 d 3\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "N") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\ne 3 c 2\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "B") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\ne 3 d 4\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "Q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\ne 3 d 4\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("K", ".").replace("X", "K") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\ne 3 d 3\n" )) ), "move king" );
    }

    @Test
    void startLeftEdge() {
        String startBoard = "kK...\n.....\nX....\n.....\n.....\n";
        FollowChess game = new FollowChess();

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "R") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\na 3 b 3\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "N") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\na 3 c 2\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "B") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\na 3 b 4\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "Q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\na 3 b 4\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace( "K", ".").replace("X", "K") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\na 3 b 3\n" )) ), "move king" );
    }

    @Test
    void endTopEdge() {
        String startBoard = "kK...\n..X..\n.....\n.....\n.....\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 4 c 5\n" )) ), "move pawn" );

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 4 c 5\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 4 e 5\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 4 d 5\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 4 c 5\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace( "k", ".").replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 4 c 5\n" )) ), "move king" );
    }

    @Test
    void endBottomEdge() {
        String startBoard = "kK...\n.....\n.....\n..X..\n.....\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "P") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nc 2 c 1\n" )) ), "move pawn" );

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 2 c 1\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 2 e 1\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 2 d 1\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 2 c 1\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace( "k", ".").replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 2 c 1\n" )) ), "move king" );
    }

    @Test
    void endRightEdge() {
        String startBoard = "kK...\n.....\n...X.\n.....\n.....\n";
        FollowChess game = new FollowChess();

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "R") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nd 3 e 3\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "N") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nd 3 e 5\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "B") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nd 3 e 4\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "Q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nd 3 e 3\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace( "K", ".").replace("X", "K") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nd 3 e 3\n" )) ), "move king" );
    }

    @Test
    void endLeftEdge() {
        String startBoard = "kK...\n.....\n.X...\n.....\n.....\n";
        FollowChess game = new FollowChess();

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "R") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nb 3 a 3\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "N") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nb 3 a 1\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "B") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nb 3 a 4\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "Q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nb 3 a 4\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace( "K", ".").replace("X", "K") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nb 3 a 3\n" )) ), "move king" );
    }

    @Test
    void endPastTopEdge() {
        String startBoard = "..X..\n.....\n.....\n.....\n...kK\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 5 c 6\n" )) ), "move pawn" );

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "R") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 5 c 6\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "N") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 5 e 6\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "B") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 5 d 6\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "Q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 5 c 6\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace( "K", ".").replace("X", "K") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 5 c 6\n" )) ), "move king" );
    }

    @Test
    void endPastBottomEdge() {
        String startBoard = "kK...\n.....\n.....\n.....\n..X..\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "P") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 c 0\n" )) ), "move pawn" );

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 c 0\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 a 0\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 d 0\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 c 0\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace( "k", ".").replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 1 c 0\n" )) ), "move king" );
    }

    @Test
    void endPastRightEdge() {
        String startBoard = "kK...\n.....\n....X\n.....\n.....\n";
        FollowChess game = new FollowChess();

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "R") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "e 3 f 3\n" )) ), "move rook" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "N") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "e 3 f 1\n" )) ), "move knight" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "B") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "e 3 f 4\n" )) ), "move bishop" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "Q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "e 3 f 4\n" )) ), "move queen" );

        // King
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace( "K", ".").replace("X", "K") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "e 3 f 3\n" )) ), "move king" );
    }

    // Can't test ending left of left edge...no letter to use as column letter.

    @Test
    void badMoveInput() {
        String startBoard = "kK...\n.....\n..X..\n.....\n.....\n";
        FollowChess game = new FollowChess();

        // Short line
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c4\n" )) ), "Too few items" );
        // Long line
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4 d\n" )) ), "Too many items" );
    }

    @Test
    void blankLineInInput() {
        String startBoard = "kK...\n.....\n..X..\n.....\n.....\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "P") )) ), "empty board stream" );
        // No assert...just don't crash.
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n\nc 4 d 4\n" )) ), "Too few items" );
    }

    @Test
    void moveOneStep() {
        String startBoard = "kK...\n.....\n..X..\n.....\n.....\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "pawn move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "pawn move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "pawn move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "pawn move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "pawn move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "pawn move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "pawn move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "pawn move up and left" );

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "P") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nc 3 c 4\n" )) ), "pawn move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "P") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "a 5 a 4\nc 3 c 2\n" )) ), "pawn move down" );

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "rook move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "rook move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "rook move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "rook move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "rook move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "rook move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 3\n" )) ), "rook move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "rook move up and left" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "knight move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "knight move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "knight move right left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "knight move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "knight move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "knight move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "knight move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "knight move up and left" );

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 5\n" )) ), "knight move up and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 5\n" )) ), "knight move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 4\n" )) ), "knight move right and up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 2\n" )) ), "knight move right and down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 1\n" )) ), "knight move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 1\n" )) ), "knight move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 2\n" )) ), "knight move left and down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 4\n" )) ), "knight move left and up" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "bishop move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "bishop move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "bishop move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "bishop move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "bishop move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "bishop move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 3\n" )) ), "bishop move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "bishop move up and left" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "queen move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "queen move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "queen move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "queen move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "queen move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "queen move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 3\n" )) ), "queen move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "queen move up and left" );

        // King
        startBoard = startBoard.replace( "k", "." );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "king move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "king move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "king move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "king move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "king move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "king move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 3\n" )) ), "king move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "king move up and left" );
    }

    @Test
    void oneStepBlocked() {
        String startBoard = "kK...\n.ppp.\n.pXp.\n.ppp.\n.....\n";
        String knightStartBoard = "kp.pK\np...p\n..X..\np...p\n.p.p.\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "pawn move up" );

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "rook move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "rook move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "rook move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 3\n" )) ), "rook move left" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 5\n" )) ), "knight move up and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 5\n" )) ), "knight move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 4\n" )) ), "knight move right and up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 2\n" )) ), "knight move right and down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 1\n" )) ), "knight move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 1\n" )) ), "knight move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 2\n" )) ), "knight move left and down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 4\n" )) ), "knight move left and up" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "bishop move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "bishop move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "bishop move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "bishop move up and left" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "queen move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "queen move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "queen move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "queen move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "queen move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "queen move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 3\n" )) ), "queen move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "queen move up and left" );

        // King
        startBoard = startBoard.replace( "k","." );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "king move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "king move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "king move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "king move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "king move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "king move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 3\n" )) ), "king move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "king move up and left" );
    }

    @Test
    void oneStepCapture() {
        String startBoard = "kK...\n.PPP.\n.PXP.\n.PPP.\n.....\n";
        String knightStartBoard = "kP.PK\nP...P\n..X..\nP...P\n.P.P.\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "pawn move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "pawn move right" );

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "rook move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "rook move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "rook move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 3\n" )) ), "rook move left" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 5\n" )) ), "knight move up and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 5\n" )) ), "knight move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 4\n" )) ), "knight move right and up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 2\n" )) ), "knight move right and down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 1\n" )) ), "knight move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 1\n" )) ), "knight move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 2\n" )) ), "knight move left and down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 4\n" )) ), "knight move left and up" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "bishop move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "bishop move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "bishop move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "bishop move up and left" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "queen move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "queen move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "queen move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "queen move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "queen move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "queen move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 3\n" )) ), "queen move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "queen move up and left" );

        // King
        startBoard = startBoard.replace( "k", "." );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "king move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 4\n" )) ), "king move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 3\n" )) ), "king move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 d 2\n" )) ), "king move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 2\n" )) ), "king move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 2\n" )) ), "king move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 3\n" )) ), "king move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 b 4\n" )) ), "king move up and left" );
    }

    @Test
    void slideToEdgeClearPath() {
        String startBoard = ".k.K.\n.....\n..X..\n.....\n.....\n";
        FollowChess game = new FollowChess();

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 5\n" )) ), "rook move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 3\n" )) ), "rook move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 1\n" )) ), "rook move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 3\n" )) ), "rook move left" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 5\n" )) ), "bishop move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 1\n" )) ), "bishop move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 1\n" )) ), "bishop move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 5\n" )) ), "bishop move up and left" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 5\n" )) ), "queen move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 5\n" )) ), "queen move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 3\n" )) ), "queen move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 1\n" )) ), "queen move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 1\n" )) ), "queen move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 1\n" )) ), "queen move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 3\n" )) ), "queen move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 5\n" )) ), "queen move up and left" );
    }

    @Test
    void slideToEdgeCapture() {
        String startBoard = "PkPKP\n.....\nP.X.P\n.....\nP.P.P\n";
        FollowChess game = new FollowChess();

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 5\n" )) ), "rook move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 3\n" )) ), "rook move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 1\n" )) ), "rook move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 3\n" )) ), "rook move left" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 5\n" )) ), "bishop move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 1\n" )) ), "bishop move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 1\n" )) ), "bishop move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 5\n" )) ), "bishop move up and left" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 5\n" )) ), "queen move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 5\n" )) ), "queen move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 3\n" )) ), "queen move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 1\n" )) ), "queen move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 1\n" )) ), "queen move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 1\n" )) ), "queen move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 3\n" )) ), "queen move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 5\n" )) ), "queen move up and left" );
    }

    @Test
    void slideToEdgeBlockedPathOwn() {
        String startBoard = ".k.K.\n.ppp.\n.pXp.\n.ppp.\n.....\n";
        FollowChess game = new FollowChess();

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 5\n" )) ), "rook move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 3\n" )) ), "rook move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 1\n" )) ), "rook move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 3\n" )) ), "rook move left" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 5\n" )) ), "bishop move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 1\n" )) ), "bishop move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 1\n" )) ), "bishop move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 5\n" )) ), "bishop move up and left" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 5\n" )) ), "queen move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 5\n" )) ), "queen move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 3\n" )) ), "queen move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 1\n" )) ), "queen move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 1\n" )) ), "queen move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 1\n" )) ), "queen move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 3\n" )) ), "queen move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 5\n" )) ), "queen move up and left" );
    }

    @Test
    void slideToEdgeBlockedPathOpponent() {
        String startBoard = ".k.K.\n.PPP.\n.PXP.\n.PPP.\n.....\n";
        FollowChess game = new FollowChess();

        // Rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 5\n" )) ), "rook move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 3\n" )) ), "rook move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 1\n" )) ), "rook move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "r") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 3\n" )) ), "rook move left" );

        // Bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 5\n" )) ), "bishop move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 1\n" )) ), "bishop move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 1\n" )) ), "bishop move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 5\n" )) ), "bishop move up and left" );

        // Queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 5\n" )) ), "queen move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 5\n" )) ), "queen move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 3\n" )) ), "queen move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 1\n" )) ), "queen move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 1\n" )) ), "queen move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 1\n" )) ), "queen move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 3\n" )) ), "queen move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "q") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 5\n" )) ), "queen move up and left" );
    }

    @Test
    void moveTooFar() {
        String startBoard = ".k.K.\n.....\n..X..\n.....\n.....\n";
        String knightStartBoard = "kK...\n.X...\n.....\n.....\n.....\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "p") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 5\n" )) ), "pawn move up" );

        // Knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( knightStartBoard.replace("X", "n") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "b 2 b 4\n" )) ), "knight two L up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "b") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "b 2 d 2\n" )) ), "bishop two L right" );

        // King
        startBoard = startBoard.replace( "k", "." );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 5\n" )) ), "king move up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 5\n" )) ), "king move up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 3\n" )) ), "king move right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 e 1\n" )) ), "king move down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 1\n" )) ), "king move down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 1\n" )) ), "king move down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 3\n" )) ), "king move left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard.replace("X", "k") )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 a 5\n" )) ), "king move up and left" );
    }

    @Test
    void moveEmptySpace() {
        String startBoard = ".k.K.\n.....\n.....\n.....\n.....\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "c 3 c 4\n" )) ), "no piece to move" );
    }

    @Test
    void goodMoveSequence() {
        String startBoard = ".PK..\n.....\n.....\n.....\n..kp.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\nd 2 d 3\nb 4 b 3\n" )) ), "good alternation" );
    }

    @Test
    void twoWhiteInSequence() {
        String startBoard = ".PK..\n.....\n.....\n.....\n..kp.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nd 2 d 3\nb 5 b 4\n" )) ), "two white in sequence" );
    }

    @Test
    void twoBlackInSequence() {
        String startBoard = ".PK..\n.....\n.....\n.....\n..kp.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\nb 4 b 3\n" )) ), "two black in sequence" );
    }

    @Test
    void blackStarts() {
        String startBoard = ".PK..\n.....\n.....\n.....\n..kp.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "b 5 b 4\nd 1 d 2\nb 4 b 3\n" )) ), "black starts" );
    }

    @Test
    void moveBeforeLoad() {
        FollowChess game = new FollowChess();

        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\nd 2 d 3\nb 4 b 3\n" )) ), "no board loaded" );
    }

    @Test
    void twoCallsWhiteWhiteStart() {
        String startBoard = ".PK..\n.....\n.....\n.....\n..kp.\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\n" )) ), "first movement call" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 2 d 3\nb 4 b 3\n" )) ), "second movement call" );
    }

    @Test
    void twoCallsWhiteBlackStart() {
        String startBoard = ".PK..\n.....\n.....\n.....\n..kp.\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\n" )) ), "first movement call" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "b 5 b 4\nd 2 d 3\nb 4 b 3\n" )) ), "second movement call" );
    }

    @Test
    void twoCallsWhiteWhiteStartEndWrong() {
        String startBoard = ".PK..\n.....\n.....\n.....\n..kp.\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\nd 2 d 3\n" )) ), "first movement call" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "d 3 d 4\nb 4 b 3\n" )) ), "second movement call" );
    }

    @Test
    void twoCallsWhiteBlackStartEndWrong() {
        String startBoard = ".PK..\n.....\n.....\n.....\n..kp.\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\n" )) ), "first movement call" );
        assertFalse( game.applyMoveSequence( new BufferedReader( new StringReader( "b 4 b 3\n" )) ), "second movement call" );
    }

    @Test
    void differentBoardSizes() {
        String narrowBoard = "...\n...\n...\n...\n...\n...\n...\n...\n...\n...\n...\n...\n...\n...\n...\n...\nk.K\n";
        String shortBoard = "....................\n....................\n.................k.K\n";
        FollowChess game = new FollowChess();

        // Pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( narrowBoard )) ), "load narrow board" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader(
                "a 1 a 2\nc 1 c 2\n"
                        + "a 2 a 3\nc 2 c 3\n"
                        + "a 3 a 4\nc 3 c 4\n"
                        + "a 4 a 5\nc 4 c 5\n"
                        + "a 5 a 6\nc 5 c 6\n"
                        + "a 6 a 7\nc 6 c 7\n"
                        + "a 7 a 8\nc 7 c 8\n"
                        + "a 8 a 9\nc 8 c 9\n"
                        + "a 9 a 10\nc 9 c 10\n"
                        + "a 10 a 11\nc 10 c 11\n"
                        + "a 11 a 12\nc 11 c 12\n"
        )) ), "first movement call" );

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( shortBoard )) ), "load short board" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader(
                "r 1 r 2\nt 1 t 2\n"
                        + "r 2 r 3\nt 2 t 3\n"
        )) ), "second movement call" );
    }

}