import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class PieceCanMoveTest {
    @Test
    void nullStream() {
        FollowChess game = new FollowChess();

        assertFalse( game.pieceCanMove( null ), "null board location" );
    }

    @Test
    void emptyStream() {
        FollowChess game = new FollowChess();

        assertFalse( game.pieceCanMove( "" ), "empty board location" );
    }

    @Test
    void canMoveFromOffBoard() {
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n.....\n.....\n.....\n.....\n" )) ), "load board" );
        assertFalse( game.pieceCanMove( "b 6" ), "above the board" );
        assertFalse( game.pieceCanMove( "c 0" ), "below the board" );
        assertFalse( game.pieceCanMove( "f 3" ), "left of the board" );
    }

    @Test
    void canMoveAtEdge() {
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".kKQ.\n.....\nq...q\n.....\n..Q..\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 5" ), "top edge" );
        assertTrue( game.pieceCanMove( "c 1" ), "bottom edge" );
        assertTrue( game.pieceCanMove( "a 3" ), "left edge" );
        assertTrue( game.pieceCanMove( "e 3" ), "right edge" );
    }

    @Test
    void noPieceAtSquare() {
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".kKQ.\n.....\nq...q\n.....\n..Q..\n" )) ), "load board" );

        // Outcome undefined in specification, so just don't crash
        game.pieceCanMove( "c 3" );
    }

    @Test
    void beforeBoardLoaded() {
        FollowChess game = new FollowChess();

        // Outcome undefined in specification, so just don't crash
        game.pieceCanMove( "a 1" );
    }

    @Test
    void checkAfterMoves() {
        FollowChess game = new FollowChess();

        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "..K..\n.....\n.p...\n...R.\n..k..\n" )) ), "load board" );
        assertTrue( game.applyMoveSequence( new BufferedReader( new StringReader( "b 3 b 4\nd 2 c 2\n" )) ), "move into check" );
        assertTrue( game.pieceCanMove( "c 5" ), "black king" );
        assertTrue( game.pieceCanMove( "c 1" ), "white king" );
        assertTrue( game.pieceCanMove( "b 4" ), "white pawn" );
        assertTrue( game.pieceCanMove( "c 2" ), "black rook" );

    }

    @Test
    void backwardPawn() {
        String startBoard = "kK...\n...P.\n...P.\n...p.\n...p.\n.....\n";
        FollowChess game = new FollowChess();

        // rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( startBoard )) ), "load board" );
        assertFalse( game.pieceCanMove( "d 2" ), "white pawn" );
        assertFalse( game.pieceCanMove( "d 5" ), "black pawn" );
    }

    @Test
    void oneStepMoveAvailable() {
        FollowChess game = new FollowChess();

        // pawn
        // covered in backwardPawn case already

        // rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n.....\n.PRP.\n..P..\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "rook can go up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n..P..\n.PRP.\n.....\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "rook can go down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n..P..\n.PR..\n..P..\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "rook can go right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n..P..\n..RP.\n..P..\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "rook can go left" );

        // knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P...\nP...P\n.kNK.\nP...P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...P.\nP...P\n.kNK.\nP...P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go up and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP...P\n.kNK.\nP...P\n.P...\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP...P\n.kNK.\nP...P\n...P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\n....P\n.kNK.\nP...P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go left and up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP...P\n.kNK.\n....P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go left and down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP....\n.kNK.\nP...P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go right and up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP...P\n.kNK.\nP....\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go right and down" );

        // bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n...P.\n..B..\n.P.P.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "bishop can go up and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.P...\n..B..\n.P.P.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "bishop can go up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.P.P.\n..B..\n...P.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "bishop can go down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.P.P.\n..B..\n.P...\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "bishop can go down and right" );

        // queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.P.P.\n.PQP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PP..\n.PQP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.PQ..\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.PQP.\n.PP..\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.PQP.\n.P.P.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.PQP.\n..PP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n..QP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n..PP.\n.PQP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go up and left" );

        // king
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.P.P.\n.PKP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PP..\n.PKP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.PK..\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.PKP.\n.PP..\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.PKP.\n.P.P.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.PKP.\n..PP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n..KP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n..PP.\n.PKP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go up and left" );

    }

    @Test
    void allOneStepMovesBlocked() {
        FollowChess game = new FollowChess();

        // pawn
        // covered in backwardPawn case already

        // rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n..P..\n.PRP.\n..P..\n.....\n" )) ), "load board" );
        assertFalse( game.pieceCanMove( "c 3" ), "rook" );

        // knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP...P\n.kNK.\nP...P\n.P.P.\n" )) ), "load board" );
        assertFalse( game.pieceCanMove( "c 3" ), "knight" );

        // bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.P.P.\n..B..\n.P.P.\n.....\n" )) ), "load board" );
        assertFalse( game.pieceCanMove( "c 3" ), "bishop" );

        // queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.PQP.\n.PPP.\n.....\n" )) ), "load board" );
        assertFalse( game.pieceCanMove( "c 3" ), "queen" );

        // king
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.PKP.\n.PPP.\n.....\n" )) ), "load board" );
        assertFalse( game.pieceCanMove( "c 3" ), "king" );

    }

    @Test
    void oneStepCaptureAvailable() {
        FollowChess game = new FollowChess();

        // pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n.Pp..\n..p..\n.....\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "pawn" );

        // rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n..q..\n.PRP.\n..P..\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "rook can go up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n..P..\n.PRP.\n..n..\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "rook can go down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n..P..\n.PRb.\n..P..\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "rook can go right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n..P..\n.rRP.\n..P..\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "rook can go left" );

        // knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.p.\nP...P\n.kNK.\nP...P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".r.P.\nP...P\n.kNK.\nP...P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go up and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP...P\n.kNK.\nP...P\n.P.n.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP...P\n.kNK.\nP...P\n.b.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nq...P\n.kNK.\nP...P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go left and up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP...P\n.kNK.\np...P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go left and down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP...p\n.kNK.\nP...P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go right and up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.P.\nP...P\n.kNK.\nP...p\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight can go right and down" );

        // bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.r.P.\n..B..\n.P.P.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "bishop can go up and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.P.n.\n..B..\n.P.P.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "bishop can go up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.P.P.\n..B..\n.b.P.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "bishop can go down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.P.P.\n..B..\n.P.q.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "bishop can go down and right" );

        // queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PpP.\n.PQP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPr.\n.PQP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.PQn.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.PQP.\n.PPb.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.PQP.\n.PqP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.PQP.\n.pPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.pQP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.pPP.\n.PQP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go up and left" );

        // king
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PpP.\n.PKP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go up" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPr.\n.PKP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go up and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.PKn.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.PKb.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go down and right" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.PKP.\n.Pqp.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go down" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.PKP.\n.pPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can down and left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.pKP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.pPP.\n.PKP.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "king can go up and left" );

    }

    @Test
    void oneStepMultipleCapturesAvailable() {
        FollowChess game = new FollowChess();

        // pawn
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n.PpP.\n..p..\n.....\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "pawn" );

        // rook
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "kK...\n..p..\n.PRP.\n..p..\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "rook" );

        // knight
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".P.p.\np...P\n.kNK.\nP...P\n.P.P.\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "knight" );

        // bishop
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.p.p.\n..B..\n.P.P.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "bishop can go up either way" );

        // queen
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( ".K.k.\n.PPP.\n.pQp.\n.PPP.\n.....\n" )) ), "load board" );
        assertTrue( game.pieceCanMove( "c 3" ), "queen can go right and left" );

        // king
        assertTrue( game.pieceCanMove( "c 3" ), "king can go right or left" );
        assertTrue( game.loadBoard( new BufferedReader( new StringReader( "...k.\n.PPP.\n.pKp.\n.PPP.\n.....\n" )) ), "load board" );

    }
}