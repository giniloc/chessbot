package Pieces;

import java.util.*;

import Utils.Board;
import Utils.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    }


    public abstract boolean isValidMove(Coordinate newCoords);

    //Return: alle legal coords waar piece naar kan moven in vorm van int list met 2 values (row, column)
    public abstract ArrayList<Coordinate> getValidMoves();

    //Idee: Dit gewoon algemeen implementen. Checken of een nieuwe move out of bounds is en of ge uzelf schaak zet als ge de move doet
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
}
