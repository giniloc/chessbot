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

    public abstract boolean isSuicideMove(Coordinate newCoords);

    public void move(Coordinate coords) {
        this.row = coords.getRow();
        this.col = coords.getCol();
        this.coords = coords;
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
