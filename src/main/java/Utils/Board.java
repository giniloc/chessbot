package Utils;

import Pieces.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {

    private Piece[][] board;
    private int rows;
    private int cols;
    private Rectangle[][] tiles;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new Piece[rows][cols];
        tiles = new Rectangle[rows][cols];
    }

    public void highlightTile(int row, int col, Color color) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            tiles[row][col].setFill(color);
        }
    }

    public Piece getPiece(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return board[row][col];
        }
        return null;
    }

    public void placePiece(Piece piece, int row, int col) {
        board[row][col] = piece;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setTile(Rectangle rect, int row, int col) {
        tiles[row][col] = rect;
    }
}
