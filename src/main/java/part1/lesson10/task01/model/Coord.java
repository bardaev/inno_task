package part1.lesson10.task01.model;

import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName("row")
    private int row;
    @SerializedName("col")
    private int col;

    public Coord() {}

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
