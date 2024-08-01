package Pieces;

public class Rook extends Piece{
    public Rook(int row, int col, boolean isWhite) {
        super(row, col, isWhite, isWhite ? "whiteRook.png" : "blackRook.png");

    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        return true;
    }
}
