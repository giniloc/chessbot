package Pieces;

public class Knight extends Piece {

    public Knight(int row, int col, boolean isWhite) {
        super(row, col, isWhite, isWhite ? "whiteKnight.png" : "blackKnight.png");

    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        return true;
    }
}
