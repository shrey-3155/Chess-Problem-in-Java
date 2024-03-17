import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class PrintBoardTest {
    @Test
    void nullStream() {
        FollowChess game = new FollowChess();

        assertFalse(game.printBoard(null), "null output stream");
    }

    private void printLoadedBoard(String board){
        FollowChess game = new FollowChess();

        assertTrue(game.loadBoard(new BufferedReader(new StringReader(board))), "load board");

        StringWriter outString = new StringWriter();
        assertTrue(game.printBoard(new PrintWriter(outString)), "print board");
        assertEquals(board, outString.toString(), "check board");
    }

    @Test
    void min1x2board(){
        printLoadedBoard("k\nK\n");
    }

    @Test
    void min2x1board(){
        printLoadedBoard("kK\n");
    }

    @Test
    void piecesAtEdge(){
        printLoadedBoard("RNBQK\nP...P\np...p\nrnbqk\n");
    }

    @Test
    void piecesInCentre(){
        printLoadedBoard(".....\n.RQK.\n.PNB.\n.pnb.\n.rqk.\n.....\n");
    }

    @Test
    void onlyKings(){
        printLoadedBoard("....\n.K..\n..k.\n....\n");
    }

    @Test
    void bigBoard(){
        printLoadedBoard("..........\n..K.RRQQ..\n..NNBB....\n.PPPPPPPP.\n..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + "..........\n"
                + ".pppppppp.\n"
                + "..nnbb....\n"
                + "..k.rrqq..\n"
                + "..........\n"
        );
    }

    @Test
    void printBeforeLoadingBoard() {
        FollowChess game = new FollowChess();
        StringWriter outString = new StringWriter();

        assertTrue(game.printBoard(new PrintWriter(outString)),"print board");

        assertEquals("", outString.toString(), "check board");
    }

}

