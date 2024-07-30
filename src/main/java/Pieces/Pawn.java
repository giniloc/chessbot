package Pieces;

public class Pawn extends Piece {
    public Pawn(int row, int col, boolean isWhite) {
        super(row, col, isWhite, isWhite ? "whitePawn.png" : "blackPawn.png");
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        if (isWhite) {
            // Witte pion: beweegt naar boven
            if (newRow == row - 1 && newCol == col && board[newRow][newCol] == null) {
                return true;
            }
        } else {
            // Zwarte pion: beweegt naar beneden
            if (newRow == row + 1 && newCol == col && board[newRow][newCol] == null) {
                return true;
            }
        }
        return false;
    }
}
