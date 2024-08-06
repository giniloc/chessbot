package Utils;

import Pieces.Piece;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {

    private Piece[][] board;
    private int rows;
    private int cols;
    private Rectangle[][] tiles;
    private Piece selectedPiece;
    private GridPane gridPane;

    public Board(int rows, int cols, GridPane gridPane) {
        this.rows = rows;
        this.cols = cols;
        this.gridPane = gridPane;
        board = new Piece[rows][cols];
        tiles = new Rectangle[rows][cols];
    }

    public void highlightTile(int row, int col, Color color) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            tiles[row][col].setFill(color);
        }
    }

    public void resetTileColors() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if ((row + col) % 2 == 0) {
                    tiles[row][col].setFill(Color.WHITE);
                } else {
                    tiles[row][col].setFill(Color.GRAY);
                }
            }
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
        if (piece != null) {
            piece.setRow(row);
            piece.setCol(col);
        }
    }

    public void removePiece(int row, int col) {
        Piece piece = board[row][col];
        if (piece != null) {
            gridPane.getChildren().remove(piece);
        }
        board[row][col] = null;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setTile(Rectangle rect, int row, int col) {
        tiles[row][col] = rect;

        // Voeg event handler toe aan de tegel
        rect.setOnMouseClicked(event -> {
            handleTileClick(row, col);
        });
    }

    public void setSelectedPiece(Piece piece) {
        this.selectedPiece = piece;
    }

    private void handleTileClick(int row, int col) {
        // Als een stuk is geselecteerd en een geldige beweging is, verplaats het stuk
        if (selectedPiece != null && selectedPiece.isValidMove(new Coordinate(row, col))) {
            // Verplaats het stuk
            selectedPiece.move(new Coordinate(row, col));

            // Reset de geselecteerde stuk
            selectedPiece = null;

            // Reset tegelkleuren
            resetTileColors();
        }
    }

    public void addPiece(Piece piece) {
        gridPane.add(piece, piece.getCol(), piece.getRow());
    }
}
