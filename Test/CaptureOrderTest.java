import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaptureOrderTest {
    @Test
    void badPlayerNumbers() {
        FollowChess game = new FollowChess();

        assertNull( game.captureOrder( -1 ), "Lower boundary player" );
        assertNull( game.captureOrder( 2 ), "Upper boundary player" );
    }

    @Test
    void noCaptures() {
        String startBoard = ".QK..\n.....\n.....\n.....\n..kq.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\nd 2 d 3\nb 4 b 3\n" )) ), "good alternation" );
        List<Character> order = game.captureOrder( 0 );
        assertNotNull( order, "list null" );
        assertEquals( 0, order.size(), "no captures white" );
        order = game.captureOrder( 1 );
        assertNotNull( order, "list null" );
        assertEquals( 0, order.size(), "no captures black" );
    }

    @Test
    void oneCapture() {
        String startBoard = ".QK..\n.p...\n...R.\n.....\n..kq.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\nd 2 d 3\nb 4 b 3\n" )) ), "good alternation" );
        List<Character> order = game.captureOrder( 0 );
        assertNotNull( order, "list null" );
        assertEquals( 1, order.size(), "one capture white" );
        assertEquals( 'R', order.get(0), "first captured white" );
        order = game.captureOrder( 1 );
        assertNotNull( order, "list null" );
        assertEquals( 1, order.size(), "one capture black" );
        assertEquals( 'p', order.get(0), "first captured black" );
    }

    @Test
    void distinctCaptures() {
        String startBoard = ".QK..\n.q...\n.n.R.\n...B.\n..kq.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\nd 2 d 3\nb 4 b 3\n" )) ), "good alternation" );
        List<Character> order = game.captureOrder( 0 );
        assertNotNull( order, "list null" );
        assertEquals( 2, order.size(), "two captures white" );
        assertEquals( 'B', order.get(0), "first capture white" );
        assertEquals( 'R', order.get(1), "second capture white" );
        order = game.captureOrder( 1 );
        assertNotNull( order, "list null" );
        assertEquals( 2, order.size(), "two captures black" );
        assertEquals( 'q', order.get(0), "first capture black" );
        assertEquals( 'n', order.get(1), "second capture black" );
    }

    @Test
    void similarCaptures() {
        String startBoard = ".QK..\n.n...\n.n.B.\n...B.\n..kq.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\nd 2 d 3\nb 4 b 3\n" )) ), "good alternation" );
        List<Character> order = game.captureOrder( 0 );
        assertNotNull( order, "list null" );
        assertEquals( 2, order.size(), "two captures white" );
        assertEquals( 'B', order.get(0), "first capture white" );
        assertEquals( 'B', order.get(1), "second capture white");
        order = game.captureOrder( 1 );
        assertNotNull( order, "list null" );
        assertEquals( 2, order.size(), "two captures black" );
        assertEquals( 'n', order.get(0), "first capture black" );
        assertEquals( 'n', order.get(1), "second capture black" );
    }

    @Test
    void mixedCaptures() {
        String startBoard = ".Q...\n.p.Q.\n.k.K.\n.p.Q.\n...q.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\nd 2 d 3\nb 4 b 3\nd 3 d 4\nb 3 b 2\n" )) ), "good alternation" );
        List<Character> order = game.captureOrder( 0 );
        assertNotNull( order, "list null" );
        assertEquals( 3, order.size(), "two captures white" );
        assertEquals( 'Q', order.get(0), "first capture white" );
        assertEquals( 'K', order.get(1), "second capture white" );
        assertEquals( 'Q', order.get(2), "third capture white" );
        order = game.captureOrder( 1 );
        assertNotNull( order, "list null" );
        assertEquals( 3, order.size(), "two captures black" );
        assertEquals( 'p', order.get(0), "first capture black" );
        assertEquals( 'k', order.get(1), "second capture black" );
        assertEquals( 'p', order.get(2), "third capture black" );
    }

    @Test
    void noBoardLoaded() {
        FollowChess game = new FollowChess();

        List<Character> order = game.captureOrder( 0 );
        assertNotNull( order, "list null" );
        assertEquals( 0, order.size(), "no board" );
    }

    @Test
    void noMovesDone() {
        String startBoard = ".QK..\n.....\n.....\n.....\n..kq.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        List<Character> order = game.captureOrder( 0 );
        assertNotNull( order, "list null" );
        assertEquals( 0, order.size(), "no captures white" );
        order = game.captureOrder( 1 );
        assertNotNull( order, "list null" );
        assertEquals( 0, order.size(), "no captures black" );
    }

    @Test
    void twoMoveCalls() {
        String startBoard = ".QK..\n.q...\n.n.R.\n...B.\n..kq.\n";
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "empty board stream" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 1 d 2\nb 5 b 4\n" )) ), "move set 1" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "d 2 d 3\nb 4 b 3\n" )) ), "move set 2" );
        List<Character> order = game.captureOrder( 0 );
        assertNotNull( order, "list null" );
        assertEquals( 2, order.size(), "two captures white" );
        assertEquals( 'B', order.get(0), "first capture white" );
        assertEquals( 'R', order.get(1), "second capture white" );
        order = game.captureOrder( 1 );
        assertNotNull( order, "list null" );
        assertEquals( 2, order.size(), "two captures black" );
        assertEquals( 'q', order.get(0), "first capture black" );
        assertEquals( 'n', order.get(1), "second capture black" );
    }

}