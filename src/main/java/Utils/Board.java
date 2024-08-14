package Utils;


import Pieces.King;
import Pieces.Piece;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Board {

    private Piece[][] board;
    private int rows;
    private int cols;
    private Rectangle[][] tiles;
    private Piece selectedPiece;
    private GridPane gridPane;
    private boolean whiteTurn = true; // Wit begint
    private boolean vsBot;

    public Board(int rows, int cols, GridPane gridPane, boolean vsBot) {
        this.rows = rows;
        this.cols = cols;
        this.gridPane = gridPane;
        board = new Piece[rows][cols];
        tiles = new Rectangle[rows][cols];
        this.vsBot = vsBot;
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
        if ((piece.isWhite() && whiteTurn) || (!piece.isWhite() && !whiteTurn)) {
            this.selectedPiece = piece;
        }
    }

    public void handleTileClick(int row, int col) {
        // Als een stuk is geselecteerd en een geldige beweging is, verplaats het stuk
        if (selectedPiece != null && selectedPiece.isValidMove(new Coordinate(row, col))) {
            // Verplaats het stuk
            if (getTileColor(row, col) == Color.RED) {
                // Verwijder het stuk op de doeltegel, indien aanwezig (capturing move)
                Piece targetPiece = getPiece(row, col);
                if (targetPiece != null && targetPiece.isWhite() != selectedPiece.isWhite()) {
                    removePiece(row, col);
                }
                // Verplaats het geselecteerde stuk
                removePiece(selectedPiece.getRow(), selectedPiece.getCol());
                selectedPiece.move(new Coordinate(row, col));

                // Wissel van beurt
                whiteTurn = !whiteTurn;

                // Controleer of de koning schaak staat
                boolean isWhiteInCheck = isKingInCheck(true);
                boolean isBlackInCheck = isKingInCheck(false);

                if (isWhiteInCheck) {
                    System.out.println("Witte koning staat schaak!");
                }
                if (isBlackInCheck) {
                    System.out.println("Zwarte koning staat schaak!");
                }

            }

            // Reset de geselecteerde stuk
            selectedPiece = null;

            // Reset tegelkleuren
            resetTileColors();
        }
    }


    public void addPiece(Piece piece) {
        gridPane.add(piece, piece.getCol(), piece.getRow());
    }



    public void changePiece(Piece newPiece) {
        this.placePiece(newPiece, newPiece.getRow(), newPiece.getCol());
        newPiece.fitWidthProperty().bind(gridPane.widthProperty().divide(8));
        newPiece.fitHeightProperty().bind(gridPane.heightProperty().divide(8));
        gridPane.add(newPiece, newPiece.getCol(), newPiece.getRow());
    }
    public boolean isKingInCheck(boolean isWhite) {
        // Zoek de positie van de koning
        Coordinate kingPosition = findKingPosition(isWhite);

        // Controleer of een vijandelijk stuk de koning kan slaan
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Piece piece = getPiece(row, col);
                if (piece != null && piece.isWhite() != isWhite) {
                    if (piece.isValidMove(kingPosition)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Coordinate findKingPosition(boolean isWhite) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Piece piece = getPiece(row, col);
                if (piece instanceof King && piece.isWhite() == isWhite) {
                    return new Coordinate(row, col);
                }
            }
        }
        return null; // Dit zou eigenlijk nooit moeten gebeuren
    }


    public Paint getTileColor(int row, int col) {
        return tiles[row][col].getFill();
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public boolean isVsBot() {
        return vsBot;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }
}
