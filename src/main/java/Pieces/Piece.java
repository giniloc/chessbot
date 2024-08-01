package Pieces;

import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Piece extends ImageView {

    protected int row;
    protected int col;
    protected boolean isWhite;
    //TODO: van board klasse maken in plaats van 2d array
    protected Piece[][] board;

    public Piece(int row, int col, boolean isWhite, String imageName, Piece[][] board) {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
        this.board = board;
        // Laad de afbeelding en stel deze in op de ImageView
        Image image = new Image(getClass().getResourceAsStream("/" + imageName));
        this.setImage(image);
    }


    //TODO: board als internal value zetten
    public abstract boolean isValidMove(int newRow, int newCol);

    //Return: alle legal coords waar piece naar kan moven in vorm van int list met 2 values (row, column)
    //TODO: int[] vervangen door coords klasse
    public abstract ArrayList<int[]> getValidMoves();

    public void move(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
