package joeflowplayschess.engine;

import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class EngineTestUtils {

    public static String n = "\r\n";
    public static String boardStart = "R N B Q K B N R" + n +
                                      "P P P P P P P P" + n +
                                      "0 0 0 0 0 0 0 0" + n +
                                      "0 0 0 0 0 0 0 0" + n +
                                      "0 0 0 0 0 0 0 0" + n +
                                      "0 0 0 0 0 0 0 0" + n +
                                      "p p p p p p p p" + n +
                                      "r n b q k b n r";


    public static int boardStringLengthLinux = 127;

    public static List<Character> pieceMapping = Arrays.asList(new Character[]{'0', 'p', 'n', 'b', 'r', 'q', 'k', 'P', 'N', 'B', 'R', 'Q', 'K'});

    public static int[] toGameBoard(String boardString) {
        int[] gameBoard = new int[64];

        if (boardString.length() != (boardStringLengthLinux + (SystemUtils.IS_OS_WINDOWS ? 8 : 0))){
            throw new IllegalArgumentException("Board String Malformed!");
        }

        char[] boardArray = boardStart.toCharArray();
        int[] boardSquares = new int[64];
        int i = 0;
        for (char c : boardArray) {
            if (Character.isLetterOrDigit(c)) {
                boardSquares[i%8 + (56 - 8 * (i / 8))] = pieceMapping.indexOf(c);
                i++;
            }
        }

        return boardSquares;
    }

    public static String loadBoardString(String boardFileName) throws IOException {
        Path p = Paths.get("test_resources","test_boards", boardFileName);
        return new String(Files.readAllBytes(p));
    }

    public static long bitBoardToLong(long row7, long row6, long row5, long row4,
                                      long row3, long row2, long row1, long row0){
        return  row7 << 56 |
                row6 << 48 |
                row5 << 40 |
                row4 << 32 |
                row3 << 24 |
                row2 << 16 |
                row1 << 8 |
                row0;
    }

}
