package part1.lesson10.task01.game;

import part1.lesson10.task01.model.Coord;
import part1.lesson10.task01.model.Field;

import java.util.ArrayList;
import java.util.List;

public class LifeField {
    private final LifeUnit[][] lifeField;
    private Field field;

    public LifeField(Field field, List<Coord> coords, WindowField windowField) {
        this.field = field;
        this.lifeField = new LifeUnit[field.getRows()][field.getCols()];

        for (int i = 0; i < this.field.getRows(); i++) {
            for (int j = 0; j < this.field.getCols(); j++) {
                this.lifeField[i][j] = new LifeUnit(windowField, new Coord(i, j));
            }
        }

        coords.forEach((c) -> setLifeUnit(c, true));
    }

    public void setLifeUnit(Coord coord, boolean isAlive) {
        this.lifeField[coord.getRow()][coord.getCol()].setAlive(isAlive);
    }

    public boolean isAlive(Coord coord) {
        return lifeField[coord.getRow()][coord.getCol()].isAlive();
    }

    public int getRowSize() {
        return this.field.getRows();
    }

    public int getColSize() {
        return this.field.getCols();
    }

    public List<Coord> getResultCoord() {
        ArrayList<Coord> result = new ArrayList<>();

        for (int i = 0; i < lifeField.length; i++) {
            for (int j = 0; j < lifeField[i].length; j++) {
                if (lifeField[i][j].isAlive()) result.add(lifeField[i][j].getCoord());
            }
        }
        return result;
    }
}
