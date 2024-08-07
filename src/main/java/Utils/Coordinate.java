package Utils;

public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    @Override
    public String toString() {
        return "Coordinate{" + "row=" + row + ", col=" + col + '}';
    }

}
