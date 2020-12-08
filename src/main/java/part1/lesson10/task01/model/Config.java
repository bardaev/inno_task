package part1.lesson10.task01.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Config {

    @SerializedName("field")
    private Field field;
    @SerializedName("steps")
    private int steps;
    @SerializedName("coords")
    private List<Coord> coords;

    public Config() {}

    public Config(Field field, int steps, List<Coord> coords) {
        this.field = field;
        this.steps = steps;
        this.coords = coords;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public List<Coord> getCoords() {
        return coords;
    }

    public void setCoords(List<Coord> coords) {
        this.coords = coords;
    }

    @Override
    public String toString() {
        return "Config{" +
                "field=" + field +
                ", steps=" + steps +
                ", coords=" + coords +
                '}';
    }
}
