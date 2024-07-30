package Pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Piece extends ImageView {

    protected int row;
    protected int col;
    protected boolean isWhite;

    public Piece(int row, int col, boolean isWhite, String imageName) {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
        // Laad de afbeelding en stel deze in op de ImageView
        Image image = new Image(getClass().getResourceAsStream("/" + imageName));
        this.setImage(image);
    }

    public abstract boolean isValidMove(int newRow, int newCol, Piece[][] board);

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
