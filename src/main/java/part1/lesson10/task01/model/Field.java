package part1.lesson10.task01.model;

import com.google.gson.annotations.SerializedName;

public class Field {

    @SerializedName("rows")
    private int rows;
    @SerializedName("cols")
    private int cols;

    public Field() {}

    public Field(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    @Override
    public String toString() {
        return "Field{" +
                "rows=" + rows +
                ", cols=" + cols +
                '}';
    }
}
