package part1.lesson10.task01.game;

import part1.lesson10.task01.model.Coord;

public class LifeUnit {
    private boolean isAlive;
    WindowField windowField;
    Coord coord;

    public LifeUnit(WindowField windowField, Coord coord) {
        this.windowField = windowField;
        this.coord = coord;
        this.isAlive = false;
        this.windowField.setColor(coord.getRow(), coord.getCol(), WindowField.WHITE);
    }

    public void setAlive(boolean flag) {
        this.isAlive = flag;
        if (flag) {
            windowField.setColor(coord.getRow(), coord.getCol(), WindowField.BLACK);
        } else {
            windowField.setColor(coord.getRow(), coord.getCol(), WindowField.WHITE);
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Coord getCoord() {
        return coord;
    }
}
