package Pieces;

import Utils.Board;
import Utils.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class Piece extends ImageView {

    protected int row;
    protected int col;
    protected Coordinate coords;
    protected boolean isWhite;
    protected Board board;

    public Piece(int row, int col, boolean isWhite, String imageName, Board board) {
        this.row = row;
        this.col = col;
        this.coords = new Coordinate(row, col);
        this.isWhite = isWhite;
        this.board = board;
        // Laad de afbeelding en stel deze in op de ImageView
        Image image = new Image(getClass().getResourceAsStream("/" + imageName));
        this.setImage(image);

        // Voeg mouse event listener toe
        this.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent event) {
        // Controleer of het de beurt van dit stuk is
        if (board.isWhiteTurn() != isWhite) {
            return; // Als het niet de beurt van dit stuk is, doe niets
        }

        // Reset tegelkleuren op het bord voordat de nieuwe geldige zetten worden getoond
        board.resetTileColors();

        // Werk het geselecteerde stuk bij op het bord
        board.setSelectedPiece(this);

        // Markeer geldige zetten voor het geselecteerde stuk
        highlightValidMoves();
    }

    protected void highlightValidMoves() {
        ArrayList<Coordinate> validMoves = getValidMoves();
        for (Coordinate move : validMoves) {
            int row = move.getRow();
            int col = move.getCol();
            board.highlightTile(row, col, Color.RED);
        }
    }

    public abstract boolean isValidMove(Coordinate newCoords);

    public abstract ArrayList<Coordinate> getValidMoves();

    public boolean isSuicideMove(Coordinate newCoords){
        if(isWhite){
            ArrayList<Piece> blackPieces = board.getBlackPieces();
            // Coverage bepalen van de zwarte stukken voor de huidige zet

            // Coverage bepalen van de zwarte stukken met de nieuwe coördinaten
            // PROBLEEM: als je iets pakt dan is dat hier waarschijnlijk nog niet weg
        } else{
            ArrayList<Piece> whitePieces = board.getWhitePieces();
        }
        return false;
    }

    public void move(Coordinate coords) {
        // Update de interne coördinaten
        this.row = coords.getRow();
        this.col = coords.getCol();
        this.coords = coords;

        // Plaats het stuk op de nieuwe locatie op het bord
        board.placePiece(this, row, col);

        // Update de positie van de afbeelding in de UI
        this.setLayoutX(col * 100);
        this.setLayoutY(row * 100);
        board.addPiece(this);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
