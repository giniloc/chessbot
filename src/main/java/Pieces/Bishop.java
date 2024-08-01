package Pieces;

public class Bishop extends Piece{
    public Bishop(int row, int col, boolean isWhite) {
        super(row, col, isWhite, isWhite ? "whiteBishop.png" : "blackBishop.png");

    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        return true;
    }
}
