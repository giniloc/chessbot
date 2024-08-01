package Pieces;

public class King extends Piece{
    public King(int row, int col, boolean isWhite) {
        super(row, col, isWhite, isWhite ? "whiteKing.png" : "blackKing.png");

    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        return true;
    }
}


