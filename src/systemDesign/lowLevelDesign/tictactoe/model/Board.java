package systemDesign.lowLevelDesign.tictactoe.model;

import systemDesign.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board (int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece (int row, int column, PlayingPiece piece) {
        if (row < 0 || row >= size || column < 0 || column >= size) {
            return false;
        }
        if (board[row][column] == null) {
            board[row][column] = piece;
            return true;
        }
        return false;
    }

    public List<Pair<Integer, Integer>> getFreeCells() {
        List<Pair<Integer, Integer>> freeCells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    freeCells.add(new Pair<>(i, j));
                }
            }
        }
        return freeCells;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    System.out.print("     ");
                } else {
                    System.out.print(board[i][j].pieceType + "    ");
                }
                if (j < size - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < size - 1) {
                System.out.println("------------------------");
            }
        }
    }
}
