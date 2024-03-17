import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

class LoadBoardTest {

    @Test
    void nullStream(){
        FollowChess game = new FollowChess();

        assertFalse( game.loadBoard( null ), "null board stream" );
    }

    @Test
    void emptyStream(){
        FollowChess game = new FollowChess();

        assertFalse( game.loadBoard( new BufferedReader( new StringReader( "" )) ), "empty board stream" );
    }

    @Test
    void min1x1board(){
        FollowChess game = new FollowChess();

        assertFalse( game.loadBoard( new BufferedReader( new StringReader( "k\n" )) ),
                "1x1 board" );
    }

    @Test
    void min1x2board(){
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "k\nK\n" )) ),
                "1x2 board" );
    }

    @Test
    void min2x1board(){
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK\n" )) ),
                "2x1 board" );
    }

    @Test
    void missingOneKing() {
        FollowChess game1 = new FollowChess();
        FollowChess game2 = new FollowChess();

        assertFalse( game1.loadBoard( new BufferedReader( new StringReader( "k.\n..\n" )) ),
                "missing black king" );
        assertFalse( game2.loadBoard( new BufferedReader( new StringReader( ".K\n..\n" )) ),
                "missing white king" );
    }

    @Test
    void tooManyKings(){
        FollowChess game1 = new FollowChess();
        FollowChess game2 = new FollowChess();

        assertFalse( game1.loadBoard( new BufferedReader( new StringReader( "kK\n.k\n" )) ),
                "too many white kings" );
        assertFalse( game2.loadBoard( new BufferedReader( new StringReader( "kK\nK.\n" )) ),
                "too many black kings" );
    }

    @Test
    void kingsOnly(){
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "....\n.K..\n..k.\n....\n" )) ),
                "kings only" );
    }

    @Test
    void allPieceTypesOneCopy(){
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "........\n.RNBQKP.\n.rnbqkp.\n........\n" )) ),
                "all piece times, one copy" );
    }

    @Test
    void invalidPieces(){
        FollowChess game = new FollowChess();

        assertFalse( game.loadBoard( new BufferedReader( new StringReader( "....\n.KX.\n.yk.\n....\n" )) ),
                "bad pieces" );
    }

    @Test
    void allPieceTypesMulipleCopies(){
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "..PPpp....\n...RNBQKP.\n.RRNNBBQQ.\n...rnbqkp.\n.qqbbnnrr.\n..........\n" )) ),
                "all piece times, many copies" );
    }

    @Test
    void regularChessBoard(){
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "RNBQKBNR\nPPPPPPPP\n........\n........\n........\n........\npppppppp\nrnbqkbnr\n" )) ),
                "regular chess board" );
    }

    @Test
    void piecesAtEdges(){
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "RNBQK\nP...P\np...p\nrnbqk\n" )) ),
                "all pieces on the edge" );
    }

    @Test
    void leadingBlankLines(){
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "\n\nK...\n....\n...k\n" )) ),
                "leading blank lines" );
    }

    @Test
    void trailingBlankLints(){
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "K...\n....\n...k\n\n\n" )) ),
                "trailing blank lines" );
    }

    @Test
    void middleBlankLines(){
        FollowChess game = new FollowChess();

        // Just looking for us not to crash.
        game.loadBoard( new BufferedReader( new StringReader( "K...\n\n....\n\n...k\n" )) );
    }

    @Test
    void secondLineShort(){
        FollowChess game = new FollowChess();

        assertFalse( game.loadBoard( new BufferedReader( new StringReader( "K...\n...\n...k\n" )) ),
                "second line short, so not rectangle" );
    }

    @Test
    void secondLineLong(){
        FollowChess game = new FollowChess();

        assertFalse( game.loadBoard( new BufferedReader( new StringReader( "K...\n.....\n...k\n" )) ),
                "second line long, so not reectangle" );
    }

    @Test
    void callTwice(){
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "K...\n....\n...k\n" )) ),
                "first call" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...K\n....\nk...\n" )) ),
                "second call" );
    }

}

