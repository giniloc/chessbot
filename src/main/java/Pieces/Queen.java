package Pieces;

public class Queen extends Piece {
    public Queen(int row, int col, boolean isWhite) {
        super(row, col, isWhite, isWhite ? "whiteQueen.png" : "blackQueen.png");
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        return true;
    }
}