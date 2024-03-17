import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InCheckTest {
    @Test
    void badPlayerNumbers() {
        FollowChess game = new FollowChess();

        assertNull( game.captureOrder( -1 ), "Lower boundary player" );
        assertNull( game.captureOrder( 2 ), "Upper boundary player" );
    }

    @Test
    void singleKingOnBoard() {
        String startBoard = ".k.K.\n.....\n.....\n.....\n.....\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertFalse( game.inCheck( 0 ), "white king" );
        assertFalse( game.inCheck( 1 ), "black king" );
    }

    @Test
    void kingCapturedAlready() {
        String startBoard = ".kK..\n.....\n.....\n.....\n.....\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "b 5 c 5\n" )) ), "capture the king" );
        assertFalse( game.inCheck( 0 ), "white king" );
        // Outcome is undefined in the requirements.  Just make sure that we don't crash.
        game.inCheck( 1 );
    }

    @Test
    void checkInOneStep() {
        FollowChess game = new FollowChess();

        // pawn can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P...\nk....\n.....\nK....\n.p...\n" )) ), "load board" );
        assertTrue( game.inCheck( 0 ), "pawn white king" );
        assertTrue( game.inCheck( 1 ), "pawn black king" );

        // rook can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "R....\nk....\n.....\nK....\nr....\n" )) ), "load board" );
        assertTrue( game.inCheck( 0 ), "rook white king" );
        assertTrue( game.inCheck( 1 ), "rook black king" );

        // knight can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "..N..\nk....\n.....\nK....\n..n..\n" )) ), "load board" );
        assertTrue( game.inCheck( 0 ), "knight white king" );
        assertTrue( game.inCheck( 1 ), "knight black king" );

        // bishop can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".B...\nk....\n.b...\nK....\n.....\n" )) ), "load board" );
        assertTrue( game.inCheck( 0 ), "bishop white king" );
        assertTrue( game.inCheck( 1 ), "bishop black king" );

        // queen can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".....\nk....\n.Q...\nKq...\n.....\n" )) ), "load board" );
        assertTrue( game.inCheck( 0 ), "queen white king" );
        assertTrue( game.inCheck( 1 ), "queen black king" );

    }

    @Test
    void checkInMoreSteps() {
        FollowChess game = new FollowChess();

        // rook can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "r....\n.k..R\n.....\nK....\n.....\n" )) ), "load board" );
        assertTrue( game.inCheck( 0 ), "rook white king" );
        assertTrue( game.inCheck( 1 ), "rook black king" );

        // bishop can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "k...K\n.....\n.....\n.....\nb...B\n" )) ), "load board" );
        assertTrue( game.inCheck( 0 ), "bishop white king" );
        assertTrue( game.inCheck( 1 ), "bishop black king" );

        // queen can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".k.Q.\n.K...\n.....\n.....\n.q...\n" )) ), "load board" );
        assertTrue( game.inCheck( 0 ), "queen white king" );
        assertTrue( game.inCheck( 1 ), "queen black king" );

    }

    @Test
    void checkBlockedMoreSteps() {
        FollowChess game = new FollowChess();

        // rook can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "r....\npkp.R\n.....\nK....\n.....\n" )) ), "load board" );
        assertFalse( game.inCheck( 0 ), "rook white king" );
        assertFalse( game.inCheck( 1 ), "rook black king" );

        // bishop can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "k...K\n.PPP.\n.....\n.....\nb...B\n" )) ), "load board" );
        assertFalse( game.inCheck( 0 ), "bishop white king" );
        assertFalse( game.inCheck( 1 ), "bishop black king" );

        // queen can capture
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".Qpk.\n.K...\n.....\n.p...\n.q...\n" )) ), "load board" );
        assertFalse( game.inCheck( 0 ), "queen white king" );
        assertFalse( game.inCheck( 1 ), "queen black king" );

    }

    @Test
    void checkFromMultiple() {
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "..K..\n.p.b.\n.....\n..Q..\nR.k..\n" )) ), "load board" );
        assertTrue( game.inCheck( 0 ), "rook white king" );
        assertTrue( game.inCheck( 1 ), "rook black king" );
    }

    @Test
    void callBeforeLoad() {
        FollowChess game = new FollowChess();

        // Outcome undefined in the assignment, so just make sure we aren't crashing.
        game.inCheck( 0 );
        game.inCheck( 1 );
    }

    @Test
    void moveIntoCheck() {
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "..K..\n.....\n.p...\n...R.\n..k..\n" )) ), "load board" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "b 3 b 4\nd 2 c 2\n" )) ), "move into check" );
        assertTrue( game.inCheck( 0 ), "rook white king" );
        assertTrue( game.inCheck( 1 ), "rook black king" );
    }

}