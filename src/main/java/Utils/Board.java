package Utils;

import Pieces.Piece;

public class Board {

    private Piece[][] board;
    private int rows;
    private int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new Piece[rows][cols];
    }

    public void placePiece(Piece piece, int row, int col) {
        board[row][col] = piece;
    }

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = board[fromRow][fromCol];
        board[fromRow][fromCol] = null;
        board[toRow][toCol] = piece;
    }
}
