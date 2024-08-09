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
            // Controleer of het stuk vijandig is en binnen de geldige zetten valt
            if (board.getSelectedPiece() != null) {
                Piece selectedPiece = board.getSelectedPiece();
                if (selectedPiece.isValidMove(coords)) {
                    // Voer de capturing move uit door het doorsturen van de klik naar de onderliggende tegel
                    board.handleTileClick(row, col);
                }
            }
            return; // Als het niet de beurt van dit stuk is, en het stuk is niet vijandig, doe niets
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

//GINILOC ZIJN PROBEERSEL OP EEN IsSuicideMove, WERKT NOG NIET
//public boolean isSuicideMove(Coordinate newCoords) {
//    // Sla de huidige positie en het stuk op de nieuwe coördinaten op
//    int currentRow = this.row;
//    int currentCol = this.col;
//    Piece targetPiece = board.getPiece(newCoords.getRow(), newCoords.getCol());
//
//    // Simuleer de zet door het stuk te verplaatsen
//    board.placePiece(null, currentRow, currentCol);  // Verwijder het stuk van zijn huidige locatie
//    board.placePiece(this, newCoords.getRow(), newCoords.getCol());  // Plaats het stuk op de nieuwe locatie
//
//    // Controleer of de koning na de zet schaak staat
//    boolean isInCheck = isKingInCheck(isWhite);
//
//    // Herstel de oorspronkelijke situatie
//    board.placePiece(this, currentRow, currentCol);  // Plaats het stuk terug op zijn oorspronkelijke locatie
//    board.placePiece(targetPiece, newCoords.getRow(), newCoords.getCol());  // Plaats het doelstuk (indien aanwezig) terug
//
//    return isInCheck;
//}

    private boolean isKingInCheck(boolean isWhite) {
        King king = board.getKing(isWhite);
        Coordinate kingCoords = king.getCoords();

        ArrayList<Piece> opponentPieces = isWhite ? board.getBlackPieces() : board.getWhitePieces();

        // Controleer of een van de tegenstander stukken de koning kan slaan
        for (Piece piece : opponentPieces) {
            ArrayList<Coordinate> validMoves = piece.getValidMoves();
            for (Coordinate move : validMoves) {
                if (move.equals(kingCoords)) {
                    return true;  // De koning staat schaak
                }
            }
        }
        return false;  // De koning staat niet schaak
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
