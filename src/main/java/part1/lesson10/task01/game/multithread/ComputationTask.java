package part1.lesson10.task01.game.multithread;

import part1.lesson10.task01.game.LifeField;
import part1.lesson10.task01.model.Coord;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;

public class ComputationTask extends RecursiveAction {

    private LifeField lifeField;
    private CopyOnWriteArrayList<Coord> list;
    private int row;

    public ComputationTask(LifeField lifeField, CopyOnWriteArrayList<Coord> list, int row) {
        this.lifeField = lifeField;
        this.list = list;
        this.row = row;
    }

    @Override
    protected void compute() {
        if (row != lifeField.getRowSize()) {
            scanField(row);
            invokeAll(new ComputationTask(lifeField, list, ++row));
        }
    }

    private void scanField(int row) {
        for (int j = 0; j < lifeField.getColSize(); j++) {
            checkLifeField(new Coord(row, j));
        }
    }

    private void checkLifeField(Coord coord) {
        if (lifeField.isAlive(coord)) {
            if (isNeedKillOrNeedBorn(coord, false)) list.add(coord);
        } else {
            if (isNeedKillOrNeedBorn(coord, true)) list.add(coord);
        }
    }

    private boolean isNeedKillOrNeedBorn(Coord coord, boolean flag) {
        int alive = 0;

        int row = coord.getRow();
        int col = coord.getCol();
        int[][] coords = {
                {row, col - 1},
                {row - 1, col - 1},
                {row - 1, col},
                {row - 1, col + 1},
                {row, col + 1},
                {row + 1, col + 1},
                {row + 1, col},
                {row + 1, col - 1}};

        for (int i = 0; i < coords.length; i++) {
            if (coords[i][0] < 0 || coords[i][0] >= lifeField.getRowSize() ||
                    coords[i][1] < 0 || coords[i][1] >= lifeField.getColSize()) continue;
            if (lifeField.isAlive(new Coord(coords[i][0], coords[i][1]))) alive++;
        }

        if (flag) {
            return alive == 3;
        } else {
            return !(alive == 2 || alive == 3);
        }
    }
}
