package joeflowplayschess.engine;

public class Utils {


    /**
     * Returns true if the bestMove is a castle. Does not check legality of bestMove.
     * @param parsedMove    the bestMove to inspect
     * @param colour        the colour making the bestMove
     * @return              true if the bestMove is a castle
     */
    public static boolean isCastleMove(int[] parsedMove, int colour) {
        return parsedMove[0] == Constants.kings[colour] &&
                parsedMove[2] == Constants.initKingPos[colour] &&
                (parsedMove[3] == Constants.queenSideCastleDestinationSquare[colour] || parsedMove[3] == Constants.queenSideCastleDestinationSquare[colour]);
    }



    /**
     * Returns the square index given a 2-element int array representing the row and column indices
     */
    public static int getIndex(int[] Position){
        int row = Position[0];
        int column = Position[1];
        return 8*row + column;
    }


    /**
     * Splits an encoded integer representing a piece bestMove into a 5 element array, according to the
     * defined bestMove encoding scheme:
     *
     * bestMove (MSB --> LSB):
     * pieceMoving (4) | capturedPiece(4) | fromSq(8) | toSq(8) | flags(8)
     *
     *  Move Flags:
     *
     *  bits 1-4: promoted piece type (Knight, Rook, Bishop, Queen)
     *  bit 5: promotion flag
     *  bit 6: en-passant capture flag
     *  bit 7: Queen Side Capture
     *  bit 8: King Side Capture
     */
    public static int[] parseMove(int move){
        int piece =          move >>> 28;
        int capturedPiece = (move << 4) >>> 28;
        int fromSq =        (move << 8) >>> 24;
        int toSq =          (move << 16) >>> 24;
        byte moveFlags =    (byte) move;

        return new int[]{piece, capturedPiece, fromSq, toSq, moveFlags};
    }

    public static boolean pieceIsWhite(int pieceNum){
        return pieceNum <= Constants.wKing;
    }

    public static int pieceNumToColour(int pieceNum){
        return (pieceNum - 1) / 6;
    }

    public static void clearMoveScoresFromMoveArray(int[][] moves){
        for(int[] move : moves){
            move[1] = 0;
        }
    }
}
